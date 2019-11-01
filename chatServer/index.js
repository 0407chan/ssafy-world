const api = require('./sql.js');

var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server,{
  pingTimeout: 1000,
});

let ipArray = new Set()
let ipMap = new Map()
let room =[];
let message = []


api.getRoom().then(res=>{
  room=res.data;
  console.log(room);
})

function timeType(data){
  let time = new Date(data.time)
  console.log(data.time.toString());
  time.setHours
  console.log(time);
  console.log(time.toString);
}

app.all('/*', function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

// localhost:3000서버에 접속하면 클라이언트로 메세지을 전송한다
app.get('/', function(req, res) {
  res.sendFile('Hellow Chating App Server');
});

io.on('connection', function(socket){

  
  if(ipArray.has(socket.handshake.address)){
    console.log("already connected");
    socket.emit('check', {
      msg: "already connect"
    });
    console.log("send msg")
    
  }else{
    console.log("connected");
    ipArray.add(socket.handshake.address)
    ipMap.set(socket.handshake.address,socket.id)
  }
  
  // 클라이언트로부터 2번째 클라이언트 수신
  socket.on('change',(data)=>{
    if(data.msg==='yes'){
      //기존에 있는 것을 끊어야 함
      let tmpid = ipMap.get(socket.handshake.address)
      ipMap.delete(socket.handshake.address)
      console.log(tmpid);
      io.to(tmpid).emit('disc');
      ipArray.add(socket.handshake.address)
      ipMap.set(socket.handshake.address,socket.id)
      console.log(ipMap);
    }else{
      //지금 접속한 것을 끊어야 함
      socket.disconnect();
      ipArray.add(socket.handshake.address)
      ipMap.set(socket.handshake.address,socket.id)
      console.log(ipMap);
    }
  })

  //1031 최재형
  // 방 생성 요청
  // 파라미터 : name
  // 해야 할 것 :  방에 해당되는 소켓을 뚫어줍니다. 
  //              그리고 룸 리스트에 추가해 줍니다.
  socket.on('create',(res)=>{
    room.push({
      rid : res.rid,
      rname : res.rname
    })
      socket.on('/chatroom/'+res.rid, function(data) {
        console.log('Message from %s: %s', data.name, data.msg);
        var msg = {
          from: {
            name: data.name,
          },
          msg: data.msg
        };

        console.log(res.rid + "/"+ data);
        
  
        message.push({
          text:data.msg, 
          uid:data.name,
          rid:res.rid
        })

        // 메시지를 전송한 클라이언트를 제외한 모든 클라이언트에게 메시지를 전송한다
        socket.broadcast.emit('/chatroom/'+res.rid, msg);
      })  
      console.log('roomInfo : '+room);
      
  })

  //현재 접속자 확인
  console.log(ipMap);

  
  // 클라이언트로부터의 메시지가 수신되면
  socket.on('/chatroom', function(data) {
    var msg = {
      from: {
        name: data.name,
      },
      msg: data.msg,
      time : data.time
    };
    console.log("0,"+msg);
    
    // 메시지를 전송한 클라이언트를 제외한 모든 클라이언트에게 메시지를 전송한다
    socket.broadcast.emit('/chatroom', msg);
    
    // api.postMessage({
    //   text : data.msg, 
    //   uid : data.name,
    //   rid : 1,
    //   time : data.time
    // })
  })  

  for(let i =0;i<room.length;i++){
    socket.on('/chatroom/'+room[i].rid, function(data) {
      var msg = {
        from: {
          name: data.name,
        },
        msg: data.msg,
        time : data.time
      };
      
      // 메시지를 전송한 클라이언트를 제외한 모든 클라이언트에게 메시지를 전송한다
      socket.broadcast.emit('/chatroom/'+room[i].rid, msg);

      console.log(room[i].rid+","+msg);
      
      api.postMessage({
        text:data.msg, 
        uid:data.name,
        rid:room[i].rid,
        time : data.time
      })
    });
  }

  socket.on('disconnect', function() {
    console.log('user disconnected: ' + socket.handshake.address);
    ipArray.delete(socket.handshake.address)
    ipMap.delete(socket.handshake.address)
  });
});

server.listen(3000);