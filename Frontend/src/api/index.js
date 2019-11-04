import axios from 'axios'

const apiUrl = 'http://localhost:8080/ssafyworld';
// const apiUrl = 'http://13.124.121.215:8080/ssafyworld';
// const apiUrl = 'http://70.12.246.62:8080/ssafyworld'; // 9비니여보야의여보야
// const apiUrl = 'http://70.12.247.61:8080/ssafyworld';

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
  getUserInfo(param) {
    return axios.get(`${apiUrl}/user/info/${param}`)
    .then(response => {
      return response
    })
    .catch(error => {
      console.log(error)
      return error.response
    });
  },

  /**
   *
   * 2019.11.01 준범이
   * 기능: 회원정보수정
   * 파라미터: param = uidx
   * 리턴: 없음
   * 특징: 이건 백문제
   *
   */
  update(params) {
    return axios.post(`${apiUrl}/user/update`, {
      uidx: params.uidx,
      uname: params.uname,
      password: params.password
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
    console.log("index", params);
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
  * 기능 : 회원가입
  * 파라미터 : params = uid, uname, password, img
  * 리턴 : 일치하는 유저 가져오기
  */
  register(params) {
    console.log("index", params);
    return axios.post(`${apiUrl}/user/register`, {
      // params,
      uid: params.uid,
      uname: params.uname,
      password: params.password,
      img: params.image,
    }).then(response => {
      return response
    })
    .catch(error => {
      return error.response
    });
  },

  // 10-17 최재형
  // 방 번호에 해당되는 이름 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomName(param) {
    return axios.get(`${apiUrl}/room/name/${param}`)
  },

  // 10-28 최재형
  // 사용자가 가지고 있는 방 번호 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getUserByRoom(uidx) {
    return axios.post(`${apiUrl}/user/rooms`,{
      'uidx' : uidx
    }).then(async res=>{
        return res.data
      })
  },

  // 10-17 최재형
  // 방 번호에 있는 메세지 전부 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomMessage(param){
    return axios.get(`${apiUrl}/message/${param}`)
  },

  // 11-4 최재형
  // 메세지 입력
  // 파라미터 : 메세지 테이블에 있는 전 목록(날짜 빼고)
  // 리턴 : 정상 전송 되었는지
  postMessage(object){
    return axios.post('/message',{ 
      text:object.text,
      uidx:object.uidx,
      ridx:object.ridx 
    }).then(response => {
      console.warn(response)
    }).catch((ex) => {
      console.warn("ERROR!!!!! : ",ex)
    })
  },

  // 11-4 최재형
  // 친구 추가
  // 파라미터 : 상대 uidx
  // 리턴 : 없을듯 (아마도)
  getFriend(uidx){
    return axios.get(`${apiUrl}/friend/${uidx}`)
    .then( res=>{
      return res.data
      //싹다 다시 구현해야함
    }).catch(error=>{
      return error
    })
  },

  // 11.1 최재형
  // 새친구를 만든다
  postAddFriend(uidx){
    return axios.post(`${apiUrl}/friend/add`, {
      'uidx': uidx
    }).then(res=>{
      return res
    }).catch(error=>{
      return error
    })
  },

  //11.1 최재형
  // 새로운 방을 만든다
  // insert
  postRoom(rname){
    return axios.post(`${apiUrl}/room/create`, {
      'rname': rname
    }).then(res=>{
      return res
    }).catch(error=>{
      return error
    })
  },

  //11.1 최재형
  //방에 유저가 접속한다
  // insert 문
  postEnterRoom(uidx,ridx){
    return axios.post(`${apiUrl}/room/enter`, {
      'uidx': uidx,
      'ridx': ridx
    }).then(res=>{
      return res
    }).catch(error=>{
      return error
    })
  },

}
