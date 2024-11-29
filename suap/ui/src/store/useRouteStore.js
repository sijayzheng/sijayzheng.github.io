import Layout from '@/layout/index.vue'

const modules = import.meta.glob('../views/**/*.vue')
export const useRouteStore = defineStore('route', () => {
  const routes = ref([])

  const setRoutes = (newRoutes) => {
    routes.value = constantRoutes.concat(newRoutes)
  }

  const generateRoutes = async () => {
    const res = await commonApi.getRoutes()
    const dynamicRoutes = convert(res.data)
    dynamicRoutes.forEach((route) => router.addRoute(route))
    setRoutes(dynamicRoutes)
    return new Promise((resolve) => resolve(dynamicRoutes))
  }

  const convert = (dynamicRoutes) => {
    return dynamicRoutes.filter((route) => {
      if (route.component) {
        if (route.component === 'Layout') {
          route.component = Layout
        } else {
          route.component = loadView(route.component)
        }
      }
      if (route.children != null && route.children && route.children.length) {
        route.children = convert(route.children, route)
      } else {
        delete route.children
        delete route.redirect
      }
      return true
    })
  }

  return {
    routes,
    setRoutes,
    generateRoutes,
  }
})

const loadView = (view) => {
  for (const path in modules) {
    if (path.includes(`views/${view}.vue`)) {
      return modules[path]
    }
  }
}

export default useRouteStore
