<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatRoom</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>

let host = location.host;
let ws = new WebSocket('ws://'+host+'<%=request.getContextPath()%>/chat/helloWebSocket');

//이벤트핸드러 mapping
ws.onopen = e =>{
	console.log("websocket open");
}

ws.onmessage= e =>{
	console.log("websocket message : "+e.data);
	let o = JSON.parse(e.data);
	var tags = [];
	tags = (o.msg+"").split(/(#[^\s]+)/g);
	var changedmsg = "";                             
	console.log(tags);
                 
	for(var i=0;i<tags.length;i++) {
		
		if(tags[i].includes("#")) {
			changedmsg += "<a href='<%=request.getContextPath()%>/searchList?searchKeyword="+tags[i].substr(1)+"' target='blank'>"+tags[i]+"</a>";
		}
		else {
			changedmsg += tags[i];
		}
	}
	
	if(o.type=='dm') {
		console.log(o.sender+" : " + o.msg);
	}
	//일반 메세지
	else{
		
		if('<%=request.getParameter("userId")%>'==o.sender){
			let msg = '<li class="list-group-item" style="text-align:right;">';
			msg += "<span class='msg mine'>"+changedmsg+"</span>&nbsp;&nbsp;";
			msg += '<span class="badge badge-warning me">'+o.sender+'</span>'+'</li>';
		
			$("#msg-container ul").append(msg);
		}
		else {
			let msg = '<li class="list-group-item">';
			msg += '<span class="badge badge-warning">'+o.sender+'</span>';
			msg += "&nbsp;&nbsp;<span class='msg'>"+changedmsg+'</span></li>';
		
			$("#msg-container ul").append(msg);	
		}
		//스크롤처리
		//scrollHeight: 실제 뷰포트에 보이지 않는 전체 높이
		//scrollTop : 요소의 맨윗부분과 뷰포트에 보이는 부분의 값을 set/get,
		let $msgContainer = $("#msg-container");
		$msgContainer.scrollTop($msgContainer.prop('scrollHeight'));
		
	}
	
	
}

ws.onerror= e =>{
	console.log("websocket error : "+ e);
}

ws.onclose= e =>{
	console.log("websocket close");
}

$(()=>{
	console.log("jquery로딩 완료!");
// 	전송(send)버튼 클릭한 경우
// 	$("#msg").on("propertychange change keyup paste input", function() {
// 		var txt = $("#msg").val();
		
// 		var tags = [];
// 		tags = txt.split(/(#[^\s]+)/g);
// 		var msg = "";                             
                     
// 		for(var i=0;i<tags.length;i++) {
			
// 			if(tags[i].includes("#")) {
<%-- 				msg += "<a href='<%=request.getContextPath()%>/restaurants?rName="+tags[i].substr(1)+"'>"+tags[i]+"</a>"; --%>
// 			}
// 			else {
// 				msg += tags[i];
// 			}
// 		}
// 		$("#msg").val(msg);
//     });
	
	$("#btn-send").click(function(){
		let msg = $("#msg").val().trim();
		
		if(msg.length==0) return;
		
		var date = new Date();
		var time = date.getFullYear()+"-"+date.getMonth()+1+"-"+date.getDate();
		
		let o = {
				type: "message",
				msg: msg,
				sender: "<%=request.getParameter("userId")%>",
				time: time
		}
		
		let s = JSON.stringify(o);
		
		//전송
		ws.send(s);
		
		//초기화
		$("#msg").val('').focus();
	});
	
	$("#msg").keyup(e=>{
		if(e.key=='Enter')
			$("#btn-send").trigger('click');
	});
	
	$("#dm-client").focus(e=>{
		let $dmClient = $(e.target);
		
		//현재 접속자 정보 조회 json
		$.ajax({
			url: "<%=request.getContextPath()%>/chat/userList",
			success: data =>{
				$dmClient.html("<option value='' disabled>접속자 목록</option>");
				for(let i in data){
					if(i%2!=0) {
						$dmClient.append("<option value='"+data[i]+"'>"+data[i]+"</option>");		
					}
					else{
						$dmClient.append("<option value='"+data[i]+"' style='background-color:#e2e3e5'>"+data[i]+"</option>");		
					}
				}
			},
			error: (x,s,e)=>{
				console.log(x,s,e);
			}
			
		});	
	}); //dm-client
	
	$("#btn-dm-send").click(e=>{
		let dmMsg = $("#dm-msg").val().trim();
		let receiver = $("#dm-client option:selected").val();
		if(dmMsg.length==0||receiver=="") return;
		var date = new Date();
		var time = date.getFullYear()+"-"+date.getMonth()+1+"-"+date.getDate();
		
		var dm = {
				type: "dm",
				msg: dmMsg,
				sender: "<%=request.getParameter("userId")%>",
				receiver: receiver,
				time: time
		}
		
		$.ajax({
			url: "<%=request.getContextPath()%>/chat/sendDM",
			data: {dm: JSON.stringify(dm)},
			success: data=>{
				alert(data.result);
				$("#dm-msg").val('').focus();
			},
			erro:(x,s,e)=>{
				console.log(x,s,e);
			}
		})
		
		
	}); //btn-dm-send
	
	//아이디 클릭시 상세 정보보기
	$(".list-group").on('click','.badge',e=>{
		var userId = $(e.target).text();
		
		var url = "<%=request.getContextPath()%>/user/userSimpleView?userId="+userId;
		var title = "회원정보보기";
		var spec = "left=500px, top=200px, width=400px, height=280px";
		open(url,title,spec);
	});
	
});

</script>
<style>
span.me {
	background-color: #fff386;
}
#chat-container{
	width: 600px;
	margin: 0 auto;
	padding: 10px;
}
#msg-container{
	height: 447px;
	overflow-y: scroll;
}
.chatroom-container>h1, .chatroom-container>p{
	text-align:center;
}
.msg {
	padding:10px;
	border-radius:20px;
	border: 1px solid gray;
}
.mine {
	background-color: #fff3cd;
	border-radius:20px;
	border:none;
}
.list-group-item {
	border-bottom: none;
}
#chat-container {
	border: 1px solid #ced4da;
}
.badge:hover {
	cursor: pointer;
}
.lead{
	padding-top: 5px;
}
.jumbotron{
	padding-top:5px;
}
</style>
</head>
<body>
	<div class="jumbotron jumbotron-fluid" style="background-color:white">
  <div class="chatroom-container">
    <h1 class="display-4">혼밥 채팅방</h1>
    <p class="lead">혼밥하기 싫은 사람들을 위한 채팅방입니다.</p>
    
    <section id="chat-container">
    	<div id="msg-container">
    		<ul class="list-group list-group-flush">
			  
			</ul>
    	</div>
    	<div class="input-group mb-3">
		  <input type="text" class="form-control" id="msg" placeholder="내용을 입력하세요" aria-label="내용을 입력하세요" aria-describedby="btn-send">
			 <div class="input-group-append">
		    	<span class="input-group-text" id="btn-send">send</span>
		  	</div>
		</div>
		
		<hr style="margin:30px 0;"/>
		<!-- dm관련 시작 -->
		
		<div id="dm-container" class="input-group mb-3">
		  <div class="input-group-prepend">
		    <label class="input-group-text" for="dm-client">개인쪽지</label>
		  </div>
		  <select class="custom-select" id="dm-client">
		    
		  </select>
		</div>
		
		<div class="input-group mb-3">
		  <input type="text" class="form-control" id="dm-msg" placeholder="보내실 쪽지 내용을 입력하세요" aria-label="보내실 쪽지 내용을 입력하세요" aria-describedby="btn-send">
			 <div class="input-group-append">
		    	<span class="input-group-text" id="btn-dm-send">쪽지 보내기</span>
		  	</div>
		</div>
		
    </section>
  </div>
</div>
</body>
</html>