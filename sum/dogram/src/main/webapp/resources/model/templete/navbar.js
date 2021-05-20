const navBarTemp = (userStatus = "", userText = "") => {
  // const jsJoin = document.querySelector("#js_join");
  // console.log(jsJoin);
  // const logText = userText ? "로그아웃" : "로그인";
  let logText = "";
  let mypage = "";
  if (userText) {
    logText = "로그아웃";
    mypage = "마이페이지";
  } else {
    logText = "로그인";
    mypage = "";
  }
  return `<div id="mySidenav" class="sidenav">
  <a href="" class="closebtn">&times;</a>
  <a href="" id='js_home'>홈</a>
  <a href="#" id='js_feed'>피드게시판</a>
  <a href="#" id='js_store'>뼈다귀장터</a>
  <a href="#" id='${userText ? "" : "js_login"}' class='${
    userText ? userStatus : ""
  }'>${logText}</a>
  <a href="#" id='js_join' class="${userText ? "dnone" : ""}">회원가입</a>
  <a href="#" class="user_status ${userText ? "" : "dnone"}">${mypage}</a>  
  </div>
  `;
};
export default navBarTemp;
