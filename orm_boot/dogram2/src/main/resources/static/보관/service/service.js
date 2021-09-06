import API from "/api/api.js";
import FeedService from "/service/feed.service.js";

class Service {
  constructor() {
    this.API = new API();
    this.model = [];
    this.userinfo = this.getCookie("id");
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

    document.cookie = `id=${data.id}`;
    this.userinfo = this.getCookie("id");
    return JSON.parse(loginResult.response);
  };
  postUpload = async (data) => {
    const uploadResult = await this.API.postUpload(data);
    return JSON.parse(uploadResult.response);
  };
  postComment = async (data) => {
    const commentResult = await this.API.postComment(data);
    return JSON.parse(commentResult.response);
  };
  deleteComment = async (data) => {
    const delCommentResult = await this.API.deleteComment(data);
    return JSON.parse(delCommentResult.response);
  };
  postJoin = async (data) => {
    const joinResult = await this.API.postJoin(data);
    return JSON.parse(joinResult.response);
  };
  updateUser = async () => {
    const updateResult = await this.API.postUpload(data);
    return JSON.parse(uploadResult.response);
  };
  getFirstFeed = async () => {
    console.log("get call");
    const getLoadFeed = await this.API.getFirstFeedLoad();
    // console.log(JSON.stringify(JSON.parse(getLoadFeed.response)));
    console.log(typeof JSON.stringify(JSON.parse(getLoadFeed.response)));
    console.log(typeof JSON.parse(getLoadFeed.response));
    return JSON.parse(getLoadFeed.response);
  };
  getMyFeedList = async () => {
    console.log("get call");
    const getMyFeed = await this.API.getMyFeedList();
    // console.log(getMyFeed.response);
    return JSON.parse(getMyFeed.response);
  };

  deleteUser = async () => {
    const deleteResult = await this.API.deleteUser();
    return JSON.parse(deleteResult.response);
    await console.log("delete!!");
  };

  addAndDeleteLike = async (data) => {
    const datas = JSON.stringify({ communityNum: data.toString() });
    const addlikeResult = await this.API.addLike(JSON.parse(datas));
    console.log(addlikeResult.response);
    return JSON.parse(addlikeResult.response);
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
