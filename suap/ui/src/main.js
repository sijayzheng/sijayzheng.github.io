import {createApp} from 'vue'
import './style.scss'
import App from './App.vue'
import 'element-plus/dist/index.css'
import router from './router'
import 'virtual:svg-icons-register'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'dayjs/locale/zh-cn'
import dayjs from 'dayjs'
import {createPinia} from "pinia";
// highlight 的样式，依赖包，组件
import 'highlight.js/styles/atom-one-light.css'
import 'highlight.js/lib/common'
import hljsVuePlugin from '@highlightjs/vue-plugin'
import hljsCommon from 'highlight.js/lib/common'

dayjs.locale('zh-cn')

const ElementIcons = {
    install: (app) => {
        for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
            app.component(key, component)
        }
    }
}

const pinia = createPinia()
hljsCommon.highlightAuto('<h1>Highlight.js has been registered successfully!</h1>').value
createApp(App)
    .use(router)
    .use(pinia)
    .use(ElementPlus, {locale: zhCn})
    .use(ElementIcons)
    .use(hljsVuePlugin)
    .mount('#app')
