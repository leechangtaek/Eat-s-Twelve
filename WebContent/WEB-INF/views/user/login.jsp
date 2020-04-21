<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="styleSheet" href="<%=request.getContextPath() %>/css/user.css" />
    
 <%
 User userLoggedIn = (User)session.getAttribute("userLoggedIn");
	String userLoginId = "";
	if(userLoggedIn != null){
		userLoginId = userLoggedIn.getUserId();
	}
	//로그인한 경우
	System.out.println("memberLoggedIn@index.jsp="+userLoggedIn);
	
	//쿠키관련
	boolean saveId = false;
	String userId = "";
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		//System.out.println("----------------------------");
		for(Cookie c : cookies){
			String k = c.getName();
			String v = c.getValue();
			//System.out.println(k + " = " + v);
			if("saveId".equals(k)){
				saveId = true;
				userId = v;
			}
			
		}
		
		//System.out.println("----------------------------");
	}
%>
<style>
.wrap{
  padding:40px 0;
  color:#888;
  width:400px;
  font-family:'Karla';
  margin:0 auto;
  text-align:center;
}
.wrap h1{color:orange;}

input{
  font-family:'Ubuntu';
  font-weight:300;
  border:0;
  border-bottom:1px solid #ff5407;
  width:100%;
  height:36px;
}

input:focus{
  outline:none;
  box-shadow:none;
  background:#ffeae2;
}

button{
  border:0;
  background:transparent;
  padding:5px;
  margin-top:50px;
  position:relative;
  outline:0;
}

.buttonafter:after{
content:'';
display:block;
position:absolute;
top:8px;
left:100%; /*should be set to 100% */
width:0;
height:0;
border-color: transparent transparent transparent #14a03d; /*border color should be same as div div background color*/
border-style: solid;
border-width: 5px;

}

.forgot{
	color:#a0a0a0;
  float:left;
  margin-right:20px;
  cursor:pointer;
}
.forgot:hover{
	color:black;
	
}

.login{
  background:#a0a0a0;
  color:#fff;
  float:right;
  cursor:pointer;
  margin-top:20px;
}

.login.buttonafter {
    background:#14a03d;
}
</style>
    
    
<script>
$(()=>{
	$('#memberId, #password').on('input', function() {
	    if ($('#memberId').val() && $('#password').val()) {
	        $('.login').addClass('buttonafter');
	    } else {
	        $('.login').removeClass('buttonafter');
	    }
	});
});

function joinBtn(){
	
		resizeTo(1100,700);

	location.href="<%=request.getContextPath()%>/user/userEnrollForm";
};
function idFind(){
	resizeTo(600,400);
	location.href="<%=request.getContextPath()%>/user/userIdFind";
};

function passwordFind(){
	resizeTo(600,400);
	location.href="<%=request.getContextPath()%>/user/userPasswordFind";
}

<%-- 
function loginBtn(){
	location.href="<%=request.getContextPath()%>/user/login?memberId="+$memberId+"&password="+$password; 
} --%>

</script>
<div class='wrap'>
	<h1>Login</h1>

	<form action="<%=request.getContextPath()%>/user/login" id="loginFrm"
		method="post">
		<div class="box_login">
			<div class="inp_text">
				<input type="text" id="memberId" name="memberId" placeholder="ID">
			</div>
			<div class="inp_text">
				<input type="password" id="password" name="password"
					placeholder="Password">
			</div>
		</div>
		<div id="btn_group">
			<input type="submit" id="test_btn5" class='login' value="로그인">
		</div>
	</form>
			<button id="test_btn6" class='forgot' onclick="joinBtn();">회원가입</button>
			<button id="test_btn7" class='forgot' onclick="idFind();">아이디찾기</button>
			<button id="test_btn8" class='forgot' onclick="passwordFind();">비밀번호찾기</button>

</div>


