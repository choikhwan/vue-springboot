<template>
   <div id="wrap" class="login">
    <div class="loginWrap">
	      <div class="horizontal-wrap left">
	    	<h1 class="logo"><img src="@/assets/logo.png" alt="EPAS"></h1>
	      <p>Electrical Property Analyzing System</p>
	</div>
		<div class="horizontal-wrap right">
			<fieldset class="login_cont">
				<legend>login</legend>  
				<form id="loginForm" action="#" method="post">
					<div class="m-logo">
						<h1 class="logo"><img src="@/assets/logo.png" alt="EPAS"></h1>
						<p>Electrical Property Analyzing System</p>
					</div>
					<div class="form-group" data-field="id">
		              <label class="form-label">ID</label>
		              <input type="text" class="input-base" v-model="user_id">
		            </div>
		            <div class="form-group" data-field="password">
		              <label class="form-label">PASSWORD</label>
		              <input type="password" class="input-base" v-model="user_pw">
		            </div>
		            <span class="selector-cover checkbox">
	                  <input type="checkbox"><label>Record login information</label>
	                </span>
	                <p class="tx-exp" style="display: none;" id="txExp"></p>
	                <button type="button" class="btn_login" @click="fnLogin">Login</button>
				</form>
			</fieldset>
		</div>
      </div>
    </div>
    
    <!-- popup -->
    <!-- 패스워드 교체 pop -->
    <div class="popupWrap open userInfoPop" style="display:none;" id="signupForm">
      <div class="dimmed"></div>
      <div class="popupContainer">
	  	<div class="popupCont">
		  <div class="popHeader">
		    <h2><spring:message code="label.user.password.change" /></h2>
		    <button type="button" class="btn_popClose" onclick="closedBpop();"><i class="ri-close-line"></i></button>
	      </div>
	      <div class="popBody">
	      	<h3><spring:message code="label.password.change" /></h3>
	      	<p class="error_desc">Your need to update your password because this is the first time you are signing in, or because your password has expired.</p>
	      	<div class="userInfoWrap">
		      	<fieldset class="fieldControlBox">
					<legend><spring:message code="label.password.change" /></legend>
					<div class="form-group row">
						<label class="form-label"><spring:message code="label.password" /></label>
						<input type="password" class="input-base" id="password_first" name="password_first" onKeyPress="popupKeyCode();">
					</div>
					<div class="form-group row">
						<label class="form-label"><spring:message code="label.password.confirm" /></label>
						<input type="password" class="input-base" id="password_confirm" name="password_confirm" onKeyPress="popupKeyCode();">
					</div>
				  </fieldset>
			  </div>
			  <p class="error_desc" id="txPopExp"></p>
			  <button type="submit" class="btn btn_ok" id="" name="" onclick="changePwd();"><spring:message code="button.confirm" /></button>
	      </div>
	  	</div>
      </div>  
    </div>
</template>
  
<script>
  
  export default {
    data() {
      return {
        user_id: 'choih',
        user_pw: '',
      }
    },
    methods: {
		/**
		* 로그인 이벤트.
		* epas\login\controller\MemberController.java => '/epas/login' annotation 호출
		* chkVal = true 이면 쿠키에 아이디값을 저장하여 사용
		* 
		*     << Modification History >>
		*     
		*     Date        Author       Description
		*     ---------   --------     --------------------
		*     20230626    kjyoo        신규생성
		**/
		fnLogin() {
			if (this.user_id === '') {
				alert('ID를 입력하세요.')
				return
			}
	
			if (this.user_pw === '') {
				alert('비밀번호를 입력하세요.')
				return
			}			

			var chkVal = "true"; //로그인 ID 저장 여부
			this.form = {
				loginId: this.user_id,
				password: this.user_pw,
				checkSaveId: chkVal
			}
			this.$axios.post('/epas/login', null, {params: this.form, headers: {}}).then((response) => {
				alert("로그인 성공");
				if (response.status === 200) {
					this.$session.start()
					this.$session.set('member', response.data.member)
				//	Vue.http.headers.common['Authorization'] = 'Bearer ' + response.body.token
					this.$router.push('/')
				}
			}).catch((err) => {
				alert("에러");
			})
		}
	}
  }
</script>
  
<style>
  #loginForm {
    width: 500px;
    margin: auto;
  }
</style>