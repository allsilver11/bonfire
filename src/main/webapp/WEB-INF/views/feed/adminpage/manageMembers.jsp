<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- manageMember.jsp -->
<style>
   .btn.btn-white.w-100{display:inline-block;font-size:22px;font-weight: bold;}
   .btn.btn-white.w-100.all{width:13%!important;}
   .btn.btn-white.w-100.main{width:13%!important;}
   .btn.btn-white.w-100.search{width:20%!important;height:98%!important;}
</style>
<div>
   <span style="font-size:30px;">회원관리</span>
   <form:form method="post" onsubmit="checkDel(event)">
   <input type="submit" value="삭제" name="manage_delete" formaction="${cp }/manage_deleteList">
   <input type="hidden">
   <table border="1" width="900" class="table table-vcenter table-mobile-md card-table">
      <tr>
         <th><input type="checkbox" id="allcheck"></th>
         <th>아이디</th>
         <th>이름</th>
         <th>이메일</th>
         <th>가입일</th>
         <th>등급</th>
         <th>상세보기</th>
      </tr>
      <c:forEach var="vo" items="${list }">
         <tr>
            <th><input type="checkbox" name="checkk" value=${vo.id }></th>
            <th>${vo.id }</th>
            <th>${vo.name}</th>
            <th>${vo.email}</th>
            <th>${vo.regdate}</th>
            <th>${vo.authority}</th>
            <th><a href="${cp }/manageMembers_detail?id=${vo.id}">상세보기</a></th>
         </tr>
      </c:forEach>
   </table>
   </form:form>
<div style="width:900px;">
   <div style="width:600px;margin:auto;text-align: center;">
      <c:choose>
         <c:when test="${pu.startPage>10 }">
               <a href="${cp }/manageMembers_List_All?pageNum=${pu.startPage-1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[이전]</span></a>
         </c:when>
         <c:otherwise>
            [이전]
         </c:otherwise>
      </c:choose>
      <c:forEach var="i" begin="${pu.startPage }" end="${pu.endPage }">
         <c:choose>
            <c:when test="${i!=pu.page }">
               <a href="${cp }/manageMembers_List_All?pageNum=${i}&field=${field}&keyword=${keyword}"><span style='color:blue'>[${i}]</span></a>
            </c:when>
            <c:otherwise>
               <a href="${cp }/manageMembers_List_All?pageNum=${i}&field=${field}&keyword=${keyword}"><span style='color:gray'>[${i}]</span></a>
            </c:otherwise>
         </c:choose>
      </c:forEach>
      <c:choose>
         <c:when test="${pu.endPage<pu.pageCount }">
               <a href="${cp }/manageMembers_List_All?pageNum=${pu.endPage+1}&field=${field}&keyword=${keyword}"><span style='color:blue'>[다음]</span></a>
         </c:when>
         <c:otherwise>
            [다음]
         </c:otherwise>
      </c:choose>
   </div>
</div>   
   <div style="width:900px;display:inline-block;">
      <div style="float:right;">
      <form:form method="post" action="${cp }/manageMembers_List_All">
      <div  style="font-size:25px;width:400px;display:inline-block;">
         <select name="field" class="form-select" style="width:90px;display:inline-block;font-size:20px;font-weight:bold;">
            <option value="id" <c:if test="${field=='id' }">selected</c:if>>아이디</option>
            <option value="name" <c:if test="${field=='name'}">selected</c:if>>이름</option>
            <option value="email" <c:if test="${field=='email' }">selected</c:if>>이메일</option>
            <option value="phone" <c:if test="${field=='phone' }">selected</c:if>>전화번호</option>
            <option value="roadfulladdr" <c:if test="${field=='roadfulladdr' }">selected</c:if>>주소</option>
            <option value="authority" <c:if test="${field=='authority' }">selected</c:if>>등급</option>
         </select>
         <input type="text" name="keyword" value="${keyword }" style="width:217px;height:45px;font-size:20px;font-weight:bold;display:inline-block;" class="form-control">
         <input type="submit" value="검색" class="btn btn-white w-100 search">
         </div>
      </form:form>
      </div>
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
                   return true;
                }
         }
</script>