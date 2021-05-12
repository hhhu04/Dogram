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
      "#/feed/",
      // ["#/feed", "#/feed/"],
      // /#\/feed\/?/,
      feedTemp(navBarTemp("logoutbtn", this.service.userinfo)),
      "#/feed/"
    );

    this.router.hashChange();
    this.didRenderMount();
    this.fistContainerLoad();
    this.router.view.FeedView.bindFeedHash(this.feedHashChange);
    this.feedState = 1;
    this.likeState = 0;
    // this.userinfo = this.getCookie("user");
    this.router.view.bindAddFeed(this.containerLoad);
    this.router.view.FeedView.bindLinkUpload(this.linkUpload);
    this.feedRenderMount();

    console.log("feedController");
  }

  moveFeedHash = () => {
    const currHash = window.location.hash;
    const hashId = currHash.replace(/[^0-9]/g, "");
    console.log(currHash.replace(/[^0-9]/g, ""));
    const id = 0;
    console.log(this.router.view.FeedView.feedAllArr);
    window.addEventListener("hashchange", (e) => {
      console.log(e);
      // scrollTo(0);
    });
    this.router.view.FeedView.feedAllArr.filter((listitem) => {
      listitem.id;
    });
    this.router.view.FeedView.feedAllArr.forEach((item) => {
      return;
    });
    window.scrollTo(0);
  };

  feedHashChange = () => {
    const windowTop = window.scrollY || document.documentElement.scrollTop;
    if (windowTop % 100 === 0) {
      const feedId = this.router.view.FeedView.feedAllArr.map((item) => {
        return item.id;
      });
      const currHash = window.location.hash;
      const regex = /[0-9]{0,10}/g;
      const changeCurrHash = currHash.replace(regex, "");
      let addHeight = 0;
      let itemId = "";
      for (let i = 0; addHeight < windowTop; i++) {
        addHeight += this.router.view.FeedView.feedAllArr[i].clientHeight;
        itemId = this.router.view.FeedView.feedAllArr[i].id;
        // console.log(addHeight);
      }
      window.location.hash = changeCurrHash + itemId;
    }
    this.router.view.FeedView.feedAllArr.forEach((item) => {
      // console.log(item.id);
      item.setAttribute(
        "href",
        `http://127.0.0.1:5501/app.html#/feed/${item.id}`
      );
    });

    // this.router.view.FeedView.feedAllArr.forEach((item) => {
    //   // console.log(item.offsetTop);
    //   // console.log(item.clientHeight);
    //   // console.log(windowTop);
    //   if (
    //     windowTop > item.offsetTop &&
    //     item.offsetTop + item.clientHeight > windowTop
    //   ) {
    //     // console.log(item.id + "피드의 id 도착");

    //     console.log(changeCurrHash);
    //     console.log(item.id);
    //     window.location.hash = changeCurrHash + item.id;
    //   }
    // });
  };
  // 좋아요 눌렀을때 모델 변경
  modelChange = (e) => {
    // console.log(e.target.classList.add("fas")); // String

    // console.log(e.target.id); // String
    // console.log(parseInt(e.target.id)); // String
    let targetId = parseInt(e.target.id);
    let heart = "";
    // console.log(this.service.userinfo);

    // 변경
    // feeddata
    this.numberCheck = this.feedData.filter((item) => {
      if (item["num"] === targetId) return true;
    });
    console.log(this.numberCheck);
    console.log(this.numberCheck[0].list);

    this.includeCheck = this.numberCheck[0].list.findIndex((item) => {
      console.log(item);
      return item.id == this.service.userinfo;
    });
    console.log(this.includeCheck);
    console.log(this.numberCheck);
    const userCheck = this.numberCheck[0].list.filter((item) => {
      if (item["id"] === this.service.userinfo) return true;
    });
    console.log(this.numberCheck[0].list); // 게시물에 좋아요한사람
    console.log(userCheck[0]); // 이용자의 아이디가 있는지? 나옴
    console.log(this.includeCheck);
    if (this.includeCheck > -1) {
      for (let i = 0; i < this.numberCheck[0].list.length; i++) {
        if (this.numberCheck[0].list[i].id === this.service.userinfo) {
          console.log(this.numberCheck[0].list[i].id);
          console.log(typeof this.numberCheck[0].list[i]);
          console.log(this.numberCheck[0].list);
          this.numberCheck[0].list.splice(i, 1);
          i--;
        }
      }
      console.log(this.numberCheck[0].list);

      this.numberCheck[0]["heart"] = "far";
      this.service.addAndDeleteLike(this.numberCheck[0].num);
    } else {
      this.numberCheck[0].list.push({ id: this.service.userinfo });
      this.numberCheck[0]["heart"] = "fas";
      console.log(this.numberCheck[0].num);
      this.service.addAndDeleteLike(this.numberCheck[0].num);
    }
    console.log(this.feedData);
    console.log(this.numberCheck[0].list);
    console.log(this.numberCheck[0].list.length);
    const likeCount = this.feedData.map((item, idx) => {
      if (idx < this.feedState * 5) {
        return feedItem(
          item.userName,
          item.img,
          item.list.length,
          "",
          "",
          "",
          item.num,
          item.heart
        );
      }
    });
    this.router.addRoute(
      "feed",
      "#/feed/",
      feedTemp(
        navBarTemp("logoutbtn", this.service.userinfo),
        likeCount.join(" ")
      )
    );
    this.router.hashChange();
    this.didRenderMount();
    this.feedRenderMount();
  };
  feedRenderMount = () => {
    // this.router.view.FeedView.bindAddLike(this.addLike);
    this.router.view.FeedView.bindAddLike(this.modelChange);
    this.router.view.FeedView.bindLinkUpload(this.linkUpload);
    this.router.view.FeedView.bindFeedHash(this.feedHashChange);
    this.router.view.FeedView.bindAddComment(this.addComment);
    // this.moveFeedHash();
  };
  getFeedData = async () => {
    this.feedData = await this.service.getFirstFeed();
    console.log(1);
    // return this.feedData;
  };
  addComment = async (e) => {
    e.preventDefault();
    const form = document.commentForm;
    this.service.postComment(new FormData(form));
  };
  fistContainerLoad = async () => {
    // console.log(this.includeCheck);
    // const data = this.getFeedData();
    const regex = /\/[0-9]{0,10}/g;
    const currHash = window.location.hash.replace(regex, "");
    console.log(currHash);
    this.feedData = await this.service.getFirstFeed();
    this.feedItemShow = this.feedData.map((item, idx) => {
      const checkInfo = item.list.findIndex((item) => {
        return item.id == this.service.userinfo;
      });
      console.log(checkInfo);
      if (checkInfo > -1) {
        item.heart = "fas";
      } else {
        item.heart = "far";
      }
      if (!currHash && idx < 5) {
        return feedItem(
          item.userName,
          item.img,
          item.list.length,
          "",
          "",
          "",
          item.num,
          item.heart
        );
      } else if (currHash) {
        return feedItem(
          item.userName,
          item.img,
          item.list.length,
          "",
          "",
          "",
          item.num,
          item.heart
        );
      }
    });
    console.log(this.feedItemShow);
    this.router.addRoute(
      "feed",
      "#/feed/",
      feedTemp(
        navBarTemp("logoutbtn", this.service.userinfo),
        this.feedItemShow.join(" ")
      )
    );
    this.router.hashChange();
    this.didRenderMount();
    this.feedRenderMount();
    // this.moveFeedHash();
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
          const checkInfo = item.list.findIndex((item) => {
            return item.id == this.service.userinfo;
          });
          console.log(checkInfo);
          if (checkInfo > -1) {
            item.heart = "fas";
          } else {
            item.heart = "far";
          }
          if (idx < this.feedState * 5 + 5) {
            return feedItem(
              item.userName,
              item.img,
              item.list.length,
              "",
              "",
              "",
              item.num,
              item.heart
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
          "#/feed/",
          feedTemp(
            navBarTemp("logoutbtn", this.service.userinfo),
            feedItemShow.join(" ")
          )
          // 리턴을 data가 있는 feedItem this.containerLoad.join(" ")
        );
        this.router.hashChange();
        this.didRenderMount();
        this.feedRenderMount();
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
  addLikeCount = () => {};
  fillLike = () => {
    if (this.userinfo) {
    }
  };
  linkUpload = (e) => {
    e.preventDefault();
    window.location.hash = "#/feed/upload";
  };
}
export default FeedController;
