(function() {
  'use strict';
  /**
   * ngStomp
   *
   * @version 0.2.0
   * @author Maik Hummel <m@ikhummel.com>
   * @license MIT
   */

  /*global
   angular, SockJS, Stomp */

  angular
    .module('app')
    .service('$stomp', ngStomp);

  /** @ngInject */
  function ngStomp($rootScope, $q) {
    this.sock = null;
    this.stomp = null;
    this.debug = null;

    this.setDebug = function (callback) {
      this.debug = callback
    };

    this.connect = function (endpoint, headers) {
      headers = headers || {};

      var dfd = $q.defer();

      this.sock = new SockJS(endpoint);
      this.stomp = Stomp.over(this.sock);
      this.stomp.debug = this.debug;
      this.stomp.connect(headers, function (frame) {
        dfd.resolve(frame);
      }, function (err) {
        dfd.reject(err)
      });

      return dfd.promise
    };

    this.disconnect = function () {
      var dfd = $q.defer();
      this.stomp.disconnect(dfd.resolve);
      return dfd.promise
    };

    this.subscribe = this.on = function (destination, callback, headers) {
      headers = headers || {};
      return this.stomp.subscribe(destination, function (res) {
        var payload = null;
        try {
          payload = angular.fromJson(res.body);
        } finally {
          if (callback) {
            callback(payload, res.headers, res)
          }
        }
      }, headers)
    };

    this.unsubscribe = this.off = function (subscription) {
      subscription.unsubscribe()
    };

    this.send = function (destination, body, headers) {
      var dfd = $q.defer();
      try {
        var payloadJson = angular.toJson(body);
        headers = headers || {};
        this.stomp.send(destination, headers, payloadJson);
        dfd.resolve()
      } catch (e) {
        dfd.reject(e)
      }
      return dfd.promise
    }
  }
})();
