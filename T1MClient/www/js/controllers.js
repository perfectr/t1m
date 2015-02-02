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
                
                $scope.options = {
                     sun: [
                        {name: "0", value: "0"},
                        {name: "1", value: "1"},
                        {name: "2", value: "2"},
                        {name: "3", value: "3"},
                        {name: "4", value: "4"},
                        {name: "5", value: "5"}],
                    
                    temp: [
                        {name: "0", value: "0"}, 
                        {name: "1", value: "1"},
                        {name: "2", value: "2"},
                        {name: "3", value: "3"},
                        {name: "4", value: "4"},
                        {name: "5", value: "5"}]
                        
                };
    
                $scope.intoJson = function(){
                    $scope.surveyRecord.fld[$scope.surveyRecord.dst.length-1] = ['stationId','startTime','stationSkipped','reasonSkipped','sun','temp'];
                    $scope.surveyRecord.dat[$scope.surveyRecord.dat.length-1] = [$scope.birdSurvey.rad,$scope.birdSurvey.time,$scope.birdSurvey.skip,$scope.birdSurvey.reasonSkip,$scope.birdSurvey.sun,$scope.birdSurvey.temp];
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