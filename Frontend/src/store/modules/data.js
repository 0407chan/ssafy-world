import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  friend : false,
  chatlist : false,

  friendList : [],
  chatroomList : [],
  currUser: '',
}

// actions
const actions = {

  async login({ commit }, params) {
    return api.login(params).then(res =>{
      if(res.status == '200'){
        state.currUser = res.data;
        actions.registFriend()
        actions.registChatroom()
      }
      return res
    });
  },
  registFriend(){
    api.postFriend(state.userLoginToken).then(res=>{
      state.friendList=res;
    })
  },
  registChatroom(){
    api.getUserByRoom(state.userLoginToken).then(res=>{
      state.chatroomList=res;
    })
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


  reverse(state,string){
    if(string=='friend')
      state.friend=!state.friend
    else
      state.chatlist=!state.chatlist
  },

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
    state.chatroomList=[]
    state.friendList=[]
  }
};

export default {
  namespaced: true,
  state,
  actions,
  mutations
}
