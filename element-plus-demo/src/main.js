import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import { createApp } from 'vue'
import App from './App.vue'
import './styles/element/index.scss'

const app = createApp(App)

app.use(ElementPlus, {
  size: 'default',
  zIndex: 3000,
  locale: zhCn
})
app.mount('#app')