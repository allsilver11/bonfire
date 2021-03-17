<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- home.jsp -->
<style>
	
	#home_wrap{font-size:26px;}
	#aSearch{text-decoration: none;color:white;text-shadow: 2px 2px 5px black;}
	
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
	#home_search{width:1200px;height:650px;margin:auto;
		background-image:url('${cp}/resources/images/banner1.jpg');background-size: 100% 100%;}
	#home_search_form{width:600px;margin:auto;position:relative;top:580px;}
	#home_recommAuthor{width:1200px;height:800px;margin:auto;background-color: #dadcde;}
	#home_recommAuthor_Div{width:1000px;margin:auto;padding-top:220px;}
	#home_popularArticle{width:1200px;height:800px;margin:auto;}
	#home_popularArticle_Div{width:1000px;margin:auto;padding-top:270px;}
	#home_popularPlace{width:1200px;height:800px;margin:auto;background-color: #dadcde;}
	#home_popularPlace_Div{width:1000px;margin:auto;padding-top:220px;}
	li{list-style: none;}
	#home_articleList li{display: inline-block;}
	#home_placeList li{display: inline-block;}
	.authorDiv{width:230px;height:230px; margin-left:10px;margin-right:10px; float:left}
	.articleDiv{width:170px;height:170px; margin-left:5px;margin-right:5px; }
	.placeDiv{width:285px;height:285px; margin-left:10px;margin-right:10px; text-align: center; padding-top: 96px;}
	.btnIcon{opacity:0.5;}
	.btnIcon:hover{opacity:1;}
	.aHover{color:#aaaaaa;text-decoration:none; }
	.aHover:hover{color:black;}
	#home_mask{
		position:absolute;
		z-index:9000;
		left:0px;
		top:0px;
		background-color:black;
		opacity: 0.6;
		display:none;
	}
	#home_searchResult{position:absolute;z-index:9990;left:0px;top:0px; text-align:center;
			width:750px;height:400px;background-color:#eeeeee;display:none;opacity: 0.6;}
	#home_searchResult_slide{position:absolute;z-index:9999;left:0px;top:0px; text-align:center;
			width:750px;height:400px;display:none;}
	.resultDiv{margin-left:10px;margin-right:10px;margin-top:10px;}
	#leftResult{background-color: white;width:180px;height:250px;
		position:absolute;left:20px;top:65px;box-shadow: 2px 2px 2px black;}
	#leftResultBack{background-color: white;width:180px;height:250px;
		position:absolute;left:20px;top:65px;display:none;box-shadow: 2px 2px 2px black;}
	#centerResult{background-color: white;width:230px;height:380px;
		position:absolute;left:250px;top:0px;box-shadow: 2px 2px 2px black;}
	#rightResult{background-color: white;width:180px;height:250px;
		position:absolute;left:530px;top:65px;box-shadow: 2px 2px 2px black;}
	#rightResultBack{background-color: white;width:180px;height:250px;
		position:absolute;left:530px;top:65px;display:none;box-shadow: 2px 2px 2px black;}
	.form-control{display:inline-block;}
</style>
<div id="home_wrap">
	<div id="home_mask">
	</div>
	<div id="home_searchResult">
	</div>
	<div id="home_searchResult_slide">
		<img src="${cp }/resources/images/leftArrowIcon2.png"  class="btnIcon" id="resultLeft"
						style="width:50px;height:50px;position:absolute;left:0px;top:170px;;z-index:10000">
		<img src="${cp }/resources/images/rightArrowIcon2.png"  class="btnIcon" id="resultRight"
						style="width:50px;height:50px;position:absolute;left:700px;top:170px;z-index:10000">
		<div class="resultDiv" id="leftResultBack">
		</div>
		<div class="resultDiv" id="leftResult">
			<img src="${cp }/resources/images/profileIcon.png" id="leftProfile"
				style="width:150px;height:150px;border-radius: 50%;margin-top:50px;">
		</div>
		<div class="resultDiv" id="centerResult">
			<img src="${cp }/resources/images/profileIcon.png" id="centerProfile"
				style="width:150px;height:150px;border-radius: 50%;margin-top:50px;margin-bottom:10px;"><br><br>
			<div id="centerDetail" style="text-align: center;width:100%;height:100px;">
				<span id="prof_id"style="font-size:24px;font-weight: bold;"></span><br>
				<span id="prof_nickname"style="font-size:20px;font-weight: bold;"></span>
			</div>
		</div>
		<div class="resultDiv" id="rightResult">
			<img src="${cp }/resources/images/profileIcon.png" id="rightProfile"
				 style="width:150px;height:150px;border-radius: 50%;margin-top:50px;">
		</div>
		<div class="resultDiv" id="rightResultBack">
		</div>
	</div>
    <div id="home_search">
    	<div id="home_search_form">
	    	<form>
		    	<input type="text" name="keyword" class="form-control" style="width:540px;height:30px;font-size: 25px;" id="searchKeyword" placeholder="작가검색">
		    	<a href="javascript:popupDiv()" id="aSearch">검색</a>
	    	</form>
    	</div>
    </div>
    <div id="home_recommAuthor">
    	<div id="home_recommAuthor_Div">
	   		<span>추천작가 <img src="${cp }/resources/images/refreshIcon.png" class="btnIcon" id="refreshAuthor" style="width:17px;height:15px;"></span>
			<div style = "margin-top: 20px;">
				<img src="${cp }/resources/images/leftArrowIcon2.png"  class="btnIcon" id="authorLeft"
							style="width:60px;height:60px;position:relative;top:-100px;left:0px;">
				<div  id="home_authors" style="width:750px;display:inline-block;overflow:hidden;position:relative;left:40px;">
					<div id="home_authorList" style="width:5000px;float:left;position:relative;">
						<c:forEach var="i" begin="1" end="12">
							<div class="authorDiv" id="author${i}"></div>
						</c:forEach>
					</div>
				</div>
				<img src="${cp }/resources/images/rightArrowIcon2.png"  class="btnIcon" id="authorRight"
							style="width:60px;height:60px;position:relative;top:-100px;left:80px;">
			</div>
		</div>
    </div>
    <div id="home_popularArticle">
    	<div id="home_popularArticle_Div">
	    	<ul>
	    		<li style = "margin-bottom: 20px;">
	    			인기글 <a href="javascript:popularHits()" class="aHover">조회</a>
	    			<a href="javascript:popularRecomm()" class="aHover">추천</a>
	    		</li>
	    		<li>
	    			<ul id="home_articleList">
	    				<li><div class="articleDiv" id="article1"></div></li>
	    				<li><div class="articleDiv" id="article2"></div></li>
	    				<li><div class="articleDiv" id="article3"></div></li>
	    				<li><div class="articleDiv" id="article4"></div></li>
	    				<li><div class="articleDiv" id="article5"></div></li>
	    			</ul>
	    		</li>
	    	</ul>
    	</div>
    </div>
    <div id="home_popularPlace">
   		<div id="home_popularPlace_Div">
	    	<ul>
	    		<li style = "margin-bottom: 20px;">인기여행지</li>
	    		<li>
		    		<ul id="home_placeList">
		   				<li>
		   					<div class="placeDiv" id="place1"></div>
		   				</li>
		   				<li>
		   					<div class="placeDiv" id="place2"></div>
		   				</li>
		   				<li>
		   					<div class="placeDiv" id="place3"></div>
		   				</li>
		   			</ul>
	   			</li>
	    	</ul>
    	</div>
    </div>
</div>
<script>
	var slideNum_g;
	var Cnt;
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();
	var bannerNum=2;
	var isLogin="<%=(String)session.getAttribute("id")%>";
// 	setInterval(function() {
// 		$("#home_search").css('backgroundImage',"url('${cp}/resources/images/banner"+bannerNum+".jpg')")
// 		bannerNum++;
// 		if(bannerNum==7){
// 			bannerNum=1;
// 		}
// 	}, 5000);
	$(window).scroll(function(){
		$("#home_mask").css('top',$(window).scrollTop());
		$("#home_searchResult").css('top',$(window).scrollTop()+windowHeight/2-250);
		$("#home_searchResult_slide").css('top',$(window).scrollTop()+windowHeight/2-250);
	});
	$(window).resize(function(){
		windowWidth = $(window).width();
		windowHeight = $(window).height();
		$("#home_mask").css('width',windowWidth).css('height',windowHeight).css('top',$(window).scrollTop());
		$("#home_searchResult").css('left',windowWidth/2-350)
		.css('top',$(window).scrollTop()+windowHeight/2-250);
		$("#home_searchResult_slide").css('left',windowWidth/2-350)
		.css('top',$(window).scrollTop()+windowHeight/2-250);
	});
	
	$(function(){
		var errorMsg="${errorMsg}";
		if(errorMsg!=''){
			alert(errorMsg);
		}
		var authorPage=1;
		
		// resultDiv Right 버튼
		$("#resultRight").click(function(){
			$("#centerDetail").hide();
			$("#leftResult").fadeOut(1000);
			
			$("#centerResult").animate({
				width:180,
				height:250,
				left:20,
				top:65
			},1000,function(){
				
				$("#centerResult").css({
					width:230,
					height:380,
					left:250,
					top:0
				});
				$("#leftResult").show();
				$("#centerDetail").show();
				
			});
			$("#rightResult").animate({
				width:230,
				height:380,
				left:250,
				top:0
			},1000,function(){
				$.ajax({
					url:"${cp}/searchAuthor.json",
					data:{
						slideNum:slideNum_g+1,
						keyword:$("#searchKeyword").val()
					},
					async: false,
					dataType: 'json',
					success:function(data){
						slideNum_g=$(data)[0].slideNum;
						if(slideNum_g!=Cnt){
							$("#resultLeft").show();
							let pfilenameL=$(data)[0].list[0].pfilename;
							let idL=$(data)[0].list[0].id;
							let nicknameL=$(data)[0].list[0].nickname;
							let pfilenameC=$(data)[0].list[1].pfilename;
							let idC=$(data)[0].list[1].id;
							let nicknameC=$(data)[0].list[1].nickname;
							let pfilenameR=$(data)[0].list[2].pfilename;
							let idR=$(data)[0].list[2].id;
							let nicknameR=$(data)[0].list[2].nickname;
							console.log(pfilenameL);
							if(pfilenameL==null){
								$("#leftProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#leftProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameL);
							}
							$("#leftResult #prof_id").html(idL);
							$("#leftResult #prof_nickname").html(nicknameL);
							$("#centerResult").unbind('click').bind('click',function(){
								location.href='${cp}/@'+idC;
							});
							if(pfilenameC==null){
								$("#centerProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#centerProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameC);
							}
							$("#centerResult #prof_id").html(idC);
							$("#centerResult #prof_nickname").html(nicknameC);
							if(pfilenameR==null){
								$("#rightProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#rightProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameR);
							}
							$("#rightResult #prof_id").html(idR);
							$("#rightResult #prof_nickname").html(nicknameR);
						}else{
							$("#resultLeft").show();
							let pfilenameL=$(data)[0].list[0].pfilename;
							let idL=$(data)[0].list[0].id;
							let nicknameL=$(data)[0].list[0].nickname;
							let pfilenameC=$(data)[0].list[1].pfilename;
							let idC=$(data)[0].list[1].id;
							let nicknameC=$(data)[0].list[1].nickname;
							if(pfilenameL==null){
								$("#leftProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#leftProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameL);
							}
							$("#leftResult #prof_id").html(idL);
							$("#leftResult #prof_nickname").html(nicknameL);
							$("#centerResult").unbind('click').bind('click',function(){
								location.href='${cp}/@'+idC;
							});
							if(pfilenameC==null){
								$("#centerProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#centerProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameC);
							}
							$("#centerResult #prof_id").html(idC);
							$("#centerResult #prof_nickname").html(nicknameC);
							$("#resultRight").hide();
							$("#rightResult").hide();
						}
						$("#rightResult").css({
							width:180,
							height:250,
							left:530,
							top:65
						});
					}
				});
				
				$("#rightResultBack").hide();
				
			});
			$("#rightResultBack").fadeIn(1000);
			console.log(slideNum_g);
			console.log(slideNum_g+1);
			
		});
		// resultDiv Left 버튼
		$("#resultLeft").click(function(){
			$("#centerDetail").hide();
			$("#rightResult").fadeOut(1000);
			
			$("#centerResult").animate({
				width:180,
				height:250,
				left:530,
				top:65
			},1000,function(){
				
				$("#centerDetail").show();
				$("#rightResult").show();
				$("#centerResult").css({
					width:230,
					height:380,
					left:250,
					top:0
				});
			});
			$("#leftResult").animate({
				width:230,
				height:380,
				left:250,
				top:0
			},1000,function(){
				$.ajax({
					url:"${cp}/searchAuthor.json",
					data:{
						slideNum:slideNum_g-1,
						keyword:$("#searchKeyword").val()
					},
					async:false,
					dataType: 'json',
					success:function(data){
						slideNum_g=$(data)[0].slideNum;
						if(slideNum_g!=1){
							$("#resultRight").show();
							let pfilenameL=$(data)[0].list[0].pfilename;
							let idL=$(data)[0].list[0].id;
							let nicknameL=$(data)[0].list[0].nickname;
							let pfilenameC=$(data)[0].list[1].pfilename;
							let idC=$(data)[0].list[1].id;
							let nicknameC=$(data)[0].list[1].nickname;
							let pfilenameR=$(data)[0].list[2].pfilename;
							let idR=$(data)[0].list[2].id;
							let nicknameR=$(data)[0].list[2].nickname;
							if(pfilenameL==null){
								$("#leftProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#leftProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameL);
							}
							$("#leftResult #prof_id").html(idL);
							$("#leftResult #prof_nickname").html(nicknameL);
							$("#centerResult").unbind('click').bind('click',function(){
								location.href='${cp}/@'+idC;
							});
							if(pfilenameC==null){
								$("#centerProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#centerProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameC);
							}
							$("#centerResult #prof_id").html(idC);
							$("#centerResult #prof_nickname").html(nicknameC);
							if(pfilenameR==null){
								$("#rightProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#rightProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameR);
							}
							$("#rightResult #prof_id").html(idR);
							$("#rightResult #prof_nickname").html(nicknameR);
						}else{
							$("#resultRight").show();
							let pfilenameC=$(data)[0].list[0].pfilename;
							let idC=$(data)[0].list[0].id;
							let nicknameC=$(data)[0].list[0].nickname;
							let pfilenameR=$(data)[0].list[1].pfilename;
							let idR=$(data)[0].list[1].id;
							let nicknameR=$(data)[0].list[1].nickname;
							$("#centerResult").unbind('click').bind('click',function(){
								location.href='${cp}/@'+idC;
							});
							if(pfilenameC==null){
								$("#centerProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#centerProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameC);
							}
							$("#centerResult #prof_id").html(idC);
							$("#centerResult #prof_nickname").html(nicknameC);
							if(pfilenameR==null){
								$("#rightProfile").prop("src","${cp}/resources/images/profileIcon.png");
							}else{
								$("#rightProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameR);
							}
							$("#rightResult #prof_id").html(idR);
							$("#rightResult #prof_nickname").html(nicknameR);
							$("#leftResult").hide();
							$("#resultLeft").hide();
						}
						$("#leftResult").css({
							width:180,
							height:250,
							left:20,
							top:65
						});
					}
				});
				
				$("#leftResultBack").hide();
				
			});
			$("#leftResultBack").fadeIn(1000);
			
		});
		//home 화면 오면 추천작가 새로고침
		$.ajax({
			url:"${cp}/recommAuthor",
			dataType: 'xml',
			success:function(data){
				var i=1;
				$(data).find("item").each(function(){
					var pfilename=$(this).find('pfilename').text();
					if(pfilename!=''){
						$("#author"+(i)).css('backgroundImage', 'url(${cp}/resources/upload/profile/'+pfilename+')');
					}else{
						$("#author"+(i)).css('backgroundImage', 'url(${cp}/resources/images/profileIcon.png)');
					}
					$("#author"+(i)).css('backgroundSize', '100% 100%');
					var id=$(this).find('id').text();
					var nickname=$(this).find('nickname').text();
					var intro=$(this).find('intro').text();
					$("#author"+(i)).click(function(){
						location.href="${cp}/@"+id;
					});
					$("#author"+(i++)).append(`
							<div style="width : 230px; height : 230px; background-color : #5d5d5d; opacity : 0.8;"></div>
							<div style="width : 230px; height : 230px; position : relative; top : -230px; text-align : center; color : white; text-shadow : 1px 1px 4px black;"><div style="font-size : 38px; margin-top : 40px;">`+id+`</div>
							<div style="font-size : 20px; margin-top : 5px;">`+nickname+`</div><div style="font-size : 20px; margin : 5px auto; overflow : hidden; width : 200px; height : 60px;">`+intro+`</div></div>
							`);
				});
			}
		});
		//추천작가 새로고침 버튼
		$("#refreshAuthor").click(function(){
			$.ajax({
				url:"${cp}/recommAuthor",
				dataType: 'xml',
				success:function(data){
					var i=1;
					$(data).find("item").each(function(){
						$("#author"+(i)).empty();
						var pfilename=$(this).find('pfilename').text();
						if(pfilename!=''){
							$("#author"+(i)).css('backgroundImage', 'url(${cp}/resources/upload/profile/'+pfilename+')');
						}else{
							$("#author"+(i)).css('backgroundImage', 'url(${cp}/resources/images/profileIcon.png)');
						}
						$("#author"+(i)).css('backgroundSize', '100% 100%');
						var id=$(this).find('id').text();
						var nickname=$(this).find('nickname').text();
						var intro=$(this).find('intro').text();
						$("#author"+(i)).click(function(){
							location.href="${cp}/@"+id;
						});
						$("#author"+(i++)).append(`
								<div style="width : 230px; height : 230px; background-color : #5d5d5d; opacity : 0.8;"></div>
								<div style="width : 230px; height : 230px; position : relative; top : -230px; text-align : center; color : white; text-shadow : 1px 1px 4px black;"><div style="font-size : 38px; margin-top : 40px;">`+id+`</div>
								<div style="font-size : 20px; margin-top : 5px;">`+nickname+`</div><div style="font-size : 20px; margin : 5px auto; overflow : hidden; width : 200px; height : 60px;">`+intro+`</div></div>
								`);
					});
				}
			});
		});
		//추천작가 왼쪽 오른쪽 버튼
		$("#authorLeft").click(function(){
			if(authorPage!=1){
				console.log(parseInt($("#home_authorList").css('left')));
				var move=parseInt($("#home_authorList").css('left'))+750;
				console.log("left2");
				$("#home_authorList").animate({
					left:move
				},500);
				console.log("left3");
				authorPage--;
			}
		});
		$("#authorRight").click(function(){
			if(authorPage!=4){
				var move=parseInt($("#home_authorList").css('left'))-750;
				$("#home_authorList").animate({
					left:move
				},500);
				authorPage++;
			}
		});
		
		
		//인기글
		$.ajax({
			url:"${cp}/popularArticle_hits",
			dataType: 'xml',
			success:function(data){
				var i=1;
				$(data).find("item").each(function(){
					var thumbnail=$(this).find('thumbnail').text();
					if(thumbnail!=''){
						$("#article"+(i)).css('backgroundImage', 'url(${cp}/resources/feedboard/'+thumbnail+')');
					}else{
						$("#article"+(i)).css('backgroundImage', 'url(${cp}/resources/images/bonfire_logo3.png)');
					}
					$("#article"+(i)).css('backgroundSize', '100% 100%');
					var num=$(this).find('num').text();
					var id=$(this).find('id').text();
					var nickname=$(this).find('nickname').text();
					var title=$(this).find('title').text();
					$("#article"+(i)).click(function(){
						if(isLogin == "null"){
							location.href="${cp}/login";
						}else{
							location.href="${cp}/feedboard_detail?num="+num;
						}
					});
					$("#article"+(i++)).append(`
							<div style="width : 170px; height : 170px; background-color : #5d5d5d; opacity : 0.8;"></div>
							<div style="width : 170px; height : 170px; position : relative; top : -170px; text-align : center; color : white; text-shadow : 1px 1px 4px black;">
							<div style="font-size : 23px;  margin : 5px auto; width : 150px; height : 60px; overflow : hidden; margin-top : 40px;">`+title+`</div>
							<div style="font-size : 17px; margin : 10px auto;">by. `+id+`</div></div>`
							);
				});
			}
		});
		
	})
	
	//search버튼 누를시 function
	function popupDiv(){
		var scrollTop=$(window).scrollTop();
		$("#home_mask").css('width',windowWidth).css('height',windowHeight)
		.css('display','block').css('top',scrollTop)
		.click(function(){
			$(this).css('display','none');
			$("#home_searchResult").css('display','none');
			$("#home_searchResult_slide").css('display','none');
// 			$("#home_searchResult").empty().css('display','none');
		});
		$("#home_searchResult").css('left',windowWidth/2-350)
		.css('top',scrollTop+windowHeight/2-250).css('display','block');
		$("#home_searchResult_slide").css('left',windowWidth/2-350)
		.css('top',scrollTop+windowHeight/2-250).css('display','block');
		$.ajax({
			url:"${cp}/searchAuthor.json",
			data:{
				slidesNum:1,
				keyword:$("#searchKeyword").val()
			},
			dataType: 'json',
			success:function(data){
				$("#resultLeft").show();
				$("#resultRight").show();
				slideNum_g=$(data)[0].slideNum;
				Cnt=$(data)[0].Cnt;
				if(Cnt==0){
					$("#home_searchResult").hide();
					$("#home_searchResult_slide").hide();
					setTimeout(function() {
						alert("해당회원이 존재하지않습니다.");
					}, 100);
				}else if(Cnt!=1){
					$("#resultLeft").hide();
					$("#leftResult").hide();
					let pfilenameC=$(data)[0].list[0].pfilename;
					let idC=$(data)[0].list[0].id;
					let nicknameC=$(data)[0].list[0].nickname;
					let pfilenameR=$(data)[0].list[1].pfilename;
					let idR=$(data)[0].list[1].id;
					let nicknameR=$(data)[0].list[1].nickname;
					$("#centerResult").unbind('click').bind('click',function(){
						location.href='${cp}/@'+idC;
					});
					if(pfilenameC==null){
						$("#centerProfile").prop("src","${cp}/resources/images/profileIcon.png");
					}else{
						$("#centerProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameC);
					}
					$("#centerResult #prof_id").html(idC);
					$("#centerResult #prof_nickname").html(nicknameC);
					if(pfilenameR==null){
						$("#rightProfile").prop("src","${cp}/resources/images/profileIcon.png");
					}else{
						$("#rightProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameR);
					}
					$("#rightResult #prof_id").html(idR);
					$("#rightResult #prof_nickname").html(nicknameR);
				}else{
					$("#resultLeft").hide();
					$("#resultRight").hide();
					$("#leftResult").hide();
					$("#rightResult").hide();
					let pfilenameC=$(data)[0].vo.pfilename;
					let idC=$(data)[0].vo.id;
					let nicknameC=$(data)[0].vo.nickname;
					$("#centerResult").unbind('click').bind('click',function(){
						location.href='${cp}/@'+idC;
					});
					if(pfilenameC==null){
						$("#centerProfile").prop("src","${cp}/resources/images/profileIcon.png");
					}else{
						$("#centerProfile").prop("src","${cp}/resources/upload/profile/"+pfilenameC);
					}
					$("#centerResult #prof_id").html(idC);
					$("#centerResult #prof_nickname").html(nicknameC);
				}
			}
		});
	}
	
	
	//인기글 조회수별 새로고침
	function popularHits(){
		$.ajax({
			url:"${cp}/popularArticle_hits",
			dataType: 'xml',
			success:function(data){
				var i=1;
				$(data).find("item").each(function(){
					$("#article"+(i)).empty();
					var thumbnail=$(this).find('thumbnail').text();
					if(thumbnail!=''){
						$("#article"+(i)).css('backgroundImage', 'url(${cp}/resources/feedboard/'+thumbnail+')');
					}else{
						$("#article"+(i)).css('backgroundImage', 'url(${cp}/resources/images/bonfire_logo3.png)');
					}
					$("#article"+(i)).css('backgroundSize', '100% 100%');
					var num=$(this).find('num').text();
					var id=$(this).find('id').text();
					var nickname=$(this).find('nickname').text();
					var title=$(this).find('title').text();
					$("#article"+(i)).click(function(){
						if(isLogin == "null"){
							location.href="${cp}/login";
						}else{
							location.href="${cp}/feedboard_detail?num="+num;
						}
					});
					$("#article"+(i++)).append(`
							<div style="width : 170px; height : 170px; background-color : #5d5d5d; opacity : 0.8;"></div>
							<div style="width : 170px; height : 170px; position : relative; top : -170px; text-align : center; color : white; text-shadow : 1px 1px 4px black;">
							<div style="font-size : 23px;  margin : 5px auto; width : 150px; height : 60px; overflow : hidden; margin-top : 40px;">`+title+`</div>
							<div style="font-size : 17px; margin : 10px auto;">by. `+id+`</div></div>`
							);
				});
			}
		});
	}
	//인기글 추천수 별 새로고침
	function popularRecomm(){
		$.ajax({
			url:"${cp}/popularArticle_recomm",
			dataType: 'xml',
			success:function(data){
				var i=1;
				$(data).find("item").each(function(){
					$("#article"+(i)).empty();
					var thumbnail=$(this).find('thumbnail').text();
					if(thumbnail!=''){
						$("#article"+(i)).css('backgroundImage', 'url(${cp}/resources/feedboard/'+thumbnail+')');
					}else{
						$("#article"+(i)).css('backgroundImage', 'url(${cp}/resources/images/bonfire_logo3.png)');
					}
					$("#article"+(i)).css('backgroundSize', '100% 100%');
					var num=$(this).find('num').text();
					var id=$(this).find('id').text();
					var nickname=$(this).find('nickname').text();
					var title=$(this).find('title').text();
					$("#article"+(i)).click(function(){
						if(isLogin == "null"){
							location.href="${cp}/login";
						}else{
							location.href="${cp}/feedboard_detail?num="+num;
						}
					});
					$("#article"+(i++)).append(`
							<div style="width : 170px; height : 170px; background-color : #5d5d5d; opacity : 0.8;"></div>
							<div style="width : 170px; height : 170px; position : relative; top : -170px; text-align : center; color : white; text-shadow : 1px 1px 4px black;">
							<div style="font-size : 23px;  margin : 5px auto; width : 150px; height : 60px; overflow : hidden; margin-top : 40px;">`+title+`</div>
							<div style="font-size : 17px; margin : 10px auto;">by. `+id+`</div></div>
							`);
				});
			}
		});
	}
	
	//인기여행지 (해당카테고리 글 갯수 기준)
	$.ajax({
		url:"${cp}/popularPlace",
		dataType: 'xml',
		success:function(data){
			var i=1;
			$(data).find("item").each(function(){
				var cname=$(this).find('cname').text();
				switch(cname){
				case "서울" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/서울.jpg)'); break;
				case "경기" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/경기도.jpg)'); break;
				case "경남" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/경남.jpg)'); break;
				case "경북" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/경북.gif)'); break;
				case "전남" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/전남.jpg)'); break;
				case "전북" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/전북.png)'); break;
				case "충남" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/충남.jpg)'); break;
				case "충북" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/충북.jpg)'); break;
				case "강원" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/강원도.png)'); break;
				case "제주" : $("#place"+(i)).css('backgroundImage', 'url(${cp}/resources/images/제주.jpg)'); break;
				}
				$("#place"+(i)).css('backgroundSize', '100% 100%');
				var cnamecnt=$(this).find('cnamecnt').text();
				$("#place"+(i)).click(function(){
					location.href="${cp}/feedboard_selectByRegion?cname="+cname;
				});
				$("#place"+(i++)).append(`
						<span style="color : white; font-size : 70px; text-shadow : 1.5px 1.5px 1.5px black">`+cname+`</span>
						`);
			});
		}
	});
</script>

