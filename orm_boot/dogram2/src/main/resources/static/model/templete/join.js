const joinTemp = (navBarTemp) => {
  return `<div class="site-wrapper">
<div class="site-wrapper-inner">
  <div class="cover-container">
    <div class="masthead clearfix">
      <div class="inner clearfix">
        <h3 class="masthead-brand"><a href="../intro.html" class="main-title">DOGRAM</a></h3>

        <span class="gnb_btn"><i class="fas fa-bars"></i></span>
      </div>
    </div>
    ${navBarTemp} 
    <div class="inner cover">
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <h1 class="login_title">회원가입</h1>
                <!-- Login Form -->
                <form class="form-horizontal" accept-charset="UTF-8" id="joinForm" onsubmit="(e) => {e.preventDefault()}" method="post" action="#" name="joinForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="id" class="cols-sm-2 control-label">아이디</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <!-- <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span> -->
                                <input type="text" class="form-control" name="id" id="id"  placeholder="Enter your id"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="cols-sm-2 control-label">Password</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <!-- <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span> -->
                                <input type="password" class="form-control" name="password" id="password"  placeholder="Enter your Password"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="cols-sm-2 control-label">반려동물이름(화면에표시되는이름입니다)</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <!-- <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span> -->
                                <input type="text" class="form-control" name="name" id="name"  placeholder="Enter your Name"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="cols-sm-2 control-label">이메일</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <!-- <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span> -->
                                <input type="text" class="form-control" name="email" id="email"  placeholder="Enter your Email"/>
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
                        <button type="submit" onclick="(e) => {e.preventDefault()}" id="js_joinBtn" class="btn btn-primary btn-lg btn-block login-button">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
  </div>
</div>
</div>`;
};
export default joinTemp;
