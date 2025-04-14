import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'
import router from './router'
// custom style
import '@/styles/index.scss'
// unocss
import 'virtual:uno.css'
import 'uno:icons.css'
// dayjs
import 'dayjs/locale/zh-cn'

const app = createApp(App)
app.use(createPinia())
app.use(router)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
// app.directive('hasPerm', {
//   mounted(el: HTMLElement, binding: DirectiveBinding) {
//     const { permissions } = useUserStore()
//     // 「其他角色」按钮权限校验
//     const { value } = binding
//     if (value && Array.isArray(value) && value.length > 0) {
//       if (!permissions.some((perm: string) => perm === '*:*:*' || value.includes(perm))) {
//         el.parentNode && el.parentNode.removeChild(el)
//         return false
//       }
//     } else {
//       throw new Error(`check perms! Like v-has-perm="['system:user:add','system:user:edit']"`)
//     }
//   },
// })
app.mount('#app')
