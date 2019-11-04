import api from '@/api'

const state = {
  msgDatas: [],
};

// getters
const getters = {
};

// actions
const actions = {
  getMsg({commit,state},params){
    api.getRoomMessage(params).then(async res=>{
      console.log(res.data);
      for(let i =0;i<res.data.length;i++){
        let user = await api.getUserInfo(res.data[i].uidx)
        console.log(user.data);
        let params ={
          user : user.data,
          msg : res.data[i].text,
          time : res.data[i].time,
        }
        commit('pushMsgData2',params)
      }
      console.log(state.msgDatas);

    })
  }
};

// mutations
const mutations = {
  pushMsgData(state, $payload) {
    if(state.msgDatas.indexOf($payload) == -1){
      state.msgDatas.push($payload);
    }
  },
  pushMsgData2(state, params) {
    state.msgDatas.push(params);
  },
  clearMsg(state){
    state.msgDatas=[]
  },


};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
