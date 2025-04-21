import type { AxiosResponse } from 'axios'
import router from '@/router'
import FileSaver from 'file-saver'

export function globalHeaders() {
  const token = getToken()
  return token ? { Authorization: `Bearer ${token}` } : {}
}

const axiosConfig = {
  timeout: 3 * 60 * 1000, // 请求超时时间
  baseURL: '/',
}
if (import.meta.env.VITE_APP_DEV)
  axiosConfig.baseURL = '/api'
const request = axios.create(axiosConfig)

// 请求拦截器，在请求头中添加 token
request.interceptors.request.use(
  (config) => {
    const token = getToken()
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  },
)

// 响应拦截器，判断请求状态
request.interceptors.response.use(
  (response: AxiosResponse) => {
    const { data, headers } = response
    if (headers['content-type'] !== 'application/json') {
      return data
    }
    if (data.code === 401) {
      if (router.currentRoute.value.fullPath !== '/login') {
        modal.confirm('重新登录').then(() => {
          useUserStore().logout().then(() => {
            router.replace({
              path: '/login',
              query: {
                redirect: router.currentRoute.value.fullPath || '/',
              },
            })
          })
        })
      }
      return Promise.reject(new Error('无效的会话，或者会话已过期，请重新登录。'))
    } else if (data.code === 200) { /* empty */
    } else if (data.code === 500) {
      ElMessage.error(data.message || '系统错误')
      return Promise.reject(new Error(data.message || '系统错误'))
    } else {
      ElMessage.warning(data.message || '系统警告')
      return Promise.reject(new Error(data.message || '系统警告'))
    }
    return data
  },
  (error) => {
    ElMessage.error(error.response ? `请求失败，状态码: ${error.response.status}` : `请求失败: ${error.message}`)
    return Promise.reject(error)
  },
)

export function download(url: string, data: any, fileName: string) {
  const loading = ElLoading.service({ text: '正在下载数据，请稍候' })
  request.post(url, data, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, responseType: 'blob' })
    .then((data: any) => {
      FileSaver.saveAs(new Blob([data]), fileName)
      loading.close()
    })
    .catch((r: any) => {
      console.error(r)
      ElMessage.error('文件下载错误')
      loading.close()
    })
}

export function downloadTemplate(url: string, fileName: string) {
  const loading = ElLoading.service({ text: '正在下载数据，请稍候' })
  request.get(url, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, responseType: 'blob' })
    .then((data: any) => {
      FileSaver.saveAs(new Blob([data]), fileName)
      loading.close()
    })
    .catch((r: any) => {
      console.error(r)
      ElMessage.error('模板文件下载错误')
      loading.close()
    })
}

export default request
