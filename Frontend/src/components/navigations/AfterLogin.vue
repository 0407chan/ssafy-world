<template>
    <v-list>
        <v-list-item @click="() => { if (afterLoginItems[0].path) { goTo(afterLoginItems[0].path) } }">
            <v-list-item-action>
                <v-icon>{{ afterLoginItems[0].icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-title>
                {{ afterLoginItems[0].title }}
            </v-list-item-title>
        </v-list-item>

        <!-- 친구 목록 열기 -->
        <v-list-item @click="reverse('friend')">
            <v-list-item-action>
                <v-icon>{{ afterLoginItems[1].icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-title>
                {{ afterLoginItems[1].title }}
            </v-list-item-title>
        </v-list-item>
        <FriendList />
        
        <!-- 대화방 열기 -->
        <v-list-item @click="reverse('chatlist')">
            <v-list-item-action>
                <v-icon>{{ afterLoginItems[2].icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-title>
                {{ afterLoginItems[2].title }}
            </v-list-item-title>
        </v-list-item>
        <ChatRoomList />
    </v-list>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import FriendList from '@/components/navigations/FriendList'
import ChatRoomList from '@/components/navigations/ChatRoomList'

export default {
    name: 'AfterLogin',
    data() {
        return {
            afterLoginItems: [
                { title: '일 정 표', icon: 'mdi-calendar', path: '' },
                { title: '친 구 들', icon: 'mdi-account', path: 'chatroom' },
                { title: '채 팅 방', icon: 'mdi-account-group-outline', path: '' },
            ],
        }
    },
    components: {
        FriendList,
        ChatRoomList,
    },
    created() {
        let params = {
            'id': sessionStorage.getItem('id'),
            'pw': sessionStorage.getItem('pw'),
        }
        
        // 이미 접속한 이력이 있을 경우
        this.$socket.on('check', (data) => {
            if (data.msg === 'already connect') {
                if (confirm("이미 접속한 브라우져가 존재합니다. \n 지금 브라우져를 사용하시겠습니까?")) {
                    this.$socket.emit('change', {
                        msg: 'yes'
                    })
                }
                else {
                    this.$socket.emit('change', {
                        msg: 'no'
                    })
                }
            }
        })
        
        this.$socket.on('disc', (data) => {
            this.$socket.disconnect()
        })
        
        if (params.id != null && params.pw != null) {
            this.login(params).then(res => {
                if (res.status == '200')
                    this.$router.push({ name: 'chatroom' })
            })
        }
    },
    computed: {
        ...mapState('data', ['friend', 'chatlist']),
        ...mapState({
            currUser: state => state.data.currUser,
        }),
    },
    methods: {
        ...mapMutations('data', ['setMenu', 'clearUser', 'reverse']),
    },
}
</script>
