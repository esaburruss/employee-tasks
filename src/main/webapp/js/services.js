angular.module('TasksApp.services', [])
  .factory('apiService', function($http) {

    var api= {};

    api.getTasks = function() {
      return $http({
        method: 'GET',
        url: '/v1/tasks'
      });
    }

    api.createTask = function(task) {
      return $http({
        method: 'POST',
        url: '/v1/tasks',
        data: task
      });
    }

    /*ergastAPI.getTaskDetails = function(id) {
      return $http({
        method: 'JSONP',
        url: 'http://ergast.com/api/f1/2013/tasks/'+ id +'/taskStandings.json?callback=JSON_CALLBACK'
      });
    }

    ergastAPI.getTaskRaces = function(id) {
      return $http({
        method: 'JSONP',
        url: 'http://ergast.com/api/f1/2013/tasks/'+ id +'/results.json?callback=JSON_CALLBACK'
      });
    }*/

    return api;
  });
