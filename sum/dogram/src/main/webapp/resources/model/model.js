class Model {
  constructor() {
    this.observers = [];
  }

  registerObserver = function (observer) {
    this.observers.push(observer);
  };

  notifyAll = function () {
    this.observers.forEach(function (observer) {
      observer.update(self);
    });
  };
}
export default Model;
