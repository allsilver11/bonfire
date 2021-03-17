<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<style>
	.btn.btn-white.w-100{display:inline-block;font-size:22px;font-weight: bold;}
	.btn.btn-white.w-100.approve{width: 7%!important;}
	.btn.btn-white.w-100.deny{width: 7%!important;}
	.btn.btn-white.w-100.listAll{width: 10%!important;}
	.btn.btn-white.w-100.search{width: 7%!important; height: 45px; margin-top: -4px;}
	#pageNum{
		position : relative;
		left : 43%;
	}
	
	#listAll{
		position : relative;
		left : 75%;
	}
</style>
<h1><b>작가 신청 관리</b></h1>
	<form:form method = "post" onsubmit = "checkDeny(event)">
	<table border = "1" width = "800px" class="table table-vcenter table-mobile-md card-table">
		<tr>
			<th width="">신청 날짜</th>
			<th>아이디</th>
			<th>간략 소개</th>
			<th><input type = "checkbox" id = "allcheck"></th>
		</tr>
		<c:forEach var = "vo" items = "${list }">
			<tr>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${vo.adddate }"/></td>
				<td><a href = "${cp }/@${vo.id }">${vo.id }</a></td>
				<td><a href = "${cp }/authorreg/detail?num=${vo.num }">${vo.pres }</a></td>
				<td><input type = "checkbox" name = "checkk" value = ${vo.num }></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<input type = "submit" id = "approve" class="btn btn-white w-100 approve" value = "승인" name = "approve" formaction = "${cp }/author/approve">
	<input type = "submit" id = "deny" class="btn btn-white w-100 deny" value = "거절" name = "deny" formaction = "${cp }/author/deny">
	<button onclick="location.href='${cp}/authorRegList'" class="btn btn-white w-100 listAll" id = "listAll" type="button">전체목록</button>
</form:form>

<div id = "pageNum">
	<c:choose>
		<c:when test="${pu.startPageNum >10 }">
				<a href="${cp }/authorRegList?pageNum=${pu.startPageNum-1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[이전]</span></a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${pu.startPageNum }" end="${pu.endPageNum }">
		<c:choose>
			<c:when test="${i==pu.pageNum }">
				<a href="${cp }/authorRegList?pageNum=${i}&field=${field}&keyword=${keyword}"><span style='color: blue'>[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/authorRegList?pageNum=${i}&field=${field}&keyword=${keyword}"><span style='color: gray'>[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pu.endPageNum < pu.totalPageCount }">
				<a href="${cp }/authorRegList?pageNum=${pu.endPageNum+1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[다음]</span></a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>

<div id = "search">
	<form:form method= "post" action="${cp }/authorRegList">
	<div id = "searchBar" style = "display: inline-block;">
		<select name = "field" class="form-select" style="width:90px;display:inline-block;font-size:20px;font-weight:bold;">
			<option value = "id" <c:if test = "${field == 'id' }">selected</c:if>>아이디</option>
			<option value = "theme" <c:if test = "${field == 'theme' }">selected</c:if>>테마</option>
			<option value = "pres" <c:if test = "${field == 'pres' }">selected</c:if>>소개</option>
		</select>
		<input type = "text" name = "keyword" class="form-control" style="width:217px;height:45px;font-size:20px;font-weight:bold;display:inline-block;">
	</div>
		<input type = "submit" class="btn btn-white w-100 search" value = "검색">
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

	function checkDeny(e){
		let cnt = 0;
		$("input:checkbox[name = 'checkk']").each(function(){
			if($(this).is(":checked")==true){
				cnt++;
				console.log(cnt);
			} else{
				console.log(cnt);
			}
		});
		if(cnt==0){
			alert("신청서를 1개 이상 선택하세요.");
			e.preventDefault();
		} else {
			if(confirm("선택된 항목을 승인 혹은 거절 하시겠습니까?")==true) {
				return true;
			} else {
				e.preventDefault();
			}
		}
	}
</script>