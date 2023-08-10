import axios from 'axios'
import {getToken, tansParams} from '@/utils'
import {ElLoading, ElMessage, ElNotification} from 'element-plus'

let downloadLoadingInstance

// 创建 axios 实例
const request = axios.create({
    baseURL: '/api',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }
})

// 请求拦截器
request.interceptors.request.use(
    (config) => {
        const token = getToken()
        if (token) {
            config.headers['Authorization'] = 'Bearer ' + token // 让每个请求携带自定义token 请根据实际情况自行修改
        }
        // get请求映射params参数
        if (config.method === 'get' && config.params) {
            let url = config.url + '?' + tansParams(config.params)
            url = url.slice(0, -1)
            config.params = {}
            config.url = url
        }
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
request.interceptors.response.use(
    (response) => {
        if (response.status === 200) {
            const code = response.data.code
            // 获取错误信息
            const msg = response.data.msg
            // 二进制数据则直接返回
            if (response.request.responseType === 'blob' || response.request.responseType === 'arraybuffer') {
                return response
            }
            if (code === '7777') {
                ElMessage({
                    message: msg,
                    type: 'error'
                })
                return Promise.reject(response.data)
            } else if (code === '9999') {
                ElMessage({
                    message: msg,
                    type: 'error'
                })
                return Promise.reject(msg)
            } else if (code !== '0000') {
                ElNotification.error({title: msg})
                return Promise.reject(response.data)
            } else {
                return Promise.resolve(response.data)
            }
        } else {
            ElMessage({
                message: response?.data?.msg,
                type: 'error',
                duration: 5 * 1000
            })
            return Promise.reject(response.data)
        }
    },
    (error) => {
        let {message} = error
        ElMessage({
            message: message,
            type: 'error',
            duration: 5 * 1000
        })
        return Promise.reject(error)
    }
)

// 通用下载方法
export function download(url, params, fileName) {
    downloadLoadingInstance = ElLoading.service({
        text: '正在下载数据，请稍候',
        background: 'rgba(0, 0, 0, 0.7)'
    })
    // prettier-ignore
    return request.post(url, params, {
        transformRequest: [
            (params) => {
                return tansParams(params)
            }
        ],
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        responseType: 'blob'
    }).then(async (resp) => {
        // const isLogin = blobValidate(resp)
        // if (isLogin) {
        //     const blob = new Blob([resp])
        //     FileSaver.saveAs(blob, fileName)
        // } else {
        //     const resText = await resp.data.text()
        //     const rspObj = JSON.parse(resText)
        //     const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
        //     ElMessage.error(errMsg)
        // }
        downloadLoadingInstance.close()
    }).catch((r) => {
        console.error(r)
        ElMessage.error('下载文件出现错误，请联系管理员！')
        downloadLoadingInstance.close()
    })
}


export default {
    login(data) {
        return request({
            url: '/login',
            method: 'post',
            data: data
        })
    },
    logout() {
        return request({
            url: '/logout',
            method: 'post'
        })
    }
}




