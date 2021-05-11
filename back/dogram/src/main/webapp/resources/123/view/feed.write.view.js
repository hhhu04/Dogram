class FeedUploadView {
  constructor() {}
  feedUploadConstructor() {
    this.imagePreview = document.querySelector("#imagePreview");
    this.realInput = document.querySelector("#file");
    this.uploadBtn = document.querySelector("#uploadBtn");
  }
  bindChangeImg(changeImg) {
    if (!this.realInput) return;
    this.realInput.addEventListener("change", changeImg);
  }
  bindPostUpload(postUpload) {
    this.uploadBtn.addEventListener("click", postUpload);
  }
}
export default FeedUploadView;
