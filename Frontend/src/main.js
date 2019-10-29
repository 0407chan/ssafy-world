import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router/router'
import store from '@/store'
import vuetify from '@/plugins/vuetify'
import directives from '@/plugins/directives'
import VueSession from 'vue-session'
import '@/plugins/socket'
import '@/assets/_common.scss'
import VueChatScroll from 'vue-chat-scroll'

Vue.config.productionTip = false
Vue.config.devtools = true

Vue.use(directives)
Vue.use(VueChatScroll)

new Vue({
  el: '#app',
  router,
  store,
  vuetify,
  VueSession,
  render: h => h(App),
  components: { App },
  template: '<App />'
})
