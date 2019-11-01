import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  friend : false,   // user가 가지고 있는 친구 목록 토글 (실시간 동기화 때문에 VueX 사용)
  chatlist : false, // user가 가지고 있는 단체방 목록 토글 (실시간 동기화 때문에 VueX 사용)
  friendList : [],  // user가 가지고 있는 친구 목록
  chatroomList : [],  // user가 접속해있는 단체방 목록
  currUser: '',
}

// actions
const actions = {

  refresh({commit},params){
    if(params != null)
      state.currUser = params;
    if(state.currUser != ''){
      actions.registFriend()
      actions.registChatroom()
    }
  },

  async login({ commit }, params) {
    return api.login(params).then(res =>{
      if (res.status == '200') {
        state.currUser = res.data;
        console.log(state.currUser);

        actions.registFriend()
        actions.registChatroom()
      }
      return res
    });
  },
  // 로그인 후 친구목록 생성
  registFriend(){
    api.postFriend(state.currUser.uid).then(res=>{
      state.friendList=res;
    })
  },
  // 로그인 후 단체방 목록 생성
  registChatroom(){
    api.getUserByRoom(state.currUser.uid).then(res=>{
      state.chatroomList=res;
    })
  },

  async register({ commit }, params) {
    const resp = await api.register(params);
    return resp;
  },

  async update({ commit }, params) {
    const resp = await api.update(params);
    return resp;
  },

  async getUser({ commit }, params) {
    const resp = await api.getUser()
  },

  async getUserInfo({ commit }, params) {
    const resp = await api.getUserInfo(params)
    console.log("test: " + resp)
    return await api.getUserInfo(params)
  },

  async getUser({ commit }, params) {
    const resp = await api.getUser()
  },

  async createChatRoom({ commit }, params) {
    return await api.createChatRoom(params)
  },

  async getRoomByUser({ commit }, params) {
    const resp = await api.getRoomByUser(params)
  },

};

// mutations
const mutations = {
  reverse(state, string) {
    if (string == 'friend')
      state.friend =! state.friend
    else
      state.chatlist =! state.chatlist
  },

  setClusterList(state, ratings) {
    state.ratingList = ratings.map(m => m)
  },
  //
  clearUser(state){
    state.currUser = ''
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
