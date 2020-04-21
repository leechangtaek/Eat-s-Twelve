<%@page import="restaurant.model.vo.Inquire"%>
<%@page import="Photo.model.vo.Upload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
List<Upload> upload = (List<Upload>)request.getAttribute("upload");
String r_name=request.getAttribute("r_name").toString();
Inquire inq=(Inquire)request.getAttribute("inq");
for(Upload u:upload){
	System.out.println("         ddddddddddddddd="+u.getOriginalFileName());
}

%>


<!DOCTYPE html>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/frame.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/boardView.css" />
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4d9befe9bbff1160082ea828e900ce9c"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer"></script>
<script type="text/javascript"
	src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet"
	href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

</div>
<body class="ng-scope">
	<main class="pg-restaurant ng-scope">
	<article class="contents">
		<!-- 레스토랑 상세 이미지 슬라이드 -->
		<aside class="restaurant-photos">
			<div class="list-photo_wrap owl-carousel owl-theme"
				style="opacity: 1; display: block;">
				<div class="owl-wrapper-outer">
					<div class="owl-wrapper"
						style="width: 3000px; left: 0px; display: block; transition: all 0ms ease 0s; transform: translate3d(0px, 0px, 0px);">

						<%for(Upload u:upload){ %>
						<div class="owl-item" style="width: 300px;">
							<figure class="list-photo">
								<figure class="restaurant-photos-item">
									<img
										src='<%=request.getContextPath()%>/images/<%=u.getReNamedFileName()%>'
										alt="" class="center-croping"
										style="object-fit: cover; width: 300px; height: 340px;">
									<div class="last_image">
										<p class="txt">사진 더보기</p>
										<span class="arrow-white"></span>
									</div>
								</figure>
							</figure>
						</div>
						<% } %>

					</div>
				</div>
			</div>
			<div class="owl-controls clickable" style="display: block;">
				<div class="owl-buttons">
					<div class="owl-prev">
						<button class="btn-nav prev" style="display: none;"></button>
					</div>
					<div class="owl-next">
						<button class="btn-nav next"></button>
					</div>
				</div>
			</div>
			</div>
		</aside>

		<!-- 레스토랑 상세 정보 -->
		<div class="column-wrapper">

			<!-- 컨텐츠 영역 -->
			<div class="column-contents">
				<div class="inner">
					<!-- 레스토랑 상세 -->
					<section class="restaurant-detail">
						<header>
							<div class="restaurant_title_wrap">
								<span class="title">
									<h1 class="restaurant_name" name="rName"><%=r_name %></h1> <strong
									class="rate-point"> <span>0.0</span>
								</strong>
									<p class="branch"></p>
								</span>
								<div class="restaurant_action_button_wrap">
									<div class="wannago_wrap">
										<!-- <button class="btn-type-icon favorite wannago_btn " data-restaurant_uuid="268918" data-action_id=""></button> -->
										<div class="anim-icon anim-icon-lg star">
											<input type="checkbox" name="" id="star"> <label
												for="star"></label>
											<script>
                                                function demo() {
                                                /***
                                                    Add your demo script here...
                                                    In this demo, click my button after 1000 milliseconds 
                                                    You have approx three seconds to show off your stuff.
                                                ***/

                                                demointerval = setInterval(function() {
                                                    var chkbox = document.querySelector('input[type="checkbox"]:not([checked])');
                                                    if (chkbox) {
                                                    chkbox.click();
                                                    chkbox.setAttribute('checked', 'checked');
                                                    } else {
                                                    clearInterval(demointerval);
                                                    }
                                                }, 600);
                                                }
                                                // Run demo if in searchresult preview frame
                                                // https://codepen.io/jesperkc/post/trigger-animation-in-preview-frame
                                                if (document.location.pathname.indexOf('fullcpgrid') > -1) {
                                                demo();
                                                }
                                                </script>
										</div>
										<img src='<%=request.getContextPath()%>/images/recent_star.png' class="favorite_star" />
										<p class="wannago_txt">가고싶다</p>
									</div>
								</div>
							</div>

							<div
								class="status
                                branch_none
                                ">
								<span class="cnt review">10</span> <span class="cnt favorite">413</span>
							</div>
						</header>

						<!-- 상세 정보 -->
						<table class="info no_menu">
							<caption>레스토랑 상세 정보</caption>

							<tbody>
								<tr class="only-desktop">
									<th>주소</th>
									<td><%=inq.getLocation()%></td>
								</tr>

								<tr class="only-desktop">
									<th>전화번호</th>
									<td><%=inq.getrPhone() %></td>
								</tr>

								<tr>
									<th>음식 종류</th>
									<td><span><%=inq.getCategory()%></span></td>
								</tr>

								<tr>
									<th>가격대</th>
									<td>만원-2만원</td>
								</tr>

								<tr>
									<th>주차</th>
									<td><%=inq.getParkingYN() %></td>
								</tr>

								<tr>
									<th style="vertical-align: top;">영업시간</th>
									<td><%=inq.getOpenTime()%> - <%=inq.getCloseTime() %></td>
								</tr>
								<%if(inq.getHoliday()!=null) {%>
								<tr>
									<th>휴일</th>
									<td><%=inq.getHoliday() %></td>
								</tr>
								<%} %>
							</tbody>
						</table>
						<div id="reviewListFocusId"></div>
					</section>
					<section class="restaurant_introduce_section">
						<div class="RestaurantIntroduceSection">
							<section
								class="RestaurantIntroduceSection__Section RestaurantOwnerSection">
								<h3 class="RestaurantIntroduceSection__SectionTitle">식당소개</h3>
								<div class="RestaurantIntroduceSection__SectionContent">
									<p class="RestaurantOwnerSection__Description">
										<%=inq.getDescription() %>
									</p>
								</div>
							</section>
						</div>
					</section>
				</div>
			</div>
		</div>
	</article>
			<div class='chk'>
				<input class='chk-btn' onclick="location.href='<%=request.getContextPath()%>/admin/accept?r_name=<%=r_name%>'"
					type="button" value="승인"> 
				<input class='chk-btn' onclick="location.href='<%=request.getContextPath()%>/admin/refuse?r_name=<%=r_name%>'"
					type="button" value="거절">
			</div>
	</main>
</body>

<style>
.bodyStart {
	display: none;
}
.chk{
	text-align:center;
}
.chk-btn{
	margin: 40px 10px;
    padding: 5px;
    width: 100px;
    cursor: pointer;
}
.favorite_star{
  	width:50px;
  	position:absolute;
  	left:742px;
  	top:18px;
}
</style>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>