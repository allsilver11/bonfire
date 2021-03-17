<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- login.jsp -->
<style>
	label{display:inline-block;}
	#login_btns{width:250px;text-align: center;}
	#login_btns a{border:1px solid black; width:250px;display:inline-block;
		text-decoration: none; color:black; font-weight:bold; background-color: #eeeeee;margin-top:10px; margin-bottom:0px;
		box-shadow: 2px 2px 2px black;}
	input{width:250px;height:20px;margin-bottom:5px;}
	.form-control{height:30px;font-size:25px;}
</style>
<div style="width:1200px;height:650px;padding-top:200px;">
	<div style="width:250px;height:150px;margin:auto;">
		<form:form method="post" action="login" id="login_form">
			<div style="margin-bottom:10px;">
				<input type="text" class="form-control" name="username" value="${id }" id="id" required placeholder="아이디">
				<input type="password" class="form-control" name="password" id="pwd" required placeholder="비밀번호">
			</div>
			<div id="login_btns">
				<a href="javascript:login_form.submit()">로그인</a><br>
				<a href="${cp }/join">회원가입</a><br>
				<a href="${cp }/searchIdPwd">id/pwd 찾기</a>
			</div>
		</form:form>
	</div>
</div>
<script>
	$(function(){
		$("#id").keydown(function(key) {
			if (key.keyCode == 13) {
				$("#login_form").submit();
			}
		});
		$("#pwd").keydown(function(key) {
			if (key.keyCode == 13) {
				$("#login_form").submit();
			}
		});
	})
</script>