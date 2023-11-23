//import './assets/common.css'
//import './assets/css/epas.style.css'
//import './assets/css/remixicon.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import { VueCookies } from 'vue-cookies'

//부트스트랩
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

import VueSession from 'vue-session'

//createApp(App).mount('#app')

var sessionOptions = {
    persist: true
  }

const app =createApp(App)
//createApp(App).mount('#app')
app.config.globalProperties.$axios = axios;  //전역변수로 설정 컴포넌트에서 this.$axios 호출할 수 있음
app.config.globalProperties.$serverUrl = '//localhost:8080' //api server
app.use(VueCookies)
app.use(BootstrapVue3)
app.use(VueSession, sessionOptions)
app.use(router).mount('#app')
