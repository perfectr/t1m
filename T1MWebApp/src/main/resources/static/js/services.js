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

myAppServices.factory('SurveySvc', ['$resource', function($resource){
    return $resource('/rest/survey/:surveyId', {}, {
      query: {method:'GET', params:{surveyId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('SurveySearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/survey/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('BirdCountSvc', ['$resource', function($resource){
    return $resource('/rest/birdCount/:dataSheetId', {}, {
      query: {method:'GET', params:{dataSheetId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('BirdCountSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/birdCount/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('BCBirdSvc', ['$resource', function($resource){
    return $resource('/rest/bcBird/:instanceId', {}, {
      query: {method:'GET', params:{instanceId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('BCBirdSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/bcBird/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('BirdDistanceSvc', ['$resource', function($resource){
    return $resource('/rest/birdDistance/:dataSheetId', {}, {
      query: {method:'GET', params:{dataSheetId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('BirdDistanceSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/birdDistance/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('BDBirdSvc', ['$resource', function($resource){
    return $resource('/rest/bdBird/:instanceId', {}, {
      query: {method:'GET', params:{instanceId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('BDBirdSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/bdBird/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('IncidentalBirdSvc', ['$resource', function($resource){
    return $resource('/rest/incidentalBird/:dataSheetId', {}, {
      query: {method:'GET', params:{dataSheetId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('IncidentalBirdSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/incidentalBird/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('LitterBeachSvc', ['$resource', function($resource){
    return $resource('/rest/litterBeach/:dataSheetId', {}, {
      query: {method:'GET', params:{dataSheetId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('LitterBeachSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/litterBeach/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('LBItemSvc', ['$resource', function($resource){
    return $resource('/rest/lbItem/:instanceId', {}, {
      query: {method:'GET', params:{instanceId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('LBItemSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/lbItem/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('LitterLargeSvc', ['$resource', function($resource){
    return $resource('/rest/litterLarge/:dataSheetId', {}, {
      query: {method:'GET', params:{dataSheetId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('LitterLargeSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/litterLarge/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('LLItemSvc', ['$resource', function($resource){
    return $resource('/rest/llItem/:instanceId', {}, {
      query: {method:'GET', params:{instanceId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('LLItemSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/llItem/search', service.searchCriteria).success(function(response) {
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

myAppServices.factory('BeachCharacterizationSvc', ['$resource', function($resource){
    return $resource('/rest/beachCharacterization/:dataSheetId', {}, {
      query: {method:'GET', params:{dataSheetId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('BeachCharacterizationSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 18,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/beachCharacterization/search', service.searchCriteria).success(function(response) {
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



myAppServices.factory('ImageSvc', ['$resource', function($resource){
    return $resource('/rest/image/:imageId', {}, {
      query: {method:'GET', params:{imageId:'search'}, isArray:false}
    });
}]);

myAppServices.factory('ImageSearchSvc', ['$http', function($http) {
    var service = {
        searchCriteria: {
            pageNumber : 1,
            pageSize : 4,
            nameCriteria : null
        },

        search: function(callback) {
            $http.post('/rest/image/search', service.searchCriteria).success(function(response) {
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