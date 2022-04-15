import Vue from 'vue'
import Router from 'vue-router'
import Appindex from "../components/home/Appindex";
import Login from '@/components/Login'
Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/index',
      name: 'Appindex',
      component: Appindex
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    }
  ]
})
