<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>

function login(){
	
	resizeTo(600,450);
	location.href= "<%=request.getContextPath()%>/user/userLoginForm";

}
</script>
<style>
div#updatePassword-container table {
	margin: 0 auto;
	border-spacing: 20px;
}

div#updatePassword-container table tr:last-of-type td {
	 text-align: center;
	 padding-top:15px;
}

#updatePassword-container h2 {
	text-align: center;
	color: orange;
	padding-top: 50px;
}
</style>
<body>
	<form name="checkPasswordDuplicateFrm">
		<input type="hidden" name="password" />
	</form>


	<div id="updatePassword-container">
		<h2>비밀번호찾기</h2>
		<form name="join" method="post"
			action="<%=request.getContextPath()%>/user/userFindPassword">
			<table align="center">


				<tr>
					<td align="center"><b>아이디:</td>
					<td><input type="text" name="userId" id="userId" size="25"></input></td>
				</tr>


				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" id="email" placeholder="ex)aaa@naver.com"
					
						class="email-check"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="비밀번호찾기" name="submit" />&nbsp; 
						<input type="button" value="뒤로"	onclick="login();" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>