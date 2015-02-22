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
                
                
                //initialising radar.
                
                $scope.radar = {};
                $scope.radar.height;         
                $scope.radar.center;

                
                
                $scope.touches = [];
                $scope.x;
                $scope.y;
                $scope.addTouch = function(event, element){
                    
                    $scope.doRadarHeight();
                    $scope.getX(event);
                    $scope.getY(event);
                    $scope.addRadarModal();
                    
                        /*$scope.touches.push({
                            "x": $scope.x,
                            "y": $scope.y
                        });*/
                    
                };
                                                          
                $scope.doRadarHeight = function(){
                    $scope.radar.height = document.getElementById("radar").clientHeight;
                    $scope.radar.width = document.getElementById("radar").clientWidth;
                    if($scope.radar.height < $scope.radar.width){
                        $scope.radar.center = $scope.radar.height/2;
                    } else {
                        $scope.radar.center = $scope.radar.width/2;
                    }
                    
                }
                                                          
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
                          },100); 
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
                        var alarm = new Media("/android_asset/www/sounds/alarm.mp3");
                        alarm.play();
                        /*while(true){
                            if(alarm.getCurrentPosition()>0){
                                break;
                            }
                        }*///FIX MEEEEEE
                        $window.alert('Time\'s up!');
                        
                        alarm.stop();
                        $scope.timer.alerted = true;
                        if($scope.radarBirds.length == 0){
                            $window.confirm("There were no bird sightings at this station.");
                        }
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
                        {name: "Leaves still/move silently", 
                         value: "Leaves still/move silently"}, 
                        {name: "Leaves rustle", 
                         value: "Leaves rustle"},
                        {name: "Leaves and branches in constant motion", 
                        value: "Leaves and branches in constant motion"},
                        {name: "Branches or trees sway", 
                         value: "Branches or trees sway"}],
                    
                    othNoi: [
                        {name: "Not important", 
                         value: "Not important"}, 
                        {name: "Moderate", 
                         value: "Moderate"},
                        {name: "Loud",
                         value: "Loud"}],
                    
                    pos: [
                        {name: "Single", 
                         value: "Single"}, 
                        {name: "Averaged", 
                         value: "Averaged"}]
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
                
                $scope.editBirdSummary = function(bird){
                    var modalInstance = $modal.open({
                              templateUrl: 'modals/editSummaryModalContent.html',
                              controller: 'SummaryModalInstanceCtrl',
                              resolve:{
                                  bird: function(){
                                  return bird;
                                }
                              }
                            });
                    modalInstance.result.then(function (b) {
                            
                            
                            
                                for(var j=0; j<$scope.touches.length; j++){
                                    if(b.x==$scope.touches[j].x &&
                                       b.y==$scope.touches[j].y){
                                        $scope.touches.splice(j,1);
                                    }
                                }
                        
                                var index = $scope.radarBirds.indexOf(b);
                                    if(index > -1){
                                        $scope.radarBirds.splice(index,1);
                                }
                            
                            
                        });
                }
                
                $scope.radarBirds = [];
                
                $scope.addBirdModal = function(){
                    
                    var modalInstance = $modal.open({
                              templateUrl: 'modals/birdModalContent.html',
                              controller: 'birdModalInstanceCtrl',
                            });
                    modalInstance.result.then(function (bt) {
                            if(bt!=null){
                                for(var i = 0; i < $scope.birdCount.length; i++){
                                    if($scope.birdCount[i].bird==bt){
                                        return;
                                    }
                                }
                                //should be add to plot list or similar
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
                                  },
                                  bds: function(){
                                      return $scope.radarBirds;
                                  },
                                  ct: function(){
                                      var secs = $scope.timer.secs;
                                      var mins = $scope.timer.mins;
                                      var curTime = ""+mins+"."+secs;
                                      return curTime;
                                  },
                                  touches: function(){
                                      return $scope.touches;
                                  },
                                  x: function(){
                                      return $scope.x;
                                  },
                                  y: function(){
                                      return $scope.y;
                                  },
                                  radius: function(){
                                      return $scope.radar.center;
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
                                                          
                $scope.pretendSubmit = function(){
                    if($window.confirm('Are you sure you want to save?')){
                        $window.location="../surveys/birdSurvey.html";
                    }
                }
            }]);


t1mControllers.controller('skipModalInstanceCtrl', function ($scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close([$scope.reasonSkip,'false']);
    };

    $scope.cancel = function () {
        $modalInstance.close(['','true']);
    };
});

t1mControllers.controller('SummaryModalInstanceCtrl', function ($scope, $window, $modalInstance, bird) {
    
    $scope.bird = bird;
    $scope.b = {};
    $scope.b.b = $scope.bird.bird;
    $scope.b.c = $scope.bird.count;
    $scope.b.comm = $scope.bird.comment;
    
    
    $scope.ok = function () {
        $scope.bird.bird = $scope.b.b;
        $scope.bird.count = $scope.b.c;
        $scope.bird.comment = $scope.b.comm;
        $modalInstance.close();
    };
    
    $scope.delete = function(){
      if($window.confirm("Are you sure you want to delete?")){
          $modalInstance.close($scope.bird);
      }
    };

    $scope.cancel = function () {
        $modalInstance.close();
    };
});

t1mControllers.controller('radarModalInstanceCtrl', function ($scope, 
                                                               $modalInstance, 
                                                               birds, 
                                                               $modal, 
                                                               bds, 
                                                               ct, 
                                                               touches,
                                                               x,
                                                               y,
                                                               radius) {
    
    $scope.birds = birds;
    $scope.birdAbrev = {
        "Brown Creeper":"BC",
        "Blue Duck":"BD",
        "Bellbird":"BE",
        "Blackbird":"BL",
        "Chaffinch":"CH",
        "Californian Quail":"CQ",
        "Dunnock (Hedge Sparrow)":"DU",
        "Falcon (NZ)":"FA",
        "Fernbird":"FE",
        "Fantail":"FT",
        "Goldfinch":"GD",
        "Greenfinch":"GN",
        "Gray Warbler":"GW",
        "Harrier Hawk":"HH",
        "House Sparrow":"HS",
        "Kea":"KE",
        "Kingfisher (NZ)":"KF",
        "Kiwi (* specify)":"KI",
        "Kaka":"KK",
        "Long Tail Cuckoo":"LC",
        "Magpie (Australian)":"MG",
        "Morepork":"MP",
        "Indian Myna":"MY",
        "Oystercatcher (* specify)":"OC",
        "Paradise Shelduck":"PD",
        "Parakeet (* specify)":"PK",
        "Pukeko":"PU",
        "Robin":"RB",
        "Redpoll":"RD",
        "Rifleman":"RM",
        "Rock Pigeon (Feral)":"RP",
        "Eastern Rosella":"RS",
        "Rock Wren":"RW",
        "Shining Cuckoo":"SC",
        "Silvereye":"SE",
        "Spur Wing Plover":"SP",
        "Starling":"ST",
        "Song Thrush":"TH",
        "Tomtit":"TT",
        "Tui":"TU",
        "Whitehead":"WH",
        "Weka":"WK",
        "Wood Pigeon (Kereru)":"WP",
        "Welcome Swallow":"WS",
        "Yellowhead":"YH",
        "Yellow Hammer":"YL",
        "Unknown":"?"
    };
    
    $scope.addBird = function(){
        for(var i=0; i < $scope.birds.length; i++){
            if($scope.birds[i].bird==$scope.typedBird){
                return;
            }
        }
      birds.push({bird:$scope.typedBird,near:0,far:0,veryFar:0,notes:""});  
    };
    
    $scope.ok = function () {
        $modalInstance.close();
    };
      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    $scope.birdAbrev;
    $scope.setBirdAbrev
    
    $scope.addRadarbird = function(b){
        
        var xSq = Math.pow((x-radius),2);
        var ySq = Math.pow((y-radius),2);
        var distance = Math.sqrt((ySq+xSq));
        $scope.dis;
        if(distance < radius*0.4){
            $scope.dis="Near";
        } else if(distance < radius*0.8){
            $scope.dis="Far";
        } else {
            $scope.dis="Very Far";
        }
        
        $scope.birdName;
        if(b != null){
            $scope.birdName = b.bird;
        } else {
            $scope.birdName = "Unknown";
        }
        
        $scope.birdAb = $scope.birdAbrev[$scope.birdName];
        if($scope.birdAb == null){
            $scope.birdAb = $scope.birdName;
        }
        
        bds.push({time:ct,bird:$scope.birdName,dist:$scope.dis,count:1,comment:"","x":x, "y":y});
        touches.push({"x": x, "y": y,"bird":$scope.birdAb, col:"blue"});
        $scope.$apply;
        $scope.ok();
    }
    
    $scope.addRadarIncrModal = function(b){
                    $scope.ok();
        
                    var xSq = Math.pow((x-radius),2);
                        var ySq = Math.pow((y-radius),2);
                        var distance = Math.sqrt((ySq+xSq));
                        $scope.dis;
                        if(distance < radius*0.4){
                            $scope.dis="Near";
                        } else if(distance < radius*0.8){
                            $scope.dis="Far";
                        } else {
                            $scope.dis="Very Far";
                        }
            
        
                    if(b!=null){
                        $scope.birdNameDetails = b.bird;
                        $scope.birdAb = $scope.birdAbrev[b.bird];
                        if($scope.birdAb == null){
                            $scope.birdAb = b.bird;
                        }
                    } else {
                        $scope.birdAb = '?';
                        $scope.birdNameDetails = "Unknown";
                    }
                    
        
                    $scope.newBird = {time:ct,bird:$scope.birdNameDetails,dist:$scope.dis,count:1,comment:"","x":x, "y":y};
                    var modalInstance = $modal.open({
                              templateUrl: 'modals/radarIncrModalContent.html',
                              controller: 'radarIncrModalInstanceCtrl',
                              resolve:{
                                  bird: function(){
                                      return $scope.newBird;
                                  }
                              }
                            });
                    modalInstance.result.then(function () {

                        bds.push($scope.newBird);
                        var colour;
                        if($scope.newBird.count > 1){
                            colour = "saddlebrown";
                        }else {
                            colour = "blue";
                        }
                        
                        touches.push({"x": $scope.newBird.x, 
                                      "y": $scope.newBird.y,
                                      "bird":$scope.birdAb,
                                      "col":colour});
                        $scope.$apply;
                        $scope.ok();
                        
                        
                        
                        
                        
                        });
                    
                };
});

t1mControllers.controller('radarIncrModalInstanceCtrl', function ($scope, $modalInstance, bird) {
    
    $scope.bird = bird;
    
    $scope.countInc = function(v){
        if(v=='min'){
            if($scope.bird.count > 1){
                $scope.bird.count--;
            }
        }
        if(v=='plus'){
            $scope.bird.count++;
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
                
    //$scope.birdText = [];
                $scope.birdSpecies = [
                            {text:'Bellbird', greyed:false},
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
                  //$scope.birdText.push(birdType);
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
    
    $scope.meta = {};  
    
    if($scope.survey != null){ 
        $scope.meta = angular.fromJson(window.localStorage.getItem($scope.survey.meta));
    }
    
    $scope.meta.typ = "litter";
    
    if($scope.survey == null){
        $scope.survey = {meta:currentSurvey+"Meta", 
                         dataSheets:[{name:currentSurvey+'beachCharacterization', 
                                      type:'beachCharacterization'}], 
                         count:0};
        window.localStorage.setItem(currentSurvey, angular.toJson($scope.survey, false));
    }  
    
     $scope.tabs = [
        {title:"Survey Info", index: 0},
        {title:"Data Sheets", index: 1},
        {title:"Upload", index: 2}
    ];
    
    $scope.selectTab = function(index){
        window.mySwipe.slide(index, 500);
        window.localStorage.setItem($scope.survey.meta, angular.toJson($scope.meta, false));
    };
    
    
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
        sendSurveyRecordToServer(new RecordSvc, surveyStorageKey);
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
    
    $scope.litterBeach = {};
    
    var savedLitterBeach =  window.localStorage.getItem(currentDataSheet);
    if(savedLitterBeach != null){
        $scope.litterBeach = angular.fromJson(savedLitterBeach);
    } else {
        $scope.litterBeach.Season = $scope.options.seasons[0].value;  
        $scope.litterBeach.Instances = {
        Instances: [],
        InstanceCount : 0
        };
    };


    $scope.saveLitterBeach = function(){
        window.localStorage.setItem(currentDataSheet, angular.toJson($scope.litterBeach, false));
    };
    
  
    
    $scope.retrieveGPS = function(gpsField){
        
           var retrieveGPSSuccess = function(position) {
               $scope.litterBeach["Latitude"+gpsField] = position.coords.latitude;
               $scope.litterBeach["Longitude"+gpsField] = position.coords.longitude;
               $scope.litterBeach.CoordSystem = "WGS84";
               $scope.$apply();       
           } 
           
           var retrieveGPSError = function(error){
               
           }
           
           var options = { enableHighAccuracy: true, maximumAge: 100 };
           navigator.geolocation.getCurrentPosition(retrieveGPSSuccess, retrieveGPSError, options);
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
        var saveName = currentDataSheet + "Instance" + $scope.litterBeach.Instances.InstanceCount;
        var modalInstance = $modal.open({
            templateUrl: '../modals/beachLitterItem.html',
            controller: 'beachLitterItemCtrl',
            resolve:{saveName: function(){
                        return saveName;
                        }
                    }
        });
        modalInstance.result.then(function (data) {
            var invalidColour = {'background-color': 'green'};
            if(data.invalidLevel == 1 || data.invalidLevel == 2){ invalidColour = {'background-color': 'red'}}
            if(data.invalidLevel == 3){invalidColour = {'background-color': 'orange'}}
            var SmallLitter = {saveName: saveName, specific: data.specific, invalidLevel: data.invalidLevel,
                               colour: invalidColour};
            $scope.litterBeach.Instances.Instances.push(SmallLitter);
            $scope.litterBeach.Instances.InstanceCount++;
            $scope.saveLitterBeach();
        });

    }
     $scope.loadLitterModal = function(index){
         var SmallLitter = $scope.litterBeach.Instances.Instances[index];
        var modalInstance = $modal.open({
                templateUrl: '../modals/beachLitterItem.html',
                controller: 'beachLitterItemCtrl',
                resolve:{saveName: function(){
                            return SmallLitter.saveName;
                        }
                    }
                });
        modalInstance.result.then(function (data) {
                var invalidColour =  {'background-color': 'green'};
                if(data.invalidLevel == 1 || data.invalidLevel == 2){ invalidColour = {'background-color': 'red'}}
                if(data.invalidLevel == 3){invalidColour = {'background-color': 'orange'}}
                SmallLitter.colour = invalidColour;
                SmallLitter.specific = data.specific;
                SmallLitter.invalidLevel = data.invalidLevel;
                $scope.saveLitterBeach();
            });

    }
    
}]);

t1mControllers.controller('beachLitterItemCtrl', function($scope, $modalInstance, $modal, $q, saveName){
    
    $scope.ImageSrc = [];
    
    $scope.positiveButton = "Add";
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

    $scope.options.specific = litterCodeSelection(null,null);
    $scope.SmallLitter = angular.fromJson(window.localStorage.getItem(saveName));
    
   if($scope.SmallLitter==null){
       $scope.SmallLitter = {};
        $scope.SmallLitter.ImageSrc = [];
    } else {
        $scope.Specific = litterCodeSelectionByCode($scope.SmallLitter.LitterCode);
        $scope.Type = $scope.Specific.type;
        $scope.positiveButton = "Save";
    }            
    
    $scope.specificValue = function(){
        if($scope.Specific == null){ return " ";}
        var value = $scope.Specific.value;
        if(value == null){ return " ";}
        return value;
    }
       
    $scope.litterTypeSelected = function(){
        var codeOptions = litterCodeSelection($scope.Type, null);
        $scope.options.specific = codeOptions;
        $scope.Specific = null;
        $scope.SmallLitter.LitterCode = null;
    }
    
    $scope.litterSpecificSelected = function(){
        var codeOptions;
        if($scope.Specific == null){
            return;
        }
        codeOption = litterCodeSelection($scope.Specific.type, $scope.Specific.value);
        $scope.Type = codeOption.type;
        $scope.Specific = codeOption;
        $scope.SmallLitter.LitterCode = codeOption.code;
    }
    
     $scope.onPhotoDataSuccess = function(imageData) {
        var imageStoreName = saveName + "Image" + $scope.SmallLitter.ImageSrc.length;
        $scope.SmallLitter.ImageSrc.push(imageStoreName);
        $scope.ImageSrc.push(imageData);
        $scope.$apply();
        window.localStorage.setItem(imageStoreName, imageData);
    }
    
    $scope.onFail = function(message) {
      alert('Failed because: ' + message);
    }

    // var destinationType = navigator.camera.destinationType;
    $scope.takePicture = function(){
        if($scope.ImageSrc.length >= 3){ return;}
        
       navigator.camera.getPicture($scope.onPhotoDataSuccess, $scope.onFail, 
            { quality: 100,
            destinationType: navigator.camera.DestinationType.DATA_URL,
            correctOrientation: true,
            allowEdit: true, 
            targetWidth: 1000, 
            targetHeight: 1000, 
            cameraDirection: navigator.camera.Direction.BACK, 
            saveToPhotoAlbum: false 
       });
    }
  
    $scope.save = function () {
        if(!isValid()){ return;}
        $scope.saveAndClose(999);
    };
    
    $scope.saveAndClose = function(level){
        window.localStorage.setItem(saveName, angular.toJson($scope.SmallLitter, false));
        $modalInstance.close({specific: $scope.Specific.value, invalidLevel: level});
    }

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    
    $scope.invalid = {};
    
    var isValid = function(){
        $scope.invalid ={};
        var invalidFields = [];
        if($scope.Specific == null){
            invalidFields.push({level: 1, value:"Specific field must be entered"});
            $scope.invalid.specific = true;
            launchItemValidationModal(invalidFields);
            return false;
        }
        
         if($scope.Specific != null){
            if($scope.Specific.value.toLowerCase().indexOf("specify") > -1){
                if($scope.SmallLitter.Description == null || $scope.SmallLitter.Description == ""){
                    invalidFields.push({level: 1, value:"If specific field contains \"specify\", Description must not be empty"});
                    $scope.invalid.description = true;
                    launchItemValidationModal(invalidFields);
                    return false;   // return as it is a level 1 invalid field.
                }
            }
        }
        if($scope.SmallLitter.Count == null || $scope.SmallLitter.Count < 1){
            if($scope.SmallLitter.Weight == null || $scope.SmallLitter.Weight < 1){
                invalidFields.push({level: 2, value:"Either count or weight (or both) must be entered"});
                $scope.invalid.count = true;
                $scope.invalid.weight = true;
            }
        }
        
        if($scope.SmallLitter.Description == null || $scope.SmallLitter.Description == ""){
            invalidFields.push({level: 3, value:"A description should be entered"});
            $scope.invalid.description = true;
        }
       
        if(invalidFields.length > 0){
            launchItemValidationModal(invalidFields);
        return false;   
        } 
        
        return true;
    }
    
    function launchItemValidationModal(invalidFields){
        if(invalidFields.length == 0){
            return true;   
        }else{
            var modalInstance = $modal.open({
                templateUrl: '../modals/beachLitterItemValidation.html',
                controller: 'beachLitterItemValidCtrl',
                resolve:{invalidFields: function(){
                            return invalidFields;
                            },
                         positiveButton: function(){
                            return $scope.positiveButton;
                            },
                        }
            }); 
            modalInstance.result.then(function (level) {
                $scope.saveAndClose(level);
            });
        }
    }
    
    
    function asyncImageLoad(){
        var deferred = $q.defer(); 
        var imageSrc = [];
        for(var i = 0; i < $scope.SmallLitter.ImageSrc.length; i++){
            var imgSrc = window.localStorage.getItem($scope.SmallLitter.ImageSrc[i]);
            if(imgSrc != null){
            imageSrc.push(imgSrc);
            } else {
                deferred.reject("no data at image save location");
            }
        }
        deferred.resolve(imageSrc);
        return deferred.promise;
    }
    
    var promise = asyncImageLoad();
    promise.then(function(imgSrc){
        $scope.ImageSrc = imgSrc;
    }, function(errorMessage){
        console.log(errorMessage);
    });
    
  
});

t1mControllers.controller('beachLitterItemValidCtrl', function($scope, $modalInstance, invalidFields, positiveButton){
    if(invalidFields == null){
        return;
    }
    
    $scope.positiveButton = positiveButton;
    $scope.showPositiveButton = true;
    
    $scope.criticalFields = [];
    $scope.mandatoryFields = [];
    $scope.warningFields = [];
    
    $scope.criticalFieldsShow = false;
    $scope.mandatoryFieldsShow = false;
    $scope.warningFieldsShow = false;
    
    var level = 999;
    
    for(var i = 0; i < invalidFields.length; i++){
        var field = invalidFields[i];
        if(field.level < level){ level = field.level;}
        switch(field.level){
                case 1:
                    $scope.criticalFields.push(field.value);
                    break;
                case 2:
                    $scope.mandatoryFields.push(field.value);
                    break;
                case 3:
                    $scope.warningFields.push(field.value);
                    break;
        }
    }
    
    if($scope.criticalFields.length > 0){
        $scope.criticalFieldsShow = true;   
        $scope.showPositiveButton = false;
    }
    if($scope.mandatoryFields.length > 0){
        $scope.mandatoryFieldsShow = true;   
    }
     if($scope.warningFields.length > 0){
        $scope.warningFieldsShow = true;   
    }
    
    $scope.saveClicked = function(){
        $modalInstance.close(level);   
    }
    
    $scope.cancelClicked = function(){
        $modalInstance.dismiss('cancel');
    }
    
});


t1mControllers.controller('t1mBeachCharacterizationCtrl', [ '$scope', 'RecordSvc',function($scope, RecordSvc) {
    
    var currentDataSheet = window.location.search.replace("?","");
    
    $scope.beachCharacterization = {};

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
    
    var savedBeachCharacterization =  window.localStorage.getItem(currentDataSheet);
    if(savedBeachCharacterization != null){
        $scope.beachCharacterization = angular.fromJson(savedBeachCharacterization);
    } else {
        $scope.beachCharacterization.Location = $scope.options.location[0].value;
        $scope.beachCharacterization.MajorUsage = $scope.options.majorUsage[0].value;
        $scope.beachCharacterization.NearestTownDirection = $scope.options.townDirection[0].value;
        $scope.beachCharacterization.NearestRiverDirection = $scope.options.riverDirection[0].value;
    }

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
    
    $scope.saveClicked = function(){
        $scope.saveBeachCharacterization();  
        history.back();
    };

     $scope.saveBeachCharacterization = function(){
        window.localStorage.setItem(currentDataSheet, angular.toJson($scope.beachCharacterization, false));
    };
    
    $scope.retrieveGPS = function(){
        
           var retrieveGPSSuccess = function(position) {
               $scope.beachCharacterization.Latitude = position.coords.latitude;
               $scope.beachCharacterization.Longitude = position.coords.longitude;
               $scope.beachCharacterization.CoordSystem = "WGS84";
               $scope.$apply();       
           } 
           
           var retrieveGPSError = function(error){               
           }
           
           var options = { enableHighAccuracy: true, maximumAge: 100 };
           navigator.geolocation.getCurrentPosition(retrieveGPSSuccess, retrieveGPSError, options);
    }
    

}]);
