import API from "/api/api.js";
import FeedService from "/service/feed.service.js";

class Service {
  constructor() {
    this.API = new API();
    this.model = [];
    this.model.push(this.API.getUserimg());
    this.feedModel = this.API.getFirstFeedLoad();
  }
  getFirstFeed = () => {
    console.log("get call");
    console.log(this.feedModel);
  };
}

export default Service;
