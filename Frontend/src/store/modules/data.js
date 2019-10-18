import api from '@/api'

const resourceHost = "http://70.12.246.62:8080/ssafyworld";

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  accessToken: null,
}

// actions
const actions = {

  async login(params) {
    console.log(params.email);
    
    return await api.getUser(params);
  },

  async logout({commit}) {
    commit('logout')
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
  
  async getClustering({commit}, params) {
    const res = await api.getClustering(params);
    console.log(res,'res')
    console.log(params,'params')
    const clusters = res.data.map(d => ({
      movie_label: d.movie_label,
      movie_cluster: d.movie_cluster,
      user_label: d.user_label,
      user_cluster: d.user_cluster
    }));
    commit('setClusterList', clusters)
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
