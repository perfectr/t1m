var t1mControllers = angular.module('t1mControllers', []);

t1mControllers.controller('t1mCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
                $scope.surveyRecord = new RecordSvc();
                $scope.startSurvey = function(surveyType){
                    $scope.surveyRecord.typ = surveyType;
                    $scope.surveyRecord.dst = [];
                    $scope.surveyRecord.fld = [[]];
                    $scope.surveyRecord.dat = [[]];
                    window.localStorage.setItem(surveyType,angular.toJson($scope.surveyRecord,false));
                    window.location = "surveys/"+surveyType+".html";
                };
            }]);

t1mControllers.controller('t1mBirdCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
                $scope.surveyRecord = new RecordSvc();
                $scope.birdSurvey = {};
                var stuff = window.localStorage.getItem('birdSurvey');
                $scope.surveyRecord = angular.fromJson(stuff);
                
                $scope.surveyRecord.dst[$scope.surveyRecord.dst.length] = 'birdCount';
                $scope.surveyRecord.fld[$scope.surveyRecord.dst.length-1] = ['stationId'];
    
                $scope.intoJson = function(){
                    $scope.surveyRecord.dat[$scope.surveyRecord.dat.length-1] = [$scope.birdSurvey.rad];
                }
                $scope.saveAction = function(){
                    $scope.surveyRecord.$save();
                    $scope.surveyRecord = new RecordSvc();
                };
            }]);

t1mControllers.controller('t1mLitterCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
                $scope.surveyRecord = new RecordSvc();

                $scope.saveAction = function(){
                    $scope.surveyRecord.$save();
                    $scope.surveyRecord = new RecordSvc();
                };
                $scope.loadData = function(surveyType){
                    var stuff = window.localStorage.getItem(surveyType);
                    $scope.surveyRecord = angular.fromJson(stuff);
                        
                };
            }]);