import introTemp from "/model/templete/intro.js";
import feedTemp from "/model/templete/feed.js";
import loginTemp from "/model/templete/login.js";
import joinTemp from "/model/templete/join.js";
import storeTemp from "/model/templete/store.js";
import navBarTemp from "/model/templete/navbar.js";
import feedItem from "/model/templete/feeditem.js";

// import View from "/view/view.js";
// import route from "/model/route.model.js";

class Controller {
  constructor(service, router) {
    this.service = service;
    // this.view = view;
    this.router = router;
    console.log(this.router);
    console.log(this.router.view);

    // intro HTML렌더링 + 요소업데이트
    console.log(navBarTemp());
    this.router.addRoute("intro", "^#/$", introTemp(navBarTemp("asd", "done")));
    this.router.addRoute("feed", "^#/feed$", feedTemp(navBarTemp()));
    this.router.addRoute("login", "^#/auth/login$", loginTemp(navBarTemp()));
    this.router.addRoute("join", "^#/auth/join$", joinTemp(navBarTemp()));
    this.router.addRoute("store", "^#/store$", storeTemp(navBarTemp()));
    this.router.hashChange();
    this.didRenderMount();
    // this.router.view.setCssUrl("css/intro.css");
    this.router.view.setTitle("Dogram");
    window.addEventListener("hashchange", this.didRenderMount);
    // this.bindMount();
    // this.router.view.bindShowNav(this.showNav);
    console.log("done!");
    console.log("bind on!");
    console.log(this);
  }
  // hash가 feed일경우 서비스와의 이벤트바인딩
  didRenderMount = () => {
    console.log("didrendermount");
    this.router.view.bindShowNav(this.showNav);
    this.router.view.bindCloseNav(this.closeNav);
    this.router.view.bindLinkHome(this.linkHome);
    this.router.view.bindLinkFeed(this.linkFeed);
    this.router.view.bindLinkStore(this.linkStore);
    this.router.view.bindLinkLogin(this.linkLogin);
    this.router.view.bindLinkJoin(this.linkJoin);
  };
  // 데이터를 받고
  showNav = () => {
    console.log("after bind");
    if (this.router.view.mySidenav.style.width == "250px") {
      this.router.view.mySidenav.style.width = "0";
      this.router.view.sideNav.classList.remove("on");
    } else {
      this.router.view.mySidenav.style.width = "250px";
      this.router.view.sideNav.classList.add("on");
    }
  };
  closeNav = (e) => {
    e.preventDefault();
    console.log(e);

    this.router.view.mySidenav.style.width = "0";
    this.router.view.sideNav.classList.remove("on");
  };
  linkHome = (e) => {
    console.log(e);
    e.preventDefault();
    window.location.hash = "#/";
    // this.didRenderMount();
  };
  linkFeed = (e) => {
    e.preventDefault();
    window.location.hash = "#/feed";
    // this.didRenderMount();
  };
  linkStore = (e) => {
    e.preventDefault();
    window.location.hash = "#/store";
    // this.didRenderMount();
  };
  linkLogin = (e) => {
    e.preventDefault();
    window.location.hash = "#/auth/login";
    // this.didRenderMount();
  };
  linkJoin = (e) => {
    e.preventDefault();
    window.location.hash = "#/auth/join";
    // this.didRenderMount();
  };
}
export default Controller;
