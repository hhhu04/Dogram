import Controller from "/controller/controller.js";
import navBarTemp from "/model/templete/navbar.js";
import mypageTemp from "/model/templete/mypage.js";
import mypostmenuTemp from "/model/templete/mypostmenu.js";
import mypostlistTemp from "/model/templete/mypage.postlist.js";

class MypageController extends Controller {
  constructor(service, router) {
    super(service, router);
    this.router.addRoute(
      "mypage",
      "#/mypage",
      mypageTemp(
        navBarTemp("logoutbtn", this.service.userinfo),
        mypostmenuTemp(mypostlistTemp())
      )
    );
    this.router.hashChange();
    this.didRenderMount();
    this.router.view.MypageView.bindLinkModify(this.linkModify);
    this.router.view.MypageView.bindDeleteUser(this.deleteUser);

    this.paging();
  }
  linkModify = (e) => {
    e.preventDefault();
    window.location.hash = "#/mypage/modify";
  };

  deleteUser = async (e) => {
    e.preventDefault();
    // window.alert("탈퇴가 정상적으로 이루어졌습니다");
    // window.location.hash = "#/";

    const a = await this.service.deleteUser();
    console.log(a);
  };

  paging = async () => {
    this.myfeed = await this.service.getMyFeedList();
    console.log(this.myfeed);
    const list = [
      { num: 1, content: "가" },
      { num: 2, content: "a" },
      { num: 3, content: "B" },
      { num: 4, content: "DD" },
      { num: 5, content: "CC" },
      { num: 6, content: "AWE" },
      { num: 7, content: "AWE" },
      { num: 8, content: "AWE" },
      { num: 9, content: "AWE" },
      { num: 10, content: "AWE" },
      { num: 11, content: "AWE" },
    ];
    const pageCount = 5;
    const blockCount = 5;
    const totalPage = Math.ceil(list.length / pageCount);
    const totalBlock = Math.ceil(totalPage / blockCount);
    const pagination = document.getElementById("pages");
    var testTable = document.getElementById("testBody");
    var renderTableAndPagination = function (page = 1) {
      renderTable(page);
      renderPagination(page);
    };
    var renderTable = function (page) {
      var startNum = pageCount * (page - 1);
      var endNum =
        pageCount * page >= list.length ? list.length : pageCount * page;

      var html = "";
      for (var index = startNum; index < endNum; index++) {
        html +=
          '<tr><td><input type="checkbox"></td><td>' +
          list[index].num +
          "</td><td>" +
          list[index].content +
          "</td></tr>";
      }

      testTable.innerHTML = html;
    };
    var renderPagination = function (page) {
      var block = Math.floor((page - 1) / blockCount) + 1;
      var startPage = (block - 1) * blockCount + 1;
      var endPage =
        startPage + blockCount - 1 > totalPage
          ? totalPage
          : startPage + blockCount - 1;

      var paginationHTML = "";

      if (page !== 1)
        paginationHTML +=
          "<a style='cursor:pointer' class='first_page'>First...</a>";
      if (block !== 1)
        paginationHTML +=
          "<a style='cursor:pointer' class='back_page'>Prev</a>   ";

      for (var index = startPage; index <= endPage; index++) {
        paginationHTML +=
          parseInt(page) === parseInt(index)
            ? "| <a style='color:#ff8400'>" + index + "</a> |"
            : "| <a style='cursor: pointer' class='go_page' data-value='" +
              index +
              "'>" +
              index +
              "</a> |";
      }

      if (block < totalBlock)
        paginationHTML +=
          "<a style='cursor:pointer' class='next_page'>    Next</a>";
      if (page < totalPage)
        paginationHTML +=
          "<a style='cursor:pointer' class='last_page'>  ...Last</a>";

      pagination.innerHTML = paginationHTML;
      addEventPagination(startPage, endPage);
    };
    var addEventPagination = function (startPage, endPage) {
      if (!!document.querySelector(".first_page")) {
        document.querySelector(".first_page").addEventListener("click", () => {
          renderTableAndPagination(1);
        });
      }

      if (!!document.querySelector(".back_page")) {
        document.querySelector(".back_page").addEventListener("click", () => {
          renderTableAndPagination(startPage - 1);
        });
      }

      document.querySelectorAll(".go_page").forEach((goPage) => {
        goPage.addEventListener("click", (e) => {
          renderTableAndPagination(
            parseInt(e.target.getAttribute("data-value"))
          );
        });
      });

      if (!!document.querySelector(".next_page")) {
        document.querySelector(".next_page").addEventListener("click", () => {
          renderTableAndPagination(endPage + 1);
        });
      }

      if (!!document.querySelector(".last_page")) {
        document.querySelector(".last_page").addEventListener("click", () => {
          renderTableAndPagination(totalPage);
        });
      }
    };
    renderTableAndPagination();
  };
}

export default MypageController;
