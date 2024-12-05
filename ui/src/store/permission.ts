import { defineStore } from 'pinia'
import router, { constantRoutes, dynamicRoutes } from '@/router'
import store from '@/store'
import auth from '@/plugins/auth'

import Layout from '@/layout/layout.vue'
import ParentView from '@/components/parentView.vue'
import InnerLink from '@/layout/innerLink.vue'

import { createCustomNameComponent } from '@/util/createCustomNameComponent'
import { ElNotification } from 'element-plus'

// 匹配views里面所有的.vue文件
const modules = import.meta.glob('./../../views/**/*.vue')
export const usePermissionStore = defineStore('permission', () => {
  const routes = ref([])
  const addRoutes = ref([])
  const defaultRoutes = ref([])
  const topbarRouters = ref([])
  const sidebarRouters = ref([])

  const getRoutes = () => {
    return routes.value
  }
  const getSidebarRoutes = () => {
    return sidebarRouters.value
  }
  const getTopbarRoutes = () => {
    return topbarRouters.value
  }

  const setRoutes = (newRoutes) => {
    addRoutes.value = newRoutes
    routes.value = constantRoutes.concat(newRoutes)
  }
  const setDefaultRoutes = (routes) => {
    defaultRoutes.value = constantRoutes.concat(routes)
  }
  const setTopbarRoutes = (routes) => {
    topbarRouters.value = routes
  }
  const setSidebarRouters = (routes) => {
    sidebarRouters.value = routes
  }
  const generateRoutes = async () => {
    const res = { data: [] } // await getRouters()
    const { data } = res
    const sdata = JSON.parse(JSON.stringify(data))
    const rdata = JSON.parse(JSON.stringify(data))
    const defaultData = JSON.parse(JSON.stringify(data))
    const sidebarRoutes = filterAsyncRouter(sdata, undefined)
    const rewriteRoutes = filterAsyncRouter(rdata, undefined, true)
    const defaultRoutes = filterAsyncRouter(defaultData, undefined)
    console.log(sidebarRoutes)
    console.log(rewriteRoutes)
    console.log(defaultRoutes)
    const asyncRoutes = filterDynamicRoutes(dynamicRoutes)
    asyncRoutes.forEach((route) => {
      router.addRoute(route)
    })
    setRoutes(rewriteRoutes)
    setSidebarRouters(constantRoutes.concat(sidebarRoutes))
    setDefaultRoutes(sidebarRoutes)
    setTopbarRoutes(defaultRoutes)
    // 路由name重复检查
    duplicateRouteChecker(asyncRoutes, sidebarRoutes)
    return new Promise((resolve) => resolve(rewriteRoutes))
  }

  /**
   * 遍历后台传来的路由字符串，转换为组件对象
   * @param asyncRouterMap 后台传来的路由字符串
   * @param lastRouter 上一级路由
   * @param type 是否是重写路由
   */
  const filterAsyncRouter = (asyncRouterMap, lastRouter, type = false) => {
    return asyncRouterMap.filter((route) => {
      if (type && route.children) {
        route.children = filterChildren(route.children, undefined)
      }
      // Layout ParentView 组件特殊处理
      if (route.component?.toString() === 'Layout') {
        route.component = Layout
      } else if (route.component?.toString() === 'ParentView') {
        route.component = ParentView
      } else if (route.component?.toString() === 'InnerLink') {
        route.component = InnerLink
      } else {
        route.component = loadView(route.component, route.name)
      }
      if (route.children != null && route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, route, type)
      } else {
        delete route.children
        delete route.redirect
      }
      return true
    })
  }
  const filterChildren = (childrenMap, lastRouter) => {
    let children = []
    childrenMap.forEach((el) => {
      if (el.children && el.children.length) {
        if (el.component?.toString() === 'ParentView' && !lastRouter) {
          el.children.forEach((c) => {
            c.path = el.path + '/' + c.path
            if (c.children && c.children.length) {
              children = children.concat(filterChildren(c.children, c))
              return
            }
            children.push(c)
          })
          return
        }
      }
      if (lastRouter) {
        el.path = lastRouter.path + '/' + el.path
        if (el.children && el.children.length) {
          children = children.concat(filterChildren(el.children, el))
          return
        }
      }
      children = children.concat(el)
    })
    return children
  }
  return {
    routes,
    topbarRouters,
    sidebarRouters,
    defaultRoutes,

    getRoutes,
    getSidebarRoutes,
    getTopbarRoutes,

    setRoutes,
    generateRoutes,
    setSidebarRouters
  }
})

// 动态路由遍历，验证是否具备权限
export const filterDynamicRoutes = (routes) => {
  const res = []
  routes.forEach((route) => {
    if (route.permissions) {
      if (auth.hasPermiOr(route.permissions)) {
        res.push(route)
      }
    } else if (route.roles) {
      if (auth.hasRoleOr(route.roles)) {
        res.push(route)
      }
    }
  })
  return res
}

export const loadView = (view, name) => {
  let res
  for (const path in modules) {
    const dir = path.split('views/')[1].split('.vue')[0]
    if (dir === view) {
      res = createCustomNameComponent(modules[path], { name })
    }
  }
  return res
}

// 非setup
export const usePermissionStoreHook = () => {
  return usePermissionStore(store)
}

/**
 * 检查路由name是否重复
 * @param localRoutes 本地路由
 * @param routes 动态路由
 */
function duplicateRouteChecker(localRoutes, routes) {
  // 展平
  function flatRoutes(routes) {
    const res = []
    routes.forEach((route) => {
      if (route.children) {
        res.push(...flatRoutes(route.children))
      } else {
        res.push(route)
      }
    })
    return res
  }

  const allRoutes = flatRoutes([...localRoutes, ...routes])

  const nameList = []
  allRoutes.forEach((route) => {
    const name = route.name.toString()
    if (name && nameList.includes(name)) {
      const message = `路由名称: [${name}] 重复, 会造成 404`
      console.error(message)
      ElNotification({
        title: '路由名称重复',
        message,
        type: 'error'
      })
      return
    }
    nameList.push(route.name.toString())
  })
}

export default usePermissionStore
