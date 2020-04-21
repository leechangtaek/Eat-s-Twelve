<%@page import="restaurant.model.vo.Restaurant, java.util.List, user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Restaurant> list = (List)request.getAttribute("foodList");
	User userLoggedIn = (User)session.getAttribute("userLoggedIn");
	String userLoginId = "";
	if(userLoggedIn != null){
		userLoginId = userLoggedIn.getUserId();
	}
	String test = (String)request.getAttribute("test");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eat's ! Twelve!</title>

<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script src="<%=request.getContextPath() %>/js/slick.min.js"></script>
<link rel="styleSheet" href="<%=request.getContextPath()%>/css/frame.css" />
<link rel="styleSheet" href="<%=request.getContextPath()%>/css/slick.css" />

<script>
$(function(){
	$('.headerImg').slick({
		  infinite: true,
		  speed: 200,
		  slidesToShow: 1,
		  adaptiveHeight: true,
		  autoplay: true,
		  autoplaySpeed: 2000
	  });
	
	var $autoSearch = $("#search_AutoSearch");
	$autoSearch.hide();
	
	$("#searchFood").on('keyup',function(e){
	
		var $sel = $(".sel");
		var $li = $autoSearch.children("li");
		
		if(e.key == "ArrowDown"){
			
			if($sel.length ==0){
				$li.first().addClass("sel");
			}
			else{
				$sel.removeClass("sel")
					.next()
					.addClass("sel")
			}
		}
		else if(e.key=='ArrowUp'){
			if($sel.length != 0){
				$sel.removeClass("sel")
					.prev()
					.addClass("sel");
			}
			else{
				$li.last().addClass("sel");
			}
		}
		else if(e.key =='Enter'){
			
			var $name = $sel.text().search('/');
			var $searchKeyword = $("#searchFood").val().trim();
			if($sel.length ==0){
				$(this).val($searchKeyword);
			}else{
			 $(this).val($sel.text().substr(0,$name)); 
			}
			$autoSearch.hide()
					   .children()
					   .remove();
			location.href="<%=request.getContextPath()%>/searchList?searchKeyword="+$(this).val().trim();
		}
		else{
			var srchFoodVal =$(this).val().trim();
			
			if(srchFoodVal.length ==0) return;
			
			$.ajax({
				url:"<%=request.getContextPath()%>/restaurant/autoSearch",
				type:"get",
				data:{srchFood : srchFoodVal},
				success : function(data){
					console.log(data);
					
					if(data.trim().length==0){
						$autoSearch.hide();
					}
					else{
						var dataArr = data.split("^");
						var html = "";
						$.each(dataArr, (idx, val)=>{
							html += "<li>"+val.replace(srchFoodVal, '<span class="srchval">'+srchFoodVal+'</span>')+"</li>";
						});
						$autoSearch.html(html).fadeIn(300);
					}
				},
				error:function(jqxhr,textStatus, errorThrown){
					console.log("ajax처리실패!",jqxhr, textStatus, errorThrown);
				}
			});
		}
		
		$autoSearch.on('click','li',e=>{
			console.log($(e.target).text().search('/'));
			var $name = $(e.target).text().search('/');
			
			$('#searchFood').val($(e.target).text().substr(0,$name));
			$autoSearch.hide()
					   .children()
					   .remove();
		}).on('mouseover','li',e=>{
			$(e.target).addClass("sel")
					   .siblings()
					   .removeClass("sel");
		}).on('mouseout','li',e=>{
			$(e.target).removeClass("sel");
		});
		
	});
	/* var $recentpage = $(".recent-page");
	$recentpage.find("ul ul").hide();
	$recentpage.find("li.recent-page-title > ul").show(); */
	
	$.ajax({
		url:"<%=request.getContextPath()%>/chat/dmNew",
		data : {userLoginId: "<%=userLoginId%>"},
		success: data =>{
// 			console.log(data);
			$("#dmNew").text(data);
		},
		error: (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
	
});
function submit(){
	console.log($("#searchFood").val());
	var $searchKeyword = $("#searchFood").val().trim();
	
	if($searchKeyword.length==0){
		alert("검색어를 입력해주세요.");
		return;
	}else{
		location.href="<%=request.getContextPath()%>/searchList?searchKeyword="+$searchKeyword;
		
	}
	
};

	
/* $(".recent_login").on('click',function(){ */
function recentLogin(a){
		
	$.ajax({
		url:"<%=request.getContextPath()%>/restaurant/recentRestaurant?userId=<%=userLoginId%>",
		type:"get",
		dataType:"json",
		success:data=>{
// 			console.log($(".myIcon").attr('class'));
			if($(a).attr('class')=="recent_login"&&$(".myIcon").hasClass('one')){
				$(".myIcon").removeClass('one');
				$(".notice").children().remove();	
			}else{
				$(".myIcon").addClass('one');				
				let $recent = $("<div class='recent_food'></div>");
				let html="";
				
				html += "<i class='recent_triangle'></i>";
				html += "<ul><li class='active'><a href=''>최근본 맛집</a><ul>";
				
				$(data).each((idx,recent)=>{
					html += "<a class='imgbox' href='<%=request.getContextPath() %>/restaurants?rName="+recent.rName+"'><li>";
					html += "<img class='recent_foodimg' src='<%=request.getContextPath()%>/images/"+recent.imgName+"'/>";
					html += "<div class='food_name'>"+recent.rName+"</div>";
					html += "<div class='food_grade'>"+recent.grade+"</div>";
					html += "<div class='food_location'>"+recent.location+"</br></br></div>";
					html += "<div class='food_type'>"+recent.type+"</div></li></a>";
				});
				html += "</ul></li><li><a onclick='wishRecent();' href='#'>가고싶다</a>";
				html += "<ul><li><img class='recent_starimg' />";
				html += "<div class='wishtext'></div>";
				html += "<div class='wishtext2'></div></li></ul></li></ul>";
				html += "<div class='recentBottom'><a href='<%=request.getContextPath()%>/user/logout' class='logoutbutton'>logout</a></div>";
				$recent.append(html);
				$(".notice").html($recent);
				var $tab_list = $(".recent_food");
			    $tab_list.find("ul ul");
			    $tab_list.find("li.active > ul").show();
			    $tab_list.find("li.active").siblings('li').children('ul').hide();
			    
			    function tabMenu(e){
			        e.preventDefault();
			        var $this = $(this);
			        $this.next("ul").show().parent("li").addClass("active").siblings("li").removeClass("active").find(">ul");
			    }
			    $tab_list.find("ul>li>a").click(tabMenu).focus(tabMenu);
			    $(".myIcon").addClass('one');
			}
							
		},
		error: (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
};

function wishRecent(a){
	$.ajax({
		url:"<%=request.getContextPath()%>/restaurant/wishRestaurant?userId=<%=userLoginId%>",
		type:"get",
		dataType:"json",
		success:data=>{
			console.log(data);
			console.log($(a));
			let $recent = $("<div class='recent_food'></div>");
			let html="";
			console.log(data.length==0);
			
			html += "<i class='recent_triangle'></i>";
			html += "<ul><li ><a href='#' onclick='recentLogin(this);'>최근본 맛집</a>";
			html += "</li><li class='active'><a onclick='wishRecent();' href=''>가고싶다</a><ul>";
			
			if(data.length==0){
				
				html += "<ul><li><img class='recent_starimg' src='<%=request.getContextPath()%>/images/recent_star.png'/>";
				html += "<div class='wishtext'>격하게 가고싶다.</div>";
				html += "<div class='wishtext2'>식당의 '별' 아이콘을 누르면 가고싶은 곳을 쉽게 저장할 수 있습니다.</div></li></ul></li></ul>";
				html += "</ul></li></ul><div class='recentBottom'><a href='<%=request.getContextPath()%>/user/logout' class='logoutbutton'>logout</a></div>";
			
			}else{
			console.log("12312312312");
			
			$(data).each((idx,recent)=>{
				html += "<a class='imgbox' href='<%=request.getContextPath() %>/restaurants?rName="+recent.rName+"&check_yn=Y'><li>";
				html += "<img class='recent_foodimg' src='<%=request.getContextPath()%>/images/"+recent.imgName+"'/>";
				html += "<div class='food_name'>"+recent.rName+"</div>";
				html += "<div class='food_grade'>"+recent.grade+"</div>";
				html += "<div class='food_location'>"+recent.location+"</div>";
				html += "<div class='food_type'>"+recent.type+"</div></li></a>";
			});
				html += "</ul></li></ul><div class='recentBottom'><a href='<%=request.getContextPath()%>/user/logout' class='logoutbutton'>logout</a></div>";
			}
			$recent.append(html);
			$(".notice").html($recent);
			var $tab_list = $(".recent_food");
		    $tab_list.find("ul ul");
		    $tab_list.find("li.active > ul").show();
		    
		    function tabMenu(e){
		        e.preventDefault();
		        var $this = $(this);
		        $this.next("ul").show().parent("li").addClass("active").siblings("li").removeClass("active").find(">ul");
		    }
		    $tab_list.find("ul>li>a").click(tabMenu).focus(tabMenu);
		},
		error: (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
	
}
function noneLogin(){
	alert("로그인을 해주세요");

	var url = "<%=request.getContextPath()%>/user/userLoginForm";
	var title = "login";
	var spec = "left=250px, top=100px, width=600px, height=400px";
	
	open(url, title, spec);
}

function gotoDMBoard(){
	var url = "<%=request.getContextPath()%>/chat/dmboard?userId="+"<%=userLoginId%>&cPage=1";
	var title = "개인쪽지함";
	var spec = "left=250px, top=100px, width=530px, height=600px";
	open(url,title,spec);
}

function goChatRoom(){
	var url = "<%=request.getContextPath()%>/honbab/honbabChatRoom?userId=<%=userLoginId%>"
	var title = "혼밥채팅방";
	var spec = "left=250px, top=100px, width=677px, height=828px";
	open(url,title,spec);
}


</script>


</head>
<body>
	<div id="innerHead">
		<div class="headerWrapper">
			<div class="header_title">
				<a href="<%=request.getContextPath()%>"> 
				<img src="<%=request.getContextPath()%>/images/title.png" />
				</a>
			</div>
			<div class="search__SearchBlock">
				<input type="text" id="searchFood" name="searchKeyword" placeholder="지역, 식당 또는 음식을 입력하세요" />
				<ul id="search_AutoSearch"></ul>
				<span id="searchIcon"></span>
			</div>
			<span> <input type="button" class="click_SearchButton" onclick="submit();" value="검색" />
			</span>
			<h3 class="blind">급상승 검색어</h3>
			<ul class="ah_1">
				<li class="ah_item"><a href="" class="ah_a">
				</a></li>
			</ul>
			<span class="ah_ico_open"></span>
			<nav>
				<div class="container">
					<ul class="tab_menu">
						<div>
							<li><a href="<%=request.getContextPath()%>/listPage">맛집리스트</a></li>
						</div>
						<%if(userLoggedIn==null){ %>
						<div>
							<li><a href="#" onclick="noneLogin();">채팅방</a></li>
						</div>
						<%}else{ %>
						<div>
							<li><a href="#" onclick="goChatRoom();">채팅방</a></li>
						</div>
						<%} %>
						<% if(userLoggedIn != null && "admin".equals(userLoggedIn.getUserId())){ %>
						 <div>
							<li><a href="<%=request.getContextPath()%>/admin/adminForm">관리자페이지</a></li>
						</div> 
						<%}
						else{ 
							if(userLoggedIn==null){%>
						<div>
							<li><a href="#" onclick="noneLogin();">마이페이지</a></li>
						</div>
						<%}else{
						%> 
						<div>
							<li><a href="<%=request.getContextPath()%>/user/userForm?userId=<%=userLoginId%>">마이페이지</a></li>
						</div>
						
						<%
						}
						}if(userLoggedIn ==null){
						%>
						<div class="myIcon">
							<li><img src="<%=request.getContextPath()%>/images/mypageicon.png" class="recent_login"
								onclick="noneLogin();" /></li>
						</div>
						<%}else{ %>
						<div class="myIcon">
							<button id="dmNew" onclick="gotoDMBoard();"> 0 </button>
							<li><img src="<%=request.getContextPath()%>/images/mypageIcon2.png" class="recent_login"
								onclick="recentLogin(this);" /></li>
						</div>
						<%} %>
					</ul>
				</div>
			</nav>
			<div class="notice"></div>
		</div>
	</div>
	<hr class="hide">
    
    
    <div class="bodyStart">
		
		
<style>
#dmNew{
	position:absolute;
	top: 9px;
	left: 418px;
	z-index: 100;
	border:none;
	background-color:red;
	color:white;
	padding: 2px 7px;
	border-radius:100%;
	font-weight: bold;
	font-size:15px;
}
#searchFood{
	top: 9% !important;

}

</style>