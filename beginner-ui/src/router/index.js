import Vue from 'vue'
import VueRouter from "vue-router";
import login from "../view/login.vue";
import index from "../view/index.vue";
import user from "../view/system/user/index.vue"
import deviceInfo from "../view/device/info/index.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: login
  },
  {
    path: '/home',
    name: 'home',
    component: index
  },
  {
    path: '/system/user',
    name: 'systemUser',
    component: user
  },
  {
    path: '/device/info',
    name: 'deviceInfo',
    component: deviceInfo,
    meta: {requiresAuth: true}
  }
]

const router = new VueRouter({
  routes
})

export default router
