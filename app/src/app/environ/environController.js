(function () {
  'use strict';

  angular
    .module('app')
    .controller('monitorEnvironController', monitorEnvironController);


  /** @ngInject */
  function monitorEnvironController($stomp, $log, backendUrl, $timeout, environService, environStat) {
    var vm = this;
    vm.environs = [];
    vm.environSubscription = null;
    vm.environStatSubscription = null;
    vm.statisticsDate = moment().format('YYYY-MM-DD');
    vm.statisticsDateLimit = moment().toString();
    vm.statisticsHourList = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
    vm.statisticsHour = -1;
    vm.statistics = null;
    vm.environStat = null;
    $stomp.setDebug(function (args) {
      $log.debug(args)
    });
    vm.getStatistics = getStatistics;

    $stomp
      .connect(backendUrl)
      .then(function () {
        vm.subscription = $stomp.subscribe('/environ/monitor', handleEnvironMessage);
        vm.environStatSubscription = $stomp.subscribe('/environStat/today/hour/latest', handleEnvironStatMessage)
      })
      .catch(function(err) {
        $log.error(err);
      });

    function handleEnvironMessage (payload, headers, res) {
      if(deviceExistInEnvirons(payload.device.id)){
        updateEnviron(payload)
      }
      else{
        $timeout(function(){
          vm.environs.push(payload);
        },0);
      }
    }

    function deviceExistInEnvirons(deviceId){
      return vm.environs.some(function(environ){
        return deviceId == environ.device.id;
      });
    }

    function updateEnviron(payload) {
      $timeout(function(){
        vm.environs = vm.environs.map(function(environ){
          return environ.device.id == payload.device.id ? payload : environ;
        });
      },0);
    }

    function handleEnvironStatMessage (payload, headers, res) {
        $timeout(function(){
          vm.environStat = payload;
        },0);
    }

    function getStatistics() {
      var year = vm.statisticsDate.split("-")[0];
      var month = vm.statisticsDate.split("-")[1];
      var day = vm.statisticsDate.split("-")[2];
      var hour = vm.statisticsHour;

      if(hour == -1 || hour == ''){
        environService
          .getStatisticsByDay(year, month, day)
          .then(function(response){
            $timeout(function(){
              vm.statistics = response;
            },0);
          })
          .catch(function(err){
            $log.error(err);
          });
      }
      else {
        environService
          .getStatisticsByHour(year, month, day, hour)
          .then(function(response){
            $timeout(function(){
              vm.statistics = response;
            },0);
          })
          .catch(function(err){
            $log.error(err);
          });
      }
    }
  }
})();
