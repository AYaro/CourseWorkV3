import Vue from 'vue'
import Router from 'vue-router'
import User from '@/components/User'
Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/display',
      name: 'Display',
      component: DisplayOrder
    },
    {
      path: '/user',
      name: 'User',
      component: User
    }
  ]
})
