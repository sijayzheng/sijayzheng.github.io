import ElementPlus from 'element-plus'
import { createApp } from 'vue'

import { createRouter, createWebHistory } from 'vue-router'

import { routes } from 'vue-router/auto-routes'
import App from './App.vue'

import '~/styles/element/index.scss'

import 'element-plus/dist/index.css'
import '~/styles/index.scss'
import 'uno.css'

// If you want to use ElMessage, import it.
import 'element-plus/theme-chalk/src/message.scss'
import 'element-plus/theme-chalk/src/message-box.scss'

const app = createApp(App)
app.use(createRouter({
  history: createWebHistory(),
  routes,
}))
app.use(ElementPlus)
app.mount('#app')
