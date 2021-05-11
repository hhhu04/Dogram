import Controller from "/controller/controller.js";
import navBarTemp from "/model/templete/navbar.js";
import mypageTemp from "/model/templete/mypage.js";
import mypostmenuTemp from "/model/templete/mypostmenu.js";
import mypostlistTemp from "/model/templete/mypage.postlist.js";

class MypageController extends Controller {
  constructor(service, router) {
    super(service, router);
    this.router.addRoute(
      "mypage",
      "#/mypage",
      mypageTemp(
        navBarTemp("logoutbtn", this.service.userinfo),
        mypostmenuTemp(mypostlistTemp())
      )
    );
    this.router.hashChange();
    this.didRenderMount();
    this.router.view.MypageView.bindLinkModify(this.linkModify);
    this.router.view.MypageView.bindDeleteUser(this.deleteUser);
  }
  linkModify = (e) => {
    e.preventDefault();
    window.location.hash = "#/mypage/modify";
  };

  deleteUser = async (e) => {
    e.preventDefault();
    await this.service.deleteUser();
  };
}

export default MypageController;
