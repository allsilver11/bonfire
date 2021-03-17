<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.form-control{display:inline-block;width:200px;height:40px;font-size:20px;font-weight:bold;}

</style>
</head>
<body>
<div style="width:1200px;height:650px;padding-top:200px;">
	<div style="width:250px;height:150px;margin:auto;">
<form:form action="${cp }/qna/delete" method="post">
	<div style="margin-bottom:10px;">
	글번호 <br>
	<input type="text" name="num" value="${num }" class="form-control"><br>
	비밀번호 <br>
	<input type="password" name="pwd" class="form-control">
	<input type="submit" value="삭제">
	</div>
</form:form>
</div>
</div>
</body>
</html>