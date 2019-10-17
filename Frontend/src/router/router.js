import Vue from 'vue'
import Router from 'vue-router'
import BeforeLoginPage from "@/pages/BeforeLoginPage.vue"

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'home',
            component: BeforeLoginPage
        },
    ]
})
