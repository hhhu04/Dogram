import Controller from "/controller/controller.js";
import View from "/view/view.js";
import Service from "/service/service.js";
import Router from "/router.js";
// import API from "/api/api.js";
const app = new Controller(new Service(), new Router(new View()));
