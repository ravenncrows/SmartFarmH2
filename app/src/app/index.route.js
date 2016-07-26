(function() {
  'use strict';

  angular
    .module('app')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/listProduct', {
        templateUrl: 'app/product/productList.html',
        controller: 'listProductController',
        controllerAs: 'vm'
      })
      .when('/listProductStock',{
        templateUrl: 'app/productStock/productStockList.html',
        controller: 'listProductStockController',
        controllerAs: 'vm'
      })
      .when('/editProductStock/:id',{
        templateUrl: 'app/product/productStockEdit.html',
        controller: 'editProductStockController',
        controllerAs: 'vm'
      })
      .when('/monitor/environ', {
        templateUrl: 'app/environ/environMonitor.html',
        controller: 'monitorEnvironController',
        controllerAs: 'vm'
      })
      .otherwise({
        redirectTo: '/listProduct'
      });
  }

})();
