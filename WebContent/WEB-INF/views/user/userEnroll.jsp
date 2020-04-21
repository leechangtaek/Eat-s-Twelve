<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>
function validate() {
    var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식

    var id = document.getElementById("uid");
    var pw = document.getElementById("upw");

       var num1 = document.getElementById("num1");
       var num2 = document.getElementById("num2");

       var arrNum1 = new Array(); // 주민번호 앞자리숫자 6개를 담을 배열
       var arrNum2 = new Array(); // 주민번호 뒷자리숫자 7개를 담을 배열


    //이름, 아이디,패스워드 //    
    if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
        return false;
    }

    if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
        return false;
    }

    if(join.upw.value != join.checkupw.value) {
        alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
        join.checkupw.value = "";
        join.checkupw.focus();
        return false;
    }
    if(join.uname.value=="") {
        alert("이름을 입력해 주세요");
        join.uname.focus();
        return false;
                 }
    
//email//
if(email.value=="") {
    alert("이메일을 입력해 주세요");
    email.focus();
    return false;
}

if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
    return false;
}

//전화번호//
$(function(){
	$(".phone-number-check").on('keydown', function(e){
  	// 숫자만 입력받기
    var trans_num = $(this).val().replace(/-/gi,'');
		var k = e.keyCode;
				
		if(trans_num.length >= 11 && ((k >= 48 && k <=126) || (k >= 12592 && k <= 12687 || k==32 || k==229 || (k>=45032 && k<=55203)) ))
		{
            
			e.preventDefault();
		}
  }).on('blur', function(){
  if($(this).val() == '') return;
				
        var trans_num = $(this).val().replace(/-/gi,'');
        if(trans_num != null && trans_num != '')
        {
            if(trans_num.length==11 || trans_num.length==10) 
            {   
                var regExp_ctn = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
                if(regExp_ctn.test(trans_num))
                {
                    trans_num = trans_num.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/, "$1-$2-$3");                  
                    $(this).val(trans_num);
                }
                else
                {
                    alert("유효하지 않은 전화번호 입니다.");
                    $(this).val("");
                    $(this).focus();
                }
            }
            else 
            {
                alert("유효하지 않은 전화번호 입니다.");
                $(this).val("");
                $(this).focus();
            }
        }
  });  
});


function check(re, what, message) {
    if(re.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    return false;
}

}
</script>

<style>
.enroll_container_wrapper{ width:1050px;min-height:610px;}
.enroll_container_wrapper h2{text-align:center; color:orange;}
section#enroll-container {text-align:center; border:1px solid gray; border-radius:3%; width:800px; height:480px; margin:0 auto;}
section#enroll-container input{margin:9px;}
section#enroll-container table{margin:0 auto;}
section#enroll-container table th {padding:10px 10px; text-align:right;}
section#enroll-container table td {padding:0 10px; text-align:left;}
.userInfoView_button{padding-top:30px;}

/* 회원수정페이지 */
section#enroll-container input[readonly]{
	background:lightgray;
}
</style>
<body>
	<form name="checkIdDuplicateFrm">
		<input type="hidden" name="uid" />
	</form>
	
	<div class='enroll_container_wrapper'>
		<h2>회원가입</h2>
		<section id=enroll-container>
		<form name="join" onsubmit="return validate();" method="post"
			action="<%=request.getContextPath()%>/user/userEnrollServlet">
			<br>
			<table align="center">
				<tr>
					<th >아이디</th>
					<td><input type="text" name="uid" id="uid" size="20"
						maxlength="12"></input>&nbsp4~12자의 영문 대소문자와 숫자로만 입력</td>
					<td><input type="button" value="아이디 중복검사"
						onclick="checkIdDuplicate();" /></td>
					<input type="hidden" id="idValid" value="0" />
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="upw" id="upw" size="21"
						maxlength="12"></input>&nbsp4~12자의 영문 대소문자와 숫자로만 입력</td>
				</tr>

				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" name="checkupw" id="checkupw"
						size="21" maxlength="12"></td>
				</tr>

				<tr>
					<th>이름</th>
					<td><input type="text" name="uname" id="uname" size="25"></input></td>
				</tr>


				<tr>
					<th>email</th>
					<td><input type="text" style="width: 170px" id="email"
						name="email" /> ex)wjdxo513@naver.com</td>
				</tr>

				<tr>
					<th>주소</th>
					<td><input type="text" name="address" id="address"></input></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="phone" name="phone" id="phone"
						class="phone-number-check"></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" id="age"></td>
				</tr>
				<tr>
					<th>관심음식</th>
					<td><input type="checkbox" name="favorite" value="한식">한식</input>
						<input type="checkbox" name="favorite" value="일식">일식</input> <input
						type="checkbox" name="favorite" value="중식">중식</input> <input
						type="checkbox" name="favorite" value="양식">양식</input></td>
				</tr>

				<tr>
					<th>성별</th>
					<td><input type="radio" name="gender" id="male" value="M"
						checked="checked"> <label for="male">남</label>
						&nbsp;&nbsp; <input type="radio" name="gender" id="female"
						value="F"> <label for="female">여</label></td>
				</tr>
			</table>
			<br> <br>
			<p align="center">
				<input type="submit" name="submit" value="회원 가입"></input>
				<input type="reset" name="reset" value="초기화"></input>
				<input type="button" value="뒤로" onclick="login();"></input>
			</p>

		</form>
</section>

	</div>
</body>
</html>
<script>
function checkIdDuplicate(){
	var $uid = $("#uid");
	//유효성검사
	if($uid.val().trim().length < 4){
		alert("아이디는 4글자 이상이어야 합니다.");
		return;
	}
	
	//폼을 팝업창에 제출
	//frm.target = 팝업창이름
	var url = "<%=request.getContextPath()%>/user/checkIdDuplicate";
	var title = "checkIdDuplicatePopup";
	var spec = "left=500px, top=100px, width=300px, height=200px";
	var popup = open("", title, spec);
	
	var frm = document.checkIdDuplicateFrm;
	frm.action = url;
	frm.target = title;
	frm.method = "POST";
	frm.uid.value = $uid.val().trim();
	frm.submit();
}
function login(){
	resizeTo(600,500);
	location.href= "<%=request.getContextPath()%>/user/userLoginForm";
}
</script>
