<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String userId = request.getParameter("userId");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
<style>
    div#updatePassword-container table {
        margin:0 auto;
        border-spacing: 20px;
    }
    div#updatePassword-container table tr:last-of-type td {
        text-align:center;
    }
    #updatePassword-container h2 {
	text-align: center;
	color: orange;
	padding-top: 0px;
}
</style>
<script>
	
function password_validate(){
    var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식

        
        var pw = document.getElementById("password");
	var pwd_new = $("#password").val().trim();
	var pwd_chk = $("#password_Chk").val().trim();
	
	
	if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
	    return false;
	}
	if(pwd_new!=pwd_chk){
		alert("입력한 비밀번호가 일치하지 않습니다.")
		$("#password").select();
		return false;
	}

	
}
function check(re, what, message) {
    if(re.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    return false;
}


</script>
</head>
<body>
<div id="updatePassword-container">
		<h2>새로운 비밀번호 설정</h2>
		<form name="updatePwdFrm" onsubmit="return password_validate();" action="<%=request.getContextPath()%>/user/userFindPasswordUpdate" method="post" >
			<table>
				<input type="hidden" name="userId" id="userId" value="<%=userId %>">
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="password" id="password" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="password_Chk" required><br>
					</td>
				</tr>
				<tr>
				<td colspan='2'>
			<input type="submit" value="변경" name="submit" /> 
			</td>
			</tr>
			</table>
		</form>
	</div>
</body>
</html>