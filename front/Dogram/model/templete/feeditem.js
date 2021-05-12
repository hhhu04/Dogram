const feedItem = (
  userName = "",
  photo = "",
  likeCount = "",
  feedTitle = "",
  commentName = "",
  commentText = "",
  contentNum = "",

  // likeState = "",
  heart = "far",
  heartColor = ""
) => {
  return `<div class="feed_item" id="${contentNum}">
    <div class="feed_header">
        <div class="user_area">
            <img src="/img/main-logo.png" alt="" style="width: 50px">
            <span class="name">${userName}</span>
        </div>
    </div>
    <div class="main_photo">
        <!-- <img src="/img/mindog.jpg" alt=""> -->
        <div style="height:400px; background:white">
        <img src="http://101.101.211.145:8080/${photo}" alt="" style="height:100%;">
        </div>
    </div>
    <div class="desc_area">
        <div class="btn_area">
            <button type="button" style="width: 15px;padding: 0;" class="likebtn" id="${contentNum}"><i class="${heart} fa-heart ${heartColor}" id="${contentNum}"></i></button>
            <!--<button type="button" class="shareBtn"><i class="far fa-paper-plane"></i></button>-->
            <button type="button" class="kakaoBtn"><i class="far fa-comment"></i></button>
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
        <div class="commentForm">
        <form method="POST" name="commentForm" class="commentForm" id="${contentNum}comment">
          <textarea class="commentInput" name="comment" autofocus="true"  placeholder="댓글 달기" autocomplete="off" autocorrect="off"></textarea>
          <button type="submit" class="commentBtn" id="${contentNum}">게시</button>
          
        </form>
        </div>
    </div>
    </div>`;
};
export default feedItem;
