<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.vo.Board, java.util.List" %>
<%@ page import="Photo.model.vo.Photo" %>
<%@ page import="restaurant.model.vo.RestaurantMap" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Restaurant r = (Restaurant)request.getAttribute("restaurant");
	List<Photo> pList = (List<Photo>)request.getAttribute("photo");
	List<Photo> nearPhotos = (List<Photo>)request.getAttribute("nearPhotos");
	List<Restaurant> rList = (List<Restaurant>)request.getAttribute("rList");
	String check_yn = (String)request.getParameter("check_yn");
	String openTime = "";
	String closeTime = "";
	
	if(r.getOpenTime()<1000) {
		String openTime_ = r.getOpenTime()+"";
		openTime = "0"+openTime_.substring(0, 1) +":"+openTime_.substring(1);
	}
	else {
		String openTime_ = r.getOpenTime()+"";
		openTime = openTime_.substring(0, 2) +":"+openTime_.substring(2);
	}
	if(r.getCloseTime()<1000) {
		String closeTime_ = r.getCloseTime()+"";
		closeTime = "0"+closeTime_.substring(0, 1) +":"+closeTime_.substring(1);
	}
	else {
		String closeTime_ = r.getCloseTime()+"";
		closeTime = closeTime_.substring(0, 2) +":"+closeTime_.substring(2);
	}
	
	
	RestaurantMap rMap = (RestaurantMap)request.getAttribute("restaurantMap");
	
	String lat ="";
	String lng ="";
	if(rMap!=null){
		lat = rMap.getLat();
		lng = rMap.getLng();
	}
	String thisRname = r.getrName();
%>


<!DOCTYPE html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boardView.css" />
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9db50509719fcbf1c091e1369e6fcf77"></script>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'/>
</div>
<body class="ng-scope">
    <main class="pg-restaurant ng-scope">
        <article class="contents">
            <!-- 레스토랑 상세 이미지 슬라이드 -->
            <aside class="restaurant-photos">
                <div class="list-photo_wrap owl-carousel owl-theme" style="opacity: 1; display: block;">
                    <div class="owl-wrapper-outer">
                        <div class="owl-wrapper" style="width: 3000px; left: 0px; display: block; transition: all 0ms ease 0s; transform: translate3d(0px, 0px, 0px);">

                            <% for(int i=0; i<pList.size(); i++){ System.out.println(pList.get(i).getImgName()); %>
                            <div class="owl-item" style="width: 300px;">                            
                                <figure class="list-photo">
                                    <figure class="restaurant-photos-item">
                                        <img src="<%=request.getContextPath() %>/images/<%=pList.get(i).getImgName() %>" alt="" class="center-croping" style="object-fit: cover; width: 300px; height: 340px;">
                                        <div class="last_image">
                                            <p class="txt">
                                                    
                                            사진 더보기
                    
                                            </p>
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
            
<script>
   $('.owl-wrapper').slick({
   	  dots: false,
   	  slidesToShow: 4,
   	  slidesToScroll: 1,
   	  touchMove: false,
   	  infinite: true
   	});
          		
</script>
<style>
    div.slick-slide{
    	width:300px !important;
    }
    div.slick-list {
    	width: 1440px !important;
    	
    }
    div.owl-wrapper.slick-initialized.slick-slider{
    	height: 340px !important;
    }
    div.slick-list.draggable{
   		height: 340px !important;
   	}
   	div.slick-track{
   		height: 340px !important;
   	}
</style>            
            
            
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
                                            <h1 class="restaurant_name" name="rName"><%=r.getrName() %></h1>
                                        <strong class="rate-point">
                                            <span><%=r.getGrade() %></span>
                                        </strong>
                                        <p class="branch"></p>
                                    </span>
                                    <div class="restaurant_action_button_wrap">
                                        <div class="wannago_wrap">
                                            <!-- <button class="btn-type-icon favorite wannago_btn " data-restaurant_uuid="268918" data-action_id=""></button> -->
                                            <div class="anim-icon anim-icon-lg star">
                                                <input type="checkbox" name="" id="star">
                                                <label for="star"></label>
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
										<%if(userLoggedIn ==null){ %>
                                            <span></span><a href="#" onclick="noneLogin();">
                                            <img src='<%=request.getContextPath()%>/images/recent_star.png'  class="favorite_star"/>
                                            </a></span>
                                            <%} else{%>
	                                            <%if(check_yn != null && check_yn.equals("Y")){ %>
	                                            <span></span><a href="<%=request.getContextPath()%>/user/userNoneFavorite?rName=<%=r.getrName()%>">
	                                            <img src='<%=request.getContextPath()%>/images/recent_starfull.png'  class="favorite_star"/>
	                                            </a></span>
	                                            <%}else{ %>
	                                            <span>
	                                            <a href="<%=request.getContextPath()%>/user/userFavorite?rName=<%=r.getrName()%>">
													<img src='<%=request.getContextPath()%>/images/recent_star.png' class="favorite_star" />
												</a></span>
												<%}
                                           	 }
	                                            %>
                                            <p class="wannago_txt">가고싶다</p>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="status
                                branch_none
                                ">
                                <span class="cnt review">10</span>
                                <span class="cnt favorite">413</span>
                              </div>
                            </header>

                                <!-- 상세 정보 -->
                                <table class="info no_menu">
                                <caption>레스토랑 상세 정보</caption>

                                <tbody>
                                <tr class="only-desktop">
                                    <th>주소</th>
                                    <td><%=r.getLocation() %></td>
                                </tr>

                                <tr class="only-desktop">
                                    <th>전화번호</th>
                                    <td><%=r.getrPhone() %></td>
                                </tr>

                                <tr>
                                    <th>음식 종류</th>
                                    <td>
                                    <span><%=r.getCategory() %></span>
                                    </td>
                                </tr>

                                <tr>
                                    <th>가격대</th>
                                    <td>만원-2만원</td>
                                </tr>

                                <tr>
                                    <th>주차</th>
                                    <td><%=r.getParkingYN() %></td>
                                </tr>

                                <tr>
                                    <th style="vertical-align:top;">영업시간</th>
                                    <td><%=openTime %> - <%=closeTime %></td>
                                </tr>
								<%if(r.getHoliday()==null||r.getHoliday().equals("없음")) {
								}else{%> 
                                <tr>
                                    <th>휴일</th>
                                    <td><%=r.getHoliday() %></td>
                                </tr>
								<%} %>
                                </tbody>
                            </table>
                            <div id="reviewListFocusId"></div>
                        </section>
                        <section class="restaurant_introduce_section">
                            <div class="RestaurantIntroduceSection">
                                <section class="RestaurantIntroduceSection__Section RestaurantOwnerSection">
                                    <h3 class="RestaurantIntroduceSection__SectionTitle">식당소개</h3>
                                    <div class="RestaurantIntroduceSection__SectionContent">
                                        <p class="RestaurantOwnerSection__Description">
                                            <%=r.getDescription() %>
                                        </p>
                                    </div>
									<br /><br />
								<section id="comment-container" class="comment-container">
									<form
										name = "commentForm" 
										action="<%=request.getContextPath()%>/comment/commentFormEnd"
										method="POST" enctype="multipart/form-data">


										<!-- 별점 댓글 틀 -->
										<div class="starcontent-container">
											<!-- 별점 부분 -->
											<div class="star-rating">
												<input id="star-5x" type="radio" name="grade" value="5" />
												<label for="star-5x" title="5 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="star-4x" type="radio" name="grade" value="4" />
												<label for="star-4x" title="4 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="star-3x" type="radio" name="grade" value="3"
													checked /> <label for="star-3x" title="3 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="star-2x" type="radio" name="grade" value="2" />
												<label for="star-2x" title="2 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="star-1x" type="radio" name="grade" value="1" />
												<label for="star-1x" title="1 star"> <i
													class="fa fa-star"></i>
												</label>
											</div>
										</div>
										<!-- 이미지 첨부 부분 -->
										<div class="img-container">
											<input type="file" name="upFile" id="upFile"
												onchange="loadImg(this);" /></br>

											<div id="img-container">
												<img id="img-viewer" width="100%" height="100%" />
											</div>
																						<!-- 댓글 부분 -->
											<div class="comment-editor">

												<textarea name="boarding" id="boarding" cols="80" rows="11"
													placeholder="맛집의 후기를 남겨주세요!"></textarea>
												<input type="submit" id="btn-insert"  value="등록" onclick="return insertComment();"/>
												<input type="hidden" name="rName" value="<%=r.getrName() %>" />
												<input type="hidden" name="location" value="<%=r.getLocation() %>" />
											</div>
										</div>
									</form>
								</section></br></br>
								<div id="photo-container" ></div>
								
								<section  class="comment-container" id="comment-update-container">
									<form
										name = "commentUpdateForm" 
										action="<%=request.getContextPath()%>/comment/commentUpdateEnd"
										method="POST" enctype="multipart/form-data">


										<!-- 별점 댓글 틀 -->
										<div class="starcontent-container" >
											<!-- 별점 부분 -->
											<div class="star-rating">
												<input id="update-star-5x" type="radio" name="updategrade" value="5" />
												<label for="update-star-5x" title="5 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="update-star-4x" type="radio" name="updategrade" value="4" />
												<label for="update-star-4x" title="4 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="update-star-3x" type="radio" name="updategrade" value="3"
													checked /> <label for="update-star-3x" title="3 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="update-star-2x" type="radio" name="updategrade" value="2" />
												<label for="update-star-2x" title="2 stars"> <i
													class="fa fa-star"></i>
												</label> <input id="update-star-1x" type="radio" name="updategrade" value="1" />
												<label for="update-star-1x" title="1 star"> <i
													class="fa fa-star"></i>
												</label>
											</div>
										</div>
										<!-- 이미지 첨부 부분 -->
										<div class="img-container">
											<input type="file" name="update-upFile" id="upFile"
												onchange="loadUpdateImg(this);" /></br>

											<div id="img-container">
												<img id="update-img-viewer" width="100%" height="100%" />
											</div>
																						<!-- 댓글 부분 -->
											<div class="comment-editor">

												<textarea name="update-boarding" id="update-boarding" cols="80" rows="11"
													placeholder="맛집의 후기를 남겨주세요!"></textarea></br>
												<input type="submit" id="btn-update"  value="수정하기" onclick="return updateComment();"/>
												<input type="button" value="닫기" onclick="hideUpdate();" />
												<input type="hidden" name="boardRef" value="0"/>
												<input type="hidden" name="rName" value="<%=r.getrName() %>" />
												<input type="hidden" name="location" value="<%=r.getLocation() %>" />
											</div>
										</div>
									</form>
								</section></br></br>

<style>
.img-container div{
	display: inline;
}
.user-comment{
	min-height: 600px;
}
section#comment-update-container{
	 display:none;
}
</style>

<script>
$(()=>{
	console.log("<%=thisRname%>");
	
	$.ajax({
		url : "<%=request.getContextPath()%>/comment/commentList",
		data: {rName:"<%=thisRname%>"},
		dataType : "json",
		success: data=>{
			for(let i  in data){
				let html = "";
				let c = data[i];
				var renamedFileName="";
				if(c.renamedFileName != "") {renamedFileName = c.renamedFileName;}else{renamedFileName = "eats.jpg"}
				var gradestar = "";
				var gradecontent = "";
				switch(c.grade){
				case 1: gradestar = "1star.png"; gradecontent = "최악이에요!"; break;
				case 2: gradestar = "2star.png"; gradecontent = "별로예요..."; break;
				case 3: gradestar = "3star.png"; gradecontent = "그저 그래요."; break;
				case 4: gradestar = "4star.png"; gradecontent = "맛있어요!"; break;
					case 5: gradestar = "5star.png"; gradecontent = "최고의 맛집!!!"; break;
				}
				
				if(i<3) {
					html += "<div class='comment_box'>"					
				}
				else {
					html += "<div class='comment_box hidden'>"
				}
				html += "<div class= 'comment_imgbox'>" 
					+ 		"<img src='<%=request.getContextPath()%>/images/" + renamedFileName + "'/>" 
					+ 	"</div>"
					
					+ 	"<div class='comment_contentbox'>" 
					
					+		"<span class='cid'> [" +  c.id + "] 님의 댓글"+ "</span>" +"<span class='cdate'> 댓글 날짜: " + c.bDate + "</span>" + "</br>"
					+		"<img class='commentstar' src='<%=request.getContextPath()%>/images/" + gradestar + "'/></br>"

					+		"<div class='cboarding'>"  + c.boarding +  "</div>" + "</br></br></br>";
				if("<%=userLoginId%>"==c.id||"<%=userLoginId%>"=="admin"){
					html += "<input type='button' value='수정하기' onclick='updateBoard(this)'>"
						+		"<input class='deleteComment' type='button' value='삭제하기' onclick='deleteBoard(this)'>"
				}		
				html +=		"<form name='boardDelFrm' action='<%=request.getContextPath()%>/comment/commentDelete' method='post'>"
				    +			"<input type='hidden' name='boardNo' value='"+ c.boardNo + "'/>"
				    +			"<input type='hidden' name='renamedFileName' value='" + c.renamedFileName+ "' />"
			    	+		"</form>"
			    	
					+ 	"</div>" 	
					
					+ "</div>";
					
					
					
				$("#photo-container").append(html);
				$("#boardNo").val(c.boardNo);
				console.log(renamedFileName);
			}
			
			if(data.length>3) {
				$("#photo-container").append("<div><button id='moreReview' onclick='moreReview()'>더보기</button></div></br></br>");
			}
		
		},
		error: (a,b,c)=>{
			console.log(a,b,c);
		}
		
	});
	
});

function updateBoard(a){
 	var $commentbox = $(a).parents().parents().eq(0);
 	var $commentContainer = $("#comment-update-container");
 	$commentContainer.css("display","block");
 	
 	
 	$commentbox.after($commentContainer);
	
 	var boardRef = $(a).next().next().children('[name=boardNo]').val();
	
 	$("[name=boardRef]").val(boardRef);
 	
 	return;
}

function hideUpdate(){
	var $commentContainer = $("#comment-update-container");
	$commentContainer.css("display","none")
}


function deleteBoard(a){
 if(!confirm('이 댓글을 삭제하시겠습니까?')) return;
 var boardNo = $(a).parents().find("[name=boardNo]").val();
 location.href = "<%=request.getContextPath()%>/comment/commentDelete?rName="+"<%=thisRname%>"+"&boardNo="+boardNo; 
 
//  console.log($(a).parents().find("[name=boardNo]").val());
//  $("[name=boardDelFrm]").submit();
}

function insertComment(){
	
// 	console.log($("#boarding").text().trim()=='');
<%-- 	console.log("<%=userLoginId%>"==""); --%>
// 	console.log($("#boarding").val());

	if($("#boarding").val().trim()=='') {
		alert("내용을 입력해 주세요.");
		return false;
	}
	else if("<%=userLoginId%>"=="") {
		alert("로그인 후 이용하실 수 있습니다.");
		return false;
	}
	else {
		return true;	
	}
}

function updateComment(){
	
// 	console.log($("#boarding").text().trim()=='');
<%-- 	console.log("<%=userLoginId%>"==""); --%>
// 	console.log($("#boarding").val());

	if($("#update-boarding").val().trim()=='') {
		alert("수정을 위해 내용을 입력해 주세요.");
		return false;
	}
	else if("<%=userLoginId%>"=="") {
		alert("로그인 후 이용하실 수 있습니다.");
		return false;
	}
	else {
		return true;	
	}
}

function moreReview(){
	console.log($(".hidden").length);
	
	if($(".hidden").length==0) {
		$("#moreReview").attr("disabled","disabled").css("cursor","not-allowed");
	}
	else if($(".hidden").length<3) {
		for(var i=0;i<$(".hidden").length;i++){
			$(".hidden").eq(i).removeClass('hidden');
		}
	}
	else {
		$(".hidden").eq(0).removeClass('hidden');
		$(".hidden").eq(1).removeClass('hidden');
		$(".hidden").eq(2).removeClass('hidden');
	}
}

function loadImg(f){
	console.dir(f);
	console.dir(f.files[0]);
	
	if(f.files && f.files[0]){
		let reader = new FileReader();
		//파일읽기, 읽기완료시 load event발생
		reader.readAsDataURL(f.files[0]);
		
		reader.onload = e => {
			//파일컨텐츠는 e.target.result속성에 담겨있음.
			$("#img-viewer").attr("src", e.target.result);
		}
	}
	else{
		$("#img-viewer").attr("src", "");
	}
}

function loadUpdateImg(f){
	console.dir(f);
	console.dir(f.files[0]);
	
	if(f.files && f.files[0]){
		let reader = new FileReader();
		//파일읽기, 읽기완료시 load event발생
		reader.readAsDataURL(f.files[0]);
		
		reader.onload = e => {
			//파일컨텐츠는 e.target.result속성에 담겨있음.
			$("#update-img-viewer").attr("src", e.target.result);
		}
	}
	else{
		$("#update-img-viewer").attr("src", "");
	}
}
</script>
                                </section>
                            </div>
                        </section>
                    </div>
                </div>
                
                <!-- 사이드 영역 -->
                <div class="side-wrap">
                    <div class="column-side">
                        <!--지도-->
                        <div class="map-container" style="position: relative; overflow: hidden;">
                            <div id="map" style="width:100%;height:350px;"></div>
                        </div>
                        <script>
                        var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
                	   	mapOption = { 
                	       center: new kakao.maps.LatLng("<%=lat%>", "<%=lng%>"), // 지도의 중심좌표
                	       level: 5 // 지도의 확대 레벨
                	   	};
                	
                		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
                		 
                		// 마커를 표시할 위치와 title 객체 배열입니다 
                	
                		// 마커 이미지의 이미지 주소입니다
                		var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
                		    
                		    
               		    // 마커 이미지의 이미지 크기 입니다
               		    var imageSize = new kakao.maps.Size(24, 35); 
               		    
               		    // 마커 이미지를 생성합니다    
               		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
               		    
               		    // 마커를 생성합니다
               		    var marker = new kakao.maps.Marker({
               		        map: map, // 마커를 표시할 지도
               		        position: new kakao.maps.LatLng("<%=lat%>", "<%=lng%>"), // 마커를 표시할 위치
               		        title : "<%=thisRname%>", // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
               		        image : markerImage // 마커 이미지 
               		    });
                        
                        </script>
                        <div class="inner">
                            <!-- 주변 인기 식당 -->
                            <section class="module near-rastaurant NearByRestaurantList">
                                <span class="title NearByRestaurantList__Title">주변 인기 식당</span>
                                <ul class="list-restaurants type-single NearByRestaurantList__List">
                                 <%for(int i=0;i<nearPhotos.size();i++){ %>
                                    <li class="NearByRestaurantItem NearByRestaurantList__Item">
                                        <div class="NearByRestaurantItem__PictureAndContent">
                                            <a href="<%=request.getContextPath() %>/restaurants?rName=<%=rList.get(i).getrName() %>" class="NearByRestaurantItem__PictureLink">
                                                <img alt="" class="NearByRestaurantItem__Picture loaded" src="<%=request.getContextPath() %>/images/<%=nearPhotos.get(i).getImgName() %>" data-was-processed="true">
                                            </a>
                                            <div class="NearByRestaurantItem__Content">
                                                <div class="NearByRestaurantItem__NameWrap">
                                                    <a href="" class="NearByRestaurantItem__Name"><%=nearPhotos.get(i).getrName()%></a>
                                                    <span class="NearByRestaurantItem__Rating"><%=rList.get(i).getGrade()%></span>
                                                </div>
                                                <div class="NearByRestaurantItem__InfoWrap">
                                                    <dl class="NearByRestaurantItem__Info">
                                                        <dt class="NearByRestaurantItem__InfoLabel">
                                                            음식 종류
                                                        </dt>
                                                        <dd class="NearByRestaurantItem__InfoValue NearByRestaurantItem__InfoValue--SubCuisine"><%=rList.get(i).getCategory() %></dd>
                                                    </dl>
                                                    <dl class="NearByRestaurantItem__Info">
                                                        <dt class="NearByRestaurantItem__InfoLabel">
                                                            위치
                                                        </dt>
                                                        <dd class="NearByRestaurantItem__InfoValue NearByRestaurantItem__InfoValue--Metro"><%=rList.get(i).getLocation() %></dd>
                                                    </dl>
                                                    <dl class="NearByRestaurantItem__Info">
                                                        <dt class="NearByRestaurantItem__InfoLabel">
                                                            영업시간
                                                        </dt>
                                                        <dd class="NearByRestaurantItem__InfoValue NearByRestaurantItem__InfoValue--PriceRange"><%=rList.get(i).getOpenTime()%> - <%=rList.get(0).getCloseTime() %></dd>
                                                    </dl>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <%} %>
                                    
                                </ul>
                            </section>
                        </div>
                    </div>
                </div>
            </div>

        </article>
    </main>
    
<style>
section#photo-wrapper{width:600px; margin:0 auto; text-align:center;}
section#photo-wrapper h2{margin:10px 0;}

div#btn-more-container { margin: 20px; }
button#btn-more{ font-size: 20px; width: 1200px; }

section#photo-wrapper input#btn-add{float:right; margin:0 0 15px;}

table#tbl-photo{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
table#tbl-photo th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
table#tbl-photo td {border:1px solid; padding: 5px 10px 5px 10px; text-align:left;}
table#tbl-photo #img-viewer-container {
	width:350px;
	height:200px;
	border:2px dashed darkgray;
	display:inline-block;
}



.comment_box {
	display: inline-block;
    box-shadow: 2px 2px 1px rgba(128, 128, 128, 0.5);
    background-color: white;
    margin-bottom: 10px;
}

.comment_imgbox {
	display: inline-block;
    float: left;

    width: 200px;
}

.comment_imgbox img {
	display: inline;
	border-radius: 10px; /* �씠誘몄� �룞洹몃옑寃� �븯�뒗 �젙�룄 */
	width: 200px;
	height: 170px;                               
	position: left;
	margin-top: 0px;
}

#img-viewer, #update-img-viewer {
	width: 200px;
	height: 200px;
	object-fit: cover;
}

.comment_contentbox {
	display: inline-block;
    float: left;
    margin-left: 0px;
    padding: 20px;
    width: 550px;
    border: 1px solid #ccc;
}

span.cid { font-size:20px; font-weight: bold; position:relative;}
.commentstar{width:100px;height:50px;}

div.cboarding { width:80%; font-size:15px;}
span.cdate {font-size:10px;  float:right; color:#9b9b9b;}


.comment_box:hover {
	box-shadow: 10px 10px 8px rgba(135, 139, 133, 0.4);
}

#moreReview{
	width: 100%;
	background-color:rgba(0,0,0,.2);
}
.hidden {
	display:none;
}
.bodyStart{
	display:none;
}

 /* 맛집 점수 */
.star-rating {
  display: block;
  margin-bottom: 5px;
  direction: rtl;
  text-align: center;
  margin-top: 5px;
}
.star-rating input[type="radio"] {
  display: none;
}
.star-rating > label {
  color: rgba(204, 204, 204, 0.925);
  -webkit-transition: all .2s ease-in-out;
  -moz-transition: all .2s ease-in-out;
  -o-transition: all .2s ease-in-out;
  transition: all .2s ease-in-out;
  cursor: pointer;
  font-size: 50px;
}
.star-rating > label:hover,
.star-rating > label:hover ~ label,
.star-rating > input[type="radio"]:checked ~ label
{color: #f2b600}
.deleteComment{
	margin-left:20px;
}
.header_title img{
	top:17px;
}
#searchIcon{
	top:41%;
}
.search__SearchBlock #searchFood{
	padding:28px 0px;
}
.click_SearchButton {
	top:35px;
}
.headerWrapper .tab_menu{
	top:26%;
}
#innerHead {top:14px; margin: -14px auto;}
<%-- 진섭이형--%>
#btn-insert{
    bottom: 70px;
    right: -10px;
    width: 60px;
    height: 40px;
    font-size: 15px;
    background-color: rgba(0,0,0,0);
    border-radius:30px 0 30px 0;
    border-color: orange;
}
#btn-insert:hover {
    background-color: orange;
    font-color:white;
}
#dmNew{
	left:378px;
}
</style>


    <%@ include file="/WEB-INF/views/common/footer.jsp" %>