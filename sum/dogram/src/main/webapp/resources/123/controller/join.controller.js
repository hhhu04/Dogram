import Controller from "/controller/controller.js";
import navBarTemp from "/model/templete/navbar.js";
import joinTemp from "/model/templete/join.js";

class JoinController extends Controller {
  constructor(service, router) {
    super(service, router);
    this.router.addRoute(
      "join",
      "#/auth/join",
      joinTemp(navBarTemp("logoutbtn", this.service.userinfo))
    );
    this.router.hashChange();
    this.didRenderMount();

    this.router.view.bindPostJoin(this.postJoin);
    // this.didRenderMount();
  }

  postJoin = (e) => {
    e.preventDefault();
    const form = document.joinForm;
    console.log(form);
    const a = new FormData(form);
    for (var key of a.keys()) {
      console.log(key);
    }
    for (var value of a.values()) {
      console.log(value);
    }
    this.service.postJoin(new FormData(form));
    window.location.hash = "#/auth/login";
  };
}

export default JoinController;
