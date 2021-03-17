<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="application"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- 테스트용 드랍다운 -->
<script src="https://unpkg.com/@popperjs/core@2"></script>
<!--테스트용 부트스트랩 -->
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/@tabler/core@latest/dist/css/tabler.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Caveat&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${cp }/resources/subboard.css">
<style>
	*{margin:0px;padding:0px;}
	#banner img{width:100%;height:100%}
</style>
</head>
<body>
<div id="wrap">
	<div id="header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="banner">
	<!-- no banner here.. -->
		<img src="${cp }/resources/images/banner-subboard-0.jpg">
	</div>
	<div>
		<div id="side">
			<tiles:insertAttribute name="side"/>
		</div>
		<div id="content" style="height: auto;">
			<tiles:insertAttribute name="content"/>
		</div>
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
</body>
</html>