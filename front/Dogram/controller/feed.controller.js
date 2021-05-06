import Controller from "/controller/controller.js";
import feedTemp from "/model/templete/feed.js";
import navBarTemp from "/model/templete/navbar.js";
import feedItem from "/model/templete/feeditem.js";

class FeedController extends Controller {
  constructor(service, router) {
    console.log(router);
    super(service, router);
    this.router.addRoute(
      "feed",
      "#/feed",
      feedTemp(navBarTemp("logoutbtn", this.service.userinfo))
    );
    this.router.hashChange();
    this.didRenderMount();
    this.getFeedData();
    this.fistContainerLoad(this.getFeedData());

    this.feedState = 1;
    this.likeState = 0;
    // this.userinfo = this.getCookie("user");
    this.router.view.bindAddFeed(this.containerLoad);

    console.log("feedController");
  }
  getFeedData = async () => {
    this.feedData = await this.service.getFirstFeed();
  };
  fistContainerLoad = (data) => {
    let feedItemShow = data.map((item, idx) => {
      // console.log(item);
      if (idx < 5) {
        return feedItem(
          item.userName,
          item.photo,
          item.likeCount,
          "",
          "",
          "",
          item.no
        );
      }
    });
    console.log(feedItemShow);
    this.router.addRoute(
      "feed",
      "#/feed",
      feedTemp(
        navBarTemp("logoutbtn", this.service.userinfo),
        feedItemShow.join(" ")
      )
    );
  };
  containerLoad = () => {
    const screenHeight = screen.height;
    const fullHeight = this.router.view.FeedView.container.clientHeight;
    const scrollPosition = pageYOffset; // 스크롤위치
    let oneTime = false;
    const madeBox = async () => {
      oneTime = false;
      if (this.feedData.length > this.feedState * 5) {
        console.log("feed load");

        //   console.log(this.feedState);
        let feedItemShow = this.feedData.map((item, idx) => {
          // console.log(idx);
          if (idx < this.feedState * 5 + 5) {
            return feedItem(
              item.userName,
              item.photo,
              item.likeCount,
              "",
              "",
              "",
              item.no
            );
          } else {
            return "";
          }
        });
        //   console.log(feedItemShow);
        this.feedState++;
        // // console.log(this.containerLoad());
        this.router.addRoute(
          "feed",
          "#/feed",
          feedTemp(
            navBarTemp("logoutbtn", this.service.userinfo),
            feedItemShow.join(" ")
          )
          // 리턴을 data가 있는 feedItem this.containerLoad.join(" ")
        );
        this.router.hashChange();
        this.router.view.FeedView.bindAddLike(this.addLike);
        this.didRenderMount();
      }
    };
    // console.log(fullHeight - screenHeight / 1.5);
    if (fullHeight - screenHeight / 1 <= scrollPosition && !oneTime) {
      oneTime = true;
      //   console.log("next");

      madeBox();
    }
  };
  addLike = () => {
    if (this.likeState === 0) {
      this.likeState = 1;
      this.router.view.FeedView.likeBtnFar.classList.remove("far");
      this.router.view.FeedView.likeBtnFar.classList.add("fas");
    } else {
      this.likeState = 0;
      this.router.view.FeedView.likeBtnFar.classList.add("far");
      this.router.view.FeedView.likeBtnFar.classList.remove("fas");
    }
  };
  fillLike = () => {
    if (this.userinfo) {
    }
  };
}
export default FeedController;
