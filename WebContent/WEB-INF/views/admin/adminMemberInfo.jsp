<%@page import="user.model.vo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%List<User> infoList = (List<User>)request.getAttribute("list");
String pageBar=(String)request.getAttribute("pageBar");
User user1=(User)request.getAttribute("user1");
int cPage=Integer.parseInt(request.getAttribute("cPage").toString());
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<style>
.bodyStart{padding-top:150px}
</style>
<script>
$(function(){

	var $autoComplete_search = $("#autoComplete_search");
	$autoComplete_search.hide();//페이지 최초 로딩시 조회결과 ul태그는 안보임처리한다.
	
	$("#srchName_admin").on('keyup', function(e){
		//방향키 제어 ArrowDown, ArrowUp, Enter
		console.log(e.key);
		
		var $sel = $(".adminsel");
		var $li = $autoComplete_search.children("li");

		if(e.key == 'ArrowDown'){
			
			if($sel.length == 0){
				$li.first().addClass("adminsel");
			}
			else{
				$sel.removeClass("adminsel")
					.next()
					.addClass("adminsel");
			}
		}
		else if(e.key == 'ArrowUp'){
			if($sel.length != 0){
				$sel.removeClass("adminsel")
					.prev()
					.addClass("adminsel");
			}
			else{
				$li.last().addClass("adminsel");
			}
			
		}
		else if(e.key == 'Enter'){
			var $mem=$(".table_searchUser");

			$mem.children().first().children().next().remove();
			$(this).val('');
			var test=$sel.text().split(':');
			var id=test[1].substr(0,test[1].indexOf('이름')-2);
			var name=test[2].substr(0,test[2].indexOf('성별')-2);
			var gender=test[3].substr(0,test[3].indexOf('나이')-2);
			var age=test[4].substr(0,test[4].indexOf('주소')-2);
			var address=test[5].substr(0,test[5].indexOf('전화번호')-2);
			var phone=test[6];
			var gender
			$autoComplete_search.hide()
						 .children()
						 .remove();
			var html='';
		
			html +="<tr><td>1</td>"
				html += "<td scope='col'>"+id+'</td>';
				html += "<td scope='col'>"+name+'</td>';
				html += "<td scope='col'>"+gender+'</td>';
				html += "<td scope='col'>"+age+'</td>';
				html += "<td scope='col'>"+address+'</td>';
				html += "<td scope='col'>"+phone+'</td>';
				html +="<td scope='col'><button class='btn-delete' id='delete' value="+id+" onclick='deleteUser();'>삭제</button></td>";
			$mem.children().first().append(html);
<%-- 			location.href="<%=request.getContextPath()%>/admin/Idfind?id="+id; --%>
		}
		else {
			var srchNameVal = $(this).val().trim();
			
			//공백 입력시 ajax요청 보내지 않음.
			if(srchNameVal.length == 0) return;
			
			$.ajax({
				url: "<%=request.getContextPath()%>/admin/UserFind",
				type: "post",
				//data: "srchName="+srchNameVal,
				data: {srchName: srchNameVal},//객체로 전달해도 jquery가 직렬화처리
				success: function(data){

					//조회된 결과가 없는 경우
					if(data.length==0){
						$autoComplete_search.hide();
					}
					//조회결과가 있는 경우
					else{					
						var html = "";
						$(data).each((idx, user) => {
						
// 							html += '<li><span class="srchval">'+user.name+'</span></li>';
							
							html += "<li> 아이디:"+(user.id).replace(srchNameVal, '<span class="admin_srchval">'+srchNameVal+'</span>');
							html += "&nbsp/&nbsp이름:"+(user.name).replace(srchNameVal, '<span class="admin_srchval">'+srchNameVal+'</span>');
							html += "&nbsp/&nbsp성별:"+(user.gender).replace(srchNameVal, '<span class="admin_srchval">'+srchNameVal+'</span>');
							html += "&nbsp/&nbsp나이:"+(user.age).replace(srchNameVal, '<span class="admin_srchval">'+srchNameVal+'</span>');
							html += "&nbsp/&nbsp주소:"+(user.address).replace(srchNameVal, '<span class="admin_srchval">'+srchNameVal+'</span>');
							html += "&nbsp/&nbsp전화번호:"+(user.phone).replace(srchNameVal, '<span class="admin_srchval">'+srchNameVal+'</span>')+"</li>";

						});
						$autoComplete_search.append().html(html).fadeIn(300);
						
						
					}
				},
				error: function(jqxhr, textStatus, errorThrown){
					console.log("ajax처리실패!", jqxhr, textStatus, errorThrown);
				}
			});//end of $.ajax
			
			
		}//end of else
	
		
			
		//이벤트버블링(자식 =>부모)을 이용한 처리
		//요소를 선택한 경우
		$autoComplete_search.on('click', 'li', e => {
			//화살표함수 내에서 this는 무조건 window
			$("#srchName_admin").val($(e.target).text());
// 			var $mem = $("#tbl-find-Member");
			var $mem=$(".table_searchUser");

			$mem.children().first().children().next().remove();
			$(this).val('');
			var test=$(e.target).text().split(':');
			var id=test[1].substr(0,test[1].indexOf('이름')-2);
			var name=test[2].substr(0,test[2].indexOf('성별')-2);
			var gender=test[3].substr(0,test[3].indexOf('나이')-2);
			var age=test[4].substr(0,test[4].indexOf('주소')-2);
			var address=test[5].substr(0,test[5].indexOf('전화번호')-2);
			var phone=test[6];
			var gender
			$autoComplete_search.hide()
						 .children()
						 .remove();
			var html='';
			html +="<tr><td>1</td>"
			html += "<td >"+id+'</td>';
			html += "<td>"+name+'</td>';
			html += "<td>"+gender+'</td>';
			html += "<td >"+age+'</td>';
			html += "<td >"+address+'</td>';
			html += "<td >"+phone+'</td>';
			html +="<td ><button class='btn-delete' id='delete' value="+id+" onclick='deleteUser();'>삭제</button></td>";
			$mem.children().first().append(html);
		}).on('mouseover','li', e=>{
			$(e.target).addClass("adminsel")
					   .siblings()
					   .removeClass("adminsel");
		}).on('mouseout','li', e=>{
			$(e.target).removeClass("adminsel");
		});		
	});
});
function deleteUser(){
	
	var id=document.getElementById("delete");
	        if(!confirm("정말 삭제하시겠습니까?")) return;
	        //삭제처리후 돌아올 현재게시판번호도 함께 전송함.
	        location.href="<%=request.getContextPath()%>/user/userDelete?Id="+id.value.trim();
	   
}
$(function(){
    $(".btn-delete").click(function(){
 
        if(!confirm("정말 삭제하시겠습니까?")) return;
        //삭제처리후 돌아올 현재게시판번호도 함께 전송함.
        location.href="<%=request.getContextPath()%>/user/userDelete?Id="+$(this).val();
    });
	
});
    //삭제버튼 클릭시
</script>
<div class="naccs">
	<div class="grid">
		<div class="gc gc--1-of-3">
			<div class="menu">
				<div 
					onclick="location.href='<%=request.getContextPath()%>/admin/adminForm'">
					<span class="light"></span><span style="color: gray;">방문현황</span>
				</div>
				<div class="active"
					onclick="location.href='<%=request.getContextPath()%>/admin/adminList'">
					<span class="light"></span><span style="color: orange">유저정보</span>
				</div>
				<div onclick="location.href='<%=request.getContextPath()%>/admin/adminInq'">
					<span class="light"></span><span style="color: gray;">맛집추가신청확인</span>
				</div>
			</div>
		</div>
		<div class="admin_user_wrapper">
			<div>
				<h2>[검색된 회원 정보]</h2>
				<div class="admin_member_info">
					<input type="text" id="srchName_admin" placeholder="검색어를 입력해주세요." />

					<ul id="autoComplete_search"></ul>
				</div>
				<div class="admin_member_info_list">
					<table class="table_searchUser">
						<tr>
							<th>No</th>
							<th>아이디</th>
							<th>이름</th>
							<th>성별</th>
							<th>나이</th>
							<th>주소</th>
							<th>전화번호</th>
							<th></th>
						</tr>
						<tr>
							<td colspan="8">검색어를 입력해주세요.</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="admin_user_info">
				<h2>[가입회원 정보]</h2>
				<table class="admin_user_info_table">
					<tr>
						<th>No</th>
						<th>아이디</th>
						<th>이름</th>
						<th>성별</th>
						<th>나이</th>
						<th>주소</th>
						<th>전화번호</th>
						<th></th>
					</tr>
					<%
						int i = 1;
						for (User u : infoList) {
							;
					%>
					<tr>
						<td scope="row"><%=(cPage - 1) * 5 + i%></td>
						<td><%=u.getUserId()%></td>
						<td><%=u.getName()%></td>
						<td><%=u.getGender()%></td>
						<td><%=u.getAge()%></td>
						<td><%=u.getAddress()%></td>
						<td><%=u.getPhone()%></td>
						<td><button class="btn-delete" value="<%=u.getUserId()%>">삭제</button></td>
					</tr>
					<%
						i++;
						}
					%>
				</table>
				<div id='pageBar'>
					<%=pageBar%>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
