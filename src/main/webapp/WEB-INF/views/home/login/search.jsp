<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- search.jsp -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<style>
	#searchApp{padding-top:130px;}
	#left_title{font-size:30px;font-weight: bold;border-bottom: 2px double;margin-bottom:10px;}
	#right_title{font-size:30px;font-weight: bold;border-bottom: 2px double;margin-bottom:10px;}
	#search_left{width:600px;height:500px;float:left;border-right: dotted;}
	#search_right{width:600px;height:500px;float:right;}
	#left_in{width:300px;margin:auto;}
	#right_in{width:300px;margin:auto;}
	.form-control{font-size:25px;font-weight: bold;}
	input{margin-top:10px;margin-bottom:10px;}
	.btn.btn-white.w-100{font-size:25px;font-weight: bold;}
</style>
<div id="searchApp">
	<div id="search_left">
		<div id="left_in">
			<span id="left_title">아이디 찾기</span><br>
			<input type="text" id="id_name" v-model="id_name" placeholder="name" class="form-control">
			<input type="email" id="id_email" v-model="id_email" placeholder="email" class="form-control">
			<button @click="searchId" class="btn btn-white w-100">아이디 찾기</button>
			<br><br>
			<div >
				{{idMsg}}<br>
				<button @click="loginPage" v-show="id_show" class="btn btn-white w-100">로그인 페이지</button>
			</div>
		</div>
	</div>
	<div id="search_right">
		<div id="right_in">
			<span id="right_title">비밀번호 찾기</span><br>
			<input type="text" id="pwd_id" v-model="pwd_id" placeholder="id" class="form-control">
			<input type="text" id="pwd_name"v-model="pwd_name" placeholder="name" class="form-control">
			<input type="email" id="pwd_email"v-model="pwd_email" placeholder="email" class="form-control">
			<button @click="searchPwd" class="btn btn-white w-100">비밀번호 찾기</button>
			<br><br>
			<div>
				{{pwdMsg}}<br>
				<input type="text" v-model="codeNum"  v-show="pwd_show">
				<span v-show="pwd_show">{{mm}}:{{ss}}</span>
				<button @click="goEditPwd" v-show="pwd_show" class="btn btn-white w-100">비밀번호 변경</button>
			</div>
		</div>
	</div>
</div>
<script>
	new Vue({
		el:'#searchApp', 
		data:{ 
			idMsg:'',
			pwdMsg:'',
			id_show:false,
			pwd_show:false,
			id_name:'',
			id_email:'',
			pwd_id:'',
			pwd_name:'',
			pwd_email:'',
			timer:300,
			mm:0,
			ss:0,
			codeNum:'',
			sendCode:''
		},
		methods:{
			searchId:function(){
				var ajax=axios({
					method:'get',
					params:{
						name:this.id_name,
						email:this.id_email
					},
					url:'${pageContext.request.contextPath}/searchId'
				});
				ajax.then(function(resp){
					if(resp.data!=''){
						this.id_show=true;
						this.idMsg="회원님의 아이디는 ["+resp.data+"]입니다.";
					}else{
						this.id_show=false;
						this.idMsg="해당회원이 존재하지 않습니다.";
					}
				}.bind(this));//bind라는 메소드를 사용하여 this를 연결
				
// 				axios.post('${pageContext.request.contextPath}/searchid').then(function(resp){
// 					alert(resp.data);
// 				}.bind(this));
			},
			searchPwd:function(){
				var ajax=axios({
					method:'get',
					params:{
						id:this.pwd_id,
						name:this.pwd_name,
						email:this.pwd_email
					},
					url:'${pageContext.request.contextPath}/searchPwd'
				});
				ajax.then(function(resp){
// 					alert(resp.data);
					if(resp.data!=''){
						this.pwdMsg="이메일로 인증코드를 발송하였습니다.";
						this.sendCode=resp.data;
						this.pwd_show=true;
						setInterval(function() {
							this.mm=parseInt(parseInt(this.timer)/60);
							this.ss=parseInt(parseInt(this.timer)-(60*parseInt(this.mm)));
							if(this.ss<10){
								this.ss = "0"+this.ss;
							}
							this.timer = parseInt(this.timer)-1;
							if(this.timer==0){
								location.href='${cp }/searchIdPwd';
							}
						}.bind(this), 1000);
					}else{
						this.pwd_show=false;
						this.pwdMsg="해당회원이 존재하지 않습니다.";
					}
				}.bind(this));//bind라는 메소드를 사용하여 this를 연결
			},
			loginPage:function(){
				location.href='${cp }/login';
			},
			goEditPwd:function(){
				if(this.codeNum==this.sendCode){
					location.href='${cp }/editPwd?searchId='+this.pwd_id;
				}else{
					alert("인증번호가 틀렸어요~");
				}
			}
		}
	});
</script>