import Controller from "/controller/controller.js";
import View from "/view/view.js";
import Service from "/service/service.js";
import Router from "/router.js";
import IntroController from "/controller/intro.controller.js";
import FeedController from "/controller/feed.controller.js";
import JoinController from "/controller/join.controller.js";
import LoginController from "/controller/login.controller.js";
import FeedUploadController from "/controller/feed.upload.ctr.js";

// import API from "/api/api.js";

const app = new Controller(new Service(), new Router(new View()));
// hash controller를 확장하자
// console.log(window.location.hash);

const windowHashChange = () => {
  let hash = window.location.hash;
  console.log(window.location.hash);

  console.log("aaaaaaaaaaaa");
  switch (hash) {
    case "#/":
      new IntroController(app.service, app.router);
      console.log("IntroController");
      break;
    case "#/feed":
      new FeedController(app.service, app.router);
      console.log("FeedController");
      break;
    case "#/feed/upload":
      new FeedUploadController(app.service, app.router);
      break;
    case "#/auth/join":
      new JoinController(app.service, app.router);
      console.log("JoinController");
      break;
    case "#/auth/login":
      new LoginController(app.service, app.router);
      console.log("login done");
      break;
  }
};
windowHashChange();
window.addEventListener("hashchange", windowHashChange);
