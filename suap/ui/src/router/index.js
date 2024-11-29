import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout/index.vue'

export const constantRoutes = [{
  path: '/',
  component: Layout,
  redirect: '/index',
  name: 'Index',
  meta: {
    title: '首页',
    visible: true,
  },
  children: [{
    path: 'index',
    component: () => import('@/views/index.vue'),
  }]
}, {
  path: '/login',
  component: () => import('@/views/login.vue'),
  meta: {
    visible: false,
  }
}, {
  path: '/:pathMatch(.*)*',
  component: () => import('@/views/error/404.vue'),
  meta: {
    visible: false,
  }
},]

const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes,
})
const whiteList = ['/login']

router.beforeEach(async (to, from, next) => {
  if (useUserStore().token) {
    if (useUserStore().permissions.length === 0) {
      const [res, err] = await awaitTo(useUserStore().getUserInfo())
      if (err) {
        await useUserStore().logout()
        ElMessage.error(err)
        next({path: '/'})
      } else {
        await useRouteStore().generateRoutes()
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
  } else {
    // 没有token
    if (whiteList.includes(to.path)) {
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

})

export default router
