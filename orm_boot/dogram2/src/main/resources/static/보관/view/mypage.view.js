class MypageView {
  constructor() {}
  mypageConstructor() {
    // header area
    this.updateBtn = document.querySelector("#updateBtn");
    this.modifyBtn = document.querySelector("#linkModify");
    this.deleteBtn = document.querySelector("#deleteBtn");

    // mypostmenu area
    this.likelistBtn = document.querySelector("#likelistBtn");
    this.mypostBtn = document.querySelector("#mypostBtn");
    this.mywantBtn = document.querySelector("#mywantBtn");
    this.myproductBtn = document.querySelector("#myproductBtn");
    this.pages = document.querySelector("#pages");
  }
  bindLinkModify(linkModify) {
    this.modifyBtn.addEventListener("click", linkModify);
  }
  bindLinkModify(linkModify) {
    this.modifyBtn.addEventListener("click", linkModify);
  }
  bindLinkModify(linkModify) {
    this.modifyBtn.addEventListener("click", linkModify);
  }
  bindLinkModify(linkModify) {
    this.modifyBtn.addEventListener("click", linkModify);
  }
  bindLinkModify(linkModify) {
    this.modifyBtn.addEventListener("click", linkModify);
  }
  bindDeleteUser(deleteUser) {
    this.deleteBtn.addEventListener("click", deleteUser);
  }
}

export default MypageView;
