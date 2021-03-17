<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- login/join.jsp -->
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<style>
	label{width:100px;display:inline-block;}
	#join_form{width:400px;margin:auto;margin-top:70px;}
	#join_form input{margin-bottom:10px;}
	#join_form input[type="text"]{width:285px;}
	#join_form input[type="email"]{width:285px;}
	#join_form input[type="password"]{width:390px;}
	#join_form input[type="date"]{width:285px;}
/* 	#join_form a{border:1px solid black; width:280px;display:inline-block; text-align:center; */
/* 		text-decoration: none; color:black; font-weight:bold; background-color: #eeeeee;box-shadow: 2px 2px 2px black;} */
	.form-control{display:inline-block;font-size:25px;}
	.btn.btn-white.w-100{display:inline-block;font-size:25px;font-weight:bold;}
	.btn.btn-white.w-100.zip{width:41%!important;}
	.btn.btn-white.w-100.join{width:98%!important;}
</style>
<div style="width:1200px;height:910px;">
	<div id="join_form">
		<form:form method="post" action="${cp }/join" id="joinForm">
			<label for="id">아이디</label><span id="idMsg"></span><br>
			<input type="text" name="id" id="id" class="form-control" required="required" placeholder="영문/숫자 4~8자리" style="width:390px;">
			<label for="pwd">비밀번호</label><span id="pwdMsg"></span><br>
			<input type="password" name="pwd" id="pwd" class="form-control" required="required" placeholder="영문/숫자 4~8자리">
			<label for="check">비밀번호확인</label><span id="checkMsg"></span><br>
			<input type="password" id="check" class="form-control" required="required">
			<label for="name">이름</label>
			<input type="text" name="name" id="name" class="form-control" required="required"><br>
			<label for="email">이메일</label>
			<input type="email" name="email" id="email" class="form-control" required="required"><br>
			<label for="birth">생년월일</label>
			<input type="date" name="birth" id="birth" class="form-control" required="required"><br>
			<label for="gender">성별</label>
			남 <input type="radio" name="gender" value="M" checked>
			여 <input type="radio" name="gender" value="W"><br>
			<label for="phone">핸드폰번호</label>
			<input type="text" name="phone" id="phone" class="form-control" required="required" placeholder="-없이 숫자만 적어주세요"><span id="phoneMsg"></span><br>
			<label for="zipNo">우편번호</label>
			<input type="text" name="zipno" id="zipno" class="form-control" readonly style="width:115px;" required="required">
			<input type="button" class="btn btn-white w-100 zip" value="주소검색" onclick="goPopup();"><br>
			<label for="roadfulladdr">도로명주소</label>
			<input type="text" name="roadfulladdr" id="roadfulladdr" class="form-control" style="width:285px" required="required" readonly><br>
			<label for="addrdetail">상세주소</label>
			<input type="text" name="addrdetail" id="addrdetail" class="form-control" style="width:285px" value=""><br>
			<a href="javascript:joinForm.submit()" class="btn btn-white w-100 join">가입하기</a>
		</form:form>
	</div>
</div>
<script>
	var popupWidth = 770;
	var popupHeight = 420;
	
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
	
	var popupY= (window.screen.height / 2) - (popupHeight / 2);
	// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
	$(function(){
		var error1=0;
		var error2=0;
		var error3=0;
		var error4=0;
		$("#id").on('keyup',function(){
			var id=$("#id").val();
			
			for(let i=0;i<id.length;i++){
				if(!((id.charAt(i)>='a'&&id.charAt(i)<='z')||
						(id.charAt(i)>='A'&&id.charAt(i)<='Z')||
						(id.charAt(i)>='0'&&id.charAt(i)<='9'))){
					$("#idMsg").html("영문/숫자로 입력해주세요.").css("color","red");
					error1++;
					return;
				}else{
					if(!(id.length>=4 && id.length<=8)){
						$("#idMsg").html("영문/숫자 4~8자리입력해주세요.").css("color","red");
						error1++;
						return;
					}else{
						$("#idMsg").html("사용가능합니다.").css("color","blue");
 						error1=0;
						$.getJSON('${pageContext.request.contextPath}/idcheck.json', {"id":id}, function(data) {
							if ($(data)[0].code=='used'){
								$("#idMsg").html("이미 사용중인 아이디입니다").css("color","red");
								error1++;
							}
							return;
						});
					}
				}
			}
			
		});
		$("#pwd").on('keyup',function(){
			var pwd=$("#pwd").val();
			for(let i=0;i<pwd.length;i++){
				if(!((pwd.charAt(i)>='a'&&pwd.charAt(i)<='z')||
						(pwd.charAt(i)>='A'&&pwd.charAt(i)<='Z')||
						(pwd.charAt(i)>='0'&&pwd.charAt(i)<='9'))){
					$("#pwdMsg").html("영문/숫자로 입력해주세요.").css("color","red");
					error2++;
					return;
				}else{
					if(!(pwd.length>=4 && pwd.length<=8)){
						$("#pwdMsg").html("영문/숫자 4~8자리입력해주세요.").css("color","red");
						error2++;
					}else{
						$("#pwdMsg").html("사용가능합니다.").css("color","blue");
 						error2=0;
					}
				}
			}
		});
		$("#phone").on('keyup',function(){
			var phone=$("#phone").val();
			for(let i=0;i<phone.length;i++){
				if(!(phone.charAt(i)>='0'&&phone.charAt(i)<='9')){
					$("#phoneMsg").html("숫자만 입력해주세요.").css("color","red");
					error3++;
					return;
				}else{
					$("#phoneMsg").html("");
					error3=0;
				}
			}
		});
		$("#check").on('keyup',function(){
			var pwd=$("#pwd").val();
			if(pwd==$("#check").val()){
				$("#checkMsg").html("일치합니다.").css("color","blue");
				error4=0;
			}else{
				$("#checkMsg").html("다시 확인해주세요.").css("color","red");
				error4++;
			}
		});
		$("#joinForm").submit(function(){
			if($("#birth").val()==""){
				alert("생년월일을 선택해주세요.");
				return false;
			}
			if(error1!=0 ||error2!=0||error3!=0||error4!=0){
				alert("한번더 확인해주세요.");
				return false;
			}
		})
	});
	
	
	function goPopup(){
	    var pop = window.open("${pageContext.request.contextPath}/jusoPopup.jsp","pop","width=770,height=420, scrollbars=yes, resizable=yes"); 
	    
// 	    var pop = window.open("${pageContext.request.contextPath}/jusoPopup.jsp","pop","width=770,height=420, scrollbars=yes, resizable=yes,left="+ popupX + ", top="+ popupY); 
	}
	function jusoCallBack(roadAddrPart1,roadAddrPart2,zipNo){
		$("#roadfulladdr").val(roadAddrPart1+roadAddrPart2);
		$("#zipno").val(zipNo);
	}
</script>