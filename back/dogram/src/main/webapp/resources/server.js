// Server
const express = require("express");
const app = express();
const fs = require("fs");
const path = require("path");

app.use(express.static(path.join(__dirname, "css")));
// app.get("/", (req, res) => {
//   res.format({
//     // 새로고침에 의한 브라우저 요청
//     "text/html": function () {
//       //   res.sendFile(path.join(__dirname + "/public/data/service.html"));
//       res.sendFile(path.join(__dirname, "html", "intro.html"));
//     },
//     // AJAX 요청
//     "application/json": function () {
//       res.send(
//         JSON.parse(fs.readFileSync("./public/data/service.json", "utf8"))
//       );
//     },
//     default: function () {
//       // log the request and respond with 406
//       res.status(406).send("Not Acceptable");
//     },
//   });
// });
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "intro.html"));
});

app.listen(3000, function () {
  console.log("listening on http//localhost:3000");
});
