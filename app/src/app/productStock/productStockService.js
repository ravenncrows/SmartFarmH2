/**
 * Created by Asus on 19/7/2559.
 */
(function() {
  'use strict'
  angular
    .module('app')
    .factory('productStockService',productStockService);

  /** @ngInject */
  function productStockService($resource){
    return $resource('/productStock/:id', { id: '@_id' }, {
      update: {
        method: 'PUT' // this method issues a PUT request
      }});

  }
})();
