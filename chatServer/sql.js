const axios = require('axios');
// const apiUrl = 'http://localhost:8080/ssafyworld';
const apiUrl = 'http://13.124.121.215:8080/ssafyworld';
// const apiUrl = 'http://70.12.246.62:8080/ssafyworld';

// 10-28 최재형
// 디비에 있는 모든 방 갯수 세기
// 파라미터 : X
// 리턴 : 방 목록 전체
module.exports.getRoom=() =>{
  return axios.get(`${apiUrl}/room`).then(response=>{
    return response
  })
}

// 10-30 최재형
// 메세지 입력
// 파라미터 : 메세지 테이블에 있는 전 목록
// 리턴 : 성공() 실패
module.exports.postMessage=(object)=>{
    axios.post(`${apiUrl}/message`,{ 
      "text":object.text, 
      "uidx":object.uidx,
      "rid":object.rid,
      "time":object.time }).then(res=>{
      }).catch(error=>{
        console.log(error.status);
      })
}


module.exports.createRoom=(name)=>{
  return axios.post(`${apiUrl}/room/create`,{
                rname : name
              }).then(response=>{
                return response
              })
}
