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
.bodyStart{padding-top:150px}
.food_list_image .favorite_star{
	width:50px;
	z-index:50;
	display:inline;
	position:absolute;
}
</style>
<script>


</script>
<div class="naccs">
	<div class="grid">
    	<div class="gc gc--1-of-3">
        	<div class="menu">
               <div class="active" onclick="location.href='<%=request.getContextPath()%>/user/userForm?userId=<%=userLoginId%>'"><span class="light"></span><span>내정보 메인</span></div>
               <div onclick="location.href='<%=request.getContextPath()%>/user/userView?userId=<%=userLoginId%>'"><span class="light"></span><span  >내정보 보기</span></div>
               <div onclick="location.href='<%=request.getContextPath()%>/user/userCommentView?userId=<%=userLoginId%>'"><span class="light"></span><span  >작성한 글 보기</span></div>
               <div onclick="location.href='<%=request.getContextPath()%>/user/userAddFood'"><span class="light"></span><span >맛집추가하기</span></div>
             </div>
     	</div>
     	
     	
        <div class="gc gc--2-of-3">
        	<ul class="nacc">
	            <li class="active">	           		
	                <div class="mypage_main_food_list">
	                
	                	    <div class="mypage_main_title">최근 본 맛집 리스트 </div>	    	                		                		
	                		<%
							if(rPList!=null){
								for(int i=0; i<rPList.size(); i++){
								Photo p = rPList.get(i);
								Restaurant r = rRList.get(i);
							%>
	                			<div class="food_list_image">
	                				<a href="<%=request.getContextPath()%>/restaurants?rName=<%=r.getrName()%>"><img src="<%=request.getContextPath()%>/images/<%=p.getImgName()%>" style="height:200px; width:260px;"/></a></br>
	                				<div class="food_list_name"><%=r.getrName()%></div>
	                				<div class="food_list_type"><%=r.getType() %></div>
	                				<div class="food_list_grade"><%=r.getGrade() %></div>
	                				<div class="food_list_location"><%=r.getLocation()%></div>
	                				         			
	                			</div>
	                			              			
	                			
	                		<%	}
							} %>
							<hr>
							<div class="mypage_main_title">찜한 맛집 리스트 </div>	    	                		                		
	                		<%
							if(wPList!=null){
								for(int i=0; i<wPList.size(); i++){
								Photo p = wPList.get(i);
								Restaurant r = wRList.get(i);
							%>
	                			<div class="food_list_image">
	                				<a href="<%=request.getContextPath()%>/user/userNoneFavorite?rName=<%=r.getrName()%>"><img src='<%=request.getContextPath()%>/images/recent_starfull.png'  class="favorite_star"/></a>
	                				<a href="<%=request.getContextPath()%>/restaurants?rName=<%=r.getrName()%>&check_yn=Y"><img src="<%=request.getContextPath()%>/images/<%=p.getImgName()%>" style="height:200px; width:260px;"/></a></br>
	                				<div class="food_list_name"><%=r.getrName()%></div>
	                				<div class="food_list_type"><%=r.getType() %></div>
	                				<div class="food_list_grade"><%=r.getGrade() %></div>
	                				<div class="food_list_location"><%=r.getLocation()%></div>	                				         			
	                			</div>	                			              				                			
	                		<%	}
							} %>
							<hr>
							<div class="mypage_main_title">좋아하는 타입 리스트 </div>	    	                		                		
	                		<%
							if(fPList!=null){
								for(int i=0; i<fPList.size(); i++){
								Photo p = fPList.get(i);
								Restaurant r = fRList.get(i);
							%>
	                			<div class="food_type_image">
	                				<a href="<%=request.getContextPath()%>/searchList?searchKeyword=<%=r.getType()%>"><img src="<%=request.getContextPath()%>/images/<%=p.getImgName()%>" style="height:150px; width:200px;"/></a></br>	                				
	                				<div class="food_list_type2"><%=r.getType() %></div>
	                					                				         			
	                			</div>	                			              				                			
	                		<%	}
							} %>								                		                		                		                		               	                 
	                </div>
	               
	               </li>
               
           	</ul>
    	</div>
 	</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>