class View {
  constructor() {
    this.app = document.getElementById("app");
    this.cssLink = document.getElementById("css_link");
    this.appTitle = document.getElementById("app_title");
    this.gnbBtn = "";
    this.sideNav = "";
    this.homeBtn = "";
    this.feedBtn = "";
    this.componentsByName = {};
  }

  setCssUrl(url) {
    this.cssLink.href = url;
  }
  setTitle(title) {
    this.appTitle.textContent = title;
  }

  viewConstructor(templete) {
    this.app.innerHTML = templete;
    console.log("load!");
    this.mySidenav = document.getElementById("mySidenav");
    console.log(this.mySidenav);
    this.closeBtn = document.querySelector(".closebtn");
    this.gnbBtn = document.querySelector(".gnb_btn");
    this.sideNav = document.querySelector(".sidenav");

    this.homeBtn = document.getElementById("js_home");
    this.feedBtn = document.getElementById("js_feed");
    this.storeBtn = document.getElementById("js_store");
    this.loginBtn = document.getElementById("js_login");
    this.joinBtn = document.getElementById("js_join");
  }

  addComponent(component) {
    this.componentsByName[component.name] = component;
    // component.model = this.proxify(component.model);
  }

  showComponent(name) {
    this.currentComponent = this.componentsByName[name];

    // if (this.currentComponent) {
    //   this.currentComponent.controller(this.currentComponent.model);
    // }
    this.updateView();
  }
  updateView() {
    // if (!this.currentComponent) {
    //   this.app.innerHTML = this.currentComponent.view(
    //     this.currentComponent.model
    //   );
    // } else {
    //   this.app.innerHTML = "<h3>Not Found</h3>";
    // }
  }
  bindShowNav(navFunc) {
    this.gnbBtn.addEventListener("click", navFunc);
  }
  bindCloseNav(navFunc) {
    this.closeBtn.addEventListener("click", navFunc);
  }
  bindLinkHome(linkHome) {
    this.homeBtn.addEventListener("click", linkHome);
  }
  bindLinkFeed(linkFeed) {
    this.feedBtn.addEventListener("click", linkFeed);
  }
  bindLinkStore(linkStore) {
    this.storeBtn.addEventListener("click", linkStore);
  }
  bindLinkLogin(linkLogin) {
    this.loginBtn.addEventListener("click", linkLogin);
  }
  bindLinkJoin(linkJoin) {
    this.joinBtn.addEventListener("click", linkJoin);
  }
}
export default View;
