import axios from 'axios'


const apiUrl = 'http://127.0.0.1:8080/ssafyworld';
//const apiUrl = 'http://13.124.121.215:8080/ssafyworld';
//const apiUrl = 'http://70.12.246.62:8080/ssafyworld';

export default {
  // param 없는 경우
  getUser() {
    return axios.get(`${apiUrl}/user`)
  },

  /** 2019.10.18 이찬호
  * 기능 : 로그인
  * 파라미터 : params = uid, password
  * 리턴 : 일치하는 유저 가져오기
  */
  login(params) {
    console.log("index",params);
    return axios.post(`${apiUrl}/user/login`, {
      uid:params.id,
      password:params.pw,
    }).then(response => {
      return response
    })
    .catch(error => {
      return error.response
    });
  },

  /** 2019.10.18 이찬호
  * 기능 : 로그인
  * 파라미터 : params = uid, uname, password
  * 리턴 : 일치하는 유저 가져오기
  */
  register(params) {
    console.log("index",params);
    return axios.post(`${apiUrl}/user/register`, {
      // params,
      uid: params.uid,
      uname: params.uname,
      password: params.password,
    })
  },

  // 10-17 최재형
  // 사용자가 가지고 있는 방 번호 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomByUser(param) {
    return axios.get(`${apiUrl}/room/${param}`).then(response=>{
      return response
    })
  },

  // 10-25 최재형
  // 방 번호에 있는 메세지 전부 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomMessage(param){
    return axios.get(`${apiUrl}/message/${param}`).then(response=>{
      return response
    }).catch(error=>{
      return error.response
    })
  },

  // 10-17 최재형
  // 메세지 입력
  // 파라미터 : 메세지 테이블에 있는 전 목록(날짜 빼고)
  // 리턴 : 성공() 실패
  postMessage(object){
    for(let i=0;i<object.lenght;i++){
      axios.post('/message',{ mid:object[i].mid, text:object[i].text, uid:object[i].uid,rid:object[i].rid })
    }
                
  }
}
