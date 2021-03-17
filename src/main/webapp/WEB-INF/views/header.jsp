<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <!-- header.jsp -->
<!-- <script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script> -->
<style>
	#header_logo{width:300px;height:100px;float:left;}
	#logo_txt{top:20px;}
	#header_menu{width:40px;height:40px;clear:both;position:relative;left:1160px;top:-35px;}
	#header_login{width:40px;height:40px;clear:both;position:relative;left:1100px;top:-75px;}
	#header_slideMenu{width:1200px;height:100px;clear:both;
	position:absolute;z-index:990;top:110px; display:none;background-color: white;opacity:0.7;}
	#header_slideMenu a{z-index:998;text-decoration: none; color:black; font-size:25px;}
	#header_menulist{width:740px;list-style:none; margin:auto;padding-top:30px;}
	#header_menulist li{display:inline-block; width:80px; margin-left:48px;margin-right:48px;}
	#profile_slide{width:180px;clear:both;padding-bottom:10px; text-align:center; border-radius:5%;
	position:absolute;z-index:999;left:-80px;top:53px; display:none;background-color: white;opacity:0.8;}
	#profile_slide a{z-index:998;text-decoration: none; color:#bbbbbb; font-size:25px;padding-left:8px;}
	#profile_slide a:hover{color:black;}
	.dropdown-menu-arrow:before{left:5.75rem;}
	
</style>
<div>
	<div id="header_logo" onclick="location.href='${cp}/'">
		<img src="${cp }/resources/images/bonfire_logo3.png" style="width:100px;height:100px;">
		<span style="font-size:50px;position:absolute;" id="logo_txt">Bonfire</span>
	</div>
	<div id="header_menu"><img src="${cp }/resources/images/menuIcon.png" style="width:40px;height:40px;" id="menuBtn"></div>
	<div id="header_login">
	<sec:authorize access="isAnonymous()">
		<img src="${cp }/resources/images/loginIcon2.png" onclick="location.href='${cp }/login'" style="width:40px;height:40px;">
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<img src="${cp }/resources/images/profileIcon.png" id="profileImg" style="width:40px;height:40px;">
		<div id="profile_slide" class="dropdown-menu dropdown-menu-demo dropdown-menu-arrow">
			
			<span style="display:inline-block;width:180px;text-align: center;font-size:30px;">[<sec:authentication property="principal.username"/>] 님</span> <br>
			<c:if test = "${role != 'ROLE_ADMIN' }">
			<a href="${cp }/@${id}">마이페이지</a><br>
			</c:if>
			<c:if test="${role == 'ROLE_ADMIN' }">
				<a href="${cp }/adminpage">관리자페이지</a><br>
			</c:if>
			<c:if test="${role == 'ROLE_MEMBER' }">
				<a href="${cp }/authorReg/insert">작가신청</a><br>
			</c:if>
			<a href="${cp }/logout">로그아웃</a><br>
<%-- 			<a href="${cp }/withdraw">회원탈퇴</a><br> --%>
			<a href="#" id="header_withdrawal">회원탈퇴</a><br>
		</div>
	</sec:authorize>
	</div>
	<div id="header_slideMenu">
		<ul id="header_menulist">
			<li><a href="${cp }/feedboard_main_selectAll">여행게시판</a></li>
			<li><a href="${cp }/board/all/list/">자유게시판</a></li>
			<li><a href="${cp }/qna/main">문의게시판</a></li>
			<li><a href="#">경로게시판</a></li>
		</ul>
	</div>
</div>
<script>
	$(function(){
		$("#menuBtn").click(function(){
			$("#header_slideMenu").slideToggle(500);
		});
		$("#profileImg").click(function(){
			$("#profile_slide").slideToggle(500);
		});
		$("#header_withdrawal").click(function(){
			if(confirm("회원 탈퇴 하시겠습니까?") == true){
				location.href="${cp}/withdraw";
			}
		})
		
		
	});
</script>
