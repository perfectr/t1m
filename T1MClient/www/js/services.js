

var t1mAppServices = angular.module('t1mAppServices', ['ngResource']);

t1mAppServices.factory('RecordSvc', ['$resource', function($resource){
/*
    return $resource('http://192.168.21.82:6080/rest/survey/:sli', {}, {});
*/
    return $resource('http://54.153.148.10/rest/survey/:sli', {}, {});
    
    //return $resource('http://192.168.21.101:6080/rest/survey/:sli', {}, {});
}]);