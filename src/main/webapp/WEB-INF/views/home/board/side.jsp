<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<style type="text/css">
    .subboardsidebar{
        list-style: none;
    }
    .subboardsidebar li a{
        text-decoration: none; 
        color:#bbbbbb;
    }
    .subboardsidebar a:hover{color:black;}
    
    .currentcat {
        color:#206bc4 !important
    }
</style>
<ul class="subboardsidebar">
    <li><a href="#" data-cat="all"
        <c:if test='${cname=="all" }'>
            class="currentcat"
        </c:if>
>전체보기</a></li>
    <c:forEach items='${catlist}' var='cat'>
        <li><a href="#" data-cat="${cat}" 
        <c:if test='${cname==cat }'>
            class="currentcat"
        </c:if>
>${cat}</a></li>
    </c:forEach>
</ul>
<script type="text/javascript">
    $('a').on('click', function(e){
        window.location.href="${cp}/board/"+e.target.dataset.cat+"/list/";
    });
</script>