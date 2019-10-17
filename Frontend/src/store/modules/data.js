import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
}

// actions
const actions = {
  async getUser({ commit }, params) {
    console.log("왔냐", params);
    const resp = await api.getUser()
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
