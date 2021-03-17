<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<style>
   .btn.btn-white.w-100{font-size:20px;font-weight:bold;}
   .btn.btn-white.w-100.del{
      float:left;
      width:10%!important;
   }
   .btn.btn-white.w-100.search{
      float:right;
      width:20%!important;
   }
   
</style>
<div id="feedboard_wrap">
   <div id="feedboard_main">
      <span>${feedId } 님의 스크랩게시판</span>
   </div>
   <form:form method="post" onsubmit="chechDel(event)">
      <div id="scrapboard_table" >
            <table border="1" width="800px" class="table table-vcenter table-mobile-md card-table">
               <tr>
                  <th>카테고리</th>
                  <th>작성자</th>
                  <th></th>
                  <th>제목</th>
                  <th>조회수</th>
                  <th>추천</th>
                  <th>스크랩 수</th>
                  <th>게시 날짜</th>
                  <th><input type="checkbox" id="allcheck"></th>
               </tr>
                <c:choose>
	                <c:when test="${!empty list }">
		               <c:forEach var="vo" items="${list }">
		                  <tr>
		                        <td>${vo.cname }</td>
		                        <td>${vo.writer}</td>
		                        <c:choose>
										<c:when test="${vo.thumbnail!=null }">
											<td><img src="${cp }/resources/feedboard/${vo.thumbnail }" style="width: 100px; height: 100px;"></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
		                        <td><a href="${cp }/scrapboard_detail?num=${vo.num }&from=scrap">${vo.title }</a></td>
		                        <td>${vo.hits}</td>
		                        <td>${vo.recommend}</td>
		                        <td>${vo.scrap}</td>
		                        <td>${vo.postdate}</td>
		                        <td><input type="checkbox" name="checkk" value=${vo.num }></td>
		               </tr>
		               </c:forEach>
			         </c:when>
				     <c:otherwise>
				       		 <tr><td colspan="9" style="text-align: center;">스크랩한 게시글이 없습니다!</td></tr>
			         </c:otherwise>
		         </c:choose>
            </table>
         <div id="scrap_editlist" style="float:left:">
            <c:if test="${id == feedId}">
               <input type="submit" value="삭제" name="scrapboard_delete" formaction="${cp }/scrapboard_delete"
                  class="btn btn-white w-100 del">
            </c:if>
         </div>
      </div>
   </form:form>
</div>
<div style="width:900px;">
   <div style="width:600px;margin:auto;text-align: center;">
      <c:if test="${pu.startPage > 10}">
            <a href="${cp }/scrapboard_feed_scraplist?page=${pu.startPage - 1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[이전]</span> </a>
       </c:if>
      <c:forEach var="i" begin="${pu.startPage }" end="${pu.endPage }">
         <c:choose>
            <c:when test="${i==pu.page }">
               <a href="${cp }/scrapboard_feed_scraplist?page=${i}&field=${field}&keyword=${keyword}"><span style='color:blue'>[${i }]</span></a>
            </c:when>
            <c:otherwise>
               <a href="${cp }/scrapboard_feed_scraplist?page=${i}&field=${field}&keyword=${keyword}"><span style='color:gray'>[${i }]</span></a>
            </c:otherwise>
         </c:choose>
      </c:forEach>
      <c:if test="${pu.endPage < pu.pageCount}">
           <a href="${cp }/scrapboard_feed_scraplist?page=${pu.endPage + 1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[다음]</span> </a>
       </c:if>
   </div>
</div>
<div style="width:900px;">
   <div style="width:400px;float:right">
      <form:form method="post" action="${cp }/scrapboard_feed_scraplist">
         <select name="field" class="form-select" style="width:90px;display:inline-block;font-size:20px;font-weight:bold;">
            <option value="cname" <c:if test="${field=='cname'}">selected</c:if>>지역</option>
            <option value="title" <c:if test="${field=='title'}">selected</c:if>>제목</option>
            <option value="content" <c:if test="${field=='content'}">selected</c:if>>내용</option>
            <option value="toc" <c:if test="${field=='toc'}">selected</c:if>>제목+내용</option>
         </select>
         <input type="text" name="keyword" value="${keyword }" class="form-control"
            style="width:220px;display:inline-block;height:45px;font-size:20px;font-weight:bold;">
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
      }
</script>