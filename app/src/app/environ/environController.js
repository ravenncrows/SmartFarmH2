(function () {
  'use strict';

  angular
    .module('app')
    .controller('monitorEnvironController', monitorEnvironController);


  /** @ngInject */
  function monitorEnvironController($stomp, $log, backendUrl, $timeout, environService) {
    var vm = this;
    vm.environ = null;
    vm.subscription = null;
    vm.statisticsDate = moment().format('YYYY-MM-DD');
    vm.statisticsDateLimit = moment().toString();
    vm.statisticsHourList = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
    vm.statisticsHour = -1;
    $stomp.setDebug(function (args) {
      $log.debug(args)
    });
    vm.getStatistics = getStatistics;

    $stomp
      .connect(backendUrl)
      // frame = CONNECTED headers
      .then(function (frame) {
        vm.subscription = $stomp.subscribe('/environ/monitor', handleMessage)
      })
      .catch(function(err) {
        $log.error(err);
      });

    function handleMessage (payload, headers, res) {
      $timeout(function(){
        vm.environ = payload;
      },0);
    }
    function getStatistics() {
      
    }
  }
})();
