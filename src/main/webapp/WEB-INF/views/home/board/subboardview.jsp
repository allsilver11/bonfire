<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
table{
    margin-top:20px;
}
table, th, td{
    border: 1px solid #bbbbbb;
}
textarea.autosize {
    min-height: 50px;
}
</style>
<div>
    <div id="article">
        <div id="article_header"></div>
        <div id="article_body">
            <div class="table-responsive">
                <table class="table table-vcenter">
                    <tbody>
                        <tr class="article_metadata">
                            <td colspan="2">제목 : <h1 style="display: inline;">${vo.title}</h1></td>
                            <td>작성일 : ${vo.adddate}</td>
                        </tr>
                        <tr class="article_metadata">
                            <td>조회수 : ${vo.hits}</td>
                            <td>
                                <div>아이디 : ${vo.id}</div>
                            </td>
                            <td>
                                <div id="myrecomm">
                                    추천 : 
                                    <span id="recommend"></span>
                                    개
                                        <svg id="tglrecomm" xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-star" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
                                            <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                                            <path d="M12 17.75l-6.172 3.245l1.179 -6.873l-5 -4.867l6.9 -1l3.086 -6.253l3.086 6.253l6.9 1l-5 4.867l1.179 6.873z"></path>
                                        </svg>
                                    <!-- <button id="tglrecomm" class="btn btn-sm"></button> -->
                                </div>
                            </td>
                        </tr>
                        <tr id = "article_content">
                            <td colspan="3">
                                ${vo.content}
                            </td>
                        </tr>
                    </tbody>
                    <tbody id="article_comment">
                    </tbody>
                    <!-- TODO:멤버 이상 권한 있을때 사용가능하도록 변경 -->
                    <tbody id="article_comment_write">
                        <tr>
                            <td colspan="3">
                                <form:form id="comment_write">
                                    <div class="mb-3">
                                        <label class="form-label">댓글 <span class="form-label-description"></span></label>
                                        <textarea class="form-control" name="content" rows="6" placeholder="댓글쓰기.."></textarea>
                                    </div>
                                    <div>
                                        <button type="submit" class="btn">작성하기</button>
                                    </div>
                                </form:form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="article_foot"></div>
    </div>

</div>
<script type="text/javascript">
    let recommend = '${vo.recommend}';
    $('#recommend').text(recommend);
    $.getJSON("${cp }/board/${cname }/article/${vo.num}/recomm",
        function (response) {
            if (response.value=='1') $('#tglrecomm').attr('fill', 'currentColor');
            else $('#tglrecomm').attr('fill', 'none');
        }
    );
    $('#tglrecomm').on('click', function(){
        $.getJSON("${cp }/board/${cname }/article/${vo.num}/recomm", {tglrecomm: $('#tglrecomm').hasClass('recommstar')?1:-1}, 
            function (response) {
                console.log(response);
                if (response.value=='1') $('#tglrecomm').attr('fill', 'currentColor');
                else $('#tglrecomm').attr('fill', 'none');
                $('#recommend').text(parseInt(recommend)+(response.value));
            }
        );
    })
    
    function getComment() {
        $.ajax({
            type: "get",
            url: "${cp}/board/${cname}/article/${vo.num}/comment",
            // data: "data",
            // dataType: "dataType",
            success: function (response) {
                console.log(response);
                $('#article_comment').empty();
                response.forEach(detail => {
                    console.log(detail);
                    let html = 
                    `<tr data-idx="`+detail.idx+`">
                        <td>
                            <div id="username">`+detail.id+`</div>
                            <div>`+detail.adddate+`</div>
                            <div>추천:`+detail.value+`</div>
                        </td>
                        <td colspan="2">`+detail.content+`</td>
                    </tr>`;
                    $('#article_comment').append(html);
                });
            }
        });
    };

    getComment();


    $("textarea.autosize").on('keydown keyup', function () {
        $(this).height(1).height($(this).prop('scrollHeight') + 12);
    });

    $("#comment_write").on('submit', function(e){
        e.preventDefault();
        console.log($(comment_write).serialize());
        $.ajax({
            type: "get",
            url: "${cp }/board/${cname }/article/${vo.num}/commentwrite",
            data: $(comment_write).serialize(),
            beforeSend(jqXHR, settings) {
                console.log(settings.url)
            },
            // dataType: "dataType",
            success: function (response) {
                console.log(response);
            }
        });
    });
    //추가해야할것-----------
    function load(filename, filetype) {
        if (filetype == "js") { //if filename is a external JavaScript file
            var fileref = document.createElement('script')
            fileref.setAttribute("type", "text/javascript")
            fileref.setAttribute("src", filename)
        }
        else if (filetype == "css") { //if filename is an external CSS file
            var fileref = document.createElement("link")
            fileref.setAttribute("rel", "stylesheet")
            fileref.setAttribute("type", "text/css")
            fileref.setAttribute("href", filename)
        }
        if (typeof fileref != "undefined")
            document.getElementsByTagName("head")[0].appendChild(fileref)
    }
    if ($('#mapcode')[0]) {
        load('https://code.jquery.com/ui/1.12.1/jquery-ui.js', 'js');
        load('https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.js', 'js');
        load('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-language/v0.10.1/mapbox-gl-language.js', 'js');
        load('https://npmcdn.com/@turf/turf@5.1.6/turf.min.js', 'js');
        load('https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js', 'js');
        load('https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js', 'js');
        load('https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.css', 'css');
        load('https://unpkg.com/@tabler/core@latest/dist/css/tabler.min.css', 'css');
        load('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.5.1/mapbox-gl-geocoder.css', 'css');
        // load('${cp }/resources/map/routemap.js', 'js');
        // load('${cp }/resources/map/routemap.css', 'css');
        $('#mapcode').load('${cp}/map');
    }
    //---------------------

</script>