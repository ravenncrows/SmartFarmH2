(function() {
  'use strict';
  angular
    .module('app')
    .factory('deviceService', deviceService);

  /** @ngInject */
  function deviceService($resource){
    return $resource('/device/:id', { id: '@_id' }, {
      update: {
        method: 'PUT' // this method issues a PUT request
      }});

  }
})();
