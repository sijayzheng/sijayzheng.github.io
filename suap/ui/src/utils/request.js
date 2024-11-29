import axios from 'axios'
import {getToken} from '@/utils/common.js'

import FileSaver from 'file-saver'
import cache from '@/utils/cache.js'

let loadingInstance
// 是否显示重新登录
export const isRelogin = {show: false}

// 创建 axios 实例
const service = axios.create({
  baseURL: '/api',
  timeout: 60000,
})
const tansParams = (params) => {
  let result = ''
  for (const propName of Object.keys(params)) {
    const value = params[propName]
    const part = encodeURIComponent(propName) + '='
    if (value !== null && value !== '' && typeof value !== 'undefined') {
      if (typeof value === 'object') {
        for (const key of Object.keys(value)) {
          if (value[key] !== null && value[key] !== '' && typeof value[key] !== 'undefined') {
            const params = propName + '[' + key + ']'
            const subPart = encodeURIComponent(params) + '='
            result += subPart + encodeURIComponent(value[key]) + '&'
          }
        }
      } else {
        result += part + encodeURIComponent(value) + '&'
      }
    }
  }
  return result
}
// 请求拦截器
service.interceptors.request.use((config) => {
    loadingInstance = ElLoading.service({
      text: '等待系统处理……',
      background: 'rgba(0, 0, 0, 0.5)'
    })
    config.headers['tk'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    // 是否需要防止数据重复提交
    const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
    // 是否需要加密
    // const isEncrypt = (config.headers || {}).isEncrypt === 'true'
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
    // if (isEncrypt && (config.method === 'post' || config.method === 'put')) {
    //     // 生成一个 AES 密钥
    //     const aesKey = generateAesKey()
    //     config.headers['encrypt-key'] = encrypt(encryptBase64(aesKey))
    //     config.data = typeof config.data === 'object' ? encryptWithAes(JSON.stringify(config.data), aesKey) : encryptWithAes(config.data, aesKey)
    // }
    // FormData数据去请求头Content-Type
    if (config.data instanceof FormData) {
      delete config.headers['Content-Type']
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)
// 响应拦截器
service.interceptors.response.use((res) => {
    loadingInstance.close()
    // 二进制数据则直接返回
    if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
      return res
    }
    const data = res.data
    // 未设置状态码则默认成功状态
    const code = data.code || 200
    // 获取错误信息
    const msg = data.message || '系统错误'
    if (code === 401) {
      if (!isRelogin.show) {
        isRelogin.show = true
        ElMessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          isRelogin.show = false
          useUserStore().logout().then(() => {
            location.href = import.meta.env.VITE_APP_CONTEXT_PATH + 'index'
          })
        }).catch(() => {
          isRelogin.show = false
        })
      }
      return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
    } else if (code === 500) {
      ElMessage({
        message: msg,
        type: 'error'
      })
      return Promise.reject(new Error(msg))
    } else if (code === 777) {
      ElMessage({
        message: msg,
        type: 'warning'
      })
      return Promise.reject(new Error(msg))
    } else if (code !== 200) {
      ElNotification.error({title: msg})
      return Promise.reject('error')
    } else {
      return Promise.resolve(data)
    }
  },
  (error) => {
    loadingInstance.close()
    let {message} = error
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
export function download(url, data, fileName) {
  loadingInstance = ElLoading.service({
    text: '正在下载数据，请稍候',
    background: 'rgba(0, 0, 0, 0.7)'
  })
  service.post(url, data, {responseType: 'blob'}).then((res) => {
    if (res.status === 200) {
      if (res.headers.getContentType() !== 'application/json') {
        const blob = new Blob([res.data])
        FileSaver.saveAs(blob, decodeURIComponent(res.headers.filename || fileName))
      } else {
        ElMessage.error(JSON.parse(res.data.text()).message || '系统错误')
      }
    } else {
      ElMessage.error(res.data.message || '系统错误')
    }
    loadingInstance.close()
  }).catch((err) => {
    console.error(err)
    ElMessage.error('文件下载错误')
    loadingInstance.close()
  })
}

// 导出 axios 实例
export default service
