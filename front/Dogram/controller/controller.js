import intro from "/model/templete/intro.js";
import feed from "/model/templete/feed.js";
import View from "/view/view.js";
// import route from "/model/route.model.js";

class Controller {
  constructor(service, router) {
    this.service = service;
    // this.view = view;
    this.router = router;
    console.log(this.router);
    console.log(this.router.view);

    // this.router.view.viewConstructor(intro);

    // intro HTML렌더링 + 요소업데이트
    this.router.addRoute("intro", "^#/$", intro);
    this.router.addRoute("feed", "^#/feed$", feed);
    this.router.hashChange();
    // this.router.view.setCssUrl("css/intro.css");
    this.router.view.setTitle("Dogram");
    console.log("done!");
    window.addEventListener("hashchange", this.didRenderMount);
    this.router.view.bindShowNav(this.showNav);
    console.log("bind on!");
    this.router.view.bindCloseNav(this.closeNav);
    this.router.view.bindLinkHome(this.linkHome);
    this.router.view.bindLinkFeed(this.linkFeed);
  }
  didRenderMount = () => {
    this.showNav();
    this.closeNav();
  };
  showNav = () => {
    console.log("after bind");
    // console.log(this.router.view.gnbBtn);
    this.router.view.gnbBtn.addEventListener("click", () => {
      console.log("click!");
      if (this.router.view.mySidenav.style.width == "250px") {
        this.router.view.mySidenav.style.width = "0";
        this.router.view.sideNav.classList.remove("on");
      } else {
        this.router.view.mySidenav.style.width = "250px";
        this.router.view.sideNav.classList.add("on");
      }
    });
  };
  closeNav = () => {
    this.router.view.closeBtn.addEventListener("click", (e) => {
      e.preventDefault();
      this.router.view.mySidenav.style.width = "0";
      this.router.view.sideNav.classList.remove("on");
    });
  };
  linkHome = () => {
    // this.didRenderMount();
    this.router.view.homeBtn.addEventListener("click", (e) => {
      e.preventDefault();
      window.location.hash = "#/";
      this.didRenderMount();

      // this.view.viewConstructor(intro);
      // this.router.viewConstructor(this.view.app, intro);
      // this.router.changeUrl("app.html/aaa");
    });
  };
  linkFeed = () => {
    // this.didRenderMount();
    // this.router.view.setCssUrl("css/feed.css");
    this.router.view.feedBtn.addEventListener("click", (e) => {
      e.preventDefault();
      window.location.hash = "#/feed";
      this.didRenderMount();

      // this.router.addRoute("feed", "^#/feed$");
      // this.router.hashChange();
      // this.router.view.setCssUrl("css/feed.css");
      // this.router.view.viewConstructor(feed);
      // // 요 과정들이 라우터에서 넘겨받아야할 정보들이다!!
      // // 추가로 url변경, js파일들 넘겨주기
    });
  };
}
export default Controller;
