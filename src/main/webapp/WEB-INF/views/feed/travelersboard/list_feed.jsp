<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<script>
/*
	일반 회원 페이지 >> (본인 + 관리자만 접근 가능) 
		글등록하기
		작성페이지 - 등록버튼
		글목록 - 등록 날짜 표시(adddate)
	
	작가회원 페이지 >> (본인 + 모든 회원 + 관리자 접근 가능)
		글등록하기
		작성페이지 - 등록버튼 / 발행버튼 (등록 - 본인만 보이기 / 발행 - 전체공개)
		글목록 - 
			로그인아이디==작가본인 : 모든 글 노출(등록날짜 발행날짜 표시)
	 		로그인아이디!=작가본인 : 발행 글 노출(발행날짜 표시)
 */
</script>
<style>
	.btn.btn-white.w-100{font-size:20px;font-weight:bold;}
	.btn.btn-white.w-100.del{
		float:left;
		width:10%!important;
	}
	.btn.btn-white.w-100.write{
		float:right;
		width:10%!important;
	}
	.btn.btn-white.w-100.search{
		float:right;
		width:20%!important;
	}
	
</style>
<div id="feedboard_wrap">
	<div id="feedboard_main">
		<span style="font-size:30px;">${feedId } 님의 여행게시판</span>
	</div>
	<form:form method="post" onsubmit="return checkDel(event)">
		<div id="feedboard_table">
			<table border="1" width="800px" class="table table-vcenter table-mobile-md card-table">
				<tr>
					<th>카테고리</th>
					<th></th>
					<th>제목</th>
					<th>게시날짜</th>
					<c:if test="${id == feedId && feedRole == 'ROLE_AUTHOR'}">
						<th>조회수</th>
						<th>추천</th>
						<th>스크랩</th>
						<th>발행여부</th>
					</c:if>
					<c:if test="${id == feedId || feedRole == 'ROLE_ADMIN'}">
						<th><input type="checkbox" id="allcheck"></th>
					</c:if>
				</tr>
			 <c:choose>
	                <c:when test="${!empty list }">
		               <c:forEach var="vo" items="${list }">
					<tr>
							<td>${vo.cname }</td>
							<c:choose>
								<c:when test="${vo.thumbnail!=null }">
									<td><img src="${cp }/resources/feedboard/${vo.thumbnail }" style="width: 100px; height: 100px;"></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td><a href="${cp }/feedboard_detail?num=${vo.num }&recentpage=feed">${vo.title }</a></td>
							<td>${vo.adddate }</td>
							<c:if test="${id == feedId && feedRole == 'ROLE_AUTHOR'}">
								<td>${vo.hits}</td>
								<td>${vo.recommend}</td>
								<td>${vo.scrap}</td>
								<td>
									<c:if test="${vo.ispost=='1' }">발행o</c:if>
									<c:if test="${vo.ispost=='-1' }">발행x</c:if>
								</td>
							</c:if>
							<c:if test="${id == feedId || feedRole == 'ROLE_ADMIN'}">
								<td><input type="checkbox" name="checkk" value=${vo.num }></td>
							</c:if>
							</tr>
						 </c:forEach>
			         </c:when>
				     <c:otherwise>
				       		 <tr><td colspan="9" style="text-align: center;">게시글이 없습니다!</td></tr>
			         </c:otherwise>
		         </c:choose>
			</table>
			<div id="feedboard_editlist" style="width:900px;">
				<c:if test="${id == feedId}">
					<input type="submit" value="삭제" class="btn btn-white w-100 del" name="feedboard_deleteMine" formaction="${cp }/feedboard_deleteMyList">
					<input type="button" value="글쓰기" class="btn btn-white w-100 write" name="feedboard_write" onclick="location.href='${cp}/member/feedboard_Towrite'">
				</c:if>
			</div>
		</div>
	</form:form>
</div>
	<div id="paging" style="width:600px;margin:auto;text-align: center;">
	<c:if test="${pu.startPage > 10}">
         <a href="${cp }/feedboard_feed_selectAllbyId?page=${pu.startPage - 1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[이전]</span> </a>
    </c:if>
	<c:forEach var="i" begin="${pu.startPage }" end="${pu.endPage }">
		<c:choose>
			<c:when test="${i==pu.page }">
				<a href="${cp }/feedboard_feed_selectAllbyId?page=${i}&field=${field}&keyword=${keyword}"><span style='color:blue'>[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/feedboard_feed_selectAllbyId?page=${i}&field=${field}&keyword=${keyword}"><span style='color:gray'>[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pu.endPage < pu.pageCount}">
     	<a href="${cp }/feedboard_feed_selectAllbyId?page=${pu.endPage + 1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[다음]</span> </a>
    </c:if>
</div><br>
<div style="width:900px;">
	<div style="width:400px;float:right">
		<form:form method="post" action="${cp }/feedboard_feed_selectAllbyId">
			<select name="field" class="form-select" style="width:90px;display:inline-block;font-size:20px;font-weight:bold;">
				<option value="cname" <c:if test="${field=='cname'}">selected</c:if>>지역</option>
				<option value="title" <c:if test="${field=='title'}">selected</c:if>>제목</option>
				<option value="content" <c:if test="${field=='content'}">selected</c:if>>내용</option>
				<option value="toc" <c:if test="${field=='toc'}">selected</c:if>>제목+내용</option>
			</select>
			<input type="text" name="keyword" style="width:220px;height:44px;font-size:20px;font-weight:bold;display:inline-block;" class="form-control" value="${keyword }" >
			<input type="submit" value="검색" class="btn btn-white w-100 search">
		</form:form>
	</div>
</div>

<script>
		var check=0;
		document.getElementById("allcheck").addEventListener("click", function(e) {
			if(check==0){
				check=1;
			}else{
				check=0;
			}
			var checkk=document.getElementsByName("checkk");
			for(var i=0;i<=checkk.length;i++){
				if(check==1){
					checkk[i].checked=true;
				}else{
					checkk[i].checked=false;
				}
			}
		}, true);
		
			function checkDel(e){
				let cnt=0;
		         $("input:checkbox[name='checkk']").each(function(){
		            if($(this).is(":checked")==true){
		            	cnt++;
		               console.log(cnt);
		            }else{
		               console.log(cnt);
		            }
		         });
		         if(cnt==0){
		        	 	alert("삭제할 글을 1개 이상 선택하세요.");
		        	 	e.preventDefault();
	 				}else{
		        	 	if(confirm("삭제하시겠습니까?")==true){
	 						return true;
		        	 	}else{
		        	 		e.preventDefault();
		        	 	}
	 				}
		         
// 			alert("why!!");
// 			var checkk=document.getElementsByName("checkk");
// 			var cnt=0;
// 			for(var i=0;i<checkk.length;i++){
// 				if(checkk[i].checked==true){
// 					cnt++;
// 					console.log(cnt);
// 				}else{
// 					console.log(cnt);
// 				}
// 			}
// 				if(cnt==0){
// 					alert("???");
// 					return false;
// 				}else{
// 					alert("!!!");
// 					return true;
// 				}
		}
</script>