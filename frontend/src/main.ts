import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Antd from 'ant-design-vue'
import router from './router'
import App from './App.vue'
import axios from 'axios'
import VueLazyload from 'vue3-lazyload'
import 'ant-design-vue/dist/reset.css'
import './style.css'

// 配置 Axios 全局默认后端地址
// 替换为后端地址，例如 http://localhost:3000/api
// axios.defaults.baseURL = 'http://example.com/api'
axios.defaults.timeout = 5000 // 可选，设置请求超时时间（单位：毫秒）

const app = createApp(App)

// 全局配置 Axios
app.config.globalProperties.$axios = axios


// 配置 vue-lazyload 插件
app.use(VueLazyload, {
    preLoad: 1.3, // 预加载高度（默认 1.3）
    error: '/assets/error.png', // 图片加载失败时的占位图
    loading: '/assets/loading.png', // 加载中的占位图
    attempt: 1, // 尝试加载次数
})
app.use(createPinia())
app.use(router)
app.use(Antd)
app.mount('#app')