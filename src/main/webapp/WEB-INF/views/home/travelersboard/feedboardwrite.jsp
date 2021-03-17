<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="${cp }/resources/js/summernote/summernote-lite.js"></script>
<script type="text/javascript" src="${cp }/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/summernote/summernote-lite.css">


<style type="text/css">
    #editorbox{background-color: white;}
    .form-select{ display: inline-block;height: 37px; width: 100px; font-size: 18px; font-weight: bold; }
    .form-control{ display: inline-block; }
    .form-control.writer{ height: 37px; width: 100px; font-size: 25px; font-weight: bold;}
    .form-control.title{ height: 37px; width: 1093px; margin-left: 6px; font-size: 25px; font-weight: bold;}
    .btn.btn-white.w-100{ display: inline-block; float: right; width: 50%!important; font-size: 25px; font-weight: bold;}
    #editorarea{margin-bottom: 100px;}
    label{width: 100px;}
</style>
<div>
    <div id='editorarea'>
        <div id="editorarea_header"></div>
        <div id="editorarea_body">
            <form:form id="write" method="post">
            	<div id="writer">
            		<label for="writer">작성자 </label>  <input type="text" id="writer" name="id" class="form-control writer" value="${id }" readonly="readonly">
            	</div>
                <div id="categoryname">
					<label for="cname">여행 지역 </label>
					<select class="form-select" id="cname" name="cname">
                        <option value="서울">서울</option>
                        <option value="경기">경기</option>
                        <option value="강원">강원</option>
                        <option value="충남">충남</option>
                        <option value="충북">충북</option>
                        <option value="경남">경남</option>
                        <option value="경북">경북</option>
                        <option value="전남">전남</option>
                        <option value="전북">전북</option>
                        <option value="제주">제주</option>
                    </select>
                </div>
                <div id="titlename">
                	<label for="title">제목 </label><input type="text" id="title" name="title" class="form-control title" required>
                </div>
                <div id=editorbox>
                    <textarea name="content" id="summernote" required></textarea>
                </div>
                <div style="width: 184px; display: inline-block; float: right;">
	                <input type="hidden" value="-1" name="ispost1">
	                <input type="submit" class="btn btn-white w-100" value="작성" formaction="${cp }/member/feedboard_add">
	                <c:if test="${feedRole == 'ROLE_AUTHOR'}">
	                	<input type="hidden" value="1" name="ispost2">
	                	<input type="submit" class="btn btn-white w-100" value="발행" formaction="${cp }/member/feedboard_post">
	                </c:if>
                </div>
            </form:form>
        </div>
        <div id="editorarea_foot"></div>
    </div>
       <script>
       let fileName=[];
       
       $('#write').submit(function (e) { 
        //추가해야할것-----------
            //mapcode 내의 길고 긴 html을 제거한다.
            $('#mapcode').empty();
            $('#summernote').summernote('code', $('#summernote').summernote('code'));
        //---------------------
           for (let index = 0; index < fileName.length; index++) {
               $('<input />').attr("type", "hidden")
                   .attr("name", "fileName")
                   .attr("value", fileName[index])
                   .appendTo('#write');
           };
       });



       $(document).ready(function() {
           $('#summernote').summernote({
               height: 600,                 // 에디터 높이
               minHeight: null,             // 최소 높이
               maxHeight: null,             // 최대 높이
               focus: true,				 // 처음 사이트들어왔을때 summernote에 focus를 준다. 커서 깜빡임.
               lang: "ko-KR",				//toolbar 설명을 한글화.
               placeholder: '필수입력입니다.<br><br>첫 번째 첨부 이미지가 썸네일 이미지로 선택됩니다!<br>수정이 불가하니 신중하게 첨부해주세요 :))',	//summernote에 아무것도 입력안되어있을때 보여지는 설명?
               // toolbar: [
               //     // [groupName, [list of button]]
               //     ['style', ['bold', 'italic', 'underline', 'clear']],
               //     ['font', ['strikethrough', 'superscript', 'subscript']],
               //     ['fontsize', ['fontsize']],
               //     ['color', ['color']],
               //     ['para', ['ul', 'ol', 'paragraph']],
               //     ['height', ['height']]
               //   ],
               callbacks: {
                   onImageUpload: function(files, editor, welEditable) { // files만 파라미터로 넘겨줘도 동작하더라. 나머지는 무엇을 의미하는고..?
                       for (var i = files.length - 1; i >= 0; i--) {sendFile(files[i], this);}
                   }
               }
           });
           // $('#summernote').summernote('code', '<p><img src="/spring/resources/upload/파이리dd.jpg" style="width: 600px;"><br></p>');
       });

       //추가해야할것-----------
        let addMapButton = $.summernote.options.buttons;
        $.summernote.options.toolbar.push(["CustomButton", ["addMapToSummerNote"]]);
        let addMapToSummerNote = function(context) {

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

            //js, css를 언로드하는 함수
            function unload(filename, filetype) {
                var targetelement = (filetype == "js") ? "script" : (filetype == "css") ? "link" : "none" //determine element type to create nodelist from
                var targetattr = (filetype == "js") ? "src" : (filetype == "css") ? "href" : "none" //determine corresponding attribute to test for
                var allsuspects = document.getElementsByTagName(targetelement)
                for (var i = allsuspects.length; i >= 0; i--) { //search backwards within nodelist for matching elements to remove
                    if (allsuspects[i] && allsuspects[i].getAttribute(targetattr) != null && allsuspects[i].getAttribute(targetattr).indexOf(filename) != -1)
                        allsuspects[i].parentNode.removeChild(allsuspects[i]) //remove element by calling parentNode.removeChild()
                }
            }


            let ui = $.summernote.ui;
            //TODO:지도 코드
            let mapcode = '<div id="mapcode"></div><p><br></p>';
            let button = ui.button({
                contents : `<svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-map-2" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><line x1="18" y1="6" x2="18" y2="6.01"></line><path d="M18 13l-3.5 -5a4 4 0 1 1 7 0l-3.5 5"></path><polyline points="10.5 4.75 9 4 3 7 3 20 9 17 15 20 21 17 21 15"></polyline><line x1="9" y1="4" x2="9" y2="17"></line><line x1="15" y1="15" x2="15" y2="20"></line></svg>지도 추가하기`,
                tooltip:'지도 추가하기',
                click: function(e) {
                    if ($('#mapcode')[0]) {
                        $('#mapcode').remove();
                    } else {
                        // load('https://code.jquery.com/jquery-3.5.1.js', 'js');
                        load('https://code.jquery.com/ui/1.12.1/jquery-ui.js', 'js');
                        load('https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.js', 'js');
                        load('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-language/v0.10.1/mapbox-gl-language.js', 'js');
                        load('https://npmcdn.com/@turf/turf@5.1.6/turf.min.js', 'js');
                        load('https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js', 'js');
                        load('https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js', 'js');
                        load('https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.css', 'css');
                        load('https://unpkg.com/@tabler/core@latest/dist/css/tabler.min.css', 'css');
                        load('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.5.1/mapbox-gl-geocoder.css', 'css');
                        load('${cp }/resources/map/routemap.js', 'js');
                        load('${cp }/resources/map/routemap.css', 'css');
                    }
                    context.invoke('pasteHTML', mapcode);
                    $('#mapcode').load('${cp}/map');
                }
            });
            return button.render();
        }
        addMapButton.addMapToSummerNote=addMapToSummerNote;
        //--------------------- 

       
       function sendFile(file, el) {	// 첨부파일 data를 ajax로 컨트롤러로 보내주는 function.
           var form_data = new FormData();	//이게 뭘 의미할까..? form으로 캡슐화시키는듯..?
           form_data.append('file', file);	//form 캡슐 안에 file을 append해주는듯..?
           console.log(form_data);
           $.ajax({
               data: form_data,
               type: "POST",
               url: '${pageContext.request.contextPath}/upload/',
               cache: false,	// 캐쉬..? ajax안에 있는 property같음.
               enctype: 'multipart/form-data',	//file을 miltipart form으로 encoding 해서 보내는듯 하다.
               contentType: false,	
               //이 둘은 multipart/form을 사용할때 false로 사용해야 한다. content-type 헤더가 muitipart/form으로 전송되게 하는 역할
               processData: false,	
               //이 둘은 multipart/form을 사용할때 false로 사용해야 한다. formdata를 string으로 변환하지 않는 옵션
               beforeSend: function (jqXHR, settings) {
                   jqXHR.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                   console.log(settings.url);
               },
               success: function(img_name) {	// ajax로 넘어온 data parameter변수이름을 임의로 img_name으로 설정
                   $(el).summernote('insertImage', "${pageContext.request.contextPath}" + img_name.url); // summernote에 insertimage 해주는데 경로는 ajax로 넘어온 url로 해준다.
                   fileName.push(img_name.fileName);
                   fileName.forEach(e=>console.log(e));
               }
           });
       }
   </script>
</div>
