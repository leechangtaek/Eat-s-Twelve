<%@page import="comment.model.vo.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Comment> comment=(List)request.getAttribute("comment");
%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="styleSheet" href="<%=request.getContextPath() %>/css/user.css" />

<style>
.enroll_container_wrapper{ width:1188px;min-height:600px; padding-left:37px;}
.enroll_container_wrapper h2{text-align:center;}
section#enroll-container {text-align:center; padding-top:50px;width:1100px; height:400px; margin:0 auto;}
section#enroll-container input{margin:9px;}
section#enroll-container table{margin:0 auto;  border-collapse:collapse;}
section#enroll-container table th {padding:10px 30px; text-align:center; background-color:#ccc; border-bottom:1px solid; }
section#enroll-container table td {padding:7px 3px; text-align:center; border-bottom:1px solid #ccc; }
.userInfoView_button{padding-top:11px;}

/* 회원수정페이지 */
section#enroll-container input[readonly]{
	background:lightgray;
}
.bodyStart{padding-top:150px}
</style>

<!-- 내정보 보기 -->
<div class="naccs">
	<div class="grid">
		<div class="gc gc--1-of-3">
			<div class="menu">
				<div
					onclick="location.href='<%=request.getContextPath()%>/user/userForm?userId=<%=userLoginId%>'">
					<span class="light"></span><span>내정보 메인</span>
				</div>
				<div
					onclick="location.href='<%=request.getContextPath()%>/user/userView?userId=<%=userLoginId%>'">
					<span class="light"></span><span>내정보 보기</span>
				</div>
				<div class="active">
					<span class="light"></span><span>작성한 글 보기</span>
				</div>
				<div
					onclick="location.href='<%=request.getContextPath()%>/user/userAddFood'">
					<span class="light"></span><span>맛집추가하기</span>
				</div>
			</div>
		</div>


		<div class="gc gc--2-of-3">
			<ul class="nacc">
				<li class="active">
					<div class="enroll_container_wrapper">
						<h2 style="color: orange">작성한 글 보기</h2>
						<section id=enroll-container>

						
							<table class="admin_user_info_table">
								<tr>
									<th>번호</th>
									<th>음식점명</th>
									<th>맛집점수</th>
									<th>댓글내용</th>
									<th>날짜</th>
								</tr>
								<%
									for (int i = 0; i < comment.size(); i++) {
										Comment c = comment.get(i);
								%>
								<tr>
									<td><%=i + 1%></td>
									<td><a href="<%=request.getContextPath()%>/restaurants?rName=<%=c.getrName()%>" style="color:black;"/><%=c.getrName()%></a></td>
									<td><%=c.getGrade()%>점</td>
									<td><%=c.getBoarding()%></td>
									<td><%=c.getbDate()%></td>
								</tr>
								<%
									}
								%>
							</table>
					</div>

					</section>
		</div>
		</li>
		</ul>
	</div>
</div>
</div>




<%@ include file="/WEB-INF/views/common/footer.jsp" %>