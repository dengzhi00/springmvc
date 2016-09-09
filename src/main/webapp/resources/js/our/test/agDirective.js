 var app=angular.module("testApp", []);
 app.directive('hello',function(testService,$http){
		
	 return{		
			restrict : 'EA',
			//templateUrl :"loginTemplate.html" ,	
			templateUrl:"http://localhost:8088/springmvc/directive",
			replace : true,
			scope : true,
			link : function(scope, element, attrs) {
				scope.getUser=testService.getUser("{}",function(data){
					console.log(data);
				});
				scope.getUser2=function(){

					console.log("angular");
					loginPath = "http://localhost:8088/springmvc/agDirective";
					$http.post(loginPath, JSON.stringify("{}")).success(function(data) {
						console.log(data); // 成功的回调函数 // 存储数据
						service = data;
						//callback(data);
					});
				
		 		}
			}
	 		
	 };
 });
 app.factory('testService', function($location, $http) {
		var path = $location.protocol() + "://" + $location.host() + ":"+ $location.port() + "/";
		var service = {};
		service.getUser=function(json,callback){
			alert(path);
			loginPath = "http://localhost:8088/springmvc/angular";
			$http.post(loginPath, JSON.stringify(json)).success(function(data) {
				console.log(data); // 成功的回调函数 // 存储数据
				service = data;
				callback(data);
			});
		}
		
		return service;
	});