<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css"/>


<script>
$(document).ready(function(){
 	$.ajax({
 		url : "<%=request.getContextPath()%>/admin/adminInquire?accept=N",
 		type : "post",
 		success : function(data){
 			//console.log("rankList:"+data);
			let $table = $("<table></table>") 
			let $table2 = $("<div></div>")
 			let html = "<tr><th>사용자아이디</th><th>식당이름</th><th>종류</th><th>장소</th><th>설명</th><th>카테고리</th><th>전화번호</th><th>주차 가능여부</th><th>open time</th><th>close time</th><th>쉬는날</th><th>요청날짜</th><th></th></tr>";
			if(data.length==0){
			 	html+= "<tr><td class='tdStyle' colspan=13>요청된 식당이 없습니다.</td></tr>"
			 }
 			let html2 ="";
 			
 			for(var i in data){
 				var inq = data[i];						
 				console.log(typeof(inq.accept_yn));
 				
 				html += "<tr><td>"+inq.id+"</td>";
 				html += "<td>"+inq.r_name+"</td>";
 				html += "<td>"+inq.type+"</td>";
 				html += "<td>"+inq.location+"</td>";
 				html += "<td>"+inq.description+"</td>";
 				html += "<td>"+inq.category+"</td>";
 				html += "<td>"+inq.r_phone+"</td>";
 				html += "<td>"+inq.parking_yn+"</td>";
 				html += "<td>"+inq.open_time+"</td>";
 				html += "<td>"+inq.close_time+"</td>";
 				html += "<td>"+inq.holiday+"</td>";
 				html += "<td>"+inq.inq_date+"</td>";
 				html += "<td><button class='btn-inq' id='btn-inq' value="+inq.r_name+" onclick='inq();'>확인</button></td></tr>";
 					
 				html2 ="<div id='pageBar'>"+data[0].pageBar+"</div>";
 			}
			$table2.append(html2);
			$table.append(html);
 			$("#addRestaurants").html($table);
 			$("#addRestaurants2").html($table2);
 		},
 		error : function(jqxhr, textStatus, errorThrown){
 			console.log("인기순위 ajax 처리 실패");
 			//에러로그
 			console.log(jqxhr);
 			console.log(textStatus);
 			console.log(errorThrown);
 		}
 	});
 });
 function call1(cPage){
	 $.ajax({
	 		url : "<%=request.getContextPath()%>/admin/adminInquire?accept=N&cPage="+cPage,
	 		type : "post",
	 		success : function(data){
	 			//console.log("rankList:"+data);
				let $table = $("<table></table>") 
				let $table2 = $("<div></div>")
	 			let html = "<tr><th>사용자아이디</th><th>식당이름</th><th>종류</th><th>장소</th><th>설명</th><th>카테고리</th><th>전화번호</th><th>주차 가능여부</th><th>open time</th><th>close time</th><th>쉬는날</th><th>요청날짜</th><th></th></tr>";
	 			let html2 ="";
	 			
	 			for(var i in data){
	 				var inq = data[i];						
	 				console.log(typeof(inq.accept_yn));
	 				
	 				html += "<tr><td>"+inq.id+"</td>";
	 				html += "<td>"+inq.r_name+"</td>";
	 				html += "<td>"+inq.type+"</td>";
	 				html += "<td>"+inq.location+"</td>";
	 				html += "<td>"+inq.description+"</td>";
	 				html += "<td>"+inq.category+"</td>";
	 				html += "<td>"+inq.r_phone+"</td>";
	 				html += "<td>"+inq.parking_yn+"</td>";
	 				html += "<td>"+inq.open_time+"</td>";
	 				html += "<td>"+inq.close_time+"</td>";
	 				html += "<td>"+inq.holiday+"</td>";
	 				html += "<td>"+inq.inq_date+"</td>";
	 				html += "<td><button class='btn-inq' id='btn-inq' value="+inq.r_name+" onclick='inq();'>확인</button></td></tr>";
	 					
	 				html2 ="<div id='pageBar'>"+data[0].pageBar+"</div>";
	 			}
				$table2.append(html2);
				$table.append(html);
	 			$("#addRestaurants").html($table);
	 			$("#addRestaurants2").html($table2);
	 		},
	 		error : function(jqxhr, textStatus, errorThrown){
	 			console.log("인기순위 ajax 처리 실패");
	 			//에러로그
	 			console.log(jqxhr);
	 			console.log(textStatus);
	 			console.log(errorThrown);
	 		}
	 	});
 }
 function call(cPage)
 {
	 $.ajax({
	 		url : "<%=request.getContextPath()%>/admin/adminInquire?accept=Y&cPage="+cPage,
	 		type : "post",
	 		success : function(data){
	 			//console.log("rankList:"+data);
				let $table = $("<table></table>") 
				let $table2 = $("<div></div>")
	 			let html = "<tr><th>사용자아이디</th><th>식당이름</th><th>종류</th><th>장소</th><th>설명</th><th>카테고리</th><th>전화번호</th><th>주차 가능여부</th><th>open time</th><th>close time</th><th>쉬는날</th><th>요청날짜</th></tr>";
	 			let html2 ="";
	 			
	 			for(var i in data){
	 				var inq = data[i];						
	 				console.log(typeof(inq.accept_yn));
	 				html += "<tr><td>"+inq.id+"</td>";
	 				html += "<td>"+inq.r_name+"</td>";
	 				html += "<td>"+inq.type+"</td>";
	 				html += "<td>"+inq.location+"</td>";
	 				html += "<td>"+inq.description+"</td>";
	 				html += "<td>"+inq.category+"</td>";
	 				html += "<td>"+inq.r_phone+"</td>";
	 				html += "<td>"+inq.parking_yn+"</td>";
	 				html += "<td>"+inq.open_time+"</td>";
	 				html += "<td>"+inq.close_time+"</td>";
	 				html += "<td>"+inq.holiday+"</td>";
	 				html += "<td>"+inq.inq_date+"</td></tr>";
	 					
	 				html2 ="<div id='pageBar'>"+data[0].pageBar+"</div>";
	 			}
				$table2.append(html2);
				$table.append(html);
	 			$("#addchk").html($table);
	 			$("#addchk2").html($table2);
	 		},
	 		error : function(jqxhr, textStatus, errorThrown){
	 			console.log("인기순위 ajax 처리 실패");
	 			//에러로그
	 			console.log(jqxhr);
	 			console.log(textStatus);
	 			console.log(errorThrown);
 		}
 	});
 
 };
$(document).ready(function(){
 	$.ajax({
 		url : "<%=request.getContextPath()%>/admin/adminInquire?accept=Y",
 		type : "post",
 		success : function(data){
			let $table = $("<table></table>") 
			let $table2 = $("<div></div>")
 			let html = "<tr><th>사용자아이디</th><th>식당이름</th><th>종류</th><th>장소</th><th>설명</th><th>카테고리</th><th>전화번호</th><th>주차 가능여부</th><th>open time</th><th>close time</th><th>쉬는날</th><th>요청날짜</th></tr>";
 			let html2 ="";
 			
 			for(var i in data){
 				var inq = data[i];						
 				console.log(typeof(inq.accept_yn));
 				html += "<tr><td>"+inq.id+"</td>";
 				html += "<td>"+inq.r_name+"</td>";
 				html += "<td>"+inq.type+"</td>";
 				html += "<td>"+inq.location+"</td>";
 				html += "<td>"+inq.description+"</td>";
 				html += "<td>"+inq.category+"</td>";
 				html += "<td>"+inq.r_phone+"</td>";
 				html += "<td>"+inq.parking_yn+"</td>";
 				html += "<td>"+inq.open_time+"</td>";
 				html += "<td>"+inq.close_time+"</td>";
 				html += "<td>"+inq.holiday+"</td>";
 				html += "<td>"+inq.inq_date+"</td></tr>";
 			//	html += "<td><button class='btn-inq' id='btn-inq' value="+inq.r_name+" onclick='inq();'>확인</button></td></tr>";
 					
 				html2 ="<div id='pageBar'>"+data[0].pageBar+"</div>";
 			}
			$table2.append(html2);
			$table.append(html);
 			$("#addchk").html($table);
 			$("#addchk2").html($table2);
 		},
 		error : function(jqxhr, textStatus, errorThrown){
 			console.log("인기순위 ajax 처리 실패");
 			//에러로그
 			console.log(jqxhr);
 			console.log(textStatus);
 			console.log(errorThrown);
 		}
 	});
 });
 
 
function inq(){
           var a=document.getElementById("btn-inq");
           console.log(a.value);
        location.href="<%=request.getContextPath()%>/admin/adminInqChk?r_name="+a.value;	
};
</script>
<div class="naccs">
	<div class="grid">
		<div class="gc gc--1-of-3">
			<div class="menu">
				<div 
					onclick="location.href='<%=request.getContextPath()%>/admin/adminForm'">
					<span class="light"></span><span style="color: gray;">방문현황</span>
				</div>
				<div onclick="location.href='<%=request.getContextPath()%>/admin/adminList'">
					<span class="light"></span><span style="color: gray">유저정보</span>
				</div>
				<div class="active" onclick="location.href='<%=request.getContextPath()%>/admin/adminInq'">
					<span class="light"></span><span style="color: orange;">맛집추가신청확인</span>
				</div>
			</div>
		</div>
		<div class="admin_user_wrapper">
			<div>
				<h2>[요청]</h2>
				<div id="addRestaurants"></div>
				<div id="addRestaurants2"></div>
			</div>
		
		<div>
				<h2>[요청확인]</h2>
				<div id="addchk"></div>
				<div id="addchk2"></div>
			</div>
			</div>
	</div>
</div>
<style>
th{padding:3px 17px;}
td{padding:5px;}
#addRestaurants2{padding-bottom:50px;}
.bodyStart{padding-top:150px}
.tdStyle{padding:10px 0;}
</style>
			
			
<%@ include file="/WEB-INF/views/common/footer.jsp" %>