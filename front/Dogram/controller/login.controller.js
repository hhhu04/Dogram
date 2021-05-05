import Controller from "/controller/controller.js";

class LoginController extends Controller {
  constructor(service, router) {
    super(service, router);

    this.router.view.LoginView.bindPostLogin(this.postLogin);
    this.didRenderMount();
  }
  postLogin = (e) => {
    e.preventDefault();
    this.service.postLogin(
      this.router.view.LoginView.loginVal.value,
      this.router.view.LoginView.passwordVal.value
    );
    this.service.API.postLogin(
      this.router.view.LoginView.loginVal.value,
      this.router.view.LoginView.passwordVal.value
    );
    console.log(this.router.view.loginVal.value);
    console.log(this.router.view.passwordVal.value);
  };
}

export default LoginController;
