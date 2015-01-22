'use strict';

/* Directives */


var myAppDirectives = angular.module('myAppDirectives', []);


myAppDirectives.directive('appVersion', ['version', function(version) {

    return function(scope, elm, attrs) {
      elm.text(version);
    };
}]);