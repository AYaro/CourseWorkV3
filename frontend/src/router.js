import Vue from 'vue'
import Router from 'vue-router'
import Login from './components/Login'
import Home from './views/Home'
import Display from "./views/Display.vue"
import User from "./components/User"
import Order from "./components/Order"

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/display',
      name: 'Display',
      component: Display
    },
    {
      path: '/Order',
      name: 'Order',
      component: Order
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
      path: "/user",
      name: "User",
      component: User
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
});
