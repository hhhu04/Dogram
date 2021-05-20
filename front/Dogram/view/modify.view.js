class ModifyView {
  constructor() {}
  modifyConstructor() {
    this.updateBtn = document.querySelector("#updateBtn");
  }
  bindPostUpdate(userUpdate) {
    console.log(this.updateBtn);
    if (!this.updateBtn) return;
    this.updateBtn.addEventListener("click", userUpdate);
  }
}

export default ModifyView;
