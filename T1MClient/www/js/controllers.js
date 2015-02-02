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

t1mControllers.controller('t1mBirdCtrl', [ '$scope', 'RecordSvc', '$modal', '$timeout', '$window', function($scope, RecordSvc, $modal, $timeout, $window) {
                $scope.surveyRecord = new RecordSvc();

                var s = $scope.surveyRecord;
                var b = $scope.birdSurvey;
                
                $scope.initializeRecord = function(){
                    $scope.surveyRecord.typ = 'bird';
                    $scope.surveyRecord.dst = [];
                    $scope.surveyRecord.fld = [[]];
                    $scope.surveyRecord.dat = [[]];
                    $scope.surveyRecord.dst[$scope.surveyRecord.dst.length] = 'birdCount';
                    
                    var storageData = window.localStorage.getItem('birdSurvey');
                    //$scope.surveyRecord = $scope.surveyRecord.concat(angular.fromJson(storageData));

                    $scope.birdSurvey = angular.fromJson(storageData);
                    if($scope.birdSurvey == null){
                        $scope.birdSurvey = {};
                    }
                    $scope.birdSurvey.skipButton = 'Skip station';
                    $scope.birdSurvey.skip = false;
                    $scope.birdSurvey.reasonSkip = '';
                    //$scope.surveyRecord.dst = 'birdCount';
                    s = $scope.surveyRecord;
                    b = $scope.birdSurvey;
                    s.sli = b.sli;
                    s.sdt = b.sdt;
                    s.edt = b.edt;
                    s.obs = b.obs;
                }
                $scope.initializeRecord();            
                
                $scope.timer = {};
                $scope.timer.mins = 5;    
                $scope.timer.secs = "00";
                $scope.timer.alerted = false;
                var stopped;
    
                $scope.countdown = function(){
                  stopped = $timeout(function(){

                      if($scope.timer.secs==0){
                          if($scope.timer.mins==0){
                              if($scope.timer.secs==0){
                                  $scope.stop();
                              }else{
                                  $scope.timer.secs--;
                              }
                          }else {
                              $scope.timer.mins--;
                              $scope.timer.secs = 59;
                          }
                      }else{
                        $scope.timer.secs--;
                      }
                      
                      if($scope.timer.secs < 10){
                          if($scope.timer.secs == 0){
                              $scope.timer.secs = "00";
                              if($scope.timer.mins==0){
                                    $scope.stop();
                                    if(!($scope.timer.alerted)){
                                        $scope.timerFin();
                                    }
                                }
                          }else{
                                $scope.timer.secs = "0"+$scope.timer.secs;
                                
                          }
                      }
                    $scope.countdown();
                  },1000);  
                };
                $scope.stop = function(){
                  $timeout.cancel(stopped);  
                };
                $scope.reset = function(){
                    $scope.stop();
                    $scope.timer.mins = 5;
                    $scope.timer.secs = "00";
                    $scope.timer.alerted = false;
                }
                $scope.timerFin = function() {
                    setTimeout(function() {
                      $window.alert('ALERT!');
                        $scope.timer.alerted = true;
                    });
                  };
    
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
                                /*$scope.scope.isDisabled = true;*/
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
                    $scope.initializeRecord();
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


/* ================== controllers for beach litter survey ========================= */
t1mControllers.controller('t1mLitterSurveyCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
    $scope.startDataSheet = function(dataSheetType) {
        window.location = "../dataSheets/"+dataSheetType+".html";
    };
    
    $scope.clearDataSheet = function(dataSheetType) {
        window.localStorage.setItem(dataSheetType, "{}");
    };
}]);


t1mControllers.controller('t1mBeachLitterCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
            $scope.litterBeach = {};
            $scope.testLitterBeach = {};
    
            var savedLitterBeach =  window.localStorage.getItem("litterBeach");
            if(savedLitterBeach != null){
                $scope.litterBeach = angular.fromJson(savedLitterBeach);
                $scope.testLitterBeach = angular.fromJson(savedLitterBeach);
            };
    
    
            $scope.options = {
                    seasons: [
                        {name: "Summer", value: "Summer", startMonth: 12, endMonth: 02}, 
                        {name: "Autum", value: "Autum", startMonth: 03, endMonth: 05},
                        {name: "Winter", value: "Winter", startMonth: 06, endMonth: 08 },
                        {name: "Spring", value: "Spring", startMonth: 09, endMonth: 11}]
                };
    
            $scope.litterBeach.Season = $scope.options.seasons[0].value;
    
             $scope.tabs = [
                    {title: "Sampling Area", index: 0},
                    {title: "Litter Data", index: 1}
                ];
    
            $scope.selectTab = function(index){
                    $scope.tabs[index].active = true;   
                };
            
            $scope.saveLitterBeach = function(){
                window.localStorage.setItem("litterBeach", angular.toJson($scope.litterBeach, false));
                $scope.testLitterBeach = angular.fromJson(window.localStorage.getItem("litterBeach"));
            };
    
            }]);


t1mControllers.controller('t1mBeachCharacterizationCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
                $scope.beachCharacterization = {};
    
                var savedBeachCharacterization =  window.localStorage.getItem("beachCharacterization");
                if(savedBeachCharacterization != null){
                    $scope.beachCharacterization = angular.fromJson(savedBeachCharacterization);
                };
                    
                $scope.options = {
                    location: [
                        {name: "Urban", value: "Urban"}, 
                        {name: "Peri-urban", value: "Peri-urban"},
                        {name: "Rural", value: "Rural"}]
                };
    
                $scope.tabs = [
                    {title: "Sampling Area", index: 0},
                    {title: "Beach Char.", index: 1},
                    {title: "Source Char.", index: 2 }
                ];
    
                $scope.beachCharacterization.Location = $scope.options.location[0].value;
    
                $scope.selectTab = function(index){
                    window.mySwipe.slide(index, 500);
                    $scope.saveBeachCharacterization();
                };
    
                
    
                $scope.getSelectTab = function(tabName){
                    for(tab in tabs){
                        if(tab.name == tabName){
                            return tab.select;   
                        }
                    }
                };
    
                 $scope.saveBeachCharacterization = function(){
                    window.localStorage.setItem("beachCharacterization", angular.toJson($scope.beachCharacterization, false));
                };
    
                $scope.saveAction = function(){
                    $scope.surveyRecord.$save();
                    $scope.surveyRecord = new RecordSvc();
                };
            }]);