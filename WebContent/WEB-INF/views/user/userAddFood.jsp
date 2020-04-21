<%@page import="Photo.model.vo.Photo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, restaurant.model.vo.*" %>

<% List<Restaurant> rRList = (List)request.getAttribute("recentRestaurantList");
   List<Photo> rPList = (List)request.getAttribute("recentPhotoList");
   List<Restaurant> wRList = (List)request.getAttribute("wishRestaurantList");
   List<Photo> wPList = (List)request.getAttribute("wishPhotoList");
   List<Restaurant> fRList = (List)request.getAttribute("favoriteRestaurantList");
   List<Photo> fPList = (List)request.getAttribute("favoritePhotoList");


%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="styleSheet" href="<%=request.getContextPath() %>/css/user.css" />
<style>
.enroll_container_wrapper{ width:1188px; min-height:900px; padding-left:37px;}
.enroll_container_wrapper h2{text-align:center;}
section#enroll-container {text-align:center; border:1px solid gray; border-radius:3%; width:800px; height:780px; margin:0 auto;}
section#enroll-container input{margin:9px;}
section#enroll-container table{margin:0 auto;}
section#enroll-container table th {padding:10px 10px; text-align:right;}
section#enroll-container table td {padding:0 10px; text-align:left;}
.bodyStart{padding-top:150px}
/* 회원수정페이지 */
section#enroll-container input[readonly]{
	background:lightgray;
}

</style>
<script>



</script>
<div class="naccs">
	<div class="grid">
		<div class="gc gc--1-of-3">
			<div class="menu">
				<div onclick="location.href='<%=request.getContextPath()%>/user/userForm?userId=<%=userLoginId%>'">
					<span class="light"></span><span style="color: gray;">내정보 메인</span>
				</div>
				<div onclick="location.href='<%=request.getContextPath()%>/user/userView?userId=<%=userLoginId%>'">
					<span class="light"></span><span style="color: gray;">내정보 보기</span>
				</div>
				<div onclick="location.href='<%=request.getContextPath()%>/user/userCommentView?userId=<%=userLoginId%>'"><span class="light"></span><span  >작성한 글 보기</span></div>
				<div class="active" onclick="location.href='<%=request.getContextPath()%>/user/userAddFood'">
					<span class="light"></span><span>맛집추가하기</span>
				</div>
			</div>
		</div>


		<div class="gc gc--2-of-3">
			<ul class="nacc">

				<li class="active">
					<div class="enroll_container_wrapper">
						<h2 style="color: orange">맛집추가하기</h2>
						<section id=enroll-container>
							<form name="join" method="post" enctype="multipart/form-data"
								action="<%=request.getContextPath()%>/user/userInquire">
								<table>
									<tr>
									<br>
										<th  class="first_table">아이디</th>
										<td>
											<input type="text" name="userId" id="userId"
												size="20" value=<%=userLoggedIn.getUserId()%> readonly
												maxlength="12"> 
											</input>
										</td>
									</tr>

									<tr>
										<th>식당이름</th>
										<td><input type="text" name="r_name" id="r_name"
											size="21" maxlength="12"></input></td>
									</tr>
									<tr>
										<th>종류</th>
										<td>
											<input type="checkbox" name="type" id="한식" value="한식" checked="checked"> <label for="한식">한식</label>
											<input type="checkbox" name="type" id="일식" value="일식"> 
											<label for="일식">일식</label> 
											<input type="checkbox" name="type" id="중식" value="중식">
											<label for="중식">중식</label>  <br/>
											<input type="checkbox" name="type" id="양식" value="양식"> 
											<label for="양식">양식</label>  
											<input type="checkbox" name="type" id="디저트"value="디저트"> 
											<label for="중식">디저트</label> <br/>
										</td>
									<tr>
										<th>지역</th>
										<td><input type="text" name="location" id="location"
											size="21" maxlength="12"></td>
									</tr>

									<tr>
										<th>설명</td>
										<td><input type="text" name="description"
											id="description" size="25"></input></td>
											
									</tr>
									<tr>
										<th>카테고리</th>
										<td><input type="text" name="category" id="category" placeholder=",로 구분해주세요"
											size="25"></input></td>
									</tr>

									<tr>
										<th>전화번호</th>
										<td><input type="text" style="width: 170px" id="r_phone"
											name="r_phone" /></td>
									</tr>
									<tr>
										<th>주차여부</th>
										<td>
											<input type="radio" name="parking" id="Y" value="Y" checked> 
											<label for="Y">Y</label> &nbsp;&nbsp; 
											<input type="radio" name="parking" id="N" value="N">
											<label for="N">N</label> &nbsp;&nbsp;
										</td>
									<tr>
										<th>open_time</th>
										<td><input type="text" name="open" id="open" placeholder="ex)1000"></td>
									</tr>
									<tr>
										<th>close_time</th>
										<td><input type="text" name="close" id="close" placeholder="ex)2300"></td>
									</tr>
									<tr>
										<th>holiday</th>
										<td><input type="text" name="holiday" id="holiday" placeholder="ex)금,토">
										</td>
									</tr>

									<tr>
										<th>첨부파일</th>
										<td><input type="file" name="upFile1"><br /> 
										<input type="file" name="upFile2" /> <br /> 
										<input type="file" name="upFile3" /> <br /> 
										<input type="file" name="upFile4" /></td>
									</tr>
								</table>
								<br> <br>
								<p align="center">
									<input type="submit" name="submit" value="승인요청" onclick="return check_onclick();"></input>
									<input type="reset" name="reset" value="다시 입력"></input>
								</p>
							</form>
							</section>
					</div>
				</li>

			</ul>
		</div>
	</div>
</div>

<script>
function check_onclick(){
	var r_name=document.getElementById("r_name");
	if(r_name.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		r_name.focus();
		return false;
		}
	var location=document.getElementById("location");
	if(location.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		location.focus();
		return false;
		}
	
	var description=document.getElementById("description");
	if(description.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		description.focus();
		return false;
		}
	var category=document.getElementById("category");
	if(category.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		category.focus();
		return false;
		}
	var r_phone=document.getElementById("r_phone");
	if(r_phone.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		r_phone.focus();
		return false;
		}
	var open=document.getElementById("open");
	if(open.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		open.focus();
		return false;
		}
	var close=document.getElementById("close");
	if(close.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		close.focus();
		return false;
		}
	var holiday=document.getElementById("holiday");
	if(holiday.value=="")
		{
		alert("입력란을 모두 채워주세요!");
		holiday.focus();
		return false;
		}
		
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>