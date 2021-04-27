import Controller from "/controller/controller.js";
import View from "/view/view.js";
import Service from "/service/service.js";
// import Model from "/model/model.js";
import Router from "/router.js";

const app = new Controller(new Service(), new Router(new View()));
