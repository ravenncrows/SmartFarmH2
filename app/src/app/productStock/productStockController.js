/**
 * Created by Asus on 19/7/2559.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .controller('listProductStockController', listProductStockController);
  /** @ngInject */
  function listProductStockController(productStockService,$route , productService) {

    var vm = this;
    vm.productStock = {};
    vm.newProductStock = {};
    vm.products = productService.query();

    productStockService.query(function (data) {
      vm.productStocks = data;

      })

    vm.deleteProductStock = function (id) {
        var answer = confirm("Do you want to delete the productStock?");
        if (answer) {
          productStockService.delete({id: id}, function () {
            $route.reload();
          })
        }
      }
    vm.updateProductStock = function (id) {

      productStockService.update({id:id},vm.productStock);
        $route.reload();
      }
    vm.addProductStock = function () {
        productStockService.save(vm.newProductStock);
        $route.reload();
      }
    }


})();
