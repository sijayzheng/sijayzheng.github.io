/**
 * Note: 路由配置项
 *
 * hidden: true                     // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true                 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect             // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * query: '{"id": 1, "name": "ry"}' // 访问路由的默认传递参数
 * roles: ['admin', 'common']       // 访问路由的角色权限
 * permissions: ['a:a:a', 'b:b:b']  // 访问路由的菜单权限
 * meta : {
 noCache: true                   // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
 title: 'title'                  // 设置该路由在侧边栏和面包屑中展示的名字
 icon: 'svg-name'                // 设置该路由的图标，对应路径src/assets/icons/svg
 breadcrumb: false               // 如果设置为false，则不会在breadcrumb面包屑中显示
 activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
 }
 */
import Layout from '@/layout/layout.vue'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/util/auth'
import useUserStore from '@/store/user'
import useSettingsStore from '@/store/settings'
import usePermissionStore from '@/store/permission'

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/components/redirect.vue')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/Login.vue'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/Register.vue'),
    hidden: true
  },
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/components/404.vue'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/components/401.vue'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: '/index',
    children: [
      {
        path: '/index',
        component: () => import('@/views/index.vue'),
        name: 'Index',
        meta: {
          title: '首页',
          icon: 'dashboard',
          affix: true
        }
      }
    ]
  }
  //     {
  //         path: '/user',
  //         component: Layout,
  //         hidden: true,
  //         redirect: 'noredirect',
  //         children: [
  //             {
  //                 path: 'profile',
  //                 component: () => import('@/views/system/user/profile/UserProfile.vue'),
  //                 name: 'Profile',
  //                 meta: {
  //                     title: '个人中心',
  //                     icon: 'user'
  //                 }
  //             }
  //         ]
  //     }
]

// 动态路由，基于用户权限动态去加载
export const dynamicRoutes = [
  //     {
  //         path: '/system/user-auth',
  //         component: Layout,
  //         hidden: true,
  //         permissions: ['system:user:edit'],
  //         children: [
  //             {
  //                 path: 'role/:userId(\\d+)',
  //                 component: () => import('@/views/system/user/authRole.vue'),
  //                 name: 'AuthRole',
  //                 meta: {
  //                     title: '分配角色',
  //                     activeMenu: '/system/user',
  //                     icon: ''
  //                 }
  //             }
  //         ]
  //     },
  //     {
  //         path: '/system/role-auth',
  //         component: Layout,
  //         hidden: true,
  //         permissions: ['system:role:edit'],
  //         children: [
  //             {
  //                 path: 'user/:roleId(\\d+)',
  //                 component: () => import('@/views/system/role/authUser.vue'),
  //                 name: 'AuthUser',
  //                 meta: {
  //                     title: '分配用户',
  //                     activeMenu: '/system/role',
  //                     icon: ''
  //                 }
  //             }
  //         ]
  //     },
  //     {
  //         path: '/system/dict-data',
  //         component: Layout,
  //         hidden: true,
  //         permissions: ['system:dict:list'],
  //         children: [
  //             {
  //                 path: 'index/:dictId(\\d+)',
  //                 component: () => import('@/views/system/dict/data.vue'),
  //                 name: 'Data',
  //                 meta: {
  //                     title: '字典数据',
  //                     activeMenu: '/system/dict',
  //                     icon: ''
  //                 }
  //             }
  //         ]
  //     },
  //     {
  //         path: '/system/oss-config',
  //         component: Layout,
  //         hidden: true,
  //         permissions: ['system:ossConfig:list'],
  //         children: [
  //             {
  //                 path: 'index',
  //                 component: () => import('@/views/system/oss/config.vue'),
  //                 name: 'OssConfig',
  //                 meta: {
  //                     title: '配置管理',
  //                     activeMenu: '/system/oss',
  //                     icon: ''
  //                 }
  //             }
  //         ]
  //     },
  //     {
  //         path: '/tool/gen-edit',
  //         component: Layout,
  //         hidden: true,
  //         permissions: ['tool:gen:edit'],
  //         children: [
  //             {
  //                 path: 'index/:tableId(\\d+)',
  //                 component: () => import('@/views/tool/gen/editTable.vue'),
  //                 name: 'GenEdit',
  //                 meta: {
  //                     title: '修改生成配置',
  //                     activeMenu: '/tool/gen',
  //                     icon: '',
  //                     noCache: true
  //                 }
  //             }
  //         ]
  //     }
]

/**
 * 创建路由
 */
const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes, // 刷新时，滚动条位置还原
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  }
})

export default router

const whiteList = ['/login', '/register', '/social-callback']

router.beforeEach(async (to, from, next) => {
  NProgress.start()
  if (getToken()) {
    to.meta.title && useSettingsStore().setTitle(to.meta.title)
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      if (useUserStore().roles.length === 0) {
        isReLogin.show = true
        // 判断当前用户是否已拉取完user_info信息
        const [err] = await ato(useUserStore().getInfo())
        if (err) {
          await useUserStore().logout()
          ElMessage.error(err)
          next({ path: '/' })
        } else {
          isReLogin.show = false
          const accessRoutes = await usePermissionStore().generateRoutes()
          // 根据roles权限生成可访问的路由表
          accessRoutes.forEach((route) => {
            if (!validateUtil.isHttp(route.path)) {
              router.addRoute(route) // 动态添加可访问路由表
            }
          })
          // @ts-ignore
          next({
            path: to.path,
            replace: true,
            params: to.params,
            query: to.query,
            hash: to.hash,
            name: to.name
          }) // hack方法 确保addRoutes已完成
        }
      } else {
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      const redirect = encodeURIComponent(to.fullPath || '/')
      next(`/login?redirect=${redirect}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
