import axios from 'axios'

//const apiUrl = 'http://70.12.246.62:8080/ssafyworld'
const apiUrl = 'http://127.0.0.1:8080/ssafyworld'

export default {

  /**
   * 10. 18. 준범이
   * 묻고 로그인으로 가!
   * 
   */
  getUser(param) {
    return axios.post(`${apiUrl}/user/login`, { param })
      .then(({ data }) => { return param.email })
  },

  /**
   * 10. 21. 준범이
   * 로그인도 안돼..
   * 메세지 호출도 안돼..
   * 이 안에 배신자가 있다
   * 이게 내 결론이다
   * 
   */
  getMessage() {
    return axios.get(`${apiUrl}/message`)
  },

  /**
   * 10. 21. 준범이
   * 이제는 메세지를 보낸다..
   * 카톡은 무너졌냐
   * 
   */
  sendMessage(param) {
    return axios.post(`${apiUrl}/message`, { param })
  },

  // 10-17 최재형
  // 로그인
  // 파라미터 : id, password
  // 리턴 : 일치하는 유저 가져오기
  getUserLogin(id, password) {
    return axios.get(`${apiUrl}/user/login`, { uid: id, password: password })
  },


  // 10-17 최재형
  // 회원가입
  // 파라미터 : 회원 obj (데이터 베이스 이름과 일치하게 넣어야 함)
  // 리턴 : 없을듯 아마도...
  getUserLogin(object) {
    return axios.post('/user/regist', { VO: object })
      .then(response => {
        console.warn(response)
      })
      .catch((ex) => {
        console.warn("ERROR!!!!! : ", ex)
      })
  },

  // 10-17 최재형
  // 사용자가 가지고 있는 방 번호 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomByUser(param) {
    return axios.get(`${apiUrl}/room/${param}`)
  },

  // 10-17 최재형
  // 방 번호에 있는 메세지 전부 가져오기
  // 파라미터 : 방 번호
  // 리턴 : 방 번호에 해당되는 리스트 (json)
  getRoomMessage(param) {
    return axios.get(`${apiUrl}/message/${param}`)
  },

  // 10-17 최재형
  // 메세지 입력
  // 파라미터 : 메세지 테이블에 있는 전 목록(날짜 빼고)
  // 리턴 : 없을듯 (아마도)
  postMessage(object) {
    return axios.post('/message', { mid: object.mid, text: object.text, uid: object.uid, rid: object.rid })
      .then(response => {
        console.warn(response)
      })
      .catch((ex) => {
        console.warn("ERROR!!!!! : ", ex)
      })
  }

}
