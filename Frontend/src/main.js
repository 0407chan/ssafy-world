import Vue from 'vue'
import App from '@/App.vue'
import vuetify from '@/plugins/vuetify'
import router from '@/router/router'
import store from '@/store'
import VueWaypoint from 'vue-waypoint/src'
import Directives from '@/plugins/directives'
import '@/plugins/socketPlugin'
import '@/assets/_common.scss'

Vue.use(VueWaypoint)
Vue.use(Directives)

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
