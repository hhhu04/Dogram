var sendRequest = (method, url, data = null) => {
  return new Promise(async (res, reject) => {
    const ajax = new XMLHttpRequest();

    ajax.open(method, url, false);
    ajax.setRequestHeader("Content-type", "application/json");
    ajax.setRequestHeader("Access-Control-Allow-Origin", "*");
    // ajax.setRequestHeader("Cookie", "id=hhh");

    // POST
    if (data) {
      console.log(data);
      await ajax.send(JSON.stringify(data));
      res(ajax);
      // await console.log(ajax.getAllResponseHeaders());
    } // 실행하고 res을 받는거를 기다림
    else {
      // await console.log(res());
      await ajax.send(JSON.stringify({}));
      res(ajax);
    }

    // return res(JSON.parse(ajax.response));
    // res를 받고나서 실행, 이벤트는 달고 res를 받을때 load가 됨
    // ajax.addEventListener("load", (err, result) => {
    //   console.log(ajax.response);
    //   console.log(JSON.parse(ajax.response));
    //   // console.log(ajax);
    //   // console.log(ajax.getAllResponseHeaders());
    //   // console.log(err.currentTarget.response);

    //   return res(JSON.parse(ajax.response));
    //   return res(ajax.response);
    // });
    // return res(ajax.response);
  });
};
var sendRequestFormData = (method, url, data = null) => {
  return new Promise(async (res, reject) => {
    const ajax = new XMLHttpRequest();

    ajax.open(method, url, false);
    // ajax.setRequestHeader("Content-type", "application/json");

    ajax.setRequestHeader("Access-Control-Allow-Origin", "*");

    // POST
    if (data) {
      console.log(data);
      await ajax.send(data);
      res(ajax);
      // await console.log(ajax.getAllResponseHeaders());
    } // 실행하고 res을 받는거를 기다림
    else {
      // await console.log(res());
      await ajax.send(data);
      res(ajax);
    }

    // return res(JSON.parse(ajax.response));
    // res를 받고나서 실행, 이벤트는 달고 res를 받을때 load가 됨
    // ajax.addEventListener("load", (err, result) => {
    //   console.log(ajax.response);
    //   console.log(JSON.parse(ajax.response));
    //   // console.log(ajax);
    //   // console.log(ajax.getAllResponseHeaders());
    //   // console.log(err.currentTarget.response);

    //   return res(JSON.parse(ajax.response));
    //   return res(ajax.response);
    // });
    // return res(ajax.response);
  });
};

// Promise()메서드를 호출하면 대기상태가 된다
// resolve를 실행하면 이행상태가 됨
// then으로 처리 결과 값을 받을 수 있다
export { sendRequest, sendRequestFormData };
