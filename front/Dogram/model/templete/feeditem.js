const feedItem = () => {
  return `<div class="feed_item">
    <div class="feed_header">
        <div class="user_area">
            <img src="/img/main-logo.png" alt="" style="width: 50px">
            <span class="name">${userName}</span>
        </div>
    </div>
    <div class="main_photo">
        <img src="/img/mindog.jpg" alt="">
        <img src="${photo}" alt="">
    </div>
    <div class="desc_area">
        <div class="btn_area">
            <button type="button" style="width: 15px;padding: 0;"><i class="far fa-heart"></i></button>
            <button type="button"><i class="far fa-paper-plane"></i></button>
            <button type="button"><i class="far fa-comment-alt"></i></button>
            <a href="#" class="message_btn"><i class="far fa-envelope"></i></a>
        </div>
        <div class="like">
          <span>좋아요</span> <span>${likeCount}</span>개
        </div>
        <div class="desc_title">
          <span class="name">${userName}</span> <span class="title">${feedTitle}</span>
        </div>
        <div class="comment_area">
          <span class="name">${commentName}</span> <span class="">${commentText}</span>
          <div><button>댓글 더보기...</button></div>
        </div>
    </div>
    </div>`;
};
export default feedItem;
