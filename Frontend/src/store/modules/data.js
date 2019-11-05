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

  currChatRoom:'',
}

// actions
const actions = {

  refresh({commit,state}, params) {
    if (params != null){
      commit('SET_CURRUSER', params)
    }

    if (state.currUser != '') {
      console.log("refresh 함수 확인");

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

  /* 2019.11.04 이찬호
    currUser 삭제
  */
  clearCurrUser({commit,state}){
    commit('clearUser')
  },

  /* 2019.11.05 이찬호
    현재 채팅방 표시
  */
  setCurrChatRoom({commit,state},params){
    commit('SET_CURRCHATROOM', params)
  },

  async login({ commit }, params) {
    return api.login(params).then(res =>{
      if (res.status == '200') {
        commit('SET_CURRUSER', res.data);

        actions.registFriend()
        actions.registChatroom()
      }
      return res
    });
  },
  // 로그인 후 친구목록 생성
  registFriend() {
    api.getFriend(state.currUser.uidx).then(res=>{
      console.log('친구 목록' , res);
      let data = []
      for(let i =0;i<res.length;i++){
        api.getUserInfo(res[i]).then(res=>{
          data.push(res.data)
        })
      }
      state.friendList=data
    })
  },

  // 로그인 후 단체방 목록 생성
  registChatroom() {
    api.getUserByRoom(state.currUser.uidx).then(res=>{
      console.log('단톡방 목록' , res);
      state.chatroomList=res
    })
  },
  async register({ commit }, params) {
    const resp = await api.register(params);
    return resp;
  },


  async getRoom({ commit }, params) {
    const resp = await api.getRoom(params);
    return resp;
  },

  async getRoomPeople({ commit }, params) {
    const resp = await api.getRoomPeople(params);
    return resp;
  },

  async update({ commit }, params) {
    const res = await api.update(params);
    commit('SET_CURRUSER', res.data);

    return res;
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

  setFriendList(state, res) {
    state.friendList = res
  },
  setChatRoomList(state, res) {
    state.chatroomList = res
  },
  setClusterList(state, ratings) {
    state.ratingList = ratings.map(m => m)
  },

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

  SET_CURRCHATROOM(state,params){
    state.currChatRoom = params
  },
};

export default {
  namespaced: true,
  state,
  actions,
  mutations
}
