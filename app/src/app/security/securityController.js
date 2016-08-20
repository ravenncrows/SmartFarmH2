/**
 * Created by Asus on 19/8/2559.
 */
/**
 * Created by Asus on 7/8/2559.
 */
(function () {

  angular.module('app')
    .controller('loginController',loginController );

  function serializeData(data) {
    //if this not an object
    if(!angular.isObject(data)){
      return((data == null)?"": data.toString());
    }
    var buffer = [];
    //Serializ each key in the obj
    for (var name in data){
      if(!data.hasOwnProperty(name)){
        continue;
      }
      var value =  data[name];
      buffer.push(
        encodeURIComponent(name)+"="+encodeURIComponent((value == null)?"": value)
      );
    }
    //
    var source = buffer.join("&").replace(/%20/g,"+");
    return(source);
  }
  /**ngInject*/
  function loginController($rootScope,$location,$cookies,UserService)
  {

    var vm = this;
    vm.rememberMe = false;
    vm.loginFailAlert =false;
    vm.login = function () {

      UserService.authenticate(serializeData({username:vm.username,password:vm.password}),
        function (authenticationResult) {
          var authToken = authenticationResult.token;
          $rootScope.authToken = authToken;
          if (vm.rememberMe) {
            $cookies.put('authToken', authToken, {expires:moment().add(5,'days').toString()});
          }
          UserService.get(function (user) {
            $rootScope.user = user;
            $location.path("/listProduct")
          });
          //delete $rootScope.error;
        },
        function(){
            vm.loginFailAlert= true;
            vm.loginMessage =" user name or password is not correct";

        })
    }
  }


})();
