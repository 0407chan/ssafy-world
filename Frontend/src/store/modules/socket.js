import api from '@/api'

const state = {
  msgDatas: [],
};

// getters
const getters = {
};

// actions
const actions = {
  getMsg({commit},params){
    api.getRoomMessage(params).then(res=>{
      console.log(res.data);
      for(let i =0;i<res.data.length;i++){
        state.msgDatas.push({
          from:{
            name : res.data[i].uid
          },
          msg : res.data[i].text,
          time : new Date(res.data[i].time),
        })
      }
      console.log(state.msgDatas);
      
    })
  }
};

// mutations
const mutations = {
  pushMsgData(state, $payload) {
    state.msgDatas.push($payload);
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