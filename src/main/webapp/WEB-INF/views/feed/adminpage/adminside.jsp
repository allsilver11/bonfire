<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- adminside.jsp -->
<style>
	li{list-style:none;}
</style>
<div>
	<span><b>관리자메뉴</b></span>
	<ul>
		<li><a href="${cp }/manageMembers_List_All">회원관리</a></li>
		<li><a href="${cp }/authorRegList">작가신청관리</a></li>
		<li><a href="${cp }/qna/insert">공지글작성</a></li>
		<li><a href="#">통계</a></li>
	</ul>
</div>