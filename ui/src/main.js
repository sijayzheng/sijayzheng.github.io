import {createApp} from 'vue'
import 'element-plus/dist/index.css'
import '@/styles/index.scss'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import {camelTobreak} from './utils'
import zhCn from 'element-plus/es/locale/lang/zh-cn.mjs'

const app = createApp(App)
const store = createPinia()

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component('i-' + camelTobreak(key), component)
}

app.use(ElementPlus, {locale: zhCn})
app.use(router)
app.use(store)

app.mount('#app')
