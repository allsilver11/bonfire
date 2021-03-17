<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<style>
	.btn.btn-white.w-100{display:inline-block;font-size:22px;font-weight: bold;}
	.btn.btn-white.w-100.search{width: 15%!important; height: 45px; margin-top: -4px;}
	.btn.btn-white.w-100.delete{width: 7%!important;}
	.btn.btn-white.w-100.write{width: 11%!important;}
	.btn.btn-white.w-100.listAll{width: 10%!important;}
	
	#pageNum{
		position : relative;
		left : 34%;
	}
	
	#listAll{
		position : relative;
		left : 70.5%;
	}
</style>

<h1><b>${id }님의 일기장</b></h1>
<form:form method="post" onsubmit="checkDel(event)">
<table border = "1" width = "800" class="table table-vcenter table-mobile-md card-table">
	<tr>
		<th>날짜</th>
		<th>제목</th>
		<th><input type="checkbox" id="allcheck"></th>
	</tr>
	<c:forEach var = "vo" items = "${list }">
		<tr>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${vo.adddate }"/>
			<td><a href = "${cp }/daily/detail?num=${vo.num}&id=${id}">${vo.title }</a>
			<td><input type="checkbox" name="checkk" value=${vo.num }></td>
		</tr>
	</c:forEach>
</table>
<button onclick="location.href='${cp }/daily/insert'" class="btn btn-white w-100 write" id = "write" type="button">글쓰기</button>
<input type="submit" value="삭제" class="btn btn-white w-100 delete" name="delete" formaction="${cp }/daily/delete" >
<button onclick="location.href='${cp}/daily_main'" class="btn btn-white w-100 listAll" id = "listAll" type="button">전체목록</button>
</form:form>
<div id = "pageNum">
	<c:if test="${pu.startPageNum > 10}">
         <a href="${cp }/daily_main?pageNum=${pu.startPageNum - 1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[이전]</span> </a>
	</c:if>
	<c:forEach var="i" begin="${pu.startPageNum }" end="${pu.endPageNum }">
		<c:choose>
			<c:when test="${i==pu.pageNum }">
				<a href="${cp }/daily_main?pageNum=${i}&field=${field}&keyword=${keyword}"><span style='color: blue'>[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/daily_main?pageNum=${i}&field=${field}&keyword=${keyword}"><span style='color: gray'>[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pu.endPageNum < pu.totalPageCount}">
            <a href="${cp }/daily_main?pageNum=${pu.endPageNum + 1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[다음]</span> </a>
	</c:if>
</div>	

<div id = "search">
	<form:form method= "post" action="${cp }/daily_main">
		<div id ="searchBar" style = "display: inline-block;">
		<select name = "field" class = "form-select" style="width:90px;display:inline-block;font-size:20px;font-weight:bold;">
			<option value = "title" <c:if test = "${field == 'title' }">selected</c:if>>제목</option>
			<option value = "content" <c:if test = "${field == 'content' }">selected</c:if>>내용</option>
		</select>
		<input type = "text" name = "keyword" style="width:217px;height:45px;font-size:20px;font-weight:bold;display:inline-block;">
		<input type = "submit" value = "검색" class="btn btn-white w-100 search">
		</div>
	</form:form>
	
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