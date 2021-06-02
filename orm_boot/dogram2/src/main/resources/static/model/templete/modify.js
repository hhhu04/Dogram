const modifyTemp = (navBarTemp) => {
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
            <h1 class="modify_title">회원정보수정</h1>
            <div class="header_section">
              <form class="form-horizontal" name="updateForm" method="post" action="#" enctype="multipart/form-data">
                <!-- <div class="form-group">
                    <label for="id" class="cols-sm-2 control-label">아이디</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            
                            <input type="text" class="form-control" name="id" id="id"  placeholder="Enter your id"/>
                        </div>
                    </div>
                </div> -->
                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">비밀번호</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <!-- <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span> -->
                            <input type="password" class="form-control" name="password" id="password"  placeholder="비밀번호를 입력하세요"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">펫 네임(화면에표시되는이름입니다)</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <!-- <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span> -->
                            <input type="text" class="form-control" name="name" id="name"  placeholder="펫네임을 입력하세요"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">이메일</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <!-- <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span> -->
                            <input type="text" class="form-control" name="email" id="email"  placeholder="이메일을 입력하세요"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="username" class="cols-sm-2 control-label">지역선택</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <!-- <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span> -->
                            <input type="text" class="form-control" name="username" id="username"  placeholder="Enter your Username"/>
                        </div>
                    </div>
                </div>
                <!-- 승인키: U01TX0FVVEgyMDIxMDQxOTEyMjEyNzExMTA2NjQ= -->
                <!-- url: http://192.168.1.37:5500/routes/register.html -->
                <div class="form-group">
                    <label for="file" class="cols-sm-2 control-label">사진업로드</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <!-- <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span> -->
                            <input type="file" name="file" id="imageFileOpenInput" accept="image/*">
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="submit" id="updateBtn" class="btn btn-primary btn-lg btn-block login-button">수정하기</button>
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

export default modifyTemp;
