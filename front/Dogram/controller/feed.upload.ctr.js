import Controller from "/controller/controller.js";
import feedUploadTemp from "/model/templete/feed.write.js";
import navBarTemp from "/model/templete/navbar.js";

class FeedUploadController extends Controller {
  constructor(service, router) {
    console.log(router);
    super(service, router);
    this.router.addRoute(
      "feedUpload",
      "#/feed/upload",
      feedUploadTemp(navBarTemp("logoutbtn", this.service.userinfo))
    );

    this.router.hashChange();
    this.didRenderMount();
    this.imagePreview = document.querySelector("#imagePreview");
    this.realInput = document.querySelector("#file");
    this.realInput.addEventListener("change", this.readInputFile);
    // this.router.hashChange();
    this.router.view.bindAddFeed(this.containerLoad);
  }
  readInputFile = (e) => {
    console.log(e);
    console.log(this.imagePreview);
    const selFiles = [];
    let files = e.target.files;
    let fileArr = Array.prototype.slice.call(files);
    let index = 0;
    fileArr.forEach((f) => {
      if (files.length < 11) {
        // if (selFiles.length === 0) {
        //   selFiles.push(f);
        // } else {
        //   selFiles.pop();
        //   selFiles.push(f);
        // }
        selFiles.push(f);

        console.log(selFiles);
        console.log(selFiles[0].name);
        var reader = new FileReader();
        reader.onload = function (e) {
          // console.log(e.target.result);
          var node = document.createElement("div");
          // var textnode = document.createTextNode(
          //   `<a id=img_id_${index}><img src=${e.target.result} data-file=${f.name} /></a>`
          // );
          // var textnode = `<img src=/img/${selFiles[0].name} data-file=${f.name} />`;
          var textnode = `<img src=${e.target.result} data-file=${f.name} />`;
          node.innerHTML = textnode;

          console.log(node);
          // var html = `<a id=img_id_${index}><img src=${e.target.result} data-file=${f.name} /></a>`;
          this.imagePreview = document.querySelector("#imagePreview");

          console.log(this.imagePreview);
          this.imagePreview.appendChild(node);
          index++;
        };

        reader.readAsDataURL(f);
      }
    });
  };
  postUpload = (e) => {
    e.preventDefault();
    const form = document.uploadForm;
    console.log(form);
    const a = new FormData(form);
    for (var key of a.keys()) {
      console.log(key);
    }
    for (var value of a.values()) {
      console.log(value);
    }
    this.service.postUpload(new FormData(form));
  };
}
export default FeedUploadController;
