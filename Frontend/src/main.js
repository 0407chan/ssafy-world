import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import './plugins/socketPlugin';
import router from './router/router'
import store from './store'

Vue.config.productionTip = false
Vue.config.devtools = true

new Vue({
  el: '#app',
  vuetify,
  router,
  store,
  render: h => h(App),
  components: { App },
  template: '<App />'
})
