// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'

Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
  router, // 注入路由配置
  render: h => h(App)
}).$mount('#app')
