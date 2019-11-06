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
      commit('clearMsg');
      console.log("비워졌고",state.msgDatas)
      for(let i =0;i<res.data.length;i++){
        let user = await api.getUserInfo(res.data[i].uidx)
        let params ={
          user : user.data,
          msg : res.data[i].text,
          time : res.data[i].time,
        }
        // console.log(params);
        commit('pushMsgData',params);
      }      
      console.log("넣었다",state.msgDatas)
    })
  },

  pushMsg({commit,state},params){
    commit('pushMsgData',params);
  }
};

// mutations
const mutations = {
  pushMsgData(state, params) {
    if(state.msgDatas.indexOf(params) == -1){
      state.msgDatas.push(params);
    }
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
