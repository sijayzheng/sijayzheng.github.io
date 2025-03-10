import type {RouteRecordRaw} from 'vue-router'
import {staticRoutes} from '@/router'

export const usePermissionStore = defineStore('permission', () => {
  const routes = ref<RouteRecordRaw[]>([])
  const generateRoutes = async (): Promise<RouteRecordRaw[]> => {
    routes.value = staticRoutes.map((item) => {
      // console.log(item)
      return item
    })
    return new Promise<RouteRecordRaw[]>(resolve => resolve(routes.value))
  }
  const setLayout = (route: RouteRecordRaw) => {
  }

  return {
    routes,
    generateRoutes,
  }
})
export default usePermissionStore
