var sendRequest = (method, url, data = null) => {
  return new Promise(async (res) => {
    const ajax = new XMLHttpRequest();

    ajax.open(method, url);
    ajax.setRequestHeader("Content-type", "application/json");
    ajax.setRequestHeader("Access-Control-Allow-Origin", "*");

    // POST
    if (!data) {
      await ajax.send(data);
    } // 실행하고 res을 받는거를 기다림

    // res를 받고나서 실행, 이벤트는 달고 res를 받을때 load가 됨
    ajax.addEventListener("load", (err, result) => {
      console.log(ajax.response);
      // console.log(err.currentTarget.response);
      return res(ajax.response);
    });
    // return res(ajax.response);
  });
};

export { sendRequest };
