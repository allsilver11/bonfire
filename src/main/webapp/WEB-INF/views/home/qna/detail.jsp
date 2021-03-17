<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.form-control{display:inline-block;font-size:20px;font-weight:bold;}
.btn.btn-white.w-100{display:inline-block;font-size:22px;font-weight: bold;}
.btn.btn-white.w-100.all{width:13%!important;}
.btn.btn-white.w-100.search{width:20%!important;height:98%!important;}

</style>
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
</head>
<body>
<table border="1" width="1200" class="table table-vcenter table-mobile-md card-table">
<c:choose>
	<c:when test="${vo.pin==1 }">
	<tr>
		<td>카테고리</td><td>${vo.cname }</td>
	</tr>
	<tr>
		<td>작성자</td><td>관리자</td>
	</tr>
	<tr>
		<td>제목</td><td>${vo.title }</td>
	</tr>
	<tr>
		<td>내용</td><td><div style='width:300px;'>${vo.content }</div></td>
	</tr>
	<tr>
		<td>조회수</td><td>${vo.hits }</td>
	</tr>
	<tr>
		<td>작성일</td><td><fmt:formatDate pattern="yyyy-MM-dd" value="${vo.adddate }"/></td>
	</tr>
	
	
	</c:when>
	<c:otherwise>
	<tr>
		<td>카테고리</td><td>${vo.cname }</td>
	</tr>
	<tr>
		<td>글번호</td><td>${vo.num }</td>
	</tr>
	<tr>
		<td>작성자</td><td>${vo.id }</td>
	</tr>
	<tr>
		<td>제목</td><td>${vo.title }</td>
	</tr>
	<tr>
		<td>내용</td><td><div style='width:300px;'>${vo.content }</div></td>
	</tr>
	<tr>
		<td>조회수</td><td>${vo.hits }</td>
	</tr>
	<tr>
		<td>작성일</td><td><fmt:formatDate pattern="yyyy-MM-dd" value="${vo.adddate }"/></td>
	</tr>
	</c:otherwise>
</c:choose>
</table>

<c:if test="${sessionScope.id==vo.id || sessionScope.id=='admin'}">

<div >
		<a href="${cp }/qna/delete?num=${vo.num}" class="btn btn-white w-100 all">삭제</a>
		<a href="${cp }/qna/update?num=${vo.num}" class="btn btn-white w-100 all">수정</a>
</div>

</c:if>

<div style="margin-top:40px;margin-bottom:20px;">
<span>문의 답변 내역</span>
<div id="commList" >
</div>
</div>


<div id="commAdd">
		<input type="hidden" value="${vo.num }" id="commnum">
		<c:if test="${sessionScope.id=='admin'}">
		답글 <br>
		<textarea rows="6" cols="100" id="comments" class="form-control"></textarea>
		<input type="button" value="등록" id="addBtn" class="btn btn-white w-100 all">
		</c:if>
</div>


<script type="text/javascript">
$(function(){
	$("#addBtn").click(function(){
		var commnum=$("#commnum").val();
		var comments=$("#comments").val();
		var test="${cp}/qna/commlist";
		console.log(test);
		$.getJSON('${cp}/qna/commm',{"comments":comments,"commnum":commnum},function(data){//1.주소2.파라미터를보내는 값
			if(data.code=='success'){
				document.getElementById("comments").value="";
                getList();
            }else if(data.code=='fail'){
                alert("ERROR");
            }
		});
	});
});
function getList(){
	var commnum=$("#commnum").val();
	$.getJSON('${cp}/qna/commlist',{"commnum":commnum},function(data){//컨트롤러에서 제공한 내용을 data로받는다.
		//delComm();
		$("#commList").empty();//기존에 있는 div요소를 삭제한다.(초기화)
		console.log(data.comments);		
		var str=data.comments;
		$("#commList").append(str);
				
	});
}
getList();//목록이 바로 실행되어야해서 


</script>
</body>
</html>