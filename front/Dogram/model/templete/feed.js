const feedTemp = (navBarTemp, feedItem = "") => {
  // console.log(data["[[PromiseResult]]"]);
  // console.log(data);
  // console.log(typeof data);
  // console.log(feedItem);
  // let feedItemShow = data.map((item) => {
  //   return feedItem(
  //     item.userName,
  //     item.photo,
  //     item.likeCount,
  //     item.commentName
  //   );
  // });
  console.log(feedItem);
  return `<div class='cover-container'>
  <div class="masthead clearfix">
    <div class="inner">
      <h3 class="masthead-brand">
        <a href="#/" class="main-title">DOGRAM</a>
        <button type="button" class="search_btn feed_searchBtn"><i class="fas fa-search"></i></button>
      </h3>
      
      <span class="gnb_btn"><i class="fas fa-bars"></i></span>
    </div>
  </div>
  ${navBarTemp}
  <div class="inner cover">
      <div class="wrapper fadeInDown row">
        <div class="content ">
        ${feedItem ? feedItem : ""}
          <a href="write.html" class="upload_btn"><i class="fas fa-plus" style="font-weight: 500;"></i></a>
      </div>
  </div>

  <!-- <div class="mastfoot">
    <div class="inner">
      <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
    </div>
  </div> -->
</div>`;
};
export default feedTemp;
