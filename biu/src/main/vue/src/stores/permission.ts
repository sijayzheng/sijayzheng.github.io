import { staticRoutes } from '@/router'

export const usePermissionStore = defineStore('permission', () => {
  const routes = ref([])
  const generateRoutes = async () => {
    routes.value = staticRoutes.map((item) => {
      // console.log(item)
      return item
    })
    return new Promise(resolve => resolve(routes.value))
  }
  const setLayout = (route) => {
  }

  return {
    routes,
    generateRoutes,
  }
})
export default usePermissionStore
