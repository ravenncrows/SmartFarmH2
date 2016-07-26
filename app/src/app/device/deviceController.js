(function () {
  'use strict';

  angular
    .module('app')
    .controller('listDeviceController', listDeviceController);


  /** @ngInject */
  function listDeviceController(deviceService, $log, devices, $timeout) {
    var vm = this;
    vm.device = {};
    vm.devices = devices;
    vm.newDevice = {};

    vm.loadDevices = function() {
      deviceService
        .query(
          function(loadedDevices) {
            $timeout(function(){
              vm.devices = loadedDevices;
            },0);
          }, function handleError(err) {
            $log.error(err);
          }
        );
    };

    vm.deleteDevice = function (id) {
      var answer = confirm("Do you want to delete the device?");
      if (answer) {
        deviceService.delete({id: id}, function () {
          vm.loadDevices();
        })
      }
    };

    vm.updateDevice = function (id) {
      vm.device.id = id;
      deviceService.update( { id: id }, vm.device, function(){
        vm.loadDevices();
      });

    };

    vm.addDevice = function () {
      deviceService.save(vm.newDevice, function(){
        vm.loadDevices();
      });
    };

  }
})();
