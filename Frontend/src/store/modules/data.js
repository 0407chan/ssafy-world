import api from '@/api'

// initial state
const state = {
  currentNavigation: 0,
  currentContent: 0,
  friend : false,   // user가 가지고 있는 친구 목록 토글 (실시간 동기화 때문에 VueX 사용)
  chatlist : false, // user가 가지고 있는 단체방 목록 토글 (실시간 동기화 때문에 VueX 사용)
  checkLogin : 0, // 내비게이션 바 조절
  friendList : [],  // user가 가지고 있는 친구 목록
  chatroomList : [],  // user가 접속해있는 단체방 목록
  currUser: '',
}

// actions
const actions = {

  async login({ commit }, params) {
    return api.login(params).then(res =>{
      if(res.status == '200'){
        state.currUser = res.data;
        state.checkLogin = 1;
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
