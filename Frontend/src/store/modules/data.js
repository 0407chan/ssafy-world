import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  userLoginToken:'',
  userLoginPassword:'',
  checkLogin:0,
  friend : false,
  chatlist : false,

  currUser:[],
  friendList : [],
  chatroomList : [],
}

// actions
const actions = {

  async login({ commit }, params) {
    return api.login(params).then(res =>{
      console.log(res)
      if(res.status == '200'){
        state.userLoginToken = params.id
        state.userLoginPassword = params.pw
        state.checkLogin=1
        sessionStorage.setItem('id',params.id)
        sessionStorage.setItem('pw',params.pw)
        actions.registFriend()
        actions.registChatroom()
        state.currUser.id = params.id
        state.currUser.name= params.name
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
