<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String userId = request.getParameter("userId");
	String receiver = request.getParameter("receiver");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 보내기</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
</head>
<body>
	 <form action="<%=request.getContextPath()%>/chat/dmboard/sendDM" method="post" name="dm" >
	 <table style="text-align:center;">
        <tr><td>받는 사람</td><td><%=receiver %></td></tr>
        <tr><td colspan='2'><textarea name="msg" id="msg" cols="70" rows="10"></textarea></td></tr>
        <tr><td colspan='2'><input type="submit" onclick="return checkmsg();" value="보내기"/>&nbsp;&nbsp;&nbsp;<button onclick="window.close();">닫기</button></td></tr>
    </table>
    <input type="hidden" name="userId" value="<%=userId %>" />
    <input type="hidden" name="receiver" value="<%=receiver%>" />
   	</form>
<style>
table {
	border-collapse:collapse;
	text-align:center;
}
table tr td {
	border : 1px solid #ced4da;
}
table tr th {
	border : 1px solid #ced4da;
}
table {
	width:520px;
	text-align:center;
}
</style>
<script>
function checkmsg(){
	if($("#msg").val().trim().length==0){
		alert("내용을 입력해주세요.");
		return false;
	}
	return true;
}
</script>
</body>
</html>