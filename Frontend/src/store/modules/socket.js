import Constant from '../../Constant';
import api from '@/api'

const state = {
  msgDatas: [],
};

// getters
const getters = {
};

// actions
const actions = {
  getMsg(params){
    api.getRoomMessage(params).then(res=>{
      state.msgDatas=res.data
    })
  }
};

// mutations
const mutations = {
  [Constant.PUSH_MSG_DATA]: ($state, $payload) => {
    $state.msgDatas.push($payload);
  },
  clearMsg(){
    state.msgDatas=[]
  },
  

};

export default {
  state,
  getters,
  actions,
  mutations,
};