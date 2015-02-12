var t1mControllers = angular.module('t1mControllers', []);

t1mControllers.controller('t1mCtrl', ['$scope', 'RecordSvc', 'ImageSvc',function($scope, RecordSvc, ImageSvc) {
    
    var storedSurveys = window.localStorage.getItem("Surveys");
    $scope.surveys = angular.fromJson(storedSurveys);
    if($scope.surveys == null){
        $scope.surveys = {surveys: [],birdCount:0, litterCount:0};   
    }
    
    $scope.addSurvey = function(type){
        var surveyName = type+$scope.surveys[type+"Count"];
        $scope.surveys.surveys.push({name:surveyName, type:type}); 
        $scope.surveys[type+"Count"] ++;
        $scope.loadSurvey(surveyName, type);
    }
    
    $scope.loadSurvey = function(surveyName, surveyType){
        var surveys = angular.toJson($scope.surveys, false);
        window.localStorage.setItem("Surveys", surveys);
        window.location = "surveys/"+surveyType+"Survey.html?"+surveyName;
    }
    
    $scope.images = [
    ];
    
    $scope.onPhotoDataSuccess = function(imageData) {
        var imageSvc = new ImageSvc;
        imageSvc.img = imageData;
        imageSvc.$save();
        $scope.images.push({ 
                        src:"data:image/jpeg;base64," + imageData
                            });
        $scope.$apply();
    }

  $scope.onFail = function(message) {
      alert('Failed because: ' + message);
    }

 // var destinationType = navigator.camera.destinationType;
  $scope.takeImage = function(){
       navigator.camera.getPicture($scope.onPhotoDataSuccess, $scope.onFail, 
            { quality: 100,
            destinationType: navigator.camera.DestinationType.DATA_URL,
            correctOrientation: true,
            allowEdit: true, 
            targetWidth: 1280, 
            targetHeight: 1280, 
            cameraDirection: navigator.camera.Direction.BACK, 
            saveToPhotoAlbum: false 
       });
  }
    
     
    
   /* var storageData = window.localStorage.getItem('birdSurvey');
     $scope.surveyRecord = new RecordSvc();



        $scope.startSurvey = function(surveyType){
            $scope.surveyRecord.typ = surveyType;
            $scope.surveyRecord.dst = [];
            $scope.surveyRecord.fld = [];
            $scope.surveyRecord.dat = [];
            $scope.surveyRecord.inf = [];
            $scope.surveyRecord.ind = [];
            window.localStorage.setItem(surveyType,angular.toJson($scope.surveyRecord,false));
            window.localStorage.setItem("surveyRecord", angular.toJson($scope.surveyRecord,false)); //beach litter saving reference.
            window.location = "surveys/"+surveyType+"Survey.html";
        };*/
    }]);

t1mControllers.controller('t1mBirdSurveyCtrl', ['$scope', 'RecordSvc',function($scope, RecordSvc) {
    $scope.currentSurvey = window.location.search.replace("?","");
    var curSurvey = window.localStorage.getItem($scope.currentSurvey);
    if(curSurvey==null){
        /*then make a new one*/
        $scope.curSurveyData = {meta:$scope.currentSurvey+"meta"}
        $scope.curSurveyMeta = {};
    }else{
        /*load it*/
        $scope.curSurveyData = curSurvey;
    }
    
    $scope.localSave = function(){
        window.localStorage.setItem($scope.currentSurvey,$scope.curSurveyData);
        window.localStorage.setItem($scope.curSurveydata.meta,$scope.curSurveyMeta);
    };
    $scope.loadDataSheet = function(){
      window.location="../dataSheets/fiveMinBirdCount.html";
    };
    
}]);

t1mControllers.controller('t1mfiveMinBirdCountCtrl', [ '$rootScope',
                                                      '$scope',
                                                      'RecordSvc',
                                                      '$modal',
                                                      '$timeout',
                                                      '$window',
                                                      function($rootScope,
                                                                $scope,
                                                                RecordSvc,
                                                                $modal,
                                                                $timeout,
                                                                $window) {
                $scope.surveyRecord = new RecordSvc();
                $scope.currentSurvey = window.location.search.replace("?","");
                var s = $scope.surveyRecord;
                var b = $scope.birdSurvey;
                
                $scope.initializeRecord = function(){
                    $scope.surveyRecord.typ = 'bird';
                    $scope.surveyRecord.dst = [];
                    $scope.surveyRecord.fld = [[]];
                    $scope.surveyRecord.dat = [[]];
                    $scope.surveyRecord.dst[$scope.surveyRecord.dst.length] = 'birdCount';
                    
                    var storageData = window.localStorage.getItem('bird');
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
                $scope.timer.started = false;
                var stopped;
                
                $scope.birdCount = [];    
                $scope.birdText = '';
    
                $scope.incrementBird = function(bt){
                    for(var i=0; i < $scope.birdCount.length; i++){
                        if($scope.birdCount[i].bird==bt){
                            $scope.birdCount[i].near++;
                            return;
                        }
                    }
                    $scope.birdCount.push({bird:bt,near:0,far:0,veryFar:0,notes:""});
                }
                
                $scope.touches = [];
                $scope.x;
                $scope.y;
                $scope.addTouch = function(event){
                    
                    $scope.addRadarModal();
                    $scope.getX(event);
                    $scope.getY(event);
                        $scope.touches.push({
                            "x": $scope.x,
                            "y": $scope.y
                        });
                    
                };
                                                          
                $scope.getX = function(event){
                    $scope.x = event.offsetX;
                }; 
                                                          
                $scope.getY = function(event){
                    $scope.y = event.offsetY;
                };                                          
    
                
                $scope.startCountdown = function(){
                    if($scope.timer.started == false){
                        $scope.countdown();
                        $scope.timer.started = true;
                    }
                };
                                                          
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
                    $scope.timer.started = false;
                };
                $scope.reset = function(){
                    $scope.stop();
                    $scope.timer.mins = 5;
                    $scope.timer.secs = "00";
                    $scope.timer.alerted = false;
                }
                $scope.timerFin = function() {
                    setTimeout(function() {
                      $window.alert('Time\'s up!');
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

                    $scope.surveyRecord.fld[$scope.surveyRecord.dst.length-1] = ['StationId',
                                                                                 'StartTime',
                                                                                 'StationSkipped',
                                                                                 'ReasonSkipped',
                                                                                 'Sun',
                                                                                 'Temp',
                                                                                 'Precipitation',
                                                                                 'Wind',
                                                                                 'OtherNoise',
                                                                                 'Easting',
                                                                                 'Northing',
                                                                                 'Elevation',
                                                                                 'Position',
                                                                                 'Notes'];
                    $scope.surveyRecord.dat[$scope.surveyRecord.dat.length-1] = [b.rad,
                                                                                 b.time,
                                                                                 b.skip,
                                                                                 b.reasonSkip,
                                                                                 b.sun,
                                                                                 b.temp,
                                                                                 b.prec,
                                                                                 b.wind,
                                                                                 b.othNoi,
                                                                                 b.east,
                                                                                 b.north,
                                                                                 b.elev,
                                                                                 b.pos,
                                                                                 b.notes];
                    $scope.surveyRecord.inf=[];
                    $scope.surveyRecord.ind=[];
                    /*window.localStorage.setItem(b.typ,angular.toJson($scope.surveyRecord,false));*/
                }
                
                $scope.skip = function () {
                    if(!($scope.birdSurvey.skip)){
                        $scope.birdSurvey.skipButton = 'Un-skip station';
                        var modalInstance = $modal.open({
                              templateUrl: 'modals/skipStationModalContent.html',
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
                
                $scope.addBirdModal = function(){
                    
                    var modalInstance = $modal.open({
                              templateUrl: 'modals/birdModalContent.html',
                              controller: 'birdModalInstanceCtrl'
                            });
                    modalInstance.result.then(function (bt) {
                            if(bt!=null){
                                for(var i = 0; i < $scope.birdCount.length; i++){
                                    if($scope.birdCount[i].bird==bt){
                                        return;
                                    }
                                }
                                $scope.incrementBird(bt);
                            }   
                        });
                    
                }
                
                $scope.addBirdIncrementModal = function(bName){
                    var b;
                    
                    for(var i=0; i < $scope.birdCount.length; i++){
                        if($scope.birdCount[i].bird==bName){
                            b = $scope.birdCount[i];
                        }
                    }
                    
                    var modalInstance = $modal.open({
                              templateUrl: 'modals/birdIncModalContent.html',
                              controller: 'birdIncrementModalCtrl',
                              resolve:{
                                  b: function(){
                                      return b;
                                  }
                              }
                            });
                    modalInstance.result.then(function (bt) {
                            
                        });
                    
                }
                
                $scope.addRadarModal = function(){
                    
                    var modalInstance = $modal.open({
                              templateUrl: 'modals/radarModalContent.html',
                              controller: 'radarModalInstanceCtrl',
                              resolve:{
                                  birds: function(){
                                      return $scope.birdCount;
                                  }
                              }
                            });
                    modalInstance.result.then(function (bt) {
                            
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

t1mControllers.controller('radarModalInstanceCtrl', function ($scope, $modalInstance, birds, $modal) {
    
    $scope.birds = birds;
    
    $scope.ok = function () {
        $modalInstance.close();
    };
      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    
    $scope.addRadarIncrModal = function(b){
                    $scope.ok();
                    var modalInstance = $modal.open({
                              templateUrl: 'modals/radarIncrModalContent.html',
                              controller: 'radarIncrModalInstanceCtrl',
                              resolve:{
                                  bird: function(){
                                      return b;
                                  }
                              }
                            });
                    modalInstance.result.then(function (bt) {
                            
                        });
                    
                };
});

t1mControllers.controller('radarIncrModalInstanceCtrl', function ($scope, $modalInstance, bird) {
    
    $scope.bird = bird;
    $scope.count = 0;
    
    $scope.countInc = function(v){
        if(v=='min'){
            if($scope.count > 0){
                $scope.count--;
            }
        }
        if(v=='plus'){
            $scope.count++;
        }
    };
    
    $scope.ok = function () {
        $modalInstance.close();
    };
      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

t1mControllers.controller('birdModalInstanceCtrl', function ($scope, $modalInstance) {
                
            $scope.birdSpecies = [
                            {text:'Bellbird'},
                            {text:'Blackbird'},
                            {text:'Blue Duck'},
                            {text:'Brown Creeper'},
                            {text:'Californian Quail'},
                            {text:'Chaffinch'},
                            {text:'Dunnock (Hedge Sparrow)'},
                            {text:'Eastern Rosella'},
                            {text:'Falcon (NZ)'},
                            {text:'Fantail'},
                            {text:'Fernbird'},
                            {text:'Goldfinch'},
                            {text:'Gray Warbler'},
                            {text:'Greenfinch'},
                            {text:'Harrier Hawk'},
                            {text:'House Sparrow'},
                            {text:'Indian Myna'},
                            {text:'Kaka'},
                            {text:'Kea'},
                            {text:'Kingfisher (NZ)'},
                            {text:'Kiwi (* specify)'},
                            {text:'Long Tail Cuckoo'},
                            {text:'Magpie (Australian)'},
                            {text:'Morepork'},
                            {text:'Oystercatcher (* specify)'},
                            {text:'Paradise Shelduck'},
                            {text:'Parakeet (* specify)'},
                            {text:'Pukeko'},
                            {text:'Redpoll'},
                            {text:'Rifleman'},
                            {text:'Robin'},
                            {text:'Rock Pigeon (Feral)'},
                            {text:'Rock Wren'},
                            {text:'Shinning Cuckoo'},
                            {text:'Silvereye'},
                            {text:'Song Thrush'},
                            {text:'Spur Wing Plover'},
                            {text:'Starling'},
                            {text:'Tomtit'},
                            {text:'Tui'},
                            {text:'Weka'},
                            {text:'Welcome Swallow'},
                            {text:'Wood Pigeon (Kereru)'},
                            {text:'Yellow Hammer'},
                            {text:'Yellowhead'}];
    
              $scope.birdPress = function(birdType){
                  $scope.birdText = birdType;
                  $scope.ok();
              }
    
              $scope.ok = function () {
                $modalInstance.close($scope.birdText);
              };

              $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
              };
        });

t1mControllers.controller('birdIncrementModalCtrl', function ($scope, $modalInstance, b) {
                
              $scope.bird = b;
              
              $scope.nearIncPlus = function(){
                  $scope.bird.near++;
              };
    
              $scope.nearIncMinus = function(){
                  if($scope.bird.near > 0){
                    $scope.bird.near--;
                  }
              };
                
              $scope.farIncPlus = function(){
                  $scope.bird.far++;
              };
    
              $scope.farIncMinus = function(){
                  if($scope.bird.far > 0){
                    $scope.bird.far--;
                  }
              }; 
    
              $scope.veryFarIncPlus = function(){
                  $scope.bird.veryFar++;
              };
    
              $scope.veryFarIncMinus = function(){
                  if($scope.bird.veryFar > 0){
                    $scope.bird.veryFar--;
                  }
              }; 
                
              $scope.ok = function () {
                $modalInstance.close();
              };

              $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
              };
        });


/* ================== controllers for beach litter survey ========================= */
t1mControllers.controller('t1mLitterSurveyCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
    
    var currentSurvey = window.location.search.replace("?","");
    
    $scope.survey = angular.fromJson(window.localStorage.getItem(currentSurvey));
    
    
    if($scope.survey == null){
        $scope.survey = {meta:currentSurvey+"Meta", 
                         dataSheets:[{name:currentSurvey+'beachCharacterization', 
                                      type:'beachCharacterization'}], 
                         count:0};
        window.localStorage.setItem(currentSurvey, angular.toJson($scope.survey, false));
    }  
    
    $scope.beachChar = angular.fromJson(window.localStorage.getItem($scope.survey.dataSheets[0].name));

    $scope.startDataSheet = function(dataSheetType) {
        var dstName = currentSurvey+dataSheetType+$scope.survey.count;
        $scope.survey.count ++;
        $scope.survey.dataSheets.push({name:dstName, type:dataSheetType});
       // $scope.loadDataSheet(dstName, dataSheetType);
    };
    
    $scope.loadDataSheet = function(dataSheetName, dataSheetType){
        window.localStorage.setItem(currentSurvey, angular.toJson($scope.survey, false));
        window.location = "../dataSheets/"+dataSheetType+".html?"+dataSheetName;
    };
    
    $scope.toSend = {};
    
    var surveyStorageKey = "storedSurvey";
    
    $scope.saveSurvey = function(){
        window.localStorage.setItem(surveyStorageKey, angular.toJson($scope.toSend, false));
        
        saveMetaDataToSurveyRecord($scope.survey.meta, surveyStorageKey);
        
        var dataSheets = $scope.survey.dataSheets;
        for(var i = 0; i < dataSheets.length; i++){
            saveDataSheetToSurveyRecord(dataSheets[i].type, dataSheets[i].name, surveyStorageKey);
        }
        
        $scope.toSend = angular.fromJson(window.localStorage.getItem(surveyStorageKey));
    };

    $scope.sendToServer = function (){
        //TODO need to add validaton so that important fields are filled
        $scope.saveSurvey();
        $scope.response = sendSurveyRecordToServer(new RecordSvc, surveyStorageKey);
    }
    
}]);

/*--========================== Beach Litter controler ==================================*/
t1mControllers.controller('t1mBeachLitterCtrl', [ '$scope', 'RecordSvc', '$modal',function($scope, RecordSvc, $modal) {
    
    var currentDataSheet = window.location.search.replace("?","");
    
     $scope.options = {
            seasons: [
                {name: "Summer", value: "Summer", startMonth: 12, endMonth: 02}, 
                {name: "Autum", value: "Autum", startMonth: 03, endMonth: 05},
                {name: "Winter", value: "Winter", startMonth: 06, endMonth: 08 },
                {name: "Spring", value: "Spring", startMonth: 09, endMonth: 11}
            ]
        };
    
    $scope.tabs = [
            {title: "Sampling Area", index: 0},
            {title: "Litter Data", index: 1}
        ];
    
    $scope.Instances = [
        {text: "test Litter Item"}, 
        {text: "test Litter Item 2"}
    ];
    
    $scope.litterBeach = {};

    var savedLitterBeach =  window.localStorage.getItem(currentDataSheet);
    if(savedLitterBeach != null){
        $scope.litterBeach = angular.fromJson(savedLitterBeach);
    } else {
        $scope.litterBeach.Season = $scope.options.seasons[0].value;   
    };


    $scope.saveLitterBeach = function(){
        window.localStorage.setItem(currentDataSheet, angular.toJson($scope.litterBeach, false));
    };
    
  
    
    $scope.retriveGPS = function(gpsField){
        
           var retriveGPSSuccess = function(position) {
               $scope.litterBeach["Latitude"+gpsField] = position.coords.latitude;
               $scope.litterBeach["Longitude"+gpsField] = position.coords.longitude;
               $scope.litterBeach.CoordSystem = "WGS84";
               $scope.$apply();       
           } 
           
           var retriveGPSError = function(error){
             /*  if(error.code = PositionError.PERMISSION_DENIED){
               }
               if(error.code = PositionError.POSITION_UNAVAILABLE){
               }
               if(error.code = PositionError.TIMEOUT){
               }*/
               
           }
           
           var options = { enableHighAccuracy: true, maximumAge: 100 };
           navigator.geolocation.getCurrentPosition(retriveGPSSuccess, retriveGPSError, options);
    }
    
     

    $scope.selectTab = function(index){
        window.mySwipe.slide(index, 500);
        $scope.saveLitterBeach();
    };
    
   $scope.getSelectTab = function(tabName){
        for(tab in tabs){
            if(tab.name == tabName){
                return tab.select;   
            }
        }
    };
    
    $scope.popNotes = function(){
        $scope.litterBeach.Notes =  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."  
    }
    
    
     $scope.addLitterModal = function(){
                    
        var modalInstance = $modal.open({
                  templateUrl: '../modals/beachLitterItem.html',
                  controller: 'beachLitterItemCtrl'
                });
        modalInstance.result.then(function (bt) {
            });

    }
     $scope.loadLitterModal = function(){
                    
        var modalInstance = $modal.open({
                  templateUrl: '../modals/beachLitterItem.html',
                  controller: 'beachLitterItemCtrl'
                });
        modalInstance.result.then(function (bt) {
            });

    }
    
}]);

t1mControllers.controller('beachLitterItemCtrl', ['$scope', function($scope, $modalInstance){
    $scope.SmallLitter = {};
    $scope.SmallLitter.Img = "";
    
   $scope.onPhotoDataSuccess = function(imageData) {
        $scope.SmallLitter.Img = "data:image/jpeg;base64," + imageData;
        $scope.$apply();
    }
   
   $scope.options = {
        types: [{type:"Plastic"},
                {type:"Foamed Plastic"},
                {type:"Cloth"},
                {type:"Glass & ceramic"},
                {type:"Metal"},
                {type:"Paper & cardboard"},
                {type:"Rubber"},
                {type:"Wood"},
                {type:"Other"}           
              ],
       specific: []
   };
    
    $scope.SmallLitter.LitterCode = "text"
    
    $scope.litterTypeSelected = function(){
        var codeOptions = litterCodeSelection($scope.SmallLitter.Type, null);
        $scope.options.specific = codeOptions;
        $scope.$apply();
    }
    
    $scope.litterSpecificSelected = function(){
        var codeOptions = litterCodeSelection($scope.SmallLitter.Specific.type, $scope.SmallLitter.Specific.value);
        console.log(codeOptions);
        $scope.SmallLitter.Type = codeOptions.type;
        $scope.SmallLitter.Specific = codeOptions;
        $scope.SmallLitter.LitterCode = codeOptions.code;
        $scope.$apply();
    }
    
    $scope.options.specific = litterCodeSelection(null,null);
    $scope.$apply();

  $scope.onFail = function(message) {
      alert('Failed because: ' + message);
    }

 // var destinationType = navigator.camera.destinationType;
  $scope.takePicture = function(){
       navigator.camera.getPicture($scope.onPhotoDataSuccess, $scope.onFail, 
            { quality: 100,
            destinationType: navigator.camera.DestinationType.DATA_URL,
            correctOrientation: true,
            allowEdit: true, 
            targetWidth: 1280, 
            targetHeight: 1280, 
            cameraDirection: navigator.camera.Direction.BACK, 
            saveToPhotoAlbum: false 
       });
  }
}]);


t1mControllers.controller('t1mBeachCharacterizationCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
    
    var currentDataSheet = window.location.search.replace("?","");
    
    $scope.beachCharacterization = {};

    var savedBeachCharacterization =  window.localStorage.getItem(currentDataSheet);
    if(savedBeachCharacterization != null){
        $scope.beachCharacterization = angular.fromJson(savedBeachCharacterization);
    };

    $scope.options = {
        location: [
            {name: "Urban", value: "Urban"}, 
            {name: "Peri-urban", value: "Peri-urban"},
            {name: "Rural", value: "Rural"}
        ],
        majorUsage: [
            {name: "Swimming", value: "Swimming"}, 
            {name: "Sunbathing", value: "Sunbathing"},
            {name: "Fishing", value: "Fishing"},
            {name: "Surfing", value: "Surfing"},
            {name: "Boat Access", value: "Boat Access"},
            {name: "Remote", value: "Remote"}
        ],
        townDirection: [
            {name: "North", value: "North"},
            {name: "North-east", value: "North-east"},
            {name: "East", value: "East"},
            {name: "South-east", value: "South-east"},
            {name: "South", value: "South"},
            {name: "South-west", value: "South-west"},
            {name: "West", value: "West"},
            {name: "North-west", value: "North-west"},
        ],
        riverDirection: [
            {name: "North", value: "North"},
            {name: "North-east", value: "North-east"},
            {name: "East", value: "East"},
            {name: "South-east", value: "South-east"},
            {name: "South", value: "South"},
            {name: "South-west", value: "South-west"},
            {name: "West", value: "West"},
            {name: "North-west", value: "North-west"},
        ]
    };

    $scope.tabs = [
        {title: "Sampling Area", index: 0},
        {title: "Beach Char.", index: 1},
        {title: "Source Char.", index: 2 }
    ];

    $scope.beachCharacterization.Location = $scope.options.location[0].value;
    $scope.beachCharacterization.MajorUsage = $scope.options.majorUsage[0].value;
    $scope.beachCharacterization.NearestTownDirection = $scope.options.townDirection[0].value;
    $scope.beachCharacterization.NearestRiverDirection = $scope.options.riverDirection[0].value;

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
        window.localStorage.setItem(currentDataSheet, angular.toJson($scope.beachCharacterization, false));
    };
    
    $scope.retriveGPS = function(){
        
           var retriveGPSSuccess = function(position) {
               $scope.beachCharacterization.Latitude = position.coords.latitude;
               $scope.beachCharacterization.Longitude = position.coords.longitude;
               $scope.beachCharacterization.CoordSystem = "WGS84";
               $scope.$apply();       
           } 
           
           var retriveGPSError = function(error){               
           }
           
           var options = { enableHighAccuracy: true, maximumAge: 100 };
           navigator.geolocation.getCurrentPosition(retriveGPSSuccess, retriveGPSError, options);
    }
    

}]);
