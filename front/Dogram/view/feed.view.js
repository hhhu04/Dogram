class FeedView {
  constructor() {}
  feedConstructor() {
    this.feedSearchBtn = document.querySelector(".feed_searchBtn");
    this.container = document.querySelector(".content");
    this.feedItem = document.querySelector(".feed_item");
    this.likeBtn = document.querySelectorAll(".likebtn");
    this.likeBtnFar = document.querySelectorAll(".likebtn > i");
    this.uploadBtn = document.querySelector(".upload_btn");
    console.log(this.container);
  }
  bindLinkUpload(linkUpload) {
    this.uploadBtn.addEventListener("click", linkUpload);
  }
  bindAddFeed(containerLoad) {
    window.addEventListener("scroll", containerLoad, { passive: true });
  }
  bindAddLike(addLike) {
    console.log(this.likeBtn);
    console.log(Array.from(this.likeBtn));

    // 노드애들은 유사배열이기때문에 배열로 바꿔줘야 한다
    Array.from(this.likeBtn).map((item) =>
      item.addEventListener("click", addLike)
    );
    // this.likeBtn.addEventListener("click", addLike);
  }
}
export default FeedView;
