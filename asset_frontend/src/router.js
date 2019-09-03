import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import AssetReg from './views/AssetReg.vue'
import SystemConfig from './views/SystemConfig.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/', // 對應路徑
      name: 'home', // 元件呈現名稱
      component: Home // 對應元件
    },
    {
      path: '/assetreg',
      name: 'AssetReg',
      component: AssetReg
    },
    {
      path: '/systemconfig',
      name: 'SystemConfig',
      component: SystemConfig
    }
  ]
})
