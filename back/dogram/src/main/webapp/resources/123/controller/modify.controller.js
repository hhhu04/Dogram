import Controller from "/controller/controller.js";
import navBarTemp from "/model/templete/navbar.js";
import modifyTemp from "/model/templete/modify.js";

class ModifyController extends Controller {
  constructor(service, router) {
    super(service, router);
    this.router.addRoute(
      "modify",
      "#/mypage/modify",
      modifyTemp(navBarTemp("logoutbtn", this.service.userinfo))
    );
    this.router.hashChange();
    this.didRenderMount();
    this.router.view.ModifyView.bindPostUpdate(this.userUpdate);
  }

  userUpdate = (e) => {
    e.preventDefault();
    console.log("click");
    const form = document.updateForm;
    const data = new FormData(form);
    this.service.updateUser(data);
  };
}

export default ModifyController;
