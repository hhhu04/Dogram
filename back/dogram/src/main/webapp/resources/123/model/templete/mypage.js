const mypageTemp = (navBarTemp, mypostmenuTemp) => {
  return `<div class="cover-container">
  <div class="masthead clearfix">
    <div class="inner">
      <h3 class="masthead-brand"><a href="../intro.html" class="main-title">DOGRAM</a></h3>

      <span class="gnb_btn"><i class="fas fa-bars"></i></span>
    </div>
  </div>
  ${navBarTemp}
  <div class="inner cover">
      <div class="wrapper fadeInDown row">
        <div class=" content col-12">
          <h1>마이페이지</h1>
          <div class="header_section">
            <div class="icon"><img src="/img/main-logo.png" alt="" style="width:70px"></div>
            <div class="name_area">
              <div class="user_name">Bomi<span class="level"></span></div>
              <div class="level_desc">기본뼈다귀등급</div>
            </div>
            <div class="btn_area">
              <a href="modify.html" id="linkModify">회원정보수정</a>
              <div class="level_check">등급정책</div>
              <a href="#" id="deleteBtn">회원탈퇴</a>
            </div>
          </div>
          ${mypostmenuTemp}
        </div>
      </div>
  </div>

  <!-- <div class="mastfoot">
    <div class="inner">
      <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
    </div>
  </div> -->
</div>`;
};

export default mypageTemp;
