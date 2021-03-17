<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- info.jsp -->
<style>
	.btn.btn-white.w-100{font-size:20px;font-weight:bold;}
	.btn.btn-white.w-100.edit{
		float:right;
		width:10%!important;
	}
	.btn.btn-white.w-100.withdrawal{
		float:right;
		width:10%!important;
	}
</style>
<div>
	<table border="1" class="table table-vcenter table-mobile-md card-table">
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
	<div style="width:900px;">
		<input type="button" value="탈퇴" onclick="location.href='${cp}/withdraw'" class="btn btn-white w-100 withdrawal">
		<input type="button" value="수정" onclick="location.href='${cp}/userModify'" class="btn btn-white w-100 edit">
	</div>
</div>