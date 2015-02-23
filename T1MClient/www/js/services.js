

var t1mAppServices = angular.module('t1mAppServices', ['ngResource']);

t1mAppServices.factory('RecordSvc', ['$resource', function($resource){
    // current amazon address
    return $resource('http://54.153.148.10/rest/survey/:sli', {}, {});
    
    //return $resource('http://192.168.21.103:6080/rest/survey/:sli', {}, {});
}]);

t1mAppServices.factory('ImageSvc', ['$resource', function($resource){
    // current amazon address
    return $resource('http://54.153.148.10/rest/litterItemImage/:img', {}, {});
    
    //return $resource('http://192.168.21.103:6080/rest/image/:img', {}, {});
}]);