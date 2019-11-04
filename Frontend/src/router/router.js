import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from "@/pages/LoginPage.vue"
import RegisterPage from "@/pages/RegisterPage.vue"
import ChatRoomPage from "@/pages/ChatRoomPage.vue"
import UserPage from "@/pages/UserPage.vue"
import UserDetailPage from "@/pages/UserDetailPage"
Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'login',
            component: LoginPage
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterPage
        },
        {
            path: '/chatroom',
            name: 'chatroom',
            component: ChatRoomPage
        },
        {
            path: '/chatroom/:id',
            name: 'selectroom',
            component: ChatRoomPage
        },
        {
            path: '/userinfo',
            name: 'userinfo',
            component: UserDetailPage
        },

        // 임시
        {
          path: '/userdetail/:uidx',
          component: UserDetailPage,
          name: 'userdetail',
          props: true,
        }

    ]
})
