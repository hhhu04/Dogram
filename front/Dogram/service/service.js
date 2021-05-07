import API from "/api/api.js";
import FeedService from "/service/feed.service.js";

class Service {
  constructor() {
    this.API = new API();
    this.model = [];
    this.userinfo = this.getCookie("user");
    console.log(this.userinfo);
    // this.model.push(this.API.getUserimg());
    // this.feedModel = this.getFirstFeed();
    // console.log(this.feedModel);
    // console.log(this.getFirstFeed());
  }

  postLogin = async (userId, userPassword) => {
    let data = {
      id: userId,
      password: userPassword,
    };

    const loginResult = await this.API.postLogin(data);

    document.cookie = `user=${data.id}`;
    this.userinfo = this.getCookie("user");
    return JSON.parse(loginResult.response);
  };
  postUpload = async (data) => {
    const uploadResult = await this.API.postUpload(data);
    return JSON.parse(uploadResult.response);
  };
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

  getCookie = (cName) => {
    cName = cName + "=";
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = "";
    if (start != -1) {
      start += cName.length;
      var end = cookieData.indexOf(";", start);
      if (end == -1) end = cookieData.length;
      cValue = cookieData.substring(start, end);
    }
    return unescape(cValue);
  };

  setCookie = (name, value, expires, path, domain, secure) => {
    var time = new Date();

    expires = expires ? time.setDate(time.getDate() + expires) : "";

    path = path ? "; path=" + path : "";

    domain = domain ? "; domain=" + domain : "";

    secure = secure ? "; secure" : "";

    document.cookie =
      name +
      "=" +
      escape(value) +
      (expires ? "; expires=" + time.toGMTString() : "") +
      path +
      domain +
      secure;
  };
}

export default Service;
