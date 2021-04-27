class Router {
  constructor(view) {
    this.view = view;
    this.routes = [];
    // this.hashChange = this.hashChange.bind(this);
    console.log(this);

    window.addEventListener("hashchange", this.hashChange);
    // window.addEventListener("DOMContentLoaded", this.hashChange);
  }
  addRoute = (name, url, model) => {
    this.routes.push({
      name,
      url,
      model,
    });
    console.log(this);

    console.log(this.routes);
  };
  hashChange = () => {
    console.log("aaa");
    console.log(this.routes);
    const hash = window.location.hash;
    console.log(this);
    const route = this.routes.filter((route) =>
      hash.match(new RegExp(route.url))
    )[0];
    console.log(route);
    if (route) {
      this.params = new RegExp(route.url).exec(hash);
      console.log(this.params);
      this.view.setCssUrl(`css/${route.name}.css`);

      this.view.viewConstructor(route.model);
      console.log("model load!");
    } else {
      this.view.viewConstructor();
    }
  };
}

export default Router;
