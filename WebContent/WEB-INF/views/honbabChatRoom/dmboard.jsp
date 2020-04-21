<%@page import="chatroom.model.vo.DM"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	List<DM> list = (List)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	String userId = (String)request.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 쪽지함</title>
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
</head>
<body>


<section id="board-container">
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>쪽지 내용</th>
			<th>보낸이</th>
			<th>보낸 날짜</th>
		</tr>
		<%
		if(list!=null) {
			for(int i=0;i<list.size();i++) {
				DM dm = list.get(i);
				if(dm.getCheck_yn().equals("N")) {
		%>
		<tr class="no-read" value="0" id="<%=dm.getDmNo()%>">
			<td><%= i+1 %></td>
			<td style='width:300px'>
				<%=dm.getMsg() %> 
			</td>
			<td><%=dm.getSender() %></td>
			<td><%=dm.getTime()%></td>
		</tr>		
		<%
			} else {
		%>
		<tr class="read" value="0" id="<%=dm.getDmNo()%>">
			<td><%= i+1 %></td>
			<td style='width:300px'>
				<%=dm.getMsg() %> 
			</td>
			<td><%=dm.getSender() %></td>
			<td><%=dm.getTime()%></td>
		</tr>
		
		<%
			}
				}
		} else {
		%>
		<tr>
			<td colspan="4">조회된 게시물이 없습니다.</td>
		</tr>
		<%
		}
		%>
	</table>

	<div id='pageBar'><%=pageBar %></div>

<style>
#board-container {
	width: 520px;
}
.no-read {
	font-weight:bold;
}
#tbl-board {
	border-collapse:collapse;
	text-align:center;
}
#tbl-board tr td {
	border : 1px solid #ced4da;
	cursor : pointer;
}
#tbl-board tr:not(:first-of-type):hover{
	background-color:#ced4da;
	color:white;
}
#tbl-board tr th {
	border : 1px solid #ced4da;
}
#pageBar {
	width:520px;
	text-align:center;
	padding-top:20px;
}
.msg{
	height:300px;
}
</style>

<script>
	$("table tr:not(:first-of-type)").click(function(){
		$(this).removeClass('no-read');
// 		console.log($(this).attr('id'));
		if($(this).val()==0) {
			
			var sender = $(this).children("td").eq(2).text();
			var msg = $(this).children("td").eq(1).text();
			
			html = "";
			html += "<tr class='boardDetail'><td colspan='2' style='font-weight:bold'>보낸사람</td><td colspan='2'>"+sender+"</td></tr>";
			html += "<tr class='boardDetail msg'><td colspan='4'>"+msg+"</td></tr>";
			html += "<tr class='boardDetail'><td colspan='4'><button onclick='openSendDM(&#39;"+sender+"&#39;)'>답장보내기</button></td></tr>";
			$(this).after(html);
			$(this).val('1');
		} else {
			$(".boardDetail").remove();
			$(this).val('0');
		}
		
		var dmNo = $(this).children('td').parents().attr('id');
		var param = {dmNo:dmNo};
		console.log(param);
		$.ajax({
			url: "<%=request.getContextPath()%>/chat/dmRead",
		data : param,
		dataType:"json",
		success: data=>{
			console.log(data);
		},
		error: (a,b,c)=>{
			
		}
		
	});
	
})
	

function openSendDM(sender) {
	var userId = "<%=userId%>";
	var receiver = sender;
	
	var url = "<%=request.getContextPath()%>/chat/openSendDM?userId="+userId+"&receiver="+receiver;
	var title = "쪽지보내기";
	var spec = "left=500px, top=200px, width=570px, height=545px";
	open(url,title,spec);
	
}

</script>


</body>
</html>