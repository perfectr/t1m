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

  $routeProvider.when('/landing', {templateUrl: '/partials/landing.html',   controller: 'LandingCtrl'}); //controller: 'LandingCtrl'});
  $routeProvider.when('/contact', {templateUrl: '/partials/ContactUs.html', controller: 'ContactCtrl'});
  $routeProvider.when('/person/search',         {templateUrl: '/partials/PersonSearch.html',  controller: 'PersonSearchCtrl'});
  $routeProvider.when('/person/edit/:personId', {templateUrl: '/partials/PersonEdit.html',    controller: 'PersonEditCtrl'});
  $routeProvider.when('/survey/search',         {templateUrl: '/partials/SurveySearch.html',  controller: 'SurveySearchCtrl'});
  $routeProvider.when('/survey/edit/:surveyId', {templateUrl: '/partials/SurveyEdit.html',    controller: 'SurveyEditCtrl'});
  $routeProvider.when('/birdCount/search',         {templateUrl: '/partials/dataSheet/birdCountSearch.html',  controller: 'BirdCountSearchCtrl'});
  $routeProvider.otherwise({redirectTo: '/landing'});
}]);



