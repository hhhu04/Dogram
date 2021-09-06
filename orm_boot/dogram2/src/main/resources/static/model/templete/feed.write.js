const feedWriteTemp = (navBarTemp, feedItem = "") => {
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
                  <div class="content">
                    <h1>새 게시물</h1>
                    <div class="header_section">
                      <form class="form-horizontal" accept-charset="UTF-8" name="uploadForm" method="post" action="#" accept="img/*" enctype="multipart/form-data">
                        <div class="left_side">
                            <div class="form-group">
                                
                                <div class="cols-sm-10">
                                    <div class="input-group filebox">
                                        <!-- <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span> -->
                                        <label for="file" class="cols-sm-2 control-label file_btn"><i class="fas fa-plus"></i></label>
                                        <input type="file" name="img" id="file" accept="image/*">

                                        <div id="imagePreview">
                                            미리보기
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="right_side">
                            <div class="form-group">
                                <label for="title" class="cols-sm-2 control-label">제목</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <!-- <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span> -->
                                        <input type="text" class="form-control" name="title" id="title"  placeholder="제목을 입력하세요"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="category" class="cols-sm-2 control-label">카테고리</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <select class="form-control" name="category" id="category">
                                            <option value='' selected>-- 선택 --</option>
                                            <option value='dog'>강아지</option>
                                            <option value='cat'>고양이</option>
                                            <option value='another'>기타</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
        
                            <div class="form-group">
                                <label for="desc" class="cols-sm-2 control-label">내용</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <textarea name="content" id="desc" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group upload_btn">
                                <button type="submit" onclick="(e) => {e.preventDefault()}" id="uploadBtn" class="btn btn-primary btn-lg btn-block login-button">등록하기</button>
                            </div>
                        </div>
                    </form>

                    </div>
                    
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
export default feedWriteTemp;
