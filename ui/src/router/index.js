import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout/index.vue'
import {getToken, upperFirstLetter} from '@/utils'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
 roles: ['admin','editor']    control the page roles (you can set multiple roles)
 title: 'title'               the name show in sidebar and breadcrumb (recommend set)
 icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
 breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
 activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
 }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
const singleRoute = {
    index: '首页',
    icons: '图标'
}
const multiRoute = {
    system: {
        title: '系统管理',
        children: {
            user: '用户管理',
            role: '角色管理',
            menu: '菜单管理',
            dept: '部门管理',
            post: '岗位管理',
            dict: '字典管理',
            config: '参数设置',
            notice: '通知公告',
            operlog: '操作日志',
            logininfor: '登录日志',
            oss: '文件管理',
            client: '客户端管理',
        }
    },
    monitor: {
        title: '监控',
        children: {
            cache: '缓存监控',
            mysql: 'MySQL监控',
        }
    },
    tool: {
        title: '工具',
        children: {
            nginx: 'nginx配置',
            test: 'test',
        }
    },
}
const external = {
    Sijay: 'https://sijayzheng.github.io',
}

export const routes = [
    ...(Object.keys(singleRoute).map(key => {
        return {
            path: '',
            component: Layout,
            redirect: '/' + key,
            children: [
                {
                    path: '/' + key,
                    name: upperFirstLetter(key),
                    component: () => import(`@/views/${key}.vue`),
                    meta: {
                        title: singleRoute[key],
                    },
                },
            ],
        }
    })),
    ...(Object.keys(multiRoute).map(key => {
        const children = multiRoute[key].children
        return {
            name: upperFirstLetter(key),
            path: '/' + key,
            component: Layout,
            meta: {
                title: multiRoute[key].title,
                noCache: false,
                link: key,
            },
            redirect: 'noRedirect',
            children: [
                ...(Object.keys(children).map(k => {
                    return {
                        name: upperFirstLetter(k),
                        path: k,
                        component: () => import(`@/views/${key}/${k}.vue`),// 'system/user/index',
                        meta: {
                            title: children[k],
                            noCache: false,
                            link: k,
                        },
                    }
                }))

            ],
        }
    })),
    {
        path: '/login',
        component: () => import('@/views/login.vue'),
        hidden: true,
    },
    {
        path: '/404',
        name: '404',
        component: () => import('@/views/404.vue'),
        hidden: true,
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/404',
        hidden: true,
    }
]
export const externalLinks = Object.keys(external).map(key => {
    return {
        name: key,
        path: external[key],
        meta: {
            title: key,
            noCache: false,
            link: external[key],
        },
    }
})

const router = createRouter({
    history: createWebHistory(),
    routes: routes, // 刷新时，滚动条位置还原
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return {top: 0}
        }
    }
})
export default router

NProgress.configure({showSpinner: false})
const whiteList = ['/login', '/icons', '/404']

router.beforeEach(async (to, from, next) => {
    NProgress.start()
    if (getToken()) {
        if (to.path === '/login') {
            next({path: '/'})
            NProgress.done()
        } else {
            next()
        }
    } else {
        if (whiteList.includes(to.path)) {
            next()
        } else {
            next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
            NProgress.done()
        }
    }
})

router.afterEach(() => {
    NProgress.done()
})
