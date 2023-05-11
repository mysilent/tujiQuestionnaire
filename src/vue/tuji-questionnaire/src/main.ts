import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import router from './router'
import naive from 'naive-ui'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './assets/main.css'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import echarts from "./utils/echarts";


const app = createApp(App)

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate); //给pinia加载piain持久化存储插件


app.use(pinia)  //pinia挂载
app.use(router) //vueRouter路由挂载
app.use(ElementPlus) //elementPlus ui组件库挂载
app.use(naive) //Naive ui组件库挂载
app.config.globalProperties.$echarts = echarts //echarts可视化图标挂载
app.mount('#app')



