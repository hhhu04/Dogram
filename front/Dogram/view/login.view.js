class LoginView {
  constructor() {}
  loginConstructor() {
    this.loginVal = document.loginForm.login;
    this.passwordVal = document.loginForm.password;
    this.postLoginBtn = document.getElementById("login_btn");
    this.joinBtn = document.querySelector(".regist_btn");
    console.log(this.loginVal);
  }
  bindPostLogin(postLogin) {
    this.postLoginBtn.addEventListener("click", postLogin);
  }
  bindLinkJoin(linkJoin) {
    this.joinBtn.addEventListener("click", linkJoin);
  }
}

export default LoginView;
