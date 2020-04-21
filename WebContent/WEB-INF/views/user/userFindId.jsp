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
	<form name="checkIdDuplicateFrm">
		<input type="hidden" name="uid" />
	</form>
	<div id="updatePassword-container">
		<h2>아이디찾기</h2>
		<form name="join" method="post"
			action="<%=request.getContextPath()%>/user/userFindId">
			<table align="center">

				<tr>
					<th>이름</th>
					<td><input type="text" name="name" id="name" size="25"></input></td>
				</tr>

				<tr>
					<th>전화번호</th>
					<td><input type="phone" name="phone" id="phone" size="25"
						placeholder="ex)010-1234-1234" class="phone-number-check"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="아이디찾기"
						name="submit" />&nbsp; <input type="button" value="뒤로"
						onclick="login();" /></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>
