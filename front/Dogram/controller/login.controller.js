import Controller from "/controller/controller.js";
import navBarTemp from "/model/templete/navbar.js";
import loginTemp from "/model/templete/login.js";

class LoginController extends Controller {
  constructor(service, router) {
    super(service, router);
    this.router.addRoute(
      "login",
      "#/auth/login",
      loginTemp(navBarTemp("logoutbtn", this.service.userinfo))
    );
    this.router.hashChange();
    this.didRenderMount();
    this.router.view.LoginView.bindPostLogin(this.postLogin);
  }
  postLogin = async (e) => {
    e.preventDefault();
    await this.service.postLogin(
      this.router.view.LoginView.loginVal.value,
      this.router.view.LoginView.passwordVal.value
    );
    // this.userinfo = this.getCookie("user");
    this.router.addRoute(
      "intro",
      "#/",
      introTemp(navBarTemp("logoutbtn", this.service.userinfo))
    );
    window.location.hash = "#/";
    this.router.hashChange();
    this.router.view.LoginView.bindPostLogin(this.postLogin);
    this.router.view.LoginView.bindLinkJoin(this.linkJoin);
    this.didRenderMount();
    // this.service.API.postLogin(
    //   this.router.view.LoginView.loginVal.value,
    //   this.router.view.LoginView.passwordVal.value
    // );
  };
}

export default LoginController;
