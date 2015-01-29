

var t1mAppServices = angular.module('t1mAppServices', ['ngResource']);

t1mAppServices.factory('RecordSvc', ['$resource', function($resource){
    return $resource('http://192.168.21.82:6080/rest/survey/:sli', {}, {});
}]);