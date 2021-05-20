import introTemp from "/model/templete/intro.js";
import feedTemp from "/model/templete/feed.js";
import loginTemp from "/model/templete/login.js";
import joinTemp from "/model/templete/join.js";
import storeTemp from "/model/templete/store.js";
import navBarTemp from "/model/templete/navbar.js";
import feedItem from "/model/templete/feeditem.js";

// import View from "/view/view.js";
// import route from "/model/route.model.js";
// feedItem()
class Controller {
  constructor(service, router) {
    this.service = service;
    // this.view = view;
    this.router = router;
    // window.addEventListener("hashchange", this.didRenderMount);
    // window.addEventListener("hashchange", this.ohterRenderMount);

    // this.router.addRoute(
    //   "intro",
    //   "#/",
    //   introTemp(navBarTemp("logoutbtn", this.service.userinfo))
    // );
    // this.router.addRoute(
    //   "feed",
    //   "#/feed",
    //   feedTemp(navBarTemp("logoutbtn", this.service.userinfo))
    // );
    // // data api를 받아서 templete에 넣고 반복문돌려서 생성한다
    // this.router.addRoute(
    //   "login",
    //   "#/auth/login",
    //   loginTemp(navBarTemp("logoutbtn", this.service.userinfo))
    // );
    // this.router.addRoute(
    //   "join",
    //   "#/auth/join",
    //   joinTemp(navBarTemp("logoutbtn", this.service.userinfo))
    // );
    // this.router.addRoute(
    //   "store",
    //   "#/store",
    //   storeTemp(navBarTemp("logoutbtn", this.service.userinfo))
    // );
    // this.router.hashChange();
    // console.log(this.router.view.gnbBtn);
    // if (this.router.view.gnbBtn) {
    //   this.didRenderMount();
    // }

    // this.ohterRenderMount();
    // this.router.view.setTitle("Dogram");

    // console.log("bind on!");
    // console.log(this);
  }
  // 공통 이벤트 바인딩
  didRenderMount = () => {
    console.log("didrendermount");
    this.router.view.bindShowNav(this.showNav);
    this.router.view.bindCloseNav(this.closeNav);
    this.router.view.bindLinkHome(this.linkHome);
    this.router.view.bindLinkFeed(this.linkFeed);
    this.router.view.bindLinkStore(this.linkStore);
    this.router.view.bindLinkLogin(this.linkLogin);
    this.router.view.bindLinkJoin(this.linkJoin);
    this.router.view.bindLogout(this.logoutFunc);
    this.router.view.bindLinkMypage(this.linkMypage);
  };

  // 해쉬 별 이벤트 바인딩
  // ohterRenderMount = async () => {
  //   console.log("did other rendermount");
  //   const hash = window.location.hash;
  //   switch (hash) {
  //     case "#/":
  //       this.router.hashChange();
  //       console.log("main hash!!");
  //       this.didRenderMount();
  //       break;
  //     case "#/auth/join":
  //       this.router.hashChange();
  //       this.router.view.bindPostJoin(this.postJoin);
  //       this.didRenderMount();
  //       break;
  //     case "#/auth/login":
  //       // this.router.view.LoginView.bindPostLogin(this.postLogin);
  //       break;
  //     case "#/feed":
  //       const getFeedData = await this.service.getFirstFeed();
  //       let feedItemShow = getFeedData.map((item, idx) => {
  //         // console.log(item);
  //         if (idx < 5) {
  //           return feedItem(
  //             item.userName,
  //             item.photo,
  //             item.likeCount,
  //             "",
  //             "",
  //             "",
  //             item.no
  //           );
  //         }
  //       });
  //       console.log(feedItemShow);
  //       this.router.addRoute(
  //         "feed",
  //         "#/feed",
  //         feedTemp(
  //           navBarTemp("logoutbtn", this.service.userinfo),
  //           feedItemShow.join(" ")
  //         )
  //       );
  //       this.router.hashChange();
  //       this.router.view.FeedView.bindAddLike(this.addLike);
  //       this.didRenderMount();
  //       break;
  //   }
  // };

  // 공통 이벤트 메서드
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
  };
  linkFeed = (e) => {
    e.preventDefault();
    window.location.hash = "#/feed/";
  };
  linkStore = (e) => {
    e.preventDefault();
    // window.location.hash = "#/store";
    alert("서비스 준비중입니다👶");
  };
  linkLogin = (e) => {
    e.preventDefault();
    window.location.hash = "#/auth/login";
  };
  linkJoin = (e) => {
    e.preventDefault();
    window.location.hash = "#/auth/join";
  };
  linkMypage = (e) => {
    e.preventDefault();
    window.location.hash = "#/mypage";
  };

  // 해쉬별 메서드
  logoutFunc = (e) => {
    e.preventDefault();
    console.log("logout");

    document.cookie = "";
    // 브라우저 셋쿠키
    this.setCookie("id", "hhh", -1);
    // 내가 저장한것 지우기
    this.service.userinfo = "";

    this.router.addRoute("intro", "#/", introTemp(navBarTemp("", "")));
    window.location.hash = "#/";
    this.router.hashChange();
    this.didRenderMount();
  };

  setCookie = (strName, strValue, iSecond) => {
    var strCookie = strName + "=" + encodeURIComponent(strValue);
    if (typeof iSecond === "number") {
      strCookie += "; max-age=" + iSecond;
    }

    // QQQ: path, domain 유효범위를 설정하고 싶으면 여기서 strCookie 변수에 추가해 주어야 한다.

    document.cookie = strCookie;
  };
}
export default Controller;
