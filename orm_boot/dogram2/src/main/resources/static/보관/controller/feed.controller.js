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

    this.moveFeedHash();

    this.feedState = 1;
    this.likeState = 0;
    // this.userinfo = this.getCookie("user");
    this.router.view.bindAddFeed(this.containerLoad);
    this.router.view.FeedView.bindLinkUpload(this.linkUpload);

    // this.feedRenderMount();

    // this.loadCommentShow();

    console.log("feedController");
  }
  linkChat = () => {
    console.log("go go chat!");
    window.open(
      "http://192.168.1.71:8070/chat",
      "_blank",
      "width=700, height=700"
    );
  };
  moveFeedHash = async () => {
    const currHash = window.location.hash;
    console.log(currHash);
    const hashId = currHash.replace(/[^0-9]/g, "");
    console.log(currHash.replace(/[^0-9]/g, ""));
    window.addEventListener("load", () => {
      this.router.view.FeedView.feedAllArr.forEach((item, idx) => {
        // console.log(item.offsetTop);
        if (hashId == item.id) {
          console.log(item.clientHeight);
          window.scrollTo(0, item.offsetTop + 50);
          // window.scrollTo(
          //   0,
          //   window.pageYOffset + item.getBoundingClientRect().top + 1050
          // );
        }
      });
    });

    this.router.view.FeedView.bindFeedHash(this.feedHashChange);
    // const id = 0;
    // console.log(this.router.view.FeedView.feedAllArr);
    // window.addEventListener("hashchange", (e) => {
    //   console.log(e);
    //   // window.scrollTo(0, 500);
    // });
    // this.router.view.FeedView.feedAllArr.filter((listitem) => {
    //   listitem.id;
    // });
  };

  feedHashChange = () => {
    // const windowTop = Math.round(
    //   window.scrollY || document.documentElement.scrollTop
    // );
    const windowTop = window.pageYOffset;

    // if (windowTop % 200 === 0) {
    //   console.log(windowTop % 200);
    // const feedId = this.router.view.FeedView.feedAllArr.map((item) => {
    //   return item.id;
    // });
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
    // }
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
  modelChange = async (e) => {
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
    // const userCheck = this.numberCheck[0].list.filter((item) => {
    //   if (item["id"] === this.service.userinfo) return true;
    // });
    console.log(this.numberCheck[0].list); // 게시물에 좋아요한사람
    // console.log(userCheck[0]);
    // 이용자의 아이디가 있는지? 나옴
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
      const likeCount = e.path[3].children[1].children[1];
      likeCount.innerHTML = parseInt(likeCount.innerHTML) - 1;
      // this.numberCheck[0]["heart"] = "far";
      // this.numberCheck[0]["heartColor"] = "heartColor";
      e.target.classList.remove("fas");
      e.target.classList.add("far");

      await this.service.addAndDeleteLike(this.numberCheck[0].num);
    } else {
      const likeCount = e.path[3].children[1].children[1];
      likeCount.innerHTML = parseInt(likeCount.innerHTML) + 1;

      e.target.classList.remove("far");
      e.target.classList.add("fas");

      console.log(e.target.classList);
      this.numberCheck[0].list.push({ id: this.service.userinfo });
      // this.numberCheck[0]["heart"] = "fas";
      console.log(this.numberCheck[0].num);
      await this.service.addAndDeleteLike(this.numberCheck[0].num);
    }

    //   console.log(this.feedData);
    //   console.log(this.numberCheck[0].list);
    //   console.log(this.numberCheck[0].list.length);
    //   this.feedData = await this.service.getFirstFeed();
    //   const likeCount = this.feedData.map((item, idx) => {
    //     const checkInfo = item.list.findIndex((item) => {
    //       return item.id == this.service.userinfo;
    //     });
    //     if (checkInfo > -1) {
    //       item.heart = "fas";
    //       item.heartColor = "heartColor";
    //     } else {
    //       item.heart = "far";
    //     }
    //     if (idx < this.feedState * 5) {
    //       return feedItem(
    //         item.userName,
    //         item.img,
    //         item.list.length,
    //         "",
    //         "",
    //         "",
    //         item.num,
    //         item.heart,
    //         item.heartColor
    //       );
    //     }
    //   });
    //   this.router.addRoute(
    //     "feed",
    //     "#/feed/",
    //     feedTemp(
    //       navBarTemp("logoutbtn", this.service.userinfo),
    //       likeCount.join(" ")
    //     )
    //   );
    //   this.router.hashChange();
    //   this.didRenderMount();
    //   this.loadCommentShow();
    //   this.router.view.FeedView.bindMoreComment(this.moreComment);
    //   this.router.view.FeedView.bindLinkUpload(this.linkUpload);
    //   this.router.view.FeedView.bindFeedHash(this.feedHashChange);
    //   this.router.view.FeedView.bindMoreComment(this.moreComment);
    //   this.router.view.FeedView.shareKakao(this.shareKakao);
    //   this.router.view.FeedView.bindAddLike(this.modelChange);
    //   this.router.view.FeedView.bindAddComment(this.addComment);
    //   this.commentBtnChange();
    //   this.deleteComment();
    // };
    // feedRenderMount = () => {
    //   // this.router.view.FeedView.bindAddLike(this.addLike);

    //   // this.router.view.FeedView.bindAddLike(this.modelChange);
    //   // this.router.view.FeedView.bindLinkUpload(this.linkUpload);
    //   // this.router.view.FeedView.bindFeedHash(this.feedHashChange);
    //   // this.router.view.FeedView.bindMoreComment(this.moreComment);
    //   // this.router.view.FeedView.bindAddComment(this.addComment);
    //   this.commentBtnChange();

    //   this.router.view.FeedView.shareKakao(this.shareKakao);

    // this.moveFeedHash();
  };
  shareKakao = (e) => {
    console.log("share event!");
    console.log(e);
    // console.log(e.path[4].children[2].children[2].innerText);
    const content = e.path[4].children[2].children[2].innerText;

    Kakao.Link.sendDefault({
      objectType: "text",
      text: `제목/내용 : ${content}`,
      link: {
        mobileWebUrl: e.path[4].getAttribute("href"),
        webUrl: e.path[4].getAttribute("href"),
      },
    });
  };
  getFeedData = async () => {
    this.feedData = await this.service.getFirstFeed();
    console.log(1);
    // return this.feedData;
  };
  addComment = async (e) => {
    e.preventDefault();
    const form = e.path[1];
    console.log(form.comment);
    console.log(form.comment.name);
    console.log(form.comment.value);

    const data = {
      comment: form.comment.value,
      communityNum: e.target.id,
    };
    console.log(e.path[1].children[1]);
    // const commentTag = document.createElement("p");
    // const commentWrap = document.createElement("div");
    const commentId = document.createElement("p");
    commentId.classList.add("js-commentWrap");
    const deleteBtn = document.createElement("div");
    deleteBtn.textContent = "삭제";
    deleteBtn.classList.add("js-deleteBtn");
    commentId.appendChild(deleteBtn);
    const commentText = document.createElement("span");
    const commentIn = document.createTextNode(`${this.service.userinfo}`);
    const commentTextIn = document.createTextNode(`${form.comment.value}`);
    commentText.classList.add("js-commentText");
    commentId.appendChild(commentIn);
    commentText.appendChild(commentTextIn);
    commentId.appendChild(commentText);
    // commentTag.appendChild(commentId);
    // commentWrap.prepend(commentId);
    // form.prepend(commentId);
    const commentWrap = e.path[1].children[0];
    console.log(e.path[1].children[0]);
    commentWrap.prepend(commentId);
    form.comment.value = "";
    console.log(e.target);

    const commentNum = await this.service.postComment(data);
    console.log(e);
    deleteBtn.dataset.num = commentNum;
    // this.loadCommentShow();
    this.deleteComment();
  };
  moreComment = (e) => {
    console.log(
      e.target.parentElement.parentElement.nextElementSibling.querySelector(
        ".commentWrap"
      )
    );
    const commentWrap =
      e.target.parentElement.parentElement.nextElementSibling.querySelector(
        ".commentWrap"
      );
    if (!commentWrap.classList.contains("commentDetail")) {
      commentWrap.classList.add("commentDetail");
    } else {
      commentWrap.classList.remove("commentDetail");
    }
  };

  // feed data의 댓글을 보여준다
  loadCommentShow = async () => {
    console.log(this.feedData);

    await this.feedData.forEach((data) => {
      // const num = data.num;
      const clist = data.clist;
      const form = document.getElementById(`${data.num}comment`);
      const commentWrap = document.createElement("div");
      commentWrap.classList.add("commentWrap");
      console.log(form);
      clist.forEach((item, idx) => {
        const comment = item.comment;
        const userId = item.userId;
        // const commentTag = document.createElement("p");

        const commentIds = document.createElement("p");
        commentIds.classList.add("js-commentWrap");
        const deleteBtn = document.createElement("div");
        deleteBtn.textContent = "삭제";
        deleteBtn.classList.add("js-deleteBtn");
        commentIds.appendChild(deleteBtn);
        deleteBtn.dataset.userNum = item.userNum;
        deleteBtn.dataset.num = item.num;
        deleteBtn.dataset.communityNum = item.communityNum;
        console.log(deleteBtn.dataset);
        const commentText = document.createElement("span");
        const commentIn = document.createTextNode(`${userId}`);
        const commentTextIn = document.createTextNode(`${comment}`);
        commentText.classList.add("js-commentText");
        commentIds.appendChild(commentIn);
        commentText.appendChild(commentTextIn);
        commentIds.appendChild(commentText);
        // commentTag.appendChild(commentId);
        console.log(commentIds);
        commentWrap.prepend(commentIds);
        console.log(commentWrap);
      });
      console.log(form);
      if (form) {
        form.prepend(commentWrap);
      }

      // for (let i = 0; i < 2; i++) {
      //   form.prepend(commentWrap);
      // }
      console.log(clist);
    });
  };
  commentBtnChange() {
    const commentBtnArr = Array.from(this.router.view.FeedView.commentBtn);
    // console.log(commentBtnArr);
    commentBtnArr.forEach((btn, idx) => {
      // console.log(idx);
      // console.log(btn);
      btn.style.color = "lightgray";
      btn.form[0].addEventListener("input", () => {
        if (btn.form[0].value != "") {
          btn.style.color = "#5550b4";
        } else {
          btn.style.color = "lightgray";
        }
      });
    });

    // if ((form.comment.value = "")) {
    //   e.target.style.color = "red";
    // }
  }
  deleteComment = () => {
    const deleteBtnArr = Array.from(document.querySelectorAll(".js-deleteBtn"));
    deleteBtnArr.forEach((btn) => {
      btn.addEventListener("click", (e) => {
        // console.log(e.path[1]);
        e.path[1].remove();
        const data = {
          userNum: e.target.dataset.userNum,
          num: e.target.dataset.num,
          communityNum: e.target.dataset.communityNum,
        };
        this.service.deleteComment(data);
      });
    });
    // console.log(document.querySelectorAll(".js-deleteBtn"));
  };
  fistContainerLoad = async () => {
    // console.log(this.includeCheck);
    // const data = this.getFeedData();

    const regex = /\/[0-9]{0,10}/g;
    const currHash = window.location.hash.replace(regex, "");
    console.log(currHash);
    this.feedData = await this.service.getFirstFeed();
    this.feedItemShow = this.feedData.map((item, idx) => {
      console.log(item);
      const checkInfo = item.list.findIndex((item) => {
        return item.id == this.service.userinfo;
      });

      // console.log(checkInfo);
      if (checkInfo > -1) {
        item.heart = "fas";
        item.heartColor = "heartColor";
      } else {
        item.heart = "far";
      }
      if (!currHash && idx < 5) {
        return feedItem(
          item.userId,
          item.img,
          item.list.length,
          item.title,
          "",
          "",
          item.num,
          item.heart,
          item.heartColor,
          item.userImg,
          item.content
        );
      } else if (currHash) {
        return feedItem(
          item.userId,
          item.img,
          item.list.length,
          item.title,
          "",
          "",
          item.num,
          item.heart,
          item.heartColor,
          item.userImg,
          item.content
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
    this.router.view.FeedView.feedAllArr.forEach((item) => {
      // console.log(item.id);
      item.setAttribute(
        "href",
        `http://127.0.0.1:5501/app.html#/feed/${item.id}`
      );
    });
    this.didRenderMount();
    this.moveFeedHash();
    this.loadCommentShow();
    // this.feedRenderMount();

    this.router.view.FeedView.bindMoreComment(this.moreComment);
    this.router.view.FeedView.bindAddLike(this.modelChange);
    this.router.view.FeedView.bindLinkUpload(this.linkUpload);
    this.router.view.FeedView.bindFeedHash(this.feedHashChange);
    this.router.view.FeedView.bindMoreComment(this.moreComment);
    this.router.view.FeedView.bindAddComment(this.addComment);
    this.commentBtnChange();
    this.deleteComment();
    this.router.view.FeedView.shareKakao(this.shareKakao);
    this.router.view.FeedView.bindChat(this.linkChat);

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
            item.heartColor = "heartColor";
          } else {
            item.heart = "far";
            // item.heartColor = "heartColor";
          }
          if (idx < this.feedState * 5 + 5) {
            return feedItem(
              item.userId,
              item.img,
              item.list.length,
              item.title,
              "",
              "",
              item.num,
              item.heart,
              item.userImg,
              item.content
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
        this.loadCommentShow();
        // this.feedRenderMount();

        this.router.view.FeedView.bindMoreComment(this.moreComment);
        this.router.view.FeedView.bindAddLike(this.modelChange);
        this.router.view.FeedView.bindLinkUpload(this.linkUpload);
        this.router.view.FeedView.bindFeedHash(this.feedHashChange);
        this.router.view.FeedView.bindMoreComment(this.moreComment);
        this.router.view.FeedView.bindAddComment(this.addComment);
        this.commentBtnChange();
        this.deleteComment();
        this.router.view.FeedView.shareKakao(this.shareKakao);
        this.router.view.FeedView.bindChat(this.linkChat);
      }
    };
    // console.log(fullHeight - screenHeight / 1.5);
    if (fullHeight - screenHeight / 1 <= scrollPosition && !oneTime) {
      oneTime = true;
      //   console.log("next");

      madeBox();
    }
  };
  // addLike = () => {
  //   if (this.likeState === 0) {
  //     this.likeState = 1;
  //     this.router.view.FeedView.likeBtnFar.classList.remove("far");
  //     this.router.view.FeedView.likeBtnFar.classList.add("fas");
  //   } else {
  //     this.likeState = 0;
  //     this.router.view.FeedView.likeBtnFar.classList.add("far");
  //     this.router.view.FeedView.likeBtnFar.classList.remove("fas");
  //   }
  // };
  // addLikeCount = () => {};
  // fillLike = () => {
  //   if (this.userinfo) {
  //   }
  // };
  linkUpload = (e) => {
    e.preventDefault();
    window.location.hash = "#/feed/upload";
  };
}
export default FeedController;
