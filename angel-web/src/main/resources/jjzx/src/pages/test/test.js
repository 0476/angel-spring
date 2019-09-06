import Vue from 'vue'

import test from './test.vue'
import ElementUI from 'element-ui'
import axios from "axios";
import 'element-ui/lib/theme-chalk/index.css'

// Vue.prototype.$baseUrl = process.env.BASE_URL
Vue.prototype.$axios = axios;
Vue.use(ElementUI)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { test },
  template: '<test/>'
})
