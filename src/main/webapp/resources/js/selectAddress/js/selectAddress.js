angular.module('selectAddress', []).directive('selectAddress', function($http, $q, $compile) {
	  
    var cityURL, delay, templateURL;
    delay = $q.defer();
    templateURL = '../resources/js/selectAddress/template/index.html';
    cityURL = '../resources/js/selectAddress/template/city';
    $http.post(cityURL).success(function(data) {
      return delay.resolve(data.rootArea.childrenList);
    });
    return {
      restrict: 'A',
      scope: {
        p: '=',
        a: '=',
        c: '=',
        area: '=',
        ngModel: '='
      },
      link: function(scope, element, attrs) {
        var popup;
        popup = {
          element: null,
          backdrop: null,
          show: function() {
            return this.element.addClass('active');
          },
          hide: function() {
            this.element.removeClass('active');
            return false;
          },
          resize: function() {
            if (!this.element) {
              return;
            }
            this.element.css({
              top: -this.element.height() - 30,
              'margin-left': -this.element.width() / 2
            });
            return false;
          },
          init: function() {
            element.on('click keydown', function(event) {
              popup.show();
              event.stopPropagation();
              return false;
            });
            this.element.on('click', function(event) {
              return event.stopPropagation();
            });
            return setTimeout((function(_this) {
              return function() {
                _this.element.show();
                return _this.resize();
              };
            })(this), 500);
          }
        };
        return delay.promise.then(function(data) {
          $http.get(templateURL).success(function(template) {
            var $template;
            $template = $compile(template)(scope);
            $('body').append($template);
            popup.element = $($template[2]);
            scope.provinces = data;
            return popup.init();
          });
          scope.aSet = {
            p: function(p, areaId) {
              scope.p = p;
              scope.area = areaId;
              scope.c = null;
              return scope.a = null;
            },
            c: function(c, areaId) {
              scope.c = c;
              scope.area = areaId;
              return scope.a = null;
            },
            a: function(a, areaId) {
              scope.a = a;
              return scope.area = areaId;
            }
          };
          scope.clear = function() {
            scope.p = null;
            scope.c = null;
            scope.a = null;
            scope.area = null;
            return scope.ngModel = '';
          };
          scope.submit = function() {
            return popup.hide();
          };
          scope.$watch('p', function(newV) {
            var v, _i, _len, _results;
            if (newV) {
              _results = [];
              for (_i = 0, _len = data.length; _i < _len; _i++) {
                v = data[_i];
                if (v.areaName === newV) {
                  _results.push(scope.cities = v.childrenList);
                }
              }
              return _results;
            } else {
              return scope.cities = [];
            }
          });
          scope.$watch('c', function(newV) {
            var v, _i, _len, _ref, _results;
            if (newV) {
              _ref = scope.cities;
              _results = [];
              for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                v = _ref[_i];
                if (v.areaName === newV) {
                  _results.push(scope.dists = v.childrenList);
                }
              }
              return _results;
            } else {
              return scope.dists = [];
            }
          });
          return scope.$watch(function() {
            scope.ngModel = '';
            if (scope.p) {
              scope.ngModel = scope.p;
            }
            if (scope.c) {
              scope.ngModel += " " + scope.c;
            }
            if (scope.a) {
              scope.ngModel += " " + scope.a;
            }
            return popup.resize();
          });
        });
      }
    };
  });