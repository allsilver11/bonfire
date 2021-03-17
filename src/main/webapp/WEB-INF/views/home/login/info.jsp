<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- info.jsp -->
<div>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>${vo.id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${vo.email }</td>
		</tr>
		<tr>
			<td>생일</td>
			<td>${vo.birth }</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${vo.gender }</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${vo.phone }</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td>${vo.zipno }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${vo.roadfulladdr }</td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td>${vo.addrdetail }</td>
		</tr>
	</table>
	<input type="button" value="수정" onclick="location.href='${cp}/userModify'">
	<input type="button" value="탈퇴" onclick="location.href='${cp}/withdraw'">
</div>