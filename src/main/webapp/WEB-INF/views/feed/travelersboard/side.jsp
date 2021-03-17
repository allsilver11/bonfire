<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- travelersboard/side.jsp -->
<style>
	li{list-style:none;}
	#traveler_side a{text-decoration: none;color:#bbbbbb;}
	#traveler_side a:hover{color: black;}
	
</style>
<div id="traveler_side">
	<span style="font-size:30px;position:relative;left:30px;">지역</span>
	<ul>
		<li><a href="${cp }/feedboard_selectByRegion?cname=서울">서울</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=강원">강원</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=경기">경기</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=경남">경남</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=경북">경북</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=충남">충남</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=충북">충북</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=전남">전남</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=전북">전북</a></li>
		<li><a href="${cp }/feedboard_selectByRegion?cname=제주">제주</a></li>
	</ul>
</div>