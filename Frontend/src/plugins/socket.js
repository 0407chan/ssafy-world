import Vue from 'vue'
import io from 'socket.io-client'

const socket = io('http://70.12.247.108:3000');
// const socket = io('http://13.124.121.215:3000');

const SocketPlugin = {
  install(vue) {
    vue.mixin({
    });

    vue.prototype.$sendMessage = ($payload) => {
      // socket.emit('/chatroom'+$payload.rid, {
      socket.emit($payload.rid, {
        msg: $payload.msg,
        user: $payload.user,
        time: $payload.time,
      });
    };

    // 인스턴스 메소드 추가
    vue.prototype.$socket = socket;
  },
};

Vue.use(SocketPlugin);
