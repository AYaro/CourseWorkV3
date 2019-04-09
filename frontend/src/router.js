import Vue from 'vue'
import Router from 'vue-router'
import User from '@/components/User'
import Display from "./views/Display.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/display',
      name: 'Display',
      component: Display
    },
    {
      path: '/user',
      name: 'User',
      component: User
    }
  ]
})
