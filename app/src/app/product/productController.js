(function () {
  'use strict';

  angular
    .module('app')
    .controller('listProductController', listProductController);


  /** @ngInject */
  function listProductController(productService,$route) {
    var vm = this;
    vm.product = {};
    vm.newProduct = {};
    productService.query(function (data) {
      vm.products = data;
    })

    vm.deleteProduct = function (id) {
      var answer = confirm("Do you want to delete the product?");
      if (answer) {
        productService.delete({id: id}, function () {
          $route.reload();
        })
      }
    }
    vm.updateProduct = function (id) {
      vm.product.id = id;
      productService.update({id:id},vm.product);
      $route.reload();
    }
    vm.addProduct = function () {
      productService.save(vm.newProduct);
      $route.reload();
    }
  }
})();
/**
 * Created by Asus on 19/7/2559.
 */
