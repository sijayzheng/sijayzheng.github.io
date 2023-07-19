import {createRouter, createWebHistory} from 'vue-router'
import whiteListRouters from './whiteList'

const routes = [...whiteListRouters]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
