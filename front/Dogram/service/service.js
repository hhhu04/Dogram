import API from "/api/api.js";
import FeedService from "/service/feed.service.js";

class Service {
  constructor() {
    this.API = new API();
    this.model = [];
    // this.model.push(this.API.getUserimg());
    // this.feedModel = this.getFirstFeed();
    // console.log(this.feedModel);
    // console.log(this.getFirstFeed());
  }
  //
  postJoin = async (data) => {
    const joinResult = await this.API.postJoin(data);
    return JSON.parse(joinResult.response);
  };
  getFirstFeed = async () => {
    console.log("get call");
    const getLoadFeed = await this.API.getFirstFeedLoad();
    // console.log(JSON.stringify(JSON.parse(getLoadFeed.response)));
    console.log(typeof JSON.stringify(JSON.parse(getLoadFeed.response)));
    console.log(typeof JSON.parse(getLoadFeed.response));
    return JSON.parse(getLoadFeed.response);
  };
}

export default Service;
