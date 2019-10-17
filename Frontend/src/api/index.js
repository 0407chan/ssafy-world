import axios from 'axios'

const apiUrl = 'http://70.12.246.62:8080/ssafyworld';

export default {
  // param 없는 경우
  getUser() {
    return axios.get(`${apiUrl}/user`)
  },

  getRoomMessage(param){
    return axios.get(`${apiUrl}/message/${param}`)
  },

  postMessage(object){
    return axios.post('/message',{ mid:object.mid, text:object.text, uid:object.uid,rid:object.rid })
                  .then(response => {
                    console.warn(response)
                  })
                  .catch((ex) => {
                      console.warn("ERROR!!!!! : ",ex)
                  })
  }

  /* param 있는 경우
  getUser(params){
    return axios.get(`${apiUrl}/user/`, {
      params,
    })
  },
  */
}
