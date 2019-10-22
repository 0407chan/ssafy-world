import Vue from 'vue'
import Vuex from 'vuex'
import data from './modules/data'
import socket from './modules/socket'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  modules: {
    data,
    socket,
  },
  strict: debug,
})
