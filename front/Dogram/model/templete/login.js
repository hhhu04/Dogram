const loginTemp = (navBarTemp) => {
  return `<div class="site-wrapper">
<div class="site-wrapper-inner">
  <div class="cover-container">
    <div class="masthead clearfix">
      <div class="inner">
        <h3 class="masthead-brand"><a href="../intro.html" class="main-title">DOGRAM</a></h3>

        <span class="gnb_btn"><i class="fas fa-bars"></i></span>
      </div>
    </div>
    ${navBarTemp}
    <div class="inner cover">
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <h1 class="login_title">Welcome!!</h1>
                <!-- Login Form -->
                <form action="#" method="post">
                    <input type="text" id="login" class="fadeIn second" name="login" placeholder="id">
                    <input type="text" id="password" class="fadeIn third" name="login" placeholder="password">
                    <input type="button" class="fadeIn fourth login_btn" value="로그인"><a class="underlineHover regist_btn" href="register.html">회원가입</a>
                    <div><a class="underlineHover find_btn" href="#">아이디 / 비밀번호 찾기</a></div>
                </form>
            </div>
        </div>
    </div>

    <!-- <div class="mastfoot">
      <div class="inner">
        <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
      </div>
    </div> -->
  </div>
</div>
</div>`;
};
export default loginTemp;
