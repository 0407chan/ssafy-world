import Vue from 'vue'
import Vuex from 'vuex'
import api from '../api';
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },

  actions: {
    async login(id){
      console.log("store",id);
      let res = await api.login(id);
      console.log("결과", res);
    }
  },
  modules: {
  }
})
