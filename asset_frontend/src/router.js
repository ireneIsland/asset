import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import AssetReg from './views/island_asset/AssetReg.vue'
import AssetQuery from './views/island_asset/AssetQuery.vue'
import AssetDetail from './views/island_asset/AssetDetail.vue'
import SystemConfig from './views/SystemConfig.vue'
import Dashboard from './components/Dashboard.vue';
import Login from './views/Login.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '*',
      redirect: 'login',
    },
    {
      path: '/login', // 對應路徑
      name: 'Login', // 元件呈現名稱
      component: Login // 對應元件
    },
    
    {
      path: '/asset',
      name: 'asset',
      component: Dashboard,
      children: [
        {
          path: '/',
          name: 'Home',
          component: Home,
          meta: { requiresAuth: true },
        },
        {
          path: 'registered',
          name: 'Registered',
          component: AssetReg,
          meta: { requiresAuth: true },
        },
        {
          path: 'query',
          name: 'Query',
          component: AssetQuery,
          meta: { requiresAuth: true },
        },
        {
          path: 'detail/:assetNo',
          name: 'Detail',
          component: AssetDetail,
          meta: { requiresAuth: true },
        },
      ]
    },
    
    
    {
      path: '/system',
      name: 'SystemConfig',
      component: Dashboard,
      children: [
        {
          path: 'config',
          name: 'Config',
          component: SystemConfig,
        },
      ],
    }
  ]
})
