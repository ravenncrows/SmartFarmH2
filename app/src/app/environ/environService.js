(function() {
  'use strict';
  angular
    .module('app')
    .factory('environService',environService);

  /** @ngInject */
  function environService($http, $q, $log, backendUrl){
    return {
      getStatisticsByDay : function(year, month, day) {
        var path = backendUrl + '/environStat/date/'+ year +'/' + month + '/' + day;
        var deferred = $q.defer();
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
      getStatisticsByHour : function(year, month, day , hour) {
        var path = backendUrl + '/environStat/date/'+ year +'/' + month + '/' + day + '/' + hour;
        var deferred = $q.defer();
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
      getStatisticsOfCurrentHour : function() {
        var path = backendUrl + '/environStat/today/hour/latest';
        var deferred = $q.defer();
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
