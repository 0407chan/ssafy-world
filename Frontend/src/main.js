import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router/router'
import store from '@/store'
import vuetify from '@/plugins/vuetify'
import directives from '@/plugins/directives'
import '@/plugins/socket'

Vue.config.productionTip = false
Vue.config.devtools = true

Vue.use(directives)

new Vue({
  el: '#app',
  router,
  store,
  vuetify,
  render: h => h(App),
  components: { App },
  template: '<App />'
})
