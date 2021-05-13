class Router {
  constructor(view) {
    this.view = view;
    this.routes = {};
    // this.FeedController = new FeedController();
    console.log(this);

    // window.addEventListener("hashchange", this.hashChange);
    // window.addEventListener("DOMContentLoaded", this.hashChange);
  }
  addRoute = (name, url, model, urlsub = "") => {
    this.routes[url] = { name, model };
  };

  hashChange = () => {
    console.log("hash change!");
    const regex = /[0-9]{0,10}/g;
    const hash = window.location.hash.replace(regex, "");
    console.log(hash);

    // const route = this.routes.filter((route) =>
    //   hash.match(new RegExp(route["url"]))
    // )[0];
    // console.log(this.routes);
    // console.log(hash);
    // const route = hash.match(new RegExp(this.routes[`^${hash}$`].name));
    const route = this.routes[hash];
    console.log(route);
    console.log(this.routes);

    // 페이지(해쉬)별 css, tempelete, view render
    if (route) {
      this.view.setCssUrl(`css/${route["name"]}.css`);
      this.view.viewConstructor(route["model"]);
      switch (route.name) {
        case "login":
          this.view.LoginView.loginConstructor();
          break;
        case "join":
          this.view.joinConstructor();

          break;
        case "feed":
          this.view.FeedView.feedConstructor();
          break;
        case "feedUpload":
          this.view.FeedUploadView.feedUploadConstructor();
          break;
        case "mypage":
          console.log(route.name);
          this.view.MypageView.mypageConstructor();
          break;

        case "modify":
          console.log(route.name);
          this.view.ModifyView.modifyConstructor();
          break;
      }
      console.log("model load!");
    } else {
      this.view.viewConstructor();
    }
  };
}

export default Router;
