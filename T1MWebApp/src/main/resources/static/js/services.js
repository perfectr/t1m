'use strict';

/* Services */
var myAppServices = angular.module('myAppServices', ['ngResource']);

myAppServices.factory('version', ['$resource',
  function($resource){
    return '${version}';
}]);


myAppServices.factory('LandingSvc', ['$location', function($location) {

    console.log('LandingSvc - loaded.');

    var service = {

        getTLSPort: function() {
            var tlsPort = '';
            if($location.port() == 6080 || $location.port() == 6443) {
                tlsPort = ':6443';
            }

            // HACK for now
            tlsPort = ':6080';

            return tlsPort;
        },

        publicAccessURL: function() {
            //return 'https://' + $location.host() + service.getTLSPort() + '/app/main';
            return 'http://' + $location.host() + service.getTLSPort() + '/app/main';
        },

        secureLoginURL:  function() {
            //return 'https://' + $location.host() + service.getTLSPort() + '/secureLogin';
            return 'http://' + $location.host() + service.getTLSPort() + '/secureLogin';
        }

    };

    return service;
}]);


myAppServices.factory('PersonSvc', ['$resource', function($resource){
    return $resource('/rest/person/:personId', {}, {
      query: {method:'GET', params:{personId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('EventSvc', ['$resource', function($resource){
    return $resource('/rest/event/:eventId', {}, {
      query: {method:'GET', params:{eventId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('PersonSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 6,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/person/search', service.searchCriteria).success(function(response) {
                service.searchResponse = response;
                //service.numPages = Math.floor(response.total / response.pageSize) + 1;
                if(callback) {
                    callback(response);
                }
            });
        },

        reset: function() {
            // For various UI databinding reasons we can just blow away service.searchCriteria because that
            // causes problems, so we need to reset the fields separately.
            service.searchCriteria.nameCriteria = null;
            service.searchResponse = {};
            //service.numPages = 0;
        }
    };

    service.reset();

    return service;
}]);

myAppServices.factory('EventSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 6,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/event/search', service.searchCriteria).success(function(response) {
                service.searchResponse = response;
                //service.numPages = Math.floor(response.total / response.pageSize) + 1;
                if(callback) {
                    callback(response);
                }
            });
        },

        reset: function() {
            // For various UI databinding reasons we can just blow away service.searchCriteria because that
            // causes problems, so we need to reset the fields separately.
            service.searchCriteria.nameCriteria = null;
            service.searchResponse = {};
            //service.numPages = 0;
        }
    };

    service.reset();

    return service;
}]);