<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="board" value="${cp }/board/${cname}/list" />
<c:set var="article" value="${cp }/board/${cname}/article" />
<c:forEach items='${fields}' var='field'>
    <c:if test='${field=="id" }'><c:set var="fieldid" value="checked='checked'" /></c:if>
    <c:if test='${field=="title" }'><c:set var="fieldtitle" value="checked='checked'" /></c:if>
    <c:if test='${field=="content" }'><c:set var="fieldcontent" value="checked='checked'" /></c:if>
</c:forEach>
<!-- 테스트용 드랍다운 -->
<!-- <script src="https://unpkg.com/@popperjs/core@2"></script> -->
<!--테스트용 부트스트랩 -->
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
<!-- <script src="https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://unpkg.com/@tabler/core@latest/dist/css/tabler.min.css"> -->
<style type="text/css">
    /* text-height: auto; */
    .boardhead{
        font-size: 25px !important;
    }
    button{
        font-size: 25px imp !important
    }
</style>
<div>
    <!-- TODO:아이디 눌러서 검색, 댓글수 체크 -->
    <div class="card">
        <div class="card-header">
            <!-- TODO:tableintro -->
        </div>
        <form action="${board}/" method="get">
            <div class="card-body">
                <div class="d-flex">
                    <!-- tabledoption & search -->
                    <div class="text-muted">
                        <!-- tableoption -->
                        글
                        <div class="mx-2 d-inline-block">
                            <input type="text" name="listSize" value="${pu.listSize}" size="3">
                            <input type="hidden" name="pageSize" value="${pu.pageSize}">
                        </div>
                        개
                    </div>
                    <div class="ms-auto d-flex">
                        <!-- 검색조건 설정 -->
                        <div class="form-selectgroup">
                            <label class="form-selectgroup-item">
                                <input type="checkbox" name="field" value="id" ${fieldid} class="form-selectgroup-input">
                                <span class="form-selectgroup-label">id</span>
                            </label>
                            <label class="form-selectgroup-item">
                                <input type="checkbox" name="field" value="title" ${fieldtitle} class="form-selectgroup-input">
                                <span class="form-selectgroup-label">title</span>
                            </label>
                            <label class="form-selectgroup-item">
                                <input type="checkbox" name="field" value="content" ${fieldcontent} class="form-selectgroup-input">
                                <span class="form-selectgroup-label">content</span>
                            </label>
                        </div>
                        <div class="row g-2">
                            <div class="col input-group input-group-flat">
                                <input type="text" name="keyword" class="form-control form-control-sm" autocomplete="off" value="${keyword}" placeholder="search">
                                <span class="input-group-text">
                                    <a href="#" class="link-secondary" title="clear search" data-bs-toggle="tooltip" data-bs-original-title="Clear search">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
                                    </a>
                                    <!-- <button type="re" class="link-secondary" title="clear search" data-bs-toggle="tooltip" data-bs-original-title="Clear search">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
                                    </button> -->
                                </span>
                            </div>
                            <div class="col-auto">
                                <!-- <a href="#" class="btn btn-white btn-icon" aria-label="Button"> -->
                                <button type="submit" class="btn btn-white btn-icon">
                                    <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><circle cx="10" cy="10" r="7"></circle><line x1="21" y1="21" x2="15" y2="15"></line></svg>
                                </button>
                                <!-- </a> -->
                            </div>
                            <!-- <button type="submit" class="btn ms-auto">search</button> -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- <input type="button" name="test" value="test"> -->
            <div class="table-responsive">
                <table class="table table-vcenter table-mobile-md card-table text-nowrap datatable">
                    <!-- <caption class="hidden">subboard</caption> -->
                    <colgroup span="1"></colgroup>
                    <colgroup span="3"></colgroup>
                    <colgroup span="2"></colgroup>
                    <colgroup span="1"></colgroup>
                    <thead>
                        <tr>
                            <th class="boardhead">글번호</th>
                            <th class="boardhead">카테고리</th>
                            <th class="boardhead">제목</th>
                            <th class="boardhead">아이디</th>
                            <th class="boardhead">조회수</th>
                            <th class="boardhead">추천수</th>
                            <th class="boardhead">등록일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="vo">
                            <tr>
                                <td>${vo.num}</td>
                                <td>${vo.cname}</td>
                                <td><a href="${cp}/board/${vo.cname}/article/${vo.num}">${vo.title}</a></td>
                                <td>
                                    <!-- <div class="dropdown"> -->

                                        <!-- <a href='#' class="btn dropdown-toggle"> -->
                                            ${vo.id}
                                        <!-- </a> -->
                                        <!-- <div class="dropdown-menu dropdown-menu-end" style="margin: 0px;"> -->
                                            <!-- <a class="dropdown-item" href="#"> -->
                                                <!-- Action -->
                                            <!-- </a> -->
                                            <!-- <a class="dropdown-item" href="#"> -->
                                                <!-- Another action -->
                                            <!-- </a> -->
                                        <!-- </div> -->
                                    <!-- </div> -->
                                </td>
                                <td>${vo.hits}</td>
                                <td>${vo.recommend}</td>
                                <td>${vo.adddate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer ">
                <div class="d-plex align-items-center">
                    <div style="display: flex;">
                        ${pu.startPage} to ${pu.endPage} of ${pu.pageCount}
                    </div>
                    <div style="display: flex;">
                    <ul class="pagination m-0 ms-auto">
                        <c:choose>
                            <c:when test="${pu.startPage==1 }">
                                <li class="page-item disabled">
                                    <!-- TODO: disabled 클래스 삽입 -->
                                    <a href="#" aria-disabled="true" class="page-link">prev</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a href="${board }/${pu.startPage-1}" class="page-link">prev</a></li>
                            </c:otherwise>
                        </c:choose>
    
                        <c:forEach var="p" begin="${pu.startPage}" end="${pu.endPage}">
                            <c:choose>
                                <c:when test="${pu.page==p }">
                                    <!-- TODO:현재글 강조 -->
                                    <li class="page-item active"><a href="${board }/${p }" class="page-link">${p }</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a href="${board }/${p }" class="page-link">${p }</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
    
                        <c:choose>
                            <c:when test="${pu.endPage eq pu.pageCount }">
                                <li class="page-item disabled">
                                    <!-- TODO: disabled 클래스 삽입 -->
                                    <a href="#" aria-disabled="true" class="page-link">next</a>
                                </li class="page-item">
                            </c:when>
                            <c:otherwise>
                                <li><a href="${board }/${pu.endPage+1}" class="page-link">next</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                    <a class="btn" href="${cp }/member/write/${cname}/">글쓰기</a>
                </div>
            </div>
        </form>
    </div>
    <!-- <sec:authorize access="isAuthenticated()">
        <sec:authorize access="hasRole('ROLE_MEMBER')" >
        </sec:authorize>
    </sec:authorize> -->
</div>
<script type="text/javascript">
    $('.page-link').each((i, e)=>{$(e).attr('href', $(e).attr('href')+'${beforeparams}');})
    $('a[title="clear search"]').on('click', function(){
        $('input[name="keyword"]').val('');
        $('input[name="field"]').each((i, e)=>{
            $(e).attr('checked', false);
        });
    });
</script>