import { sendRequest } from "/lib/ajax.js";

class API {
  getTodoList() {
    let url = "/api/todolist";
    let response = sendRequest("GET", url);

    return response;
  }
  async getUserimg() {
    // let url =
    //   "http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=a73a95d23aafd36d49c370750d7a2781";
    let url = "http://jsonplaceholder.typicode.com/todos/1";
    let response = await sendRequest("GET", url);

    return response;
  }
  async postJoin() {
    // let url =
    //   "http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=a73a95d23aafd36d49c370750d7a2781";
    let url = "/user/join";
    let response = await sendRequest("POST", url);

    return response;
  }
  getData() {
    let data = "../data.weather.json";
    console.log(data);
    return data;
  }
}

export default API;
