export default [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login.vue'),
        meta: {
            title: '其他'
        }
    }, {
        path: '/:pathMatch(.*)*',
        hidden: true,
        component: () => import('@/layout/other/404.vue')
    }
]
