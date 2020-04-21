<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean isUsable = (boolean)request.getAttribute("isUsable");
	String uid = request.getParameter("uid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<style>
#checkId-container{
	text-align: center;
	padding-top: 50px;
}
span#duplicated {
	color: red;
	font-weight: bold;
}

</style>
<script>
/*
* 아이디 중복검사 함수: 팝업창
*/
function checkIdDuplicate(){
	var $uid = $("#uid");
	//유효성검사
	if($uid.val().trim().length < 4){
		alert("아이디는 4글자 이상이어야 합니다.");
		return;
	}
	
	var frm = document.checkIdDuplicateFrm;
	frm.uid.value = $uid.val().trim();
	frm.submit();
	
}
function setUserId(){
	var uid = "<%=uid%>";
	//부모창의 폼 접근
	var frm = opener.join;
	frm.uid.value = uid;
	frm.idValid.value = 1;
	
	frm.upw.focus();
	
	self.close();
}


</script>
</head>
<body>
	<div id="checkId-container">
	<% if(isUsable){ %>
		[<span><%=uid %></span>]는 사용가능합니다.
		<br /><br />
		<button type="button" onclick="setUserId();">사용</button>
	<% }
		else {
	%>	
		[<span id="duplicated"><%=uid %></span>]는 이미 사용중입니다.
		<br /><br />
		
		<form action="<%=request.getContextPath()%>/user/checkIdDuplicate"
			  name="checkIdDuplicateFrm"
			  method="post">
			<input type="text" name="uid" id="uid"
				   placeholder="아이디를 입력하세요." />
			<br /><br />
			<input type="button" value="아이디 중복 검사" 
				   onclick="checkIdDuplicate();"/>
		
		</form>
		
	
	
	<%
		}
	%>
	
	</div>
</body>
</html>









