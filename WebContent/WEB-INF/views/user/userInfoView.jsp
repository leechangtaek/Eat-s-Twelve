<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	User u = (User)request.getAttribute("user");

  	String favorite = u.getFavorite()!=null?u.getFavorite():"";
%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="styleSheet" href="<%=request.getContextPath() %>/css/user.css" />
<script>
function updatePassword(){
	var url = "<%=request.getContextPath()%>/user/updatePassword?userId=<%=u.getUserId()%>";
	var title = "비밀번호 변경";
	var spec = "left=500px, top=300px, width=400px, height=260px";
	
	open(url, title, spec);
}

</script>
<style>
.bodyStart{padding-top:150px}
.enroll_container_wrapper{width:1188px;min-height:600px; padding-left:37px;}
.enroll_container_wrapper h2{text-align:center;}
section#enroll-container {text-align:center; border:1px solid gray; border-radius:3%; width:800px; height:480px; margin:0 auto;}
section#enroll-container input{margin:9px;}
section#enroll-container table{margin:0 auto;}
section#enroll-container table th {padding:5px 5px; text-align:right;}
section#enroll-container table td {padding:0 10px; text-align:left;}
.userInfoView_button{padding-top:30px;}

/* 회원수정페이지 */
section#enroll-container input[readonly]{
	background:lightgray;
}

</style>
<div class="naccs">
	<div class="grid">
    	<div class="gc gc--1-of-3">
        	<div class="menu">
               <div onclick="location.href='<%=request.getContextPath()%>/user/userForm?userId=<%=userLoginId%>'"><span class="light"></span><span>내정보 메인</span></div>
               <div class="active" onclick="location.href='<%=request.getContextPath()%>/user/userView?userId=<%=userLoginId%>'"><span class="light"></span><span  >내정보 보기</span></div>
               <div onclick="location.href='<%=request.getContextPath()%>/user/userCommentView?userId=<%=userLoginId%>'"><span class="light"></span><span  >작성한 글 보기</span></div>
               <div onclick="location.href='<%=request.getContextPath()%>/user/userAddFood'"><span class="light"></span><span >맛집추가하기</span></div>
             </div>
     	</div>
     	
     	
        <div class="gc gc--2-of-3">
        	<ul class="nacc">
	            <li class="active">
	            <div class="enroll_container_wrapper">
					<h2 style="color:orange">회원 정보 보기 및 수정</h2>
					<section id=enroll-container>
						<form action="<%=request.getContextPath()%>/user/userUpdateEnd" name="userUpdateFrm" method="post" onsubmit="return updateValidate();">
							<table>
							<br>
								<tr>
									<th>아이디</th>
									<td><input type="text" placeholder="4글자이상" name="userId"
										value="<%=u.getUserId()%>" id="userId_" required readonly>
									</td>
								</tr>

								<tr>
									<th>이름</th>
									<td><input type="text" name="name" id="name"
										value="<%=u.getName()%>" required> <br></td>
								</tr>
								<tr>
									<th>나이</th>
									<td><input type="number" name="age" id="age"
										value="<%=u.getAge()%>"> <br></td>
								</tr>
								<tr>
									<th>이메일</th>
									<td><input type="email" placeholder="abc@xyz.com" style="width:200px"
										name="email" value="<%=u.getEmail()%>" id="email"> <br>
									</td>
								</tr>
								<tr>
									<th>휴대폰</th>
									<td><input type="tel" placeholder="(-없이)01012345678"
										value="<%=u.getPhone()%>" name="phone" id="phone"
										maxlength="11" required> <br></td>
								</tr>
								<tr>
									<th>주소</th>
									<td><input type="text" name="address" value="<%=u.getAddress()%>"
										id="address" style="width:200px"> <br></td>
								</tr>
								<tr>
									<th>성별</th>
									<td><input type="radio" name="gender" id="gender0"
										value="M" <%="M".equals(u.getGender()) ? "checked" : ""%>>
										<label for="gender0">남</label> <input type="radio"
										name="gender" id="gender1" value="F"
										<%="F".equals(u.getGender()) ? "checked" : ""%>> <label
										for="gender1">여</label></td>
								</tr>
								<tr>
									<th>좋아하는 타입</th>
									<td>
										<input type="checkbox" name="favorite" id="favortie0" value="한식" <%=favorite.contains("한식") ? "checked" : ""%>> 
										<label for="favortie0">한식</label> 
										<input type="checkbox" name="favorite" id="favorite1" value="양식" <%=favorite.contains("양식") ? "checked" : ""%>>
							 			<label for="favorite1" >양식</label> 
										<input type="checkbox" name="favorite" id="favorite2" value="일식" <%=favorite.contains("일식") ? "checked" : ""%>> 
										<label for="favorite2">일식</label> <br /> 
										<input type="checkbox" name="favorite" id="favorite3" value="중식" <%=favorite.contains("중식") ? "checked" : ""%>> 
										<label for="favorite3" >중식</label> 
										<input type="checkbox" name="favorite" id="favorite4" value="디저트" <%=favorite.contains("디저트") ? "checked" : ""%>>
										<label for="favorite4" >디저트</label> <br />
									</td>
								</tr>
							</table>
							<div class="userInfoView_button">
								<input type="submit" value="정보수정"> 
								<input type="button" value="비밀번호 변경" onclick="updatePassword();"> 
								<input type="reset" value="초기화"> 
								<input type="button" value="탈퇴"
									onclick="location.href='<%=request.getContextPath()%>/user/delete?userId=<%=u.getUserId()%>'">
							</div>
						</form>
					</section>
				</div>
				</li>
           	</ul>
    	</div>
 	</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>