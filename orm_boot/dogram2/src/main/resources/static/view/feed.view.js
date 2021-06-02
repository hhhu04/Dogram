class FeedView {
  constructor() {}
  feedConstructor() {
    this.feedSearchBtn = document.querySelector(".feed_searchBtn");
    this.container = document.querySelector(".content");
    this.feedItem = document.querySelector(".feed_item");
    this.likeBtn = document.querySelectorAll(".likebtn");
    this.likeBtnFar = document.querySelectorAll(".likebtn > i");
    this.uploadBtn = document.querySelector(".upload_btn");
    this.feedAll = document.querySelectorAll(".feed_item");
    this.feedAllArr = Array.from(this.feedAll);
    this.commentBtn = document.querySelectorAll(".commentBtn");
    this.kakaoBtn = document.querySelectorAll(".kakaoBtn");
    this.moreBtn = document.querySelectorAll(".moreBtn");
    this.searchBtn = document.querySelector(".feed_searchBtn");
    this.chat_btn = document.querySelector(".chat_btn");
    // this.deleteBtnArr = Array.from(document.querySelectorAll(".js-deleteBtn"));
    console.log(this.container);
  }
  // bindCommentDelete(deleteComment) {
  //   console.log(this.deleteBtnArr);
  //   // if (!this.deleteBtnArr) return;
  //   this.deleteBtnArr.forEach((btn) => {
  //     btn.addEventListener("click", deleteComment);
  //   });
  // }

  bindChat(chat) {
    this.chat_btn.addEventListener("click", chat);
  }
  bindLinkUpload(linkUpload) {
    this.uploadBtn.addEventListener("click", linkUpload);
  }
  bindAddFeed(containerLoad) {
    window.addEventListener("scroll", containerLoad, { passive: true });
  }
  bindFeedHash(feedHashChange) {
    window.addEventListener("scroll", feedHashChange, { passive: true });
  }
  bindAddLike(addLike) {
    console.log(this.likeBtn);
    console.log(Array.from(this.likeBtn));

    // 노드애들은 유사배열이기때문에 배열로 바꿔줘야 한다
    Array.from(this.likeBtn).map((item) =>
      item.addEventListener("click", addLike)
    );
  }
  bindAddComment(addComment) {
    Array.from(this.commentBtn).map((item) =>
      item.addEventListener("click", addComment)
    );
  }
  bindMoreComment(moreComment) {
    Array.from(this.moreBtn).map((item) =>
      item.addEventListener("click", moreComment)
    );
  }
  shareKakao(shareKakao) {
    Array.from(this.kakaoBtn).forEach((item) => {
      console.log(item);
      item.addEventListener("click", shareKakao);
      // Kakao.Link.createDefaultButton({
      //   container: ".kakaoBtn",
      //   objectType: "feed",
      //   content: {
      //     title: "asdasd",
      //     description: "asdasd",
      //     imageUrl: "asdasd",
      //     link: {
      //       mobileWebUrl: e.path[3].getAttribute("href"),
      //       webUrl: e.path[3].getAttribute("href"),
      //     },
      //   },
      //   social: {
      //     likeCount: 286,
      //     commentCount: 45,
      //     sharedCount: 845,
      //   },
      //   buttons: [
      //     {
      //       title: "웹으로 보기",
      //       link: {
      //         webUrl: e.path[3].getAttribute("href"),
      //       },
      //     },
      //   ],
      // });
      // Kakao.Link.sendDefault({
      //   objectType: "text",
      //   text: "기본 템플릿으로 제공되는 텍스트 템플릿은 텍스트를 최대 200자까지 표시할 수 있습니다. 텍스트 템플릿은 텍스트 영역과 하나의 기본 버튼을 가집니다. 임의의 버튼을 설정할 수도 있습니다. 여러 장의 이미지, 프로필 정보 등 보다 확장된 형태의 카카오링크는 다른 템플릿을 이용해 보낼 수 있습니다.",
      //   link: {
      //     mobileWebUrl: e.path[3].getAttribute("href"),
      //     webUrl: e.path[3].getAttribute("href"),
      //   },
      // });
    });
  }
}
export default FeedView;
