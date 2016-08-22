/**
 * Created by Asus on 22/8/2559.
 */
(function() {
  'use strict';
  angular
    .module('app')
    .factory('deviceSettingService', deviceSettingService);

  /** @ngInject */
  function deviceSettingService($resource){
    return $resource('/deviceSetting/:id', { id: '@_id' }, {
      update: {
        method: 'PUT' // this method issues a PUT request
      }});

  }
})();
