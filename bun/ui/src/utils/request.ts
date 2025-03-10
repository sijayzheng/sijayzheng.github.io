import {getToken} from '@/utils/index'
import {axiosRequestAdapter} from '@alova/adapter-axios'
import {createAlova} from 'alova'
import VueHook from 'alova/vue'
import dayjs from 'dayjs'
import FileSaver from 'file-saver'
import qs from 'qs'

export function globalHeaders() {
  const token = getToken()
  return token ? {Authorization: `Bearer ${token}`, clientid: 'e5cd7e4891bf95d1d19206ce24a7b32e'} : {}
}

const ao = createAlova({
  statesHook: VueHook,
  baseURL: '/api',
  timeout: 3 * 60 * 1000,
  beforeRequest: (method) => {
    const token = getToken()
    if (token && !method.meta?.ignoreToken) {
      method.config.headers.Authorization = `Bearer ${token}`
      method.config.headers.clientid = 'e5cd7e4891bf95d1d19206ce24a7b32e'
    }
  },
  requestAdapter: axiosRequestAdapter({}),
  responded: {
    onSuccess: (response, method) => {
      const {data, headers} = response
      if (method.config.meta?.isDownload && headers['Content-Type'] !== 'application/json') {
        if (data.code === 401) {
          if (router.currentRoute.value.fullPath !== '/login') {
            useUserStore().logout().then(() => {
              router.replace({
                path: '/login',
                query: {
                  redirect: encodeURIComponent(router.currentRoute.value.fullPath || '/'),
                },
              })
            })
          }
          ElMessage.error('无效的会话，或者会话已过期，请重新登录。')
          return null
        } else if (data.code === 200) {
          ElMessage.success(data.message || '操作成功')
        } else if (data.code === 500) {
          ElMessage.error(data.message || '系统错误')
          return null
        } else {
          ElMessage.warning(data.message || '系统警告')
        }
        const blob = new Blob([data])
        FileSaver.saveAs(blob, decodeURIComponent(headers['download-filename']) || method.config.meta?.fileName || `文件名_${dayjs().unix()}`)
      } else {
        console.log(router.currentRoute.value.fullPath)
        if (data.code === 401) {
          if (router.currentRoute.value.fullPath !== '/login') {
            useUserStore().logout().then(() => {
              router.replace({
                path: '/login',
                query: {
                  redirect: encodeURIComponent(router.currentRoute.value.fullPath || '/'),
                },
              })
            })
          }
          ElMessage.error('无效的会话，或者会话已过期，请重新登录。')
          return null
        } else if (data.code === 200) {
          ElMessage.success(data.message || '操作成功')
        } else if (data.code === 500) {
          ElMessage.error(data.message || '系统错误')
        } else {
          ElMessage.warning(data.message || '系统警告')
        }
        return data
      }
    },
    onError: (err) => {
      console.error(err)
    },
  },
})

export function hola(method: 'get' | 'post', url: string, data?: any, config?: any) {
  switch (method) {
    case 'post':
      return ao.Post(url, data, {meta: config})
    case 'get':
      return ao.Get(`${url}?${qs.stringify(data)}`, {meta: config})
  }
}

export function holaDownload(url: string, data?: any, config?: any) {
  return ao.Post(url, data, {
    meta: {
      ...config,
      isDownload: true,
    },
  })
}
