import { sendRequest } from "/lib/ajax.js";

class API {
  getTodoList() {
    let url = "/api/todolist";
    let response = sendRequest("GET", url);

    return response;
  }

  putList(data) {
    let url = "";
  }
}

export default API;
