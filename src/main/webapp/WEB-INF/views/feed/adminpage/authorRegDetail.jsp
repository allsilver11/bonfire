<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>${vo.id }님의 작가 신청서</h1>
<form:form method = "post" onsubmit = "check(event)">
<table border = "1" width = "800">
	<tr>
		<th>신청 날짜</th>
		<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${vo.adddate }"/></td>
	</tr>
	<tr>
		<th>한마디 요약</th>
		<td>${vo.pres }</td>
	</tr>
	<tr>
		<th>테마</th>
		<td>${vo.theme }</td>
	</tr>
	<tr>
		<th>신청 사유</th>
		<td>${vo.intro }</td>
	</tr>
</table>
<input type = "submit" value = "승인" name = "approve" formaction = "${cp }/author/approveOne?num=${vo.num}">
<input type = "submit" value = "거부" name = "deny" formaction = "${cp }/author/denyOne?num=${vo.num}">
</form:form>
<div id = "buttons">
	<a href="${cp }/authorRegList">목록</a>
</div>
<script>
function check(e){
	if(confirm("이 항목을 승인 혹은 거절 하시겠습니까?")==true) {
		return true;
	} else {
		e.preventDefault();
	}
}
</script>