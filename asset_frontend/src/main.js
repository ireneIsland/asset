import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'

// bootstrap相關元件
import BootstrapVue from 'bootstrap-vue'

// 訊息相關元件
import Snotify, { SnotifyPosition } from 'vue-snotify'

// 讀取畫面相關元件
import Loading from 'vue-loading-overlay'
import 'vue-loading-overlay/dist/vue-loading.css'

// 表單驗證
import VeeValidate from 'vee-validate';

// snotify 設定
const snotify_options = {
  toast: {
    position: SnotifyPosition.rightBottom,
    showProgressBar: true,
    closeOnClick: true,
    pauseOnHover: true
  }
}

// vue在啟動時生成生產提示 設置為false
Vue.config.productionTip = false

// 啟用套件
Vue.use(VueAxios, axios)
Vue.use(Snotify, snotify_options)
Vue.use(VeeValidate)
Vue.use(BootstrapVue)

// 設定元件
Vue.component('Loading', Loading)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
