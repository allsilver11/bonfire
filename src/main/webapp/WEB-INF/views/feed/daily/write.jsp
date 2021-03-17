<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jquery 사용을 위해 import -->
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<!-- bootstrap을 사용하지않은 summernote-lite의 사용을 위해 import -->
<script type="text/javascript" src="${cp }/resources/js/summernote/summernote-lite.js"></script>
<!-- toolbar ko-KR 사용을 위해 import -->
<script type="text/javascript" src="${cp }/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<!-- summernote-lite에 css적용을 위해 css파일을 가져온다. -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/summernote/summernote-lite.css">

<h1>일기 등록</h1>
<form:form id = "write" action = "${cp }/daily/insert" method = "post">
	<c:if test = "${sessionScope.id == id}">
		작성자<br>
		<span>${sessionScope.id }</span><br>
		<input type = "text" name = "id" value = "${sessionScope.id }" hidden="hidden"><br>
		제목<br>
		<input type = "text" name = "title"><br>
		내용<br>
		<textarea name = "content" id = "summernote"></textarea><br>
		<input type="submit" value="등록">
	</c:if>
</form:form>

<script>
        let fileName=[];
        
        $('#write').submit(function (e) { 
            for (let index = 0; index < fileName.length; index++) {
                $('<input />').attr("type", "hidden")
                    .attr("name", "fileName")
                    .attr("value", fileName[index])
                    .appendTo('#write');
            };
        });



        $(document).ready(function() {
            $('#summernote').summernote({
                height: 200,                 // 에디터 높이
                minHeight: null,             // 최소 높이
                maxHeight: null,             // 최대 높이
                focus: true,				 // 처음 사이트들어왔을때 summernote에 focus를 준다. 커서 깜빡임.
                lang: "ko-KR",				//toolbar 설명을 한글화.
                placeholder: '최대 2048자까지 쓸 수 있습니다',	//summernote에 아무것도 입력안되어있을때 보여지는 설명?
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
                beforeSend: function (jqXHR) {
                    jqXHR.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");//spring security header 설정 
                },
                success: function(img_name) {	// ajax로 넘어온 data parameter변수이름을 임의로 img_name으로 설정
                    $(el).summernote('insertImage', "${pageContext.request.contextPath}" + img_name.url); // summernote에 insertimage 해주는데 경로는 ajax로 넘어온 url로 해준다.
                    fileName.push(img_name.fileName);
                }
            });
        }
</script>