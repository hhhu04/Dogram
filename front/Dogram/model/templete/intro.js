const introTemp = (navBarTemp) => {
  return `<video autoplay muted loop id="myVideo">
    <source src="video/intro_bg.mp4" type="video/mp4">
    </video>
    <div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="cover-container">
            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand"><a href="#/" class="main-title">DOGRAM</a></h3>
                    <span class="gnb_btn"><i class="fas fa-bars"></i></span>
                </div>
            </div>
            ${navBarTemp}
            <div class="inner cover">
                <h1 class="cover-heading">우리 모두가 느끼는 행복</h1>
                <p class="lead">나의 반려동물에게도 행복한 삶과 기록을 남겨주세요. 그 바람은 사실 반려동물을 키우든 안키우든 마음속으로는 모두가 가지고있는 욕망입니다. 이제, 그 모습을 사람들에게 알려주세요.</p>
                <p class="lead ">
                <a href="routes/login.html" class="btn btn-lg btn-default learn-btn">Learn more</a>
                </p>
            </div>
    
            <div class="mastfoot">
                <div class="inner">
                    <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
                </div>
            </div>
        </div>
    </div>
    </div>`;
};
export default introTemp;
