import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'dayjs/locale/zh-cn'
import dayjs from 'dayjs'
//style
import './styles/index.scss'
import './styles/element/index.scss'
import 'uno.css'
import 'animate.css'
import {hasPermission} from '@/directive/hasPermission.js'

dayjs.locale('zh-cn')
const app = createApp(App)

const pinia = createPinia()
app.use(pinia)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(ElementPlus, {
  size: 'default',
  zIndex: 3000,
  locale: zhCn,
  button: {
    autoInsertSpace: true
  },
  message: {
    max: 3
  },
})
app.use(router)
app.directive('hasPermission', hasPermission)
app.mount('#app')
