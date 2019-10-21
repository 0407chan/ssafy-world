import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  accessToken: null,
  messageList: [],
}

// actions
const actions = {

  async login({commit}, param) {
    return state.accessToken = await api.getUser(param);
  },

  async logout({commit}) {
    commit('logout')
  },

  async getMessages({ commit }) {
    return state.messageList = await api.getMessage();
  },

  async sendMessages({ commit }, param) {
    return state.messageList = await api.sendMessage(param);
  },

  async getUser({ commit }, params) {
    console.log("왔냐", params);
    const resp = await api.getUser()
    console.log(resp);
  },

  async getRoomByUser({ commit }, params) {
    console.log("왔냐", params);
    const resp = await api.getRoomByUser(params)
    console.log(resp);
  },

};

// mutations
const mutations = {

  login(state, {accessToken}) {
    state.accessToken = accessToken
  },

  logout(state) {
    state.accessToken = null
  }

};

export default {
  namespaced: true,
  state,
  actions,
  mutations
}
