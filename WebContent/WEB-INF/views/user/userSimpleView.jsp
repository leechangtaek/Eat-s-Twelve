<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	User user = (User)request.getAttribute("user");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세보기</title>
</head>
<body>
	<table class="userInfo">
		<tr><td>아이디</td><td><%=user.getUserId() %></td></tr>
		<tr><td>나이</td><td><%=user.getAge()%> 세</td></tr>
		<tr><td>성별</td><td><%=user.getGender().equals("M")?"남자":"여자" %></td></tr>
		<tr><td>이메일 주소</td><td><%=user.getEmail() %></td></tr>
		<tr><td>사는곳</td><td><%=user.getAddress() %></td></tr>
		<tr><td>관심사</td><td><%=user.getFavorite() %></td></tr>
		<button onclick="window.close();" id="close">닫기</button>
	</table>
</body>


<style>
.userInfo{
	border-collapse:collapse;
	position:relative;
}
.userInfo tr td{
	width: 300px;
	height: 40px;
}
.userInfo tr td:first-of-type {
	width: 150px;
}
#close{
	position:absolute;
	left:393px;
	top: 266px;
}
.userInfo tr td{
	text-align: center;
	vertical-align:middle;
	border:1px solid #dee2e6;
}

</style>
</html>