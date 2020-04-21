<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="styleSheet" href="<%=request.getContextPath()%>/css/admin.css" />
<link rel="styleSheet" href="<%=request.getContextPath()%>/css/user.css" />
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<%
Calendar c1 = new GregorianCalendar();
String[]day=new String[7];
c1.add(Calendar.DATE,0);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷 
day[0] = sdf.format(c1.getTime()); // String으로 저장
for(int i=1;i<7;i++){
c1.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷 
day[i] = sdf.format(c1.getTime()); // String으로 저장
}
%>
<style>
.bodyStart{padding-top:150px}
</style>
<script>


$(document).ready(function(){ 
 	$.ajax({
 		url : "<%=request.getContextPath()%>/admin/adminchart",
 		type : "post",
 		success : function(data1){
 			google.charts.load('current', {'packages':['bar']});
 		 google.charts.setOnLoadCallback(drawChart);
 		function drawChart(){
 			 var data = new google.visualization.DataTable();
 			  data.addColumn('string', '최근일주일');
 			  data.addColumn('number', '접속수');
 			
 			  data.addRows([
 			    ['<%=day[6]%>', data1[0]==null?0:data1[0].total],
 			    ['<%=day[5]%>', data1[1]==null?0:data1[1].total],
 			    ['<%=day[4]%>', data1[2]==null?0:data1[2].total],
 			    ['<%=day[3]%>', data1[3]==null?0:data1[3].total],
 			    ['<%=day[2]%>', data1[4]==null?0:data1[4].total],
 			    ['<%=day[1]%>', data1[5]==null?0:data1[5].total],
 			    ['<%=day[0]%>', data1[6]==null?0:data1[6].total]
 			  ]);
 			  var options ={
 			    title: '총 접속수',
 			    height: 700
 			  };
 			  var chart=new google.charts.Bar(document.getElementById('chart_div'));
 			  chart.draw(data, google.charts.Bar.convertOptions(options));			
 		}},
 		error : function(jqxhr, textStatus, errorThrown){
 			console.log("인기순위 ajax 처리 실패");
 			//에러로그
 			console.log(jqxhr);
 			console.log(textStatus);
 			console.log(errorThrown);
 		}
 	});
 });



$(document).ready(function(){
 	console.log("dd");
 	$.ajax({
 		url : "<%=request.getContextPath()%>/admin/adminvisit",
 		type : "post",
 		success : function(data){
 			//console.log("rankList:"+data);
			let $table = $("<table></table>") 
			let $table2 = $("<div></div>")
 			let html = "<tr><th>아이디</th><th>방문 시간</th><th>횟수</th></tr>";
 			let html2 ="";
 			console.log(data[0].pageBar);
 			for(var i in data){
 				var visit = data[i];						
 				html += "<tr><td>"+visit.id+"</td>";
 				html += "<td>"+visit.visit_time+"</td>";
 				html += "<td>"+visit.total+"</td></tr>";
 				
 			}
 				html2 ="<div id='pageBar'>"+data[0].pageBar+"</div>";
			$table2.append(html2);
			$table.append(html);
 			$("#visit").html($table);
 			$("#visit2").html($table2);
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
 
 
 function call(cPage){
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/adminvisit?cPage="+cPage,
		type : "post",
		success : function(data){
			//console.log("rankList:"+data);
		let $table = $("<table></table>") 
		let $table2 = $("<div></div>")
			let html = "<tr><th>아이디</th><th>방문 시간</th><th>횟수</th></tr>";
			let html2 ="";
			for(var i in data){
				var visit = data[i];						
				html += "<tr><td>"+visit.id+"</td>";
				html += "<td>"+visit.visit_time+"</td>";
				html += "<td>"+visit.total+"</td></tr>";
				
			}
				html2 ="<div id='pageBar'>"+data[0].pageBar+"</div>";
		$table2.append(html2);
		$table.append(html);
			$("#visit").html($table);
			$("#visit2").html($table2);
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
</script>
<div class="naccs">
	<div class="grid">
    	<div class="gc gc--1-of-3">
        	<div class="menu">
               <div class="active" onclick="location.href='<%=request.getContextPath()%>/admin/adminForm'"><span class="light"></span><span style="color: gray; color:orange">방문현황</span></div>
               <div onclick="location.href='<%=request.getContextPath()%>/admin/adminList'"><span class="light"></span><span style="color: gray;" >유저정보</span></div>
               <div onclick="location.href='<%=request.getContextPath()%>/admin/adminInq'"><span class="light"></span><span style="color: gray;">맛집추가신청확인</span></div>
             </div>
     	</div>
  
<div class="admin_chart">
	<div id="chart_div"></div>
</div>

<div class="admin_visit_table">
	<h2 class="admin_visit_title">실시간방문목록</h2>
	<div id="visit"></div>
	<div id="visit2"></div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>