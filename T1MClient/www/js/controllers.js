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

t1mControllers.controller('t1mBirdCtrl', [ '$scope', 'RecordSvc', '$modal', function($scope, RecordSvc, $modal) {
                $scope.surveyRecord = new RecordSvc();
                $scope.birdSurvey = {};
                $scope.birdSurvey.skipButton = 'Skip station';
                $scope.birdSurvey.skip = false;
                $scope.birdSurvey.reasonSkip = '';
                var storageData = window.localStorage.getItem('birdSurvey');
                $scope.surveyRecord = angular.fromJson(storageData);
                
                $scope.surveyRecord.dst[$scope.surveyRecord.dst.length] = 'birdCount';
                
                
    
    
                $scope.intoJson = function(){
                    $scope.surveyRecord.fld[$scope.surveyRecord.dst.length-1] = ['stationId','startTime','stationSkipped','reasonSkipped','sun'];
                    $scope.surveyRecord.dat[$scope.surveyRecord.dat.length-1] = [$scope.birdSurvey.rad,$scope.birdSurvey.time,$scope.birdSurvey.skip,$scope.birdSurvey.reasonSkip,$scope.birdSurvey.sun];
                }
                $scope.skip = function () {
                    if(!($scope.birdSurvey.skip)){
                        $scope.birdSurvey.skipButton = 'Un-skip station';
                        var modalInstance = $modal.open({
                              templateUrl: 'skipStationModalContent.html',
                              controller: 'skipModalInstanceCtrl'
                            });
                    }else{
                        $scope.birdSurvey.skipButton = 'Skip station';
                    }
                    modalInstance.result.then(function (reaSkip) {                            
                            if(reaSkip[1]=='false'){
                                $scope.birdSurvey.reasonSkip = reaSkip[0];
                                $scope.scope.isDisabled = true;
                            }else{
                                $scope.birdSurvey.skip = false;
                                $scope.birdSurvey.skipButton = 'Skip station';
                            }
                        });
                }
                $scope.saveAction = function(){
                    $scope.surveyRecord.$save();
                    $scope.surveyRecord = new RecordSvc();
                };
            }]);
t1mControllers.controller('skipModalInstanceCtrl', function ($scope, $modalInstance) {

              $scope.ok = function () {
                $modalInstance.close([$scope.reasonSkip,'false']);
              };

              $scope.cancel = function () {
                $modalInstance.close(['','true']);
              };
            });


t1mControllers.controller('t1mLitterCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
                $scope.surveyRecord = new RecordSvc();
                $scope.litterSurvey = {};
                var stuff = window.localStorage.getItem("litterSurvey");
                $scope.surveyRecord = angular.fromJson(stuff);     
                
                $scope.saveTab = function(){
                      $scope.surveyRecord.dst[0] = "beachLitter";
                };
    
                $scope.saveAction = function(){
                    $scope.surveyRecord.$save();
                    $scope.surveyRecord = new RecordSvc();
                };
            }]);