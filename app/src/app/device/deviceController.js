(function () {
  'use strict';

  angular
    .module('app')
    .controller('listDeviceController', listDeviceController)
    .controller('modalDeviceSettingInstanceController', modalDeviceSettingInstanceController);


  /** @ngInject */
  function listDeviceController(deviceService, deviceSettingService, $log, devices, $timeout, $uibModal) {
    var vm = this;
    vm.device = {};
    vm.devices = devices;
    vm.newDevice = {};
    vm.waterControllerDevice = devices[0]; // fix to devices[0]
    //fix water controller device by name using the following line and replace __name_of_device__ with device name 
      //devices.filter(function(device){device.name == '__name_of_device__'}).pop();

    vm.loadDevices = function() {
      deviceService
        .query(
          function(loadedDevices) {
            $timeout(function(){
              vm.devices = loadedDevices;
              vm.waterControllerDevice = loadedDevices[0] || {};
              $log.debug(vm.waterControllerDevice);
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

    vm.openSettingModal = function(device){
      var modalInstance = $uibModal.open({
        animation: true,
        templateUrl: 'app/device/deviceSetting.html',
        controller: 'modalDeviceSettingInstanceController',
        controllerAs: 'vm',
        size:'m',
        resolve: {
          device: function () {
            return device;
          },
          deviceSetting: function () {
            return deviceSettingService.get({id: device.id})
          }
        }
      });
      modalInstance.result.then(function () {
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    }
  }

  function modalDeviceSettingInstanceController($uibModalInstance, deviceSettingService, device, deviceSetting, $timeout) {
    var vm = this;
    vm.deviceSetting = deviceSetting;
    vm.device = device;
    vm.ok = function () {
      $uibModalInstance.close();
    };

    vm.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };

    vm.deleteDeviceSetting = function (id) {
      var answer = confirm("Do you want to delete the setting of this device?");
      if (answer) {
        deviceSettingService.delete({id: id}, function () {
          $timeout(function(){
            vm.deviceSetting = undefined;
          },0);
        })
      }
    };

    vm.updateDeviceSetting = function (id) {
      deviceSettingService.update( { id: id }, vm.deviceSetting, function(newDeviceSetting){
        $timeout(function(){
          vm.deviceSetting = newDeviceSetting;
        },0);
      });
    };

    vm.addDeviceSetting = function () {
      vm.deviceSetting.device = device;
      deviceSettingService.save(vm.deviceSetting, function(newDeviceSetting){
        $timeout(function(){
          vm.deviceSetting = newDeviceSetting;
        },0);
      });
    };
  }
})();
