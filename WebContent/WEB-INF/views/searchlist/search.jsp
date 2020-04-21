<%@page import="Photo.model.vo.Photo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/search.css" />

<!DOCTYPE html>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	String orderByGrade = (String)request.getAttribute("orderByGrade");
	String orderByLike = (String)request.getAttribute("orderByLike");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
	
%>
<style>
.#searchIcon{
	top:14%;
}
.search__SearchBlock #searchFood{
	padding:24px 0px;
}
#search-header{background-color:#EAEAEA; }
.bodyStart{padding-top:33px; !important}
#innerHead {top:14px; margin:-14px auto;}
</style>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9db50509719fcbf1c091e1369e6fcf77"></script>
<div id="search-header">
	<h2 class="tit">'<%=searchKeyword %>'검색결과</h2>
</div>
<div id="search-container">
	<aside>
		<div id="search-side-nav">
			<div class="box">
			   <div>
			   
				<div class="sort">
					<button class="grade-like like-grade" value="grade">평점순</button>
					<button class="grade-like" value="like">인기순</button>
				</div>
				<hr style="border: 1px solid #E7E7E7;border-radius: 5px;" />
				<div class='selectOption' style="margin-top:20px;">
			
				
				<div class="demo-toggle" style="margin-bottom: 20px;">
					<label for="singleYN" class="checkbox">
						<input type="checkbox" id="singleYN"/>
						<span for="singleYN">혼밥 가능</span>
					</label>
					<label for="parkingYN" class="checkbox">
						<input type="checkbox" id="parkingYN" />
						<span for="parkingYN" >주차 가능</span>
					</label>
				</div>
				
				<hr style="border: 1px solid #E7E7E7;border-radius: 5px;" />
				
				<div class="demo-toggle">
					<div class="typeSelect" style="text-align:left; margin-top: 20px; margin-bottom: 20px;">
						<label for="kor" class="radio">
							<input type="radio" name="type" id="kor" value="한식" />
							<span>한식</span>
						</label>
						<label for="chn" class="radio">
							<input type="radio" name="type" id="chn" value="중식" />
							<span>중식</span>
						</label>
						<label for="jpn" class="radio">
							<input type="radio" name="type" id="jpn" value="일식" />
							<span>일식</span>
						</label>
						<label for="wes" class="radio">
							<input type="radio" name="type" id="wes" value="양식" />
							<span>양식</span>
						</label>
						<label for="des" class="radio">
							<input type="radio" name="type" id="des" value="디저트" />
							<span>디저트</span>
						</label>
					</div>
				</div>
				
				<hr style="border: 1px solid #E7E7E7;border-radius: 5px;" />
				
				<div class="runTime">
					<br />
					<label for="openTime">영업 시작 시간</label> <br /><br />
					<select name="openTime" id="openTime" style="height:30px;">
						<option value="0" selected >시간을 선택해 주세요.</option>
						<option value="900">9:00</option>
						<option value="930">9:30</option>
						<option value="1000">10:00</option>
						<option value="1030">10:30</option>
						<option value="1100">11:00</option>
						<option value="1130">11:30</option>
						<option value="1200">12:00</option>
						<option value="1201">12시 이후</option>
					</select>
					<br /><br />
					<label for="closeTime">영업 종료 시간</label> <br /><br />
					<select name="closeTime" id="closeTime" style="height:30px;">
						<option value="2401" selected>시간을 선택해 주세요.</option>
						<option value="2100">21:00</option>
						<option value="2100">21:30</option>
						<option value="2200">22:00</option>
						<option value="2230">22:30</option>
						<option value="2300">23:00</option>
						<option value="2330">23:30</option>
						<option value="2400">24:00</option>
						<option value="2401">12시 이후</option>
					</select>
					<br /><br />
				</div>
			</div>
			</div>
		</div>
	</div>
	</aside>
	<div id="photo-container1">
		<table class="photo-tbl">
		</table>
		<div id='morePhoto'></div>
	</div>
	<aside>
		<div id="search-side-map" style="margin-top:30px;">
			<div id="map" style="width:100%;height:350px;"></div>
			
			<br />
			<br />
			<br />
			<div id="wrap-side">
			  <ul id="slider-side"></ul>
			</div>
		
		</div>
		
		
	</aside>
</div>

<style>
#more-btn{
	border: none;
	width: 1000px;
}
.slide-item{
	color:orange;
	text-align:center;
	font-size: 20px;
	font-weight: bold;
	height: 375px;
}
	
#randomRec {
	text-align:center;
	font-size:20px;
	color: orange;
	font-weight:20px;
	margin:30px 0px;
	
}
#wrap-side {
	width: 300px;
	height: 350px;
	padding: 0px;
}

#slider-side {
	padding: 0px;
	width: 300px;
	height: 350px;
	margin: 0 auto 10px;
}
.slide-item img { 
	width:300px;
	height: 300px;
	margin: 0 auto;
}

}
.typeSelect input {
	margin: 15px;
}

.sort button:first-of-type {
	background-color: #ff792a;
	padding: 5px;
	border-radius: 25%;
	font-weight: bold;
}

.photo-tbl tr td {
	width: 400px;
	height: 400px;
	text-align: center;
	vertical-align: top;
}

.photo-tbl {
	margin: 0 auto;
	border-spacing: 20px;
}

.photo-tbl>tr>td>p {
	margin-top: 10px;
}

.name {
	font-size: 30px;
	font-weight: bold;
	position:absolute;
	top:104%;
	left:9%;
}

.category {
	font-size: 15px;
	color: gray;
	position:absolute;
	top:109%;
	left:10%;
	
}

.dsc {
	font-size: 20px;
	font-weight: bold;
	position:absolute;
	top:120%;
	left:10%;
}

.grade {
	position:absolute;
	top:104%;
	left:76%;
	color: orange;
	font-size:21px;
	
}

.none {
	display: none;
}
</style>

<script>
var jsonData;
var positions=[];
$(()=>{
	tableFilter("grade");
	
	showTable(jsonData);
	$(".grade-like").click(function(){
		$(this).siblings("button").css({
			"background-color": "white",
			padding: "5px",
			"border-radius":"25%",
			"font-weight": "normal"
		}).removeClass('like-grade');
		$(this).css({
			"background-color": "#ff792a",
			padding: "5px",
			"border-radius":"25%",
			"font-weight": "bold"
		}).addClass('like-grade');
		tableFilter($(this).val());
	});
	
	//카카오 맵 api시작
	var firstLating;
	if(positions != null||positions != undefined) {
		firstLatLng = positions[0];
	}
	if(firstLatLng!=null||firstLatLng!=undefined) {
	// 	console.log(firstLatLng.latlng.Ga, firstLatLng.latlng.Ha);
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	   	mapOption = { 
	       center: new kakao.maps.LatLng(firstLatLng.latlng.Ha, firstLatLng.latlng.Ga), // 지도의 중심좌표
	       level: 5 // 지도의 확대 레벨
	   	};
	
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		 
		// 마커를 표시할 위치와 title 객체 배열입니다 
	
		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		    
		for (var i = 0; i < positions.length; i ++) {
		    
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		}
	}

	//카카오 맵 api 종료
	
	$("#more-btn").click(function (){
		var $tbl = $(".photo-tbl");
		var $btnVal = Number($("#more-btn").val());
		
// 		console.log($(".photo-tbl").children('tr').length);
		
		if($btnVal*4<=$(".photo-tbl").children().length) {
			for(var i=$btnVal*4;i<$btnVal*4*2;i++) {
				$tbl.children().eq(i).removeClass('none');
			}
		}
		
		else {
			for(var i=$btnVal*4;i<$(".photo-tbl").children().length;i++) {
				$tbl.children().removeClass('none');
			}
			$("#more-btn").attr("disabled","disabled").css("cursor","not-allowed");
		}

		$("#more-btn").val(Number($("#more-btn").val())+1);
	});
	
	
	//slick random
	var slickhtml =""
	for(var i in jsonData) {
		let p = jsonData[i];
		slickhtml += "<li class='slide-item'><a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><img src='<%=request.getContextPath()%>/images/"+p.imgName+"'/></a></br></br>"+p.description+"</li>"
	}
	$("#slider-side").append(slickhtml);
	
	  $("#slider-side").slick({
	
	    arrows: false,
	
	    autoplay: true,
	
	    autoplaySpeed: 0,
	
	    speed: 100,
	
	    cssEase: "linear",
	
	    pauseOnHover: false
	
	  });
	var randomTimer = Math.floor(Math.random()*10);
	setTimeout(function(){
	  $("#slider-side").slick('slickPause');
	  $("#wrap-side").append("<p id='randomRec'>Eat's 추천 !! 오늘은 이집 어때요?</p>")	
	},randomTimer*1000);
	
	
	
}); //온로드함수 종료




function tableFilter(value) {
	$(".photo-tbl").children().remove();
	
	var order = ""; 
	if(value=="grade") {
		order = "<%=orderByGrade%>";
	}
	else {
		order = "<%=orderByLike%>";
	}
	
    var	param = {
			order : order   		
    };
    
	$.ajax({
		url: "<%=request.getContextPath()%>/searchListFilter",
		async:false,
		data: param,
		datatype: "json",
		success: data =>{
			
			jsonData = data;	
			showTable(data);			
		},
		error : (x,s,e) => {
			console.log("ajax처리 실패!",x,s,e);
		}
		
	}); 
}//등급순, 인기순 정렬 종료	

//photo table
function showTable(jsonData){
	let html ="";
// 	console.log(jsonData[0]);
	
	if(jsonData.length==0){
		html+="<tr><td colspan='2'><h3>조회된 결과가 없습니다.</h3></td></tr>";
	}
	else{
		for(let i in jsonData){
			let p = jsonData[i];
			if(i<8) {
				if(i%2==0) {
					html += "<tr><td><div class='item'><div class='thumb'><a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><img src='<%=request.getContextPath()%>/images/"+p.imgName+"'/></a></div>"; 
					html += "<a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><span class='name'>"+p.rName+"</span><span class='grade'>☆"+p.grade+"</span>";
					html += "<p class='category'>"+p.category+"</p>";
					html += "<p class='dsc'>"+p.description+"</p></div></td>";
				}
				else {
					html += "<td><div class='item'><div class='thumb'><a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><img src='<%=request.getContextPath()%>/images/"+p.imgName+"'/></a></div>"; 
					html += "<a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><span class='name'>"+p.rName+"</span><span class='grade'>☆"+p.grade+"</span>";
					html += "<p class='category'>"+p.category+"</p>";
					html += "<p class='dsc'>"+p.description+"</p></a></div></td></tr>";					
				}
				
				if(jsonData.length>8) {
					$("#morePhoto").html("<button id='more-btn' value='1'>더보기</button>");
				}
			
			}
			else {
				if(i%2==0) {
					html += "<tr class='none'><td><div class='item'><div class='thumb'><a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><img src='<%=request.getContextPath()%>/images/"+p.imgName+"'/></a></div>"; 
					html += "</br><a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><span class='name'>"+p.rName+"</span>&nbsp;&nbsp;&nbsp;<span class='grade'>☆"+p.grade+"</span></br>";
					html += "<p class='category'>"+p.category+"</p>";
					html += "<p class='dsc'>"+p.description+"</p></div></td>";
				}
				else {
					html += "<td><div class='item'><div class='thumb'><a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><img src='<%=request.getContextPath()%>/images/"+p.imgName+"'/></a></div>"; 
					html += "</br><a href='<%=request.getContextPath()%>/restaurants?rName="+p.rName+"'><span class='name'>"+p.rName+"</span>&nbsp;&nbsp;&nbsp;<span class='grade'>☆"+p.grade+"</span></br>";
					html += "<p class='category'>"+p.category+"</p>";
					html += "<p class='dsc'>"+p.description+"</p></a></div></td></tr>";					
				}
			}
			
			var positionsArr = {title : p.rName, latlng : new kakao.maps.LatLng(p.lat, p.lng)};
			positions.push(positionsArr);
				
		}
	}
	$(".photo-tbl").html(html);
}
//photo table 종료

//옵션 선택 
$(".selectOption").children().change(function(){
	var filterJsonData = [];
	$(".photo-tbl").children().remove();
	$("#more-btn").val(1);
// 	console.log(jsonData);
	
	var singleYN="";
	var parkingYN="";
	var rType="";
	var runTime=0;
	var closeTime=0;
	var cPage
	
	if($("#singleYN:checked").val()=="on") {
		singleYN = "Y";
	}
	if($("#parkingYN:checked").val()=="on") {
		parkingYN = "Y";
	}
	rType = $(".typeSelect").children().children("input:checked").val();
	if(rType == undefined) {
		rType="";
	}
	closeTime = $("#closeTime").children("option:checked").val();
	openTime = $("#openTime").children("option:checked").val()
	
// 	console.log(singleYN+","+parkingYN+","+rType+","+openTime+","+closeTime);
	positions=[];
   	
	for(let i in jsonData){
		let p = jsonData[i];
		if(singleYN=="Y"&&p.singleYN=="N") {
// 			console.log("1번필터"); 
			continue;
		}
		if(parkingYN=="Y"&&p.parkingYN=="N") {
// 			console.log("2번필터"); 
			continue;
		}
		if(rType!=""&&rType!=p.type) {
// 			console.log("3번필터"); 
			continue;
		}
		if(openTime>p.openTime) {
// 			console.log("4번필터"); 
			continue;
		}
		if(closeTime<p.closeTime) {
// 			console.log("5번필터"); 
			continue;
		}
		
		filterJsonData.push(p);
		
		var positionsArr = {title : p.rName, latlng : new kakao.maps.LatLng(p.lat, p.lng)};
		
		positions.push(positionsArr);
		
	}
	
// 	console.log(filterJsonData);


	$("#map").children().remove();
	//카카오 맵 api시작
	var firstLating;
	if(positions != null||positions != undefined) {
		firstLatLng = positions[0];
	}
	if(firstLatLng!=null||firstLatLng!=undefined) {
	// 	console.log(firstLatLng.latlng.Ha, firstLatLng.latlng.Ga);
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	   	mapOption = { 
	       center: new kakao.maps.LatLng(firstLatLng.latlng.Ha, firstLatLng.latlng.Ga), // 지도의 중심좌표
	       level: 5 // 지도의 확대 레벨
	   	};
	
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		 
		// 마커를 표시할 위치와 title 객체 배열입니다 
	
		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		    
		for (var i = 0; i < positions.length; i ++) {
		    
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		}
	}
	//카카오 맵 api 종료
	
	showTable(filterJsonData);
});	
//혼밥주차 종료



	

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>