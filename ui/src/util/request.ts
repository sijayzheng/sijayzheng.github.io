import axios from 'axios'
import { useUserStore } from '@/store/user'
import { getToken } from '@/util/auth'
import { blobValidate, tansParams } from '@/util/ruoyi'
import cache from '@/plugins/cache'
import { errorCode } from '@/util/errorCode'
import FileSaver from 'file-saver'
import { encryptBase64, encryptWithAes, generateAesKey } from '@/util/crypto'
import { encrypt } from '@/util/jsencrypt'
import { ElMessage } from 'element-plus'
import { modalUtil } from '@/util/modalUtil.ts'

const encryptHeader = 'encrypt-key'
//是否显示重新登录
export const isReLogin = { show: false }
export const globalHeaders = () => {
  return {
    Authorization: 'Bearer ' + getToken()
  }
}

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
//创建 axios 实例
const service = axios.create({
  baseURL: '/api',
  timeout: 10 * 60 * 1000
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    modalUtil.loading('系统正在处理，请稍候……')
    const isToken = config.headers?.isToken === false
    // 是否需要防止数据重复提交
    const isRepeatSubmit = config.headers?.repeatSubmit === false
    // 是否需要加密
    const isEncrypt = config.headers?.isEncrypt === 'true'

    if (getToken() && !isToken) {
      config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    // get请求映射params参数
    if (config.method === 'get' && config.params) {
      let url = config.url + '?' + tansParams(config.params)
      url = url.slice(0, -1)
      config.params = {}
      config.url = url
    }

    if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
      const requestObj = {
        url: config.url,
        data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
        time: new Date().getTime()
      }
      const sessionObj = cache.session.getJSON('sessionObj')
      if (sessionObj === undefined || sessionObj === null || sessionObj === '') {
        cache.session.setJSON('sessionObj', requestObj)
      } else {
        const s_url = sessionObj.url // 请求地址
        const s_data = sessionObj.data // 请求数据
        const s_time = sessionObj.time // 请求时间
        const interval = 500 // 间隔时间(ms)，小于此时间视为重复提交
        if (s_data === requestObj.data && requestObj.time - s_time < interval && s_url === requestObj.url) {
          const message = '数据正在处理，请勿重复提交'
          console.warn(`[${s_url}]: ` + message)
          return Promise.reject(new Error(message))
        } else {
          cache.session.setJSON('sessionObj', requestObj)
        }
      }
    }
    // 当开启参数加密
    if (isEncrypt && (config.method === 'post' || config.method === 'put')) {
      // 生成一个 AES 密钥
      const aesKey = generateAesKey()
      config.headers[encryptHeader] = encrypt(encryptBase64(aesKey))
      config.data = typeof config.data === 'object' ? encryptWithAes(JSON.stringify(config.data), aesKey) : encryptWithAes(config.data, aesKey)
    }
    // FormData数据去请求头Content-Type
    if (config.data instanceof FormData) {
      delete config.headers['Content-Type']
    }
    return config
  },
  (error) => {
    modalUtil.closeLoading()
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (res) => {
    // if (true) {
    //   // 加密后的 AES 秘钥
    //   const keyStr = res.headers[encryptHeader]
    //   // 加密
    //   if (keyStr != null && keyStr !== '') {
    //     const data = res.data
    //     // 请求体 AES 解密
    //     const base64Str = decrypt(keyStr)
    //     // base64 解码 得到请求头的 AES 秘钥
    //     const aesKey = decryptBase64(base64Str.toString())
    //     // aesKey 解码 data
    //     const decryptData = decryptWithAes(data, aesKey)
    //     // 将结果 (得到的是 JSON 字符串) 转为 JSON
    //     res.data = JSON.parse(decryptData)
    //   }
    // }
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200
    // 获取错误信息
    const msg = errorCode[code] || res.data.msg || errorCode['default']
    // 二进制数据则直接返回
    modalUtil.closeLoading()
    if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
      return res.data
    }
    if (code === 401) {
      // prettier-ignore
      if (!isReLogin.show) {
        isReLogin.show = true
        ElMessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          isReLogin.show = false
          useUserStore().logout().then(() => {
            location.href = '/'
          })
        }).catch(() => {
          isReLogin.show = false
        })
      }
      return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
    } else if (code === 500) {
      ElMessage({
        message: msg,
        type: 'error'
      })
      return Promise.reject(new Error(msg))
    } else if (code === 700) {
      ElMessage({
        message: msg,
        type: 'warning'
      })
      return Promise.reject(new Error(msg))
    } else if (code !== 200) {
      ElNotification.error({ title: msg })
      return Promise.reject('error')
    } else {
      return Promise.resolve(res.data)
    }
  },
  (error) => {
    modalUtil.closeLoading()
    let { message } = error
    if (message === 'Network Error') {
      message = '后端接口连接异常'
    } else if (message.includes('timeout')) {
      message = '系统接口请求超时'
    } else if (message.includes('Request failed with status code')) {
      message = '系统接口' + message.substring(message.length - 3) + '异常'
    }
    ElMessage({
      message: message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

// 通用下载方法
export const downloadFile = async (url: string, fileName: string, params: object) => {
  modalUtil.loading('正在下载数据，请稍候')
  // prettier-ignore
  return service.post(url, params, {
    transformRequest: [
      (params) => {
        return tansParams(params)
      }
    ],
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    responseType: 'blob'
  }).then(async (resp) => {
    const isBlob = blobValidate(resp)
    if (isBlob) {
      const blob = new Blob([resp.data])
      FileSaver.saveAs(blob, fileName)
    } else {
      const resText = await resp.data.text()
      const rspObj = JSON.parse(resText)
      const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
      ElMessage.error(errMsg)
    }
    modalUtil.closeLoading()
  }).catch((r) => {
    console.error(r)
    ElMessage.error('下载文件出现错误，请联系管理员！')
    modalUtil.closeLoading()
  })
}

// 导出 axios 实例
export default service
