import Vue from "vue";
import Router from "vue-router";
import User from "@/components/User";
import Test1 from "@/components/Test1";
import Test2 from "@/components/Test2";
import Index from "./components/Index";
Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/user",
      name: "User",
      component: User
    },
    {
      path: "/test1",
      name: "Test1",
      component: Test1
    },
    {
      path: "/test2",
      name: "Test2",
      component: Test2
    },
    {
      path: "/index",
      name: "Index",
      component: Index
    }
  ]
});
