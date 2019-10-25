import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  userLoginToken:'',
  userLoginPassword:''
}

// actions
const actions = {

  async login({ commit }, params) {
    const resp = await api.login(params);
    if(resp.data==='LOGIN SUCCESS'){
      state.userLoginToken = params.id
      state.userLoginPassword = params.pw
    }
    return resp;
  },

  async register({ commit }, params) {
    const resp = await api.register(params);
  },

  async getUser({ commit }, params) {
    const resp = await api.getUser()
  },

  async getUser({ commit }, params) {
    const resp = await api.getUser()
  },

  async getRoomByUser({ commit }, params) {
    const resp = await api.getRoomByUser(params)
  },

};

// mutations
const mutations = {

  setClusterList(state, ratings) {
    state.ratingList = ratings.map(m => m)
  }
};

export default {
  namespaced: true,
  state,
  actions,
  mutations
}
