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
    // this.FeedController = new FeedController(service, router);
    // console.log(this.router);
    // console.log(this.router.view);
    // this.service.API.getUserimg();
    window.addEventListener("hashchange", this.didRenderMount);
    window.addEventListener("hashchange", this.ohterRenderMount);

    this.router.addRoute("intro", "#/", introTemp(navBarTemp("asd", "done")));
    this.router.addRoute("feed", "#/feed", feedTemp(navBarTemp()));
    // data api를 받아서 templete에 넣고 반복문돌려서 생성한다
    this.router.addRoute("login", "#/auth/login", loginTemp(navBarTemp()));
    this.router.addRoute("join", "#/auth/join", joinTemp(navBarTemp()));
    this.router.addRoute("store", "#/store", storeTemp(navBarTemp()));
    this.router.hashChange();
    this.didRenderMount();
    this.ohterRenderMount();
    this.router.view.setTitle("Dogram");

    console.log("bind on!");
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
  };

  // 해쉬 별 이벤트 바인딩
  ohterRenderMount = async () => {
    console.log("did other rendermount");

    const hash = window.location.hash;
    switch (hash) {
      case "#/":
        this.didRenderMount();
        break;
      case "#/auth/join":
        this.router.view.bindPostJoin(this.postJoin);
        // this.didRenderMount();
        break;
      case "#/auth/login":
        this.router.view.bindPostLogin(this.postLogin);
        this.router.view.bindLinkJoin(this.linkJoin);
        // this.didRenderMount();
        break;
      case "#/feed":
        const getFeedData = await this.service.getFirstFeed();
        let feedItemShow = getFeedData.map((item, idx) => {
          // console.log(item);
          if (idx < 5) {
            return feedItem(
              item.userName,
              item.photo,
              item.likeCount,
              item.commentName
            );
          }
        });
        console.log(feedItemShow);
        this.router.addRoute(
          "feed",
          "#/feed",
          feedTemp(navBarTemp(), feedItemShow.join(" "))
        );
        this.router.hashChange();
        this.didRenderMount();
        break;
    }
  };
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
    // addrouter
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
  // postJoin = (e) => {
  //   e.preventDefault();
  //   this.service.API.postJoin();
  // };
  postLogin = (e) => {
    e.preventDefault();
    this.service.API.postLogin(
      this.router.view.loginVal.value,
      this.router.view.passwordVal.value
    );
    console.log(this.router.view.loginVal.value);
    console.log(this.router.view.passwordVal.value);
  };
  // containerLoad = () => {
  //   const screenHeight = screen.height;
  //   const fullHeight = this.router.view.FeedView.container.clientHeight;
  //   const scrollPosition = pageYOffset; // 스크롤위치
  //   let oneTime = false;
  //   // let feedItemShow = getFeedData.map((item, idx) => {
  //   //   console.log(idx);
  //   //   if (idx <= 9) {
  //   //     return feedItem(
  //   //       item.userName,
  //   //       item.photo,
  //   //       item.likeCount,
  //   //       item.commentName
  //   //     );
  //   //   }
  //   // });
  //   const madeBox = async () => {
  //     oneTime = false;
  //     // const getFeedData = await this.service.getFirstFeed();
  //     // console.log(getFeedData);
  //     const getFeedData = await this.service.getFirstFeed();
  //     console.log(getFeedData);
  //     console.log("feed load");
  //     // console.log(typeof getFeedData);
  //     // console.log(getFeedData[0].userName);
  //     // console.log("feed!!!");
  //     this.feedState++;
  //     let feedItemShow = getFeedData.map((item, idx) => {
  //       console.log(idx);
  //       if (this.feedState * 5 <= idx < this.feedState * 5 + 5) {
  //         return feedItem(
  //           item.userName,
  //           item.photo,
  //           item.likeCount,
  //           item.commentName
  //         );
  //       }
  //     });
  //     // // console.log(feedItemShow);
  //     // // console.log(this.containerLoad());
  //     this.router.addRoute(
  //       "feed",
  //       "#/feed",
  //       feedTemp(navBarTemp(), feedItemShow.join(" "))
  //       // 리턴을 data가 있는 feedItem this.containerLoad.join(" ")
  //     );
  //     this.router.hashChange();
  //     this.didRenderMount();
  //   };
  //   if (fullHeight - screenHeight / 1 <= scrollPosition && !oneTime) {
  //     oneTime = true;
  //     console.log("next");
  //     madeBox();
  //   }
  // };
}
export default Controller;
