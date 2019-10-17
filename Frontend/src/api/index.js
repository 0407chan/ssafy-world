import axios from 'axios'

const apiUrl = 'http://localhost:8080/ssafyworld';
//const apiUrl = '/ssafyworld';

export default {
  // param 없는 경우
  getUser(){
    return axios.get(`${apiUrl}/user`)
  },

  /* param 있는 경우
  getUser(params){
    return axios.get(`${apiUrl}/user/`, {
      params,
    })
  },
  */
}
