import Vue from 'vue'
import App from '@/App.vue'
import vuetify from '@/plugins/vuetify'
import router from '@/router/router'
import store from '@/store'
import VueWaypoint from 'vue-waypoint/src'

Vue.use(VueWaypoint)
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
