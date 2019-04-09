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
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    },{
      path: "/contacts",
      name: "contacts",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
          import(/* webpackChunkName: "about" */ "./views/Contacts.vue")
    }
  ]
})
