angular.module('TasksApp', [
  'TasksApp.services',
  'TasksApp.controllers',
  'ngRoute'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.
	when("/tasks", {templateUrl: "partials/tasks.html", controller: "tasksController"}).
  when("/task", {templateUrl: "partials/task-create.html", controller: "taskController"}).
  when("/task/:id", {templateUrl: "partials/task.html", controller: "taskController"}).
	otherwise({redirectTo: '/tasks'});
}]);
