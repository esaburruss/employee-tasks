angular.module('TasksApp.controllers', []).

  /* Tasks controller */
  controller('tasksController', function($scope, apiService) {
    //$scope.nameFilter = null;
    $scope.tasksList = [];
    /*$scope.searchFilter = function (task) {
        var re = new RegExp($scope.nameFilter, 'i');
        return !$scope.nameFilter || re.test(task.Task.givenName) || re.test(task.Task.familyName);
    };*/

    apiService.getTasks().then(function (response) {
        //Digging into the response to get the relevant data
        $scope.tasksList = response.data;
        console.log(response);
    }).catch(function(err) {
      // handle errors
      console.log(err);
    });
  }).

  /* Task controller */
  controller('taskController', function($scope, $routeParams, apiService) {
    //$scope.id = $routeParams.id;
    $scope.task = null;
    $scope.master = {};

    $scope.update = function(task) {
      $scope.master = angular.copy(task);
      apiService.createTask(task).then(function (response) {
        console.log(response);
      }).catch(function(err) {
        console.log(err);
      });
    };

    $scope.reset = function() {
      $scope.user = angular.copy($scope.master);
    };

    $scope.reset();
    /*apiService.getTaskDetails($scope.id).success(function (response) {
        $scope.task = response.MRData.StandingsTable.StandingsLists[0].TaskStandings[0];
    });

    apiService.getTaskRaces($scope.id).success(function (response) {
        $scope.races = response.MRData.RaceTable.Races;
    });*/
  });
