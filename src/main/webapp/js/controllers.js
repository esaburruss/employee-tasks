angular.module('TasksApp.controllers', []).

  /* Tasks controller */
  controller('tasksController', function($scope, apiService) {

    $scope.tasksList = [];


    $scope.load = function() {
      apiService.getTasks().then(function (response) {
        $scope.tasksList = response.data;
      }).catch(function(err) {
        console.log(err);
      });
    }

    $scope.complete = function(id) {
      var task = {
        id: id,
        completedDate: new Date(),
        completed: true
      }

      apiService.completeTask(task).then(function (response) {
        $scope.load();
      }).catch(function(err) {
        console.log(err);
      });
    };

    $scope.delete = function(id) {

      apiService.deleteTask(id).then(function (response) {
        $scope.load();
      }).catch(function(err) {
        console.log(err);
      });
    };

    $scope.format = function (date) {
      if(date) {
        return moment(date).format('MM-DD-YYYY hh:mm');
      } else {
        return 'Unfinished';
      }
    }

    $scope.load();
  }).

  /* Task controller */
  controller('taskController', function($scope, $routeParams, apiService) {

    $scope.task = null;
    $scope.master = {};

    $scope.update = function(task) {
      $scope.master = angular.copy(task);
      apiService.createTask(task).then(function (response) {
        $scope.reset();
      }).catch(function(err) {
        //console.log(err);
      });
    };

    $scope.reset = function() {
      $scope.user = angular.copy($scope.master);
    };

    $scope.reset();
  });
