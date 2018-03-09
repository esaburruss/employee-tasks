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

    api.deleteTask = function(id) {
      return $http({
        method: 'DELETE',
        url: '/v1/tasks/' + id
      });
    }

    api.completeTask = function(task) {
      return $http({
        method: 'PUT',
        url: '/v1/tasks/' + task.id,
        data: task
      });
    }

    return api;
  });
