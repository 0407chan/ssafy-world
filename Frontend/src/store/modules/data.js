import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  userLoginToken: '',
}

// actions
const actions = {

  async login({ commit }, params) {
    return await api.login(params);
  },

  async register({ commit }, params) {
    const resp = await api.register(params);
    return resp;
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
