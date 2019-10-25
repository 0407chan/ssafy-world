const apiUrl = 'http://70.12.246.62:8080/ssafyworld';
const api = require('./sql.js');

var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server,{
  pingTimeout: 1000,
});

let ipArray = new Set()
let ipMap = new Map()

app.all('/*', function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

// localhost:3000서버에 접속하면 클라이언트로 메세지을 전송한다
app.get('/', function(req, res) {
  res.sendFile('Hellow Chating App Server');
});

async()=>{
  let data = await axios.get(`${apiUrl}/chat/1`)
  console.log("test"+data.mid)
}
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

  //현재 접속자 확인
  console.log(ipMap);
  
  // 클라이언트로부터의 메시지가 수신되면
  socket.on('chat', function(data) {
    console.log('Message from %s: %s', data.name, data.msg);
    var msg = {
      from: {
        name: data.name,
      },
      msg: data.msg
    };

    // 메시지를 전송한 클라이언트를 제외한 모든 클라이언트에게 메시지를 전송한다
    socket.broadcast.emit('chat', msg);
  });

  socket.on('disconnect', function() {
    console.log('user disconnected: ' + socket.handshake.address);
    ipArray.delete(socket.handshake.address)
    ipMap.delete(socket.handshake.address)
  });
});

server.listen(3000);