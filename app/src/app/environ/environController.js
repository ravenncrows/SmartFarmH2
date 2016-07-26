(function () {
  'use strict';

  angular
    .module('app')
    .controller('monitorEnvironController', monitorEnvironController);


  /** @ngInject */
  function monitorEnvironController($stomp, $log, backendUrl, $timeout) {
    var vm = this;
    vm.environ = null;
    vm.subscription = null;
    vm.statisticsDate = moment().format('YYYY-MM-DD');
    vm.statisticsDateLimit = moment().toString();

    $stomp.setDebug(function (args) {
      $log.debug(args)
    });

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

  }
})();
