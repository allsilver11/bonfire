<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- side.jsp -->
<style>
	li{list-style:none;}
	#feed_profile{border-right:2px solid black;border-bottom:2px solid black;text-align:center;
		border-radius: 10px;}
	#profileImg img{width:100px;height:100px;border-radius: 50%;}
	#feed_side_menu a{text-decoration: none; color:#bbbbbb;}
	#feed_side_menu a:hover{color:black;}
	
</style>
<div>
	<div id="feed_profile">
		<div id="profileImg">
			<c:choose>
				<c:when test="${proVo.pfilename!=null }">
					<img src="${cp }/resources/upload/profile/${proVo.pfilename}">
				</c:when>
				<c:otherwise>
					<img src="${cp }/resources/images/profileIcon.png">
				</c:otherwise>			
			</c:choose>
		</div>
		<span style="font-weight: bold;">${proVo.nickname }</span><br>
		<p>${proVo.intro }</p>
		<span>팔로우 ${proVo.follow }</span><br>
		<span>팔로워 ${proVo.follower }</span><br>
		<c:if test="${feedId==id }">
			<a href="${cp }/editProfile">프로필수정</a>
		</c:if>
	</div><br>
	<ul id="feed_side_menu">
		<c:if test="${feedId==id }">
			<li></li>
			<li><a href="${cp }/daily_main">일기장</a></li>
			<li><a href="${cp }/scrapboard_feed_scraplist?id=${feedId }">스크랩</a></li>
		</c:if>
		<li><a href="${cp }/feedboard_feed_selectAllbyId?id=${feedId }">여행글</a></li>
		<li><a href="#">경로게시판</a></li>
		<c:if test="${role == 'ROLE_MEMBER' }">
			<li><a href="${cp }/authorReg/insert">작가신청</a></li>
		</c:if>
		<c:if test="${feedId==id }">
			<li><a href="${cp }/userInfo">내정보</a></li>
		</c:if>
	</ul>
</div>