import router, {constantRoutes} from '@/router/index.js'
// import {menuApi} from '@/api/router.js'
import Layout from '@/layout/index.vue'
import ParentView from '@/components/parentView.vue'
import InnerLink from '@/components/innerLink.vue'
import {defineStore} from "pinia";
// 匹配views里面所有的.vue文件
const modules = import.meta.glob('./../views/**/*.vue')


export const useRouteStore = defineStore('route', () => {
    const routes = ref([])

    const setRoutes = (newRoutes) => {
        routes.value = constantRoutes.concat(newRoutes)
        console.log(routes.value)
    }

    const generateRoutes = async () => {
        const res = {
            data: [
                {
                    name: 'Data',
                    path: '/data',
                    hidden: false,
                    redirect: 'noRedirect',
                    component: 'Layout',
                    alwaysShow: true,
                    meta: {
                        title: '数据管理',
                        icon: 'tool',
                        noCache: false,
                        link: null,
                    },
                    children: [
                        {
                            name: 'DataDict',
                            path: 'dataDict',
                            hidden: false,
                            component: 'data/dataDict',
                            meta: {
                                title: '数据字典',
                                icon: 'user',
                                noCache: false,
                                link: null,
                            },
                        },
                    ],
                },
                {
                    name: 'Tool',
                    path: '/tool',
                    hidden: false,
                    redirect: 'noRedirect',
                    component: 'Layout',
                    alwaysShow: true,
                    meta: {
                        title: '系统工具',
                        icon: 'tool',
                        noCache: false,
                        link: null,
                    },
                    children: [
                        {
                            name: 'Index',
                            path: 'gen',
                            hidden: false,
                            component: 'gen/index',
                            meta: {
                                title: '代码生成',
                                icon: 'user',
                                noCache: false,
                                link: null,
                            },
                        },
                    ],
                },
                {
                    name: 'Sys',
                    path: '/sys',
                    hidden: false,
                    redirect: 'noRedirect',
                    component: 'Layout',
                    alwaysShow: true,
                    meta: {
                        title: '系统管理',
                        icon: 'tool',
                        noCache: false,
                        link: null,
                    },
                    children: [
                        {
                            name: 'SysMenu',
                            path: 'sysMenu',
                            hidden: false,
                            component: 'sys/sysMenu',
                            meta: {
                                title: '菜单信息',
                                icon: 'user',
                                noCache: false,
                                link: null,
                            },
                        },
                    ],
                },
                {
                    name: 'Demo',
                    path: '/demo',
                    hidden: false,
                    redirect: 'noRedirect',
                    component: 'Layout',
                    alwaysShow: true,
                    meta: {
                        title: '示例',
                        icon: 'tool',
                        noCache: false,
                        link: null,
                    },
                    children: [
                        {
                            name: 'ElIndex',
                            path: 'elIndex',
                            hidden: false,
                            component: 'demo/el/elIndex',
                            meta: {
                                title: 'Element示例',
                                icon: 'user',
                                noCache: false,
                                link: null,
                            },
                        },
                    ],
                },
            ]
        }//await menuApi.getRouters()
        const rewriteRoutes = convert(res.data, undefined, true)
        const asyncRoutes = filterDynamicRoutes(dynamicRoutes)
        asyncRoutes.forEach((route) => {
            router.addRoute(route)
        })
        setRoutes(rewriteRoutes)
        return new Promise((resolve) => resolve(rewriteRoutes))
    }


    const convert = (asyncRouterMap, lastRouter, type = false) => {
        return asyncRouterMap.filter((route) => {
            if (type && route.children) {
                route.children = filterChildren(route.children, undefined)
            }
            if (route.component) {
                // Layout ParentView 组件特殊处理
                if (route.component === 'Layout') {
                    route.component = Layout
                } else if (route.component === 'ParentView') {
                    route.component = ParentView
                } else if (route.component === 'InnerLink') {
                    route.component = InnerLink
                } else {
                    route.component = loadView(route.component)
                }
            }
            if (route.children != null && route.children && route.children.length) {
                route.children = convert(route.children, route, type)
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
                if (el.component === 'ParentView' && !lastRouter) {
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
            }
            children = children.concat(el)
        })
        return children
    }
    return {
        routes,
        setRoutes,
        generateRoutes,
    }
})

// 动态路由遍历，验证是否具备权限
export const filterDynamicRoutes = (routes) => {
    const res = []
    routes.forEach((route) => {
        // if (route.permissions) {
        //     if (auth.hasPermiOr(route.permissions)) {
        res.push(route)
        // }
        // } else if (route.roles) {
        //     if (auth.hasRoleOr(route.roles)) {
        //         res.push(route)
        //     }
        // }
    })
    return res
}

export const loadView = (view) => {
    let res
    for (const path in modules) {
        const dir = path.split('views/')[1].split('.vue')[0]
        if (dir === view) {
            res = () => modules[path]()
        }
    }
    return res
}

// 非setup
export const routerStoreHook = () => {
    return useRouteStore(store)
}

export default useRouteStore
