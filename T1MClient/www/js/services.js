

var t1mAppServices = angular.module('t1mAppServices', ['ngResource']);

t1mAppServices.factory('RecordSvc', ['$resource', function($resource){
    return $resource('http://192.168.21.104:6080/rest/event/:sli', {}, {});
}]);