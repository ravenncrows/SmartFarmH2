/**
 * Created by Asus on 19/8/2559.
 */
(function () {
  'use strict'
  angular.module('app').controller('registerController',registerController)
    .controller('accountRequestController',accountRequestController);
  /**ngInject*/
  function registerController(userService,$location) {
    var vm = this;

    var password = document.getElementById("password")
      , confirm_password = document.getElementById("confirm-password");

    function validatePassword(){
      if(password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
      } else {
        confirm_password.setCustomValidity('');
      }
    }

    vm.register = function (user) {
      userService.save(user);
    }.then(function(){
      $location.path('/login');
    })
  }

  function accountRequestController(queryAccountRequestService) {
    var vm = this;
    vm.newUsers=queryAccountRequestService.query();
  }

})();
