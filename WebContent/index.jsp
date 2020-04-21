<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="restaurant.model.vo.Restaurant" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!DOCTYPE html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
<script src="./js/jquery-3.4.1.min.js"></script>


    <div class="bodyStart" id="content">

		<h4 class="title_home" title="새로 뜨는 맛집">
			<span class="inner">
				새로
				<span></span>
				뜨는
				<span></span>
				맛집
			</span>
		</h4>
		<div class="module_list_restaurant_designed extend_four extend_thumbnail_tall">
			<ul class="list new">
			<!--
				<li class="item">
					<a href="#" class="area_overview">
						<div class="area_thumbnail">
							<div class="thumbnail">
								 가게이미지 
								<img src="https://shop-phinf.pstatic.net/20191216_228/1576458969360vz0NL_JPEG/13821302917716140_1024121219.jpg?type=f296_385" alt="상호명" class="image img_full_h">
							</div>
							<div class="area_title">
								<string class="title" title="상호명">가게이름</string>
								<strong class="point search_point">4.3</strong>
							</div>
						</div>
						<div class="area_category">
							<strong class="category">
								<span class="food"></span>
							</strong>
						</div>
					</a>
				</li>
				-->
			</ul>
		</div>

		<div class="module_list_bestreview">
			<h4 class="title_home" title="새로운 리뷰">
				<span class="inner">
					새로운
					<span></span>
					리뷰
				</span>
			</h4>
			<div class="layout_inner">
				<ul class="list_circle best">
					<!-- <li class="item">
						<a href="#" class="area_overview">
							<div class="thumbnail">
								<img src="" alt="" class="image img_full_h">
							</div>
							<div class="area_stylebox">
								<div class="area_text">
									<p class="text">
										<span class="ellipsis">
											댓글내용
										</span>
									</p>
								</div>
								<div class="area_reviewinfo">
									<span class="text_info">닉네임</span>
									<span class="text_info">2019.12.22</span>
								</div>
								<div class="restaurant_name">
									<span class="ellipsis" title="">가게이름</span>
								</div>
							</div>
						</a>
					</li> -->
				</ul>
			</div>
		</div>

		<h4 class="title_home" title="평점이 높은 식당">
			<span class="inner">
				평점이
				<span></span>
				높은
				<span></span>
				식당
			</span>
		</h4>
		
		<div class="list_goods">
			<div class="bx-wrapper" style="max-width: 1280px; margin: 0px auto;">
				<div class="bx-viewport" style="width: 100%; position: absoulte height: 506px">
					<ul class="list best-grade" style="width:1015%; position: relative; transition-duration: 0s; transform: translate3d(0px, 0px, 0px); margin-left: 80px;">
						<!--  <li class style="float: left; list-style: none; position: relative; width: 249px; margin-right: 18px;">
							<a href="" class="thumb_goods">
								<img src="" alt="" class="thumb">
							</a>
							<div class="info_goods">
								<span class="name">
									<a href="#" class="txt"></a>
								</span>
								<span class="grade"></span>
							</div>
						</li> -->
					</ul>
				
				</div>
			</div>
		</div>

<script>
$(()=>{
	$.ajax({
		url : "<%=request.getContextPath()%>/main/newRestaurants",
		dataType: "json",
		success : data =>{
	 			let html = "";
				console.log(data);
				
				for(let i in data){
					let n = data[i];
					
				html +=  "<li class='item' id='new''>"
					 + "<a href='<%=request.getContextPath()%>/restaurants?rName=" + n.rName +  "'class='area_overview' >"
					 + "<div class='area_thumbnail'>"
					 + "<div class='thumbnail new'>"
					 + "<img class='image img_full_h' src='<%=request.getContextPath()%>/images/" + n.imgName + "'>"
					 + "</div>"
					 + "<div class='area_title'>"
					 + "<strong class='title' title='상호명'>"+n.rName+"&nbsp</strong>"
					 + "<strong class='point search_point'>"+n.grade+"</strong></br>"
					 + "<strong class='description' >"+n.description+"</strong>"
					 + "</div>"
					 + "</div>"
					 + "<div class='area_category'>"
					 + "<strong class='category'>"
					 + "<span class='food'></span>"
					 + "</strong>"
					 + "</div>"
					 + "</a>"
					 + "</li>";
				}
				$("ul.list.new").append(html);
		},
		error : (a,b,c)=> {
			console.log(a,b,c);
		}
	});
	
	$.ajax({
		url : "<%=request.getContextPath()%>/main/bestReview",
		dataType: "json",
		success : data =>{
	 			let html = "";
				console.log(data);
				
				for(let i in data){
					let c = data[i];
				
				html +=  "<li class='item' id='best''>"
					 + "<a href='<%=request.getContextPath()%>/restaurants?rName=" + c.rName +  "'class='area_overview' >"
					 + "<div class='thumbnail'>"
					 + "<img class='image img_full_h' src='<%=request.getContextPath()%>/images/" + c.imgName + "'>"
					 + "</div>"
					 + "<div class='area_stylebox'>"
					 + "<div class='area_text'>"
					 + "<p class='text'>"
					 + "<span class='ellipsis'>"
					 + c.boarding
					 + "</span>"
					 + "</p>"
					 + "</div>"
					 + "<div class='area_reviewinfo'>"
					 + "<span class='text_info'>"+c.id+"</span>"
					 + "<span class='text_info'>"+c.bDate+"</span>"
					 + "</div>"
					 + "<div class='restaurant_name'>"
					 + "<span class='ellipsis'>"+c.rName+"</span>"
					 + "</div>"
					 + "</div>"
					 + "</a>"
					 + "</li>";
				}
				$("ul.list_circle.best").append(html);
		},
		error : (a,b,c)=> {
			console.log(a,b,c);
		}
	});
	
	$.ajax({
		url : "<%=request.getContextPath()%>/main/bestGradeRestaurants",
		dataType: "json",
		success : data =>{
	 			let html = "";
				console.log(data);
				
				for(let i in data){
					let c = data[i];
				
					html +=  "<li class style='float: left; list-style: none; position: relative; width: 249px; margin-right: 40px;''>"
						 + "<a href='<%=request.getContextPath()%>/restaurants?rName=" + c.rName +  "' class='thumb_goods'>"
						 + "<img src='<%=request.getContextPath()%>/images/" + c.imgName + "' alt='' class='thumb'></br>"
						 + "</a>"
						 + "<div class='info_goods'>"
						 + "<strong class='title' title='상호명'>"+c.rName+"&nbsp</strong>"
						 + "<strong class='point search_point'>"+c.grade+"</strong></br>"
						 + "<strong class='description' >"+c.description+"</strong>"
						 + "</div>"
						 + "</li>";
				}
				$("ul.list.best-grade").append(html);
		},
		error : (a,b,c)=> {
			console.log(a,b,c);
		}
	});
});
</script>
<style>
.bodyStart{
	padding-top:35px;
	padding-bottom:0px;
}

</style>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
