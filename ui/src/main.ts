import { createApp } from 'vue'
import App from './App.vue'
import 'dayjs/locale/zh-cn'
import store from './store/index.ts'
import router from './router/index.ts'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import VXETable from 'vxe-table'
import ElementPlus from 'element-plus'
import '@/style/index.scss'
import 'element-plus/dist/index.css'
import 'vxe-table/lib/style.css'

const app = createApp(App)
app.use(store)
app.use(router)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// VXETable.setConfig({
//   zIndex: 999999
// })
app.use(VXETable)


app.use(ElementPlus)
app.mount('#app')
/*
// 自定义指令
import directive from "./directive";

// 注册插件
import plugins from "./plugins/index"; // plugins
// 高亮组件
// import 'highlight.js/styles/a11y-light.css';
import HighLight from "@highlightjs/vue-plugin";
import "highlight.js/lib/common";
import "highlight.js/styles/atom-one-dark.css";

// svg图标
import "virtual:svg-icons-register";

// permission control
import "./permission";


// ElDialog.props.closeOnClickModal.default = false;

app.use(HighLight);
app.use(plugins);
// 自定义指令
directive(app);

 */
