const storeTemp = (navBarTemp) => {
  return `<div class="cover-container ">
<div class="masthead clearfix">
  <div class="inner">
    <h3 class="masthead-brand">
      <a href="../intro.html" class="main-title">DOGRAM</a>
      <button type="button" class="search_btn"><i class="fas fa-search"></i></button>
    </h3>
    
    <span class="gnb_btn"><i class="fas fa-bars"></i></span>
  </div>
</div>
${navBarTemp}
<div class="inner cover">
    <div class="wrapper fadeInDown row">
      <div class="content ">
          <h1 class="title">펫사랑장터</h1>
          <div class="item">
              <a href="productdetail.html" class="product_img"><img src="/img/main-logo.png" alt="" style="width: 100px"></a>
              
              <div class="desc_area">
                  <h2 class="title">귀여운 고슴도치 인형 팔아요</h2>
                  <p class="price">5,000원</p>
              </div>
              <div class="btn_area">
                  <div class="message_area">
                      <a href="#"><i class="far fa-envelope"></i></a><span class="count">3</span>
                  </div>
                  <div class="cart_area">
                      <button class="cart_btn"><i class="fal fa-paw"></i></button><span class="count">100</span>
                  </div>
              </div>
          </div>
          <div class="item">
              <a href="#" class="product_img"><img src="/img/main-logo.png" alt="" style="width: 100px"></a>
              
              <div class="desc_area">
                  <h2 class="title">귀여운 고슴도치 인형 팔아요</h2>
                  <p class="price">5,000원</p>
              </div>
              <div class="btn_area">
                  <div class="message_area">
                      <a href="#"><i class="far fa-envelope"></i></a><span class="count">3</span>
                  </div>
                  <div class="cart_area">
                      <button class="cart_btn"><i class="fal fa-paw"></i></button><span class="count">100</span>
                  </div>
              </div>
          </div>
        
        <a href="marketupload.html" class="upload_btn"><i class="fas fa-plus" style="font-weight: 500;"></i></a>
    </div>
</div>

<!-- <div class="mastfoot">
  <div class="inner">
    <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
  </div>
</div> -->
</div>`;
};
export default storeTemp;
