import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'

Vue.config.productionTip = false
Vue.config.devtools = true

new Vue({
  el: '#app',
  vuetify,
  render: h => h(App),
  router,
  components: { App },
  template: '<App />'
})
