import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  friend : false,   // user가 가지고 있는 친구 목록 토글 (실시간 동기화 때문에 VueX 사용)
  chatlist : false, // user가 가지고 있는 단체방 목록 토글 (실시간 동기화 때문에 VueX 사용)
  friendList : [],  // user가 가지고 있는 친구 목록
  chatroomList : [],  // user가 접속해있는 단체방 목록

  newMessage :false,
  chatListRealTimeCheck : false,

  currUser: '',
  navDrawer: false,
}

// actions
const actions = {

  refresh({commit}, params) {
    if (params != null)
      state.currUser = params;
    if (state.currUser != '') {
      actions.registFriend()
      actions.registChatroom()
    }
  },

  async getUsers({ commit }) {
    return await api.getUsers()
  },
  
  async adminUpdateUser({ commit }, params) {
    return await api.adminUpdateUser(params)
  },

  async adminDeleteUser({ commit }, param) {
    await api.adminDeleteUser(param)
  },

  /* 2019.11.03 이찬호
    currUser 세팅
  */
  setCurrUser({commit, state}, params){
    commit('SET_CURRUSER', params)
    return state.currUser
  },

  async login({ commit }, params) {
    return api.login(params).then(res =>{
      if (res.status == '200') {
        state.currUser = res.data;
        console.log("로그인 했냐 ",state.currUser);

        actions.registFriend()
        actions.registChatroom()
      }
      return res
    });
  },
  // 로그인 후 친구목록 생성
  registFriend() {
    api.postFriend(state.currUser.uid).then(res=>{
      state.friendList = res;
    })
  },

  // 로그인 후 단체방 목록 생성
  registChatroom() {
    api.getUserByRoom(state.currUser.uid).then(res=>{
      state.chatroomList = res;
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
  },

  toggleNav(state){
    state.navDrawer = !state.navDrawer;
  },

  SET_CURRUSER(state, params){
    state.currUser = params
  },

};

export default {
  namespaced: true,
  state,
  actions,
  mutations
}
