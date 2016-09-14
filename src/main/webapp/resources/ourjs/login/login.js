var app = angular.module("loginApp", []);// 'angularFileUpload','selectAddress','tm.pagination'
app.controller("loginCtl", function($scope, $http, $rootScope, $location) {
	$scope.basePath = $location.protocol() + "://" + $location.host() + ":"
			+ $location.port() + "/";
	$scope.data = "";
});
app.directive('login', function(loginService) {
	return {
		restrict : 'EA',
		templateUrl : loginService.getLoginTemplateUrl,
		replace : true,
		scope : true,
		link : function(scope, element, attrs) {
			scope.loginState = false;
			scope.register = function() {
				window.location.href = loginService.getRegTemplateUrl;
			}
			scope.clearUserName = function() {
				console(scope.userName);
				scope.userName = "";
			}
			// 校验码
			scope.checkCodeImg = loginService.getCheckCodeUrl;
			scope.changeCheckCode = function() {
				scope.checkCodeImg = loginService.getCheckCodeUrl + "?"
						+ (new Date()).valueOf();
			}

			scope.myKeyup = function(e) {
				var keycode = window.event ? e.keyCode : e.which;
				if (keycode == 13) {
					scope.login();
				}
			};
			scope.usrerName == "";
			scope.pwd == "";
			scope.checkCode == "";
			scope.check = function() {
				if (scope.userName == "" || scope.userName == undefined
						|| scope.pwd == "" || scope.pwd == undefined
						|| scope.checkCode == ""
						|| scope.checkCode == undefined) {
					scope.userNameVerify = true;
					scope.pwdVerify = true;
					scope.checkCodeVerify = true;
					return false;
				} else {
					scope.userNameVerify = false;
					scope.pwdVerify = false;
					scope.checkCodeVerify = false;
					return true;
				}
			}
			scope.login = function() {
				// 校验入参
				var checkResult = scope.check();
				if (checkResult == false) {
					return;
				}
				var param = {
					"pwd" : scope.pwd,
					"userName" : scope.userName,
					"checkCode" : scope.checkCode,
					"method" : "login"
				};
				scope.loginState = true;
				var data = loginService.login(param, function(data) {
					console.log(data);
					scope.loginState = false;
					if (data.result == '0') {
						window.location.href = loginService.getIndexUrl + "?"
								+ "userId=" + data.loginUser.USER_ID;
					} else {
						scope.changeCheckCode();
						alert(data.msg);
						scope.checkCode = "";
						scope.error = data.msg;
					}
				});
			}
		}
	};
});
// 登录服务
app.factory('loginService', function($location, $http) {
	var path = $location.protocol() + "://" + $location.host() + ":"
			+ $location.port() + "/";
	var service = {};

	service.basePath = path + "springmvc";

	// 获取验证码
	service.getCheckCodeUrl = service.basePath + "/login/checkCode";
	// 登陆模板地址
	service.getLoginTemplateUrl = service.basePath + "/login/getLoginTemplate";
	// 注册页面地址
	service.getRegTemplateUrl = service.basePath + "/login/getReg";
	// 主页
	service.getIndexUrl = service.basePath + "/main";
	service.login = function(json, callback) {
		loginPath = service.basePath + "/login/login";
		$http.post(loginPath, JSON.stringify(json)).success(function(data) {
			// 成功的回调函数 // 存储数据
			// service = data;
			callback(data);
		});
	};
	service.loginOut = function(json, callback) {
		loginOutPath = service.basePath + "/login/loginOut";
		$http.post(loginPath, JSON.stringify(json)).success(function(data) {
			// 成功的回调函数 // 存储数据
			// service = data;
			callback(data);
		});
	}

	service.checkCode = function(json, callback) {
		checkPath = service.basePath + "/login/checkCode";
		$http.post(checkPath, JSON.stringify(json)).success(function(data) {
			// service = data;
			callback(data);
		});
	}
	return service;
});

/*
 * $.ajax({ type : "POST", url : path, data : JSON.stringify({ " name" : name,
 * "pwd" : pwd }), async:"false", dataType : "json", success : function(data) { }
 * });
 */

