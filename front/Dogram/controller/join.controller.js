import Controller from "/controller/controller.js";
import navBarTemp from "/model/templete/navbar.js";

class JoinController extends Controller {
  constructor(service, router) {
    super(service, router);

    this.router.view.bindPostJoin(this.postJoin);
    this.didRenderMount();
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
  };
}

export default JoinController;
