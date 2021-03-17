<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<!-- <script type="text/JavaScript" src="/js/jquery-1.8.3.min.js" ></script> -->

<script language="javascript">
function getAddr(){
	// 적용예 (api 호출 전에 검색어 체크) 	
	if (!checkSearchedWord(document.form.keyword)) {
		return ;
	}

	$.ajax({
		 url :"https://www.juso.go.kr/addrlink/addrLinkApiJsonp.do"  //인터넷망
		,type:"post"
		,data:$("#form").serialize()
		,dataType:"jsonp"
		,crossDomain:true
		,success:function(jsonStr){
			$("#list").html("");
			var errCode = jsonStr.results.common.errorCode;
			var errDesc = jsonStr.results.common.errorMessage;
			if(errCode != "0"){
				alert(errCode+"="+errDesc);
			}else{
				if(jsonStr != null){
					makeListJson(jsonStr);
				}
			}
		}
	    ,error: function(xhr,status, error){
	    	alert("에러발생");
	    }
	});
}

function makeListJson(jsonStr){
	var htmlStr = "";
	htmlStr += "<table border='1' class='table table-vcenter table-mobile-md card-table'>";
	$(jsonStr.results.juso).each(function(){
		htmlStr += "<tr>";
		htmlStr += "<td>"+this.roadAddrPart1+this.roadAddrPart2+"</td>";
		htmlStr += "<td>"+this.zipNo+"</td>";
		htmlStr += "<td><a href=\'javascript:callback(\""+this.roadAddrPart1+"\",\""+this.roadAddrPart2+"\",\""+this.zipNo+"\")\'>선택</a></td>";
		htmlStr += "</tr>";
		console.log(htmlStr);
	});
	htmlStr += "</table>";
	$("#list").html(htmlStr);
}
function callback(roadAddrPart1,roadAddrPart2,zipNo){
	opener.jusoCallBack(roadAddrPart1,roadAddrPart2,zipNo);
	window.close();
}
//특수문자, 특정문자열(sql예약어의 앞뒤공백포함) 제거
function checkSearchedWord(obj){
	if(obj.value.length >0){
		//특수문자 제거
		var expText = /[%=><]/ ;
		if(expText.test(obj.value) == true){
			alert("특수문자를 입력 할수 없습니다.") ;
			obj.value = obj.value.split(expText).join(""); 
			return false;
		}
		
		//특정문자열(sql예약어의 앞뒤공백포함) 제거
		var sqlArray = new Array(
			//sql 예약어
			"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
             		 "UNION",  "FETCH", "DECLARE", "TRUNCATE" 
		);
		
		var regex;
		for(var i=0; i<sqlArray.length; i++){
			regex = new RegExp( sqlArray[i] ,"gi") ;
			
			if (regex.test(obj.value) ) {
			    alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
				obj.value =obj.value.replace(regex, "");
				return false;
			}
		}
	}
	return true ;
}

function enterSearch() {
	var evt_code = (window.netscape) ? ev.which : event.keyCode;
	if (evt_code == 13) {    
		event.keyCode = 0;  
		getAddr(); //jsonp사용시 enter검색 
	} 
}
</script>
<title>Insert title here</title>
<!-- 테스트용 드랍다운 -->
<script src="https://unpkg.com/@popperjs/core@2"></script>
<!--테스트용 부트스트랩 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/@tabler/core@latest/dist/css/tabler.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Caveat&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${cp }/resources/feed.css">
<style>
	*{font-family: '나눔손글씨 사랑해 아들';font-size:25px;font-weight:bold;}
	input::-webkit-input-placeholder {
	  color: #aaaaaa;
	  font-family:'나눔손글씨 사랑해 아들';
	  font-size:25px;
	  font-weight:bold;
	}
	input:-ms-input-placeholder {
	  color: #aaaaaa;
	  font-family:'나눔손글씨 사랑해 아들';
	  font-size:25px;
	  font-weight:bold;
	}
	.form-control{display:inline-block;font-family: '나눔손글씨 사랑해 아들';font-size:25px;
		font-weight:bold;width:540px;margin:8px;}
	.btn.btn-white.w-100{font-family:'나눔손글씨 사랑해 아들';
	  font-size:25px;
	  font-weight:bold;display:inline-block;width:20%!important;position:relative;top:-5px;}
</style>
</head>
<body>
<div style="width:750px;">
	<form name="form" id="form" method="post">
		<input type="hidden" name="currentPage" value="1"/> <!-- 요청 변수 설정 (현재 페이지. currentPage : n > 0) -->
		<input type="hidden" name="countPerPage" value="10"/><!-- 요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100) -->
		<input type="hidden" name="resultType" value="json"/> <!-- 요청 변수 설정 (검색결과형식 설정, json) --> 
<!-- 		<input type="hidden" name="confmKey" value="TESTJUSOGOKR"/>요청 변수 설정 (승인키) -->
		<input type="hidden" name="confmKey" value="devU01TX0FVVEgyMDIxMDIwNDIwMjUxNDExMDc4Mjk="/><%--요청 변수 설정 (승인키) --%>
		<input type="text" name="keyword" class="form-control" placeholder="도로명 주소 입력" value="" onkeydown="enterSearch();" /><!-- 요청 변수 설정 (키워드) -->
		<input type="button" class="btn btn-white w-100" onClick="getAddr();" value="주소검색"/>
		<div id="list" ></div><!-- 검색 결과 리스트 출력 영역 -->
	</form>
</div>
</body>
</html>
						