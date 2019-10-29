import axios from 'axios'

const apiUrl = 'http://localhost:8080/ssafyworld';
// const apiUrl = 'http://13.124.121.215:8080/ssafyworld';
// const apiUrl = 'http://70.12.246.62:8080/ssafyworld';

export default {
  // param 없는 경우
  getUser() {
    return axios.get(`${apiUrl}/user`)
  },


  /**
   * 
   * 2019.10.29 준범이
   * 자소서도 안돼
   * 면접도 안돼
   * 이 안에 배신자가 있다
   * 이게 내 결론이다
   *  
   */
  getUserInfo(params) {
    console.log(params.id)
    return axios.post(`${apiUrl}/user/info`, {
      uid: params.id,
    }).then(response => {
      console.log("규빈")
      console.log(response)
      return response
    })
    .catch(error => {
      console.log(error)
      return error.response
    });
  },


  /** 2019.10.18 이찬호
  * 기능 : 로그인
  * 파라미터 : params = uid, password
  * 리턴 : 일치하는 유저 가져오기
  */
  login(params) {
    console.log("index",params);
    return axios.post(`${apiUrl}/user/login`, {
      uid: params.id,
      password: params.pw,
    }).then(response => {
      return response
    })
    .catch(error => {
      console.log(error);

      return error.response
    });
  },

  /** 2019.10.25 이찬호
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
    }).then(response => {
      return response
    })
    .catch(error => {
      return error.response
    });
  },


  // 10-17 최재형
  // 회원가입
  // 파라미터 : 회원 obj (데이터 베이스 이름과 일치하게 넣어야 함)
  // 리턴 : 없을듯 아마도...
  getUserLogin(object) {
    return axios.post('/user/regist',{ VO:object})
                  .then(response => {
                    console.warn(response)
                  })
                  .catch((ex) => {
                      console.warn("ERROR!!!!! : ",ex)
                  })
  },

  // 10-17 최재형
  // 방 번호에 해당되는 이름 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomName(param) {
    return axios.get(`${apiUrl}/room/${param}`)
  },

  // 10-28 최재형
  // 사용자가 가지고 있는 방 번호 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getUserByRoom(param) {
    return axios.get(`${apiUrl}/user/${param}`).then(async res=>{
        let arr = []
        for(let i=0;i<res.data.length;i++){
          data = {
            name : await getRoomName(res.data[i]),
            path : '/chatroom/'+res.data[i]
          }
          arr.push(data)
        }
        return arr
      })
  },

  // 10-17 최재형
  // 방 번호에 있는 메세지 전부 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomMessage(param){
    return axios.get(`${apiUrl}/message/${param}`)
  },

  // 10-17 최재형
  // 메세지 입력
  // 파라미터 : 메세지 테이블에 있는 전 목록(날짜 빼고)
  // 리턴 : 없을듯 (아마도)
  postMessage(object){
    return axios.post('/message',{ mid:object.mid, text:object.text, uid:object.uid,rid:object.rid })
                  .then(response => {
                    console.warn(response)
                  })
                  .catch((ex) => {
                      console.warn("ERROR!!!!! : ",ex)
                  })
  },

  // 10-17 최재형
  // 메세지 입력
  // 파라미터 : 메세지 테이블에 있는 전 목록(날짜 빼고)
  // 리턴 : 없을듯 (아마도)
  postFriend(uid){
    return axios.post(`${apiUrl}/friend/`, {
      'uid': uid
    }).then(res=>{
      return res
    }).then(error=>{
      return error
    })
  },

  postAddFriend(uid){
    return axios.post(`${apiUrl}/friend/add`, {
      'uid': uid
    }).then(res=>{
      return res
    }).then(error=>{
      return error
    })
  },
  
}
