import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  userLoginToken:'',
  userLoginPassword:'',
  checkLogin:0,

  currUser:[],
}

// actions
const actions = {

  async login({ commit }, params) {
    return api.login(params).then(res =>{
      console.log(res)
      if(res.data==='Login Success'){
        state.userLoginToken = params.id
        state.userLoginPassword = params.pw
        state.checkLogin=1
        sessionStorage.setItem('id',params.id)
        sessionStorage.setItem('pw',params.pw)
        state.currUser.id = params.id
        state.currUser.name= params.name
      }
      return res
    });
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
  },
  //
  setMenu(state, value) {
    state.checkLogin = value;
  },

  clearUser(state){
    state.userLoginPassword=''
    state.userLoginToken=''
  }
};

export default {
  namespaced: true,
  state,
  actions,
  mutations
}
