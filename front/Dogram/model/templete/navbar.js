const navBarTemp = (userStatus = "", userText = "") => {
  return `<div id="mySidenav" class="sidenav">
  <a href="" class="closebtn">&times;</a>
  <a href="" id='js_home'>홈</a>
  <a href="#" id='js_feed'>피드게시판</a>
  <a href="#" id='js_store'>뼈다귀장터</a>
  <a href="#" id='js_login'>로그인</a>
  <a href="#" id='js_join'>회원가입</a>
  <a href="#" class="user_status" id='${userStatus}'>${userText}</a>  
  </div>
  `;
};
export default navBarTemp;
