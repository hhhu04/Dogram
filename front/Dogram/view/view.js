import FeedView from "/view/feed.view.js";
import LoginView from "/view/login.view.js";

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
    this.FeedView = new FeedView();
    this.LoginView = new LoginView();
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
    this.mainBtn = document.querySelector(".main-title");
    this.feedBtn = document.getElementById("js_feed");
    this.storeBtn = document.getElementById("js_store");
    this.loginBtn = document.getElementById("js_login");
    this.joinBtn = document.getElementById("js_join");

    this.postJoinBtn = document.getElementById("js_joinBtn");
  }

  // loginConstructor() {
  //   this.loginVal = document.loginForm.login;
  //   this.passwordVal = document.loginForm.password;
  //   this.postLoginBtn = document.getElementById("login_btn");
  //   this.joinBtn = document.querySelector(".regist_btn");
  //   console.log(this.loginVal);
  // }
  joinConstructor() {
    this.postJoinBtn = document.getElementById("js_joinBtn");
    this.idVal = document.joinForm.id;
    this.passwordVal = document.joinForm.password;
    this.nameVal = document.joinForm.name;
    this.emailVal = document.joinForm.email;
    this.fileVal = document.joinForm.file;
  }
  // addComponent(component) {
  //   this.componentsByName[component.name] = component;
  // }

  // showComponent(name) {
  //   this.currentComponent = this.componentsByName[name];
  //   this.updateView();
  // }
  // updateView() {
  // }
  bindShowNav(navFunc) {
    this.gnbBtn.addEventListener("click", navFunc);
  }
  bindCloseNav(navFunc) {
    this.closeBtn.addEventListener("click", navFunc);
  }
  bindLinkHome(linkHome) {
    this.homeBtn.addEventListener("click", linkHome);
    this.mainBtn.addEventListener("click", linkHome);
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
    // this.registBtn.addEventListener("click", linkJoin);
  }
  bindPostJoin(postJoin) {
    this.postJoinBtn.addEventListener("click", postJoin);
  }
  // bindPostLogin(postLogin) {
  //   this.postLoginBtn.addEventListener("click", postLogin);
  // }
  bindAddFeed(containerLoad) {
    window.addEventListener("scroll", containerLoad, { passive: true });
    console.log("scroll down!");
  }
}
export default View;
