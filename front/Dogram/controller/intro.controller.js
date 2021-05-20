import Controller from "/controller/controller.js";
import introTemp from "/model/templete/intro.js";
import navBarTemp from "/model/templete/navbar.js";

class IntroController extends Controller {
  constructor(service, router) {
    super(service, router);
    this.router.addRoute(
      "intro",
      "#/",
      introTemp(navBarTemp("logoutbtn", this.service.userinfo))
    );
    this.router.hashChange();
    this.didRenderMount();
  }
}

export default IntroController;
