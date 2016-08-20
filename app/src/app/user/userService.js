/**
 * Created by Asus on 19/8/2559.
 */
(function() {
  'use strict'
  angular
    .module('app')
    .factory('userService',userService)
    .factory('queryUserService',queryUserService)
    .factory('queryAccountRequestService',queryAccountRequestService);

  /** @ngInject */
  function userService($resource){
    return $resource('/user/:id', { id: '@_id' }, {
      update: {
        method: 'PUT' // this method issues a PUT request
      }});

  }
  /** @ngInject */
  function queryUserService($resource){
    return $resource('/getUser/?name=:name',
      {get:{method:'GET',params:{name:''}}
      });
  }
  /** @ngInject */
  function queryAccountRequestService($resource){
    return $resource('/getNewUser',
      {query:{method:'GET',isArray:true}
      });
  }
})();
