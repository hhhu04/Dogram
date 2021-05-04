class FeedView {
  constructor() {}
  feedConstructor() {
    this.feedSearchBtn = document.querySelector(".feed_searchBtn");
    this.container = document.querySelector(".content");
    this.feedItem = document.querySelector(".feed_item");
    console.log(this.container);
  }
  bindAddFeed(containerLoad) {
    window.addEventListener("scroll", containerLoad, { passive: true });
  }
}
export default FeedView;
