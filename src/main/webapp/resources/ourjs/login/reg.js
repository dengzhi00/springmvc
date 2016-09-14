var app = angular.module("regApp", []);// 'angularFileUpload','selectAddress','tm.pagination'
app.controller("regCtl", function($scope, $location, checkService, regService) {

	var regState = false;
	$scope.rest=function (){
		$scope.userName="";
		$scope.nickName="";
		$scope.pwd="";
		$scope.pwdConfirm="";
	}
	$scope.checkUserName = function() {
		var json = {
			"userName" : $scope.userName,
			"nickName" : $scope.nickName,
			"pwd" : $scope.pwd,
			"method" : "check"
		};
		checkService.check(json, function(data) {
			if (data.result != null) {
				if (data.result == "0") {
					regState = true;
					$scope.errInfo = data.msg;
				} else if (data.result == "1") {
					regState = false;
					$scope.errInfo = data.msg;
				} else if (data.result == "-1") {
					regState = false;
					alert("服务器异常!" + data.msg);
				}
			} else {
				alert("服务器正忙!");
			}
		})
	}
	$scope.register = function() {
		if (regState == false || $scope.userName == null
				|| $scope.nickName == null || $scope.pwd == null
				|| $scope.userName == ""
				|| $scope.nickName == "" || $scope.pwd == "") {
			console.log($scope.userName+"_"+$scope.nickName+"_"+$scope.pwd+"_"+$scope.pwdConfirm );
			alert("请输入完整信息!");
			return;
		}
		if($scope.pwdConfirm != $scope.pwd ){
			alert("确认密码有误!");return;
		}
		var json = {
			"userName" : $scope.userName,
			"nickName" : $scope.nickName,
			"pwd" : $scope.pwd,
			"method" : "register"
		};
		regService.reg(json, function(data) {
			if (data.result != null) {
				if (data.result == "0") {
					regState = true;
					window.location.href = "/demo/html/login/login.html";
				} else if (data.result == "1") {
					regState = false;
					$scope.info = data.msg;
				} else if (data.result == "-1") {
					regState = false;
					alert("服务器异常!" + data.msg);
				}
			} else {
				alert("服务器正忙!");
			}
		})
	}
});
app.factory('checkService', function($location, $http) {

	var path = $location.protocol() + "://" + $location.host() + ":"
			+ $location.port() + "/";
	var service = {};
	path = path + "demo/service/register";
	service.check = function(json, callback) {
		$http.post(path, JSON.stringify(json)).success(function(data) {
			// 成功的回调函数 // 存储数据
			service = data;
			callback(data);
		});
	};
	return service;
});
app.factory('regService', function($location, $http) {

	var path = $location.protocol() + "://" + $location.host() + ":"
			+ $location.port() + "/";
	var service = {};
	path = path + "demo/service/register";
	service.reg = function(json, callback) {
		$http.post(path, JSON.stringify(json)).success(function(data) {
			// 成功的回调函数 // 存储数据
			service = data;
			callback(data);
		});
	};
	return service;
});
/*
 * $scope.checkUserName=function(){ var json={"userName" :
 * $scope.userName,"method" : "check"}; var checkeDate =
 * checkService.check(json, function(data) { if (data.result == "0") {
 * $scope.info = $scope.userName + "该用户可用!"; } else if (data.result == "1") {
 * $scope.info = $scope.userName + "该用户已被注册!"; } else { alert("服务器正忙!") } }); }
 */
/*
 * $scope.reg=function(){ var
 * json={"userName":$scope.userName,"pwd":$scope.pwd,"nickName":$scope.nickName,"method" :
 * "reg"}; }
 */

/*
 * app.service('checkService',['$http','$q',function($http,$q,$location){ return {
 * getData:function($location,$http){ var path = $location.protocol() + "://" +
 * $location.host() + ":"+ $location.port() + "/"; path = path +
 * "demo/service/reg/reg"; var deferred = $q.defer(); var promise =
 * $http.post(path, JSON.stringify(json)); promise.then( // 通讯成功的处理
 * function(answer){ //在这里可以对返回的数据集做一定的处理,再交由controller进行处理 answer.status =
 * true; deferred.resolve(answer); }, // 通讯失败的处理 function(error){ //
 * 可以先对失败的数据集做处理，再交由controller进行处理 error.status = false; deferred.reject(error);
 * }); //返回promise对象，交由controller继续处理成功、失败的业务回调 return deferred.promise; } }
 * }]);
 */
/*
 * app.module().factory('regService', function($location,$http) { var path =
 * $location.protocol() + "://" + $location.host() + ":" + $location.port() +
 * "/"; var service={} ; path = path + "demo/service/reg/reg"; service.reg =
 * function(json,callback) { $http.post(path,
 * JSON.stringify(json)).success(function(data) { console.log(data); // 成功的回调函数 //
 * 存储数据 service = data; callback(data); }); }; return service; });
 *  })
 */