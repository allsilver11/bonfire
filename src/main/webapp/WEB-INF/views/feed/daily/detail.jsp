<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>${vo.title }</h1>

<table border = "1" width = "600">
	<tr>
		<th>제목</th>
		<td>${vo.title }</td>
	</tr>
	<tr>
		<th>날짜</th>
		<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${vo.adddate }"/></td>
		
	</tr>
	<tr>
		<th>글내용</th>
		<td>${vo.content }</td>
	</tr>
</table>

<div id = "buttons">
	<a href="${cp }/daily/delete?num=${vo.num}">삭제</a>
	<a href="${cp }/daily/update?num=${vo.num}">수정</a>
</div>