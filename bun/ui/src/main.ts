// ElementPlus
import ElementPlus from 'element-plus'

import zhCn from 'element-plus/es/locale/lang/zh-cn'

import {createApp} from 'vue'
import App from './App.vue'
import '@/styles/index.scss'
// unocss
import 'virtual:uno.css'
import 'uno:icons.css'
// dayjs
import 'dayjs/locale/zh-cn'

const app = createApp(App)
app.use(createPinia())

app.use(router)

app.use(ElementPlus, {
  locale: zhCn,
  button: {
    autoInsertSpace: true,
  },
  message: {
    max: 5,
    showClose: true,
  },
})
app.mount('#app')
