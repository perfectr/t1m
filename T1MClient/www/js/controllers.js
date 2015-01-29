var t1mControllers = angular.module('t1mControllers', []);

t1mControllers.controller('t1mCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
            var storageData = window.localStorage.getItem('birdSurvey');
             $scope.surveyRecord = new RecordSvc();

            
            
                $scope.startSurvey = function(surveyType){
                    $scope.surveyRecord.typ = 'bird';
                    $scope.surveyRecord.dst = [];
                    $scope.surveyRecord.fld = [[]];
                    $scope.surveyRecord.dat = [[]];
                    window.localStorage.setItem(surveyType,angular.toJson($scope.surveyRecord,false));
                    window.location = "surveys/"+surveyType+".html";
                };
            }]);

t1mControllers.controller('t1mBirdCtrl', [ '$scope', 'RecordSvc', '$modal', function($scope, RecordSvc, $modal) {
                $scope.surveyRecord = new RecordSvc();
                $scope.surveyRecord.typ = 'bird';
                $scope.surveyRecord.dst = [];
                $scope.surveyRecord.fld = [[]];
                $scope.surveyRecord.dat = [[]];
                    
    
                var storageData = window.localStorage.getItem('birdSurvey');
                //$scope.surveyRecord = $scope.surveyRecord.concat(angular.fromJson(storageData));
    
                $scope.birdSurvey = angular.fromJson(storageData);
                $scope.birdSurvey.skipButton = 'Skip station';
                $scope.birdSurvey.skip = false;
                $scope.birdSurvey.reasonSkip = '';
                $scope.surveyRecord.dst[$scope.surveyRecord.dst.length] = 'birdCount';
                //$scope.surveyRecord.dst = 'birdCount';
                $scope.options = {
                     sun: [
                        {name: "0", value: "0"}, 
                        {name: "1", value: "1"},
                        {name: "2", value: "2"},   
                        {name: "3", value: "3"},
                        {name: "4", value: "4"},
                        {name: "5", value: "5"}],
                    
                    temp: [
                        {name: "<0°C", value: "<0°C"}, 
                        {name: "0-5°C", value: "0-5°C"},
                        {name: "6-10°C", value: "6-10°C"},
                        {name: "11-15°C", value: "11-15°C"},
                        {name: "16-22°C", value: "16-22°C"},
                        {name: ">22°C", value: ">22°C"}],
                    
                    prec: [
                        {name: "None", value: "None"}, 
                        {name: "Dripping foliage", value: "Dripping foliage"},
                        {name: "Drizzle", value: "Drizzle"},
                        {name: "Light", value: "Light"},
                        {name: "Moderate", value: "Moderate"},
                        {name: "Heavy", value: "Heavy"},
                        {name: "Mist", value: "Mist"},
                        {name: "Rain", value: "Rain"},
                        {name: "Hail", value: "Hail"},
                        {name: "Snow", value: "Snow"}],
                    
                    wind: [
                        {name: "Leaves still/move silently", value: "Leaves still/move silently"}, 
                        {name: "Leaves rustle", value: "Leaves rustle"},
                        {name: "Leaves and branches in constant motion", value: "Leaves and branches in constant motion"},
                        {name: "Branches or trees sway", value: "Branches or trees sway"}],
                    
                    othNoi: [
                        {name: "Not important", value: "Not important"}, 
                        {name: "Moderate", value: "Moderate"},
                        {name: "Loud", value: "Loud"}],
                    
                    pos: [
                        {name: "Single", value: "Single"}, 
                        {name: "Averaged", value: "Averaged"}]
                };
                
                $scope.intoJson = function(){
                    s.sli = b.sli;
                    s.sdt = b.sdt;
                    s.edt = b.edt;
                    s.obs = b.obs;
                    
                    $scope.surveyRecord.fld[$scope.surveyRecord.dst.length-1] = ['StationId','StartTime','StationSkipped','ReasonSkipped','Sun','Temp','Precipitation','Wind','OtherNoise','Easting','Northing','Elevation','Position','Notes'];
                    $scope.surveyRecord.dat[$scope.surveyRecord.dat.length-1] = [b.rad,b.time,b.skip,b.reasonSkip,b.sun,b.temp,b.prec,b.wind,b.othNoi,b.east,b.north,b.elev,b.pos,b.notes];
                    /*window.localStorage.setItem(b.typ,angular.toJson($scope.surveyRecord,false));*/
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
                    $scope.intoJson();
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



t1mControllers.controller('t1mLitterSurveyCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
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


t1mControllers.controller('t1mBeachCharacterizationCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
                $scope.surveyRecord = new RecordSvc();
                $scope.beachCharacterization = {};
                    
                $scope.options = {
                    location: [
                        {name: "Urban", value: "Urban"}, 
                        {name: "Peri-urban", value: "Peri-urban"},
                        {name: "Rural", value: "Rural"}]
                };
    
                $scope.beachCharacterization.location = $scope.options.location[0].value;
    
                var stuff = window.localStorage.getItem("litterSurvey");
                $scope.surveyRecord = angular.fromJson(stuff);  
    
                
                $scope.saveTab = function(){
                      $scope.surveyRecord.dst[0] = "beachLitter";
                };
    
                $scope.selectTab = function(index){
                      window.mySwipe.slide(index, 500);
                };
    
                $scope.saveAction = function(){
                    $scope.surveyRecord.$save();
                    $scope.surveyRecord = new RecordSvc();
                };
            }]);