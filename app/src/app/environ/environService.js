(function() {
  'use strict';
  angular
    .module('app')
    .factory('environService',environService);

  /** @ngInject */
  function environService($http, $q, $log, backendUrl){
    return {
      getStatisticsByDay : function(date) {
        var path = backendUrl + '';
        var deferred = $q.deferred;
        $http
          .get(path)
          .then(function(response) {
            return deferred.resolve(response.data);
          })
          .catch(function(err) {
            return deferred.reject(err);
          });
        return deferred.promise;
      },
      getStatisticsByHour : function(date, time) {
        var path = backendUrl + '';
        var deferred = $q.deferred;
        $http
          .get(path)
          .then(function(response) {
            return deferred.resolve(response.data);
          })
          .catch(function(err) {
            return deferred.reject(err);
          });
        return deferred.promise;
      }
    }
  }
})();
