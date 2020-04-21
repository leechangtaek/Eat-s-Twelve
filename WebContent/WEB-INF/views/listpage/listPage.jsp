<%@page import="Photo.model.vo.Photo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String kr = (String)request.getAttribute("kr");
	String jpn = (String)request.getAttribute("jpn");
	String chn = (String)request.getAttribute("chn");
	String wst = (String)request.getAttribute("wst");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">


<div class="list-title"><span>한식 맛집 리스트 20곳</span></div>
<section class="imgList orderByKr" id="k">
	
</section>
<div class="list-title"><span>중식 맛집 리스트 20곳</span></div>
<section class="imgList orderByChn" id="c">
</section>
<div class="list-title"><span>일식 맛집 리스트 20곳</span></div>
<section class="imgList orderByJpn" id="j">
</section>
<div class="list-title"><span>중식 맛집 리스트 20곳</span></div>
<section class="imgList orderByWst" id="w">
</section>



<style>
.list-title {
	margin: 0 auto;
	width: 1400px;
	height: 50px;
	margin-top: 40px;
	margin-bottom: 0px;
	text-align:center;
	
}
.list-title span {
	color: orange;
	font-weight: bold;
	font-size: 30px;
}
.imgList{
	position: relative;
	width: 1300px;
	height: 350px;
	margin: 0 auto;
}
.imgList div span{
	font-weight: bold;
	font-size: 25px;
   
}
.list img{
	width:320px;
	height:250px;
}
.imgList button.slick-prev {
	position: absolute;
	top:30%;
	left: -55px;
	background : white;
	width: 50px;
	height: 50px;
	border-radius: 100%;
	border: none;
}
.imgList button.slick-next {
	position: absolute;
	top:30%;
	right: -55px;
	background : white;
	width: 50px;
	height: 50px;
	border-radius: 100%;
	border:none;
}
.imgList button:before{font-family:'xeicon'; color:rgba(0,0,0,.5); font-size:40px;}
.imgList button.slick-prev{font-size:0; color:transparent;}
.imgList button.slick-prev:before{content:"\e93d";}
.imgList button.slick-next{font-size:0; color:transparent;}
.imgList button.slick-next:before{content:"\e940";}

ul.slick-dots li button {font-size:0; color:transparent; width:10px; height:10px; border-radius:100%; border:none; background-color:#ccc;}
ul.slick-dots li:first-of-type button{position:absolute;left:40%;top:96%;}
ul.slick-dots li:nth-of-type(2) button{position:absolute;left:45%;top:96%;}
ul.slick-dots li:nth-of-type(3) button{position:absolute;left:50%;top:96%;}
ul.slick-dots li:nth-of-type(4) button{position:absolute;left:55%;top:96%;}
ul.slick-dots li:nth-of-type(5) button{position:absolute;left:60%;top:96%;}
ul.slick-dots li.slick-active button{background-color:#ff792a;}
</style>

<script>
$(()=>{
	
	imgShow("k");
	imgShow("j");
	imgShow("c");
	imgShow("w");
	
	$('.imgList').slick({
		  infinite: true, //양방향 무한 모션
		  speed:300, // 슬라이드 스피드
		  autoplay: false, //자동플레이 유무( false시 자동플레이 안됨 )
		  slidesToShow: 4, //한 화면에 보여줄 아이템수
		  slidesToScroll: 4, // 한번에 슬라이드 시킬 아이템 개수
		  arrows: true, //좌우 화살 버튼 노출 여부 ( false 시 안보임 )
		  dots: true,
		  draggable: false
	});
	
});

function imgShow(rType) {
	var json;
	switch(rType) {
	case "k": json = JSON.parse('<%=kr%>'); break; 
	case "j": json = JSON.parse('<%=jpn%>'); break; 
	case "c": json = JSON.parse('<%=chn%>'); break; 
	case "w": json = JSON.parse('<%=wst%>'); break; 
	}
	
	var $section = $("#"+rType);
	var html = "";
	
	for(let i in json) {
		var data = json[i];
		
		html += "<div class='list'><a href='<%=request.getContextPath()%>/restaurants?rName="+data.rName+"'><img src='<%=request.getContextPath()%>/images/"+data.imgName+"'/></a></br>";
		html += "<span>"+data.rName+"</span></div>";
	}
	$section.append(html);
	
};


</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>