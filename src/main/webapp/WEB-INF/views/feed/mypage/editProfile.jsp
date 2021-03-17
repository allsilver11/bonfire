<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- editProfile.jsp -->
<style>
	#editProfile_wrap{width:900px;padding-top:30px;}
	#editProfile_form{width:400px;margin:auto;}
	#editProfile_span{font-size:30px;font-weight: bold;border-bottom: double;}
	.form-control{font-size:25px;font-weight: bold;}
	input{margin-top:10px;margin-bottom:10px;}
	.btn.btn-white.w-100{font-size:25px;font-weight: bold;}
</style>
<div id="editProfile_wrap">
		<div id="editProfile_form">
		<span id="#editProfile_span">프로필 수정</span>
		<form:form method="post" action="${cp }/editProfile" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${vo.id }"><br>
			<label>닉네임</label><input type="text" class="form-control" name="nickname" value="${vo.nickname }">
			<label>소개</label><input type="text" class="form-control" name="intro" value="${vo.intro }" placeholder="50자 이내">
			<label>배너사진</label><input type="file" class="form-control" name="bfile">
			<label>프로필사진</label><input type="file" class="form-control" name="pfile"><br>
			<input type="submit" class="btn btn-white w-100"  value="수정">
		</form:form>
	</div>
</div>