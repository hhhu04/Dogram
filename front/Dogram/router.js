class Router {
  constructor(view) {
    this.view = view;
    // this.routes = [];
    this.routes = {};
    // this.hashChange = this.hashChange.bind(this);
    console.log(this);

    window.addEventListener("hashchange", this.hashChange);
    // window.addEventListener("DOMContentLoaded", this.hashChange);
  }
  addRoute = (name, url, model) => {
    this.routes[url] = { name, model };
  };

  hashChange = () => {
    console.log("hash change!");

    const hash = window.location.hash;

    // const route = this.routes.filter((route) =>
    //   hash.match(new RegExp(route["url"]))
    // )[0];
    // console.log(this.routes);
    // console.log(hash);
    // const route = hash.match(new RegExp(this.routes[`^${hash}$`].name));
    const route = this.routes[hash];
    console.log(route);
    if (route) {
      this.view.setCssUrl(`css/${route["name"]}.css`);
      this.view.viewConstructor(route["model"]);
      console.log("model load!");
    } else {
      this.view.viewConstructor();
    }
  };
}

export default Router;
