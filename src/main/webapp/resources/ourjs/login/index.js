var app = angular.module("mainApp", []);// 'angularFileUpload','selectAddress','tm.pagination'
app.controller("mainController",
		function($scope, $http, $rootScope, $location) {
			$scope.basePath = $location.protocol() + "://" + $location.host()
					+ ":" + $location.port() + "/";
			$scope.data = "";
		});