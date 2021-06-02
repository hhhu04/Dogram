(function () {
  const root = document.querySelector(".user_status");
  //   const navigation = document.getElementById("navigation");

  // 라우팅
  const routes = {
    // path: url
    "/": "intro.html",
    "/service": "/data/service.json",
    "/about": "/data/about.html",
    "/login": "/data/status.json",
  };

  // 렌더링
  const render = async (path) => {
    try {
      const url = routes[path];
      console.log(url);
      if (!url) {
        root.innerHTML = `${path} Not Found`;
        return;
      }

      const res = await fetch(url);
      const contentType = res.headers.get("content-type");

      if (contentType?.includes("application/json")) {
        const json = await res.json();
        root.innerHTML = `<h1>${json.title}</h1>`;
        // <p>${json.content}</p>
      } else {
        root.innerHTML = await res.text();
      }
    } catch (err) {
      console.error(err);
    }
  };
  window.addEventListener("popstate", (e) => {
    // e.state는 pushState 메서드의 첫번째 인수
    console.log("[popstate]", e.state);
    // 이전페이지 / 다음페이지 button이 클릭되면 render를 호출
    render(e.state.path);
  });
  navigation.addEventListener("click", (e) => {
    if (!e.target.matches("#navigation > li > a")) return;
    e.preventDefault();
    // 이동 페이지
    const path = e.target.getAttribute("href");
    history.pushState({ path }, null, path);
    // path에 의한 AJAX 요청
    render(path);
  });
  render("/");
})();
