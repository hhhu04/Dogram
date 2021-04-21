/* Set the width of the side navigation to 250px */
function openNav() {
  const mySidenav = document.getElementById("mySidenav");

  if (mySidenav.style.width == "250px") {
    mySidenav.style.width = "0";
  } else {
    document.getElementById("mySidenav").style.width = "250px";
  }
}

/* Set the width of the side navigation to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
