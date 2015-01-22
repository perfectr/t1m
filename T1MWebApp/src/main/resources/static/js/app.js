'use strict';

// IE workaround, create fake console if one doesn't already exist
window.console = window.console || {};
window.console.log = window.console.log || function() {};


// Declare app level module which depends on filters, and services
var myApp = angular.module('myApp', [
  'ngRoute',
  'ui.bootstrap',
  'myAppFilters',
  'myAppServices',
  'myAppDirectives',
  'myAppControllers'
]);

myApp.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {

    //http://www.oodlestechnologies.com/blogs/AngularJS-caching-issue-for-Internet-Explorer
    $httpProvider.defaults.cache = false;
    if (!$httpProvider.defaults.headers.get) {
      $httpProvider.defaults.headers.get = {};
    }
    // disable IE ajax request caching
    $httpProvider.defaults.headers.get['If-Modified-Since'] = '0';


  $routeProvider.when('/landing', {templateUrl: '/partials/landing.html',   controller: 'LandingCtrl'});
  $routeProvider.when('/contact', {templateUrl: '/partials/ContactUs.html', controller: 'ContactCtrl'});
  $routeProvider.when('/person/search',         {templateUrl: '/partials/PersonSearch.html',  controller: 'PersonSearchCtrl'});
  $routeProvider.when('/person/edit/:personId', {templateUrl: '/partials/PersonEdit.html',    controller: 'PersonEditCtrl'});
  $routeProvider.when('/event/search',         {templateUrl: '/partials/EventSearch.html',  controller: 'EventSearchCtrl'});
  $routeProvider.when('/event/edit/:eventId', {templateUrl: '/partials/EventEdit.html',    controller: 'EventEditCtrl'});
  $routeProvider.otherwise({redirectTo: '/landing'});
}]);



