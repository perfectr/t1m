'use strict';

var myAppControllers = angular.module('myAppControllers', []);

myAppControllers.controller('MenuCtrl', ['$scope', '$location', 'LandingSvc', function($scope, $location, LandingSvc) {

    console.log('MenuCtrl - location.path = ' + $location.path());

    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };

    $scope.publicAccessURL = LandingSvc.publicAccessURL();
    $scope.secureLoginURL  = LandingSvc.secureLoginURL();
}]);

myAppControllers.controller('LandingCtrl', ['$scope', 'LandingSvc', function($scope, LandingSvc) {

    console.log('LandingCtrl - loaded.');
    $scope.publicAccessURL = LandingSvc.publicAccessURL();
    $scope.secureLoginURL  = LandingSvc.secureLoginURL();
}]);

myAppControllers.controller('PersonSearchCtrl', ['$scope', '$window', 'PersonSearchSvc', function($scope, $window, PersonSearchSvc) {

    $scope.personSearchSvc = PersonSearchSvc;

    $scope.pageChanged = function() {
        PersonSearchSvc.search();
    };

    $scope.searchAction = function() {
        PersonSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        PersonSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/person/edit/-1';
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('SurveySearchCtrl', ['$scope', '$window', 'SurveySearchSvc', function($scope, $window, SurveySearchSvc) {

    $scope.surveySearchSvc = SurveySearchSvc;

    $scope.pageChanged = function() {
        SurveySearchSvc.search();
    };

    $scope.searchAction = function() {
        SurveySearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        SurveySearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/survey/edit/-1';
    }

    $scope.displayOne = function(id) {
        $window.location.href = '#/survey/edit/'+id;
    }

    $scope.getType = function(type) {
        if (type.indexOf("itter")>-1) { return "Beach Litter Survey" }
        else if (type.indexOf("ird")>-1) { return "5 Minute Bird Survey" }
        else { return "Unrecognised Survey Type (" + type + ")" }
    }

    $scope.capFirst = function(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('BirdCountSearchCtrl', ['$scope', '$window', 'BirdCountSearchSvc', function($scope, $window, BirdCountSearchSvc) {

    $scope.birdCountSearchSvc = BirdCountSearchSvc;

    $scope.pageChanged = function() {
        BirdCountSearchSvc.search();
    };

    $scope.searchAction = function() {
        BirdCountSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        BirdCountSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/birdCount/edit/-1';
    }

    $scope.displayOne = function(id) {
        $window.location.href = '#/birdCount/edit/'+id;
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('BCBirdSearchCtrl', ['$scope', '$window', 'BCBirdSearchSvc', function($scope, $window, BCBirdSearchSvc) {

    $scope.bcBirdSearchSvc = BCBirdSearchSvc;

    $scope.pageChanged = function() {
        BCBirdSearchSvc.search();
    };

    $scope.searchAction = function() {
        BCBirdSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        BCBirdSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/bcBird/edit/-1';
    }

    $scope.displayOne = function(id) {
        $window.location.href = '#/bcBird/edit/'+id;
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('BirdDistanceSearchCtrl', ['$scope', '$window', 'BirdDistanceSearchSvc', function($scope, $window, BirdDistanceSearchSvc) {

    $scope.birdDistanceSearchSvc = BirdDistanceSearchSvc;

    $scope.pageChanged = function() {
        BirdDistanceSearchSvc.search();
    };

    $scope.searchAction = function() {
        BirdDistanceSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        BirdDistanceSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/birdDistance/edit/-1';
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('BDBirdSearchCtrl', ['$scope', '$window', 'BDBirdSearchSvc', function($scope, $window, BDBirdSearchSvc) {

    $scope.bdBirdSearchSvc = BDBirdSearchSvc;

    $scope.pageChanged = function() {
        BDBirdSearchSvc.search();
    };

    $scope.searchAction = function() {
        BDBirdSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        BDBirdSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/bdBird/edit/-1';
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('IncidentalBirdSearchCtrl', ['$scope', '$window', 'IncidentalBirdSearchSvc', function($scope, $window, IncidentalBirdSearchSvc) {

    $scope.incidentalBirdSearchSvc = IncidentalBirdSearchSvc;

    $scope.pageChanged = function() {
        IncidentalBirdSearchSvc.search();
    };

    $scope.searchAction = function() {
        IncidentalBirdSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        IncidentalBirdSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/incidentalBird/edit/-1';
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('LitterBeachSearchCtrl', ['$scope', '$window', 'LitterBeachSearchSvc', function($scope, $window, LitterBeachSearchSvc) {

    $scope.litterBeachSearchSvc = LitterBeachSearchSvc;

    $scope.pageChanged = function() {
        LitterBeachSearchSvc.search();
    };

    $scope.searchAction = function() {
        LitterBeachSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        LitterBeachSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/litterBeach/edit/-1';
    }

    $scope.displayOne = function(id) {
        $window.location.href = '#/litterBeach/edit/'+id;
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('LBItemSearchCtrl', ['$scope', '$window', 'LBItemSearchSvc', function($scope, $window, LBItemSearchSvc) {

    $scope.lbItemSearchSvc = LBItemSearchSvc;

    $scope.pageChanged = function() {
        LBItemSearchSvc.search();
    };

    $scope.searchAction = function() {
        LBItemSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        LBItemSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/lbItem/edit/-1';
    }

    $scope.displayOne = function(id) {
        $window.location.href = '#/lbItem/edit/'+id;
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('LitterLargeSearchCtrl', ['$scope', '$window', 'LitterLargeSearchSvc', function($scope, $window, LitterLargeSearchSvc) {

    $scope.litterLargeSearchSvc = LitterLargeSearchSvc;

    $scope.pageChanged = function() {
        LitterLargeSearchSvc.search();
    };

    $scope.searchAction = function() {
        LitterLargeSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        LitterLargeSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/litterLarge/edit/-1';
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('LLItemSearchCtrl', ['$scope', '$window', 'LLItemSearchSvc', function($scope, $window, LLItemSearchSvc) {

    $scope.llItemSearchSvc = LLItemSearchSvc;

    $scope.pageChanged = function() {
        LLItemSearchSvc.search();
    };

    $scope.searchAction = function() {
        LLItemSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        LLItemSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/llItem/edit/-1';
    }

    $scope.pageChanged();
}]);

myAppControllers.controller('BeachCharacterizationSearchCtrl', ['$scope', '$window', 'BeachCharacterizationSearchSvc', function($scope, $window, BeachCharacterizationSearchSvc) {

    $scope.beachCharacterizationSearchSvc = BeachCharacterizationSearchSvc;

    $scope.pageChanged = function() {
        BeachCharacterizationSearchSvc.search();
    };

    $scope.searchAction = function() {
        BeachCharacterizationSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        BeachCharacterizationSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/beachCharacterization/edit/-1';
    }

    $scope.displayOne = function(id) {
        $window.location.href = '#/beachChar/edit/'+id;
    }

    $scope.pageChanged();
}]);


myAppControllers.controller('ImageSearchCtrl', ['$scope', '$window', 'ImageSearchSvc', function($scope, $window, ImageSearchSvc) {

    $scope.imageSearchSvc = ImageSearchSvc;

    $scope.pageChanged = function() {
        ImageSearchSvc.search();
    };

    $scope.searchAction = function() {
        ImageSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        ImageSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.setImage = function(imageData) {
        var image = document.getElementById(imageData.imageId);
        image.style.display = 'block';
        image.src = "data:image/jpeg;base64," + imageData.imageString;
    }

    $scope.scaleImage = function(id) {
        var scale = 1;
        var height = $('#'+id).height();
        var width = $('#'+id).width();
        if (height > width) { scale = 500/height; }
        else { scale = 500/width; }
        if (scale < 1) {
            $('#'+id).height(height*scale);
            $('#'+id).width(width*scale);
        }
    }

    $scope.pageChanged();
}]);




myAppControllers.controller('PersonEditCtrl', ['$scope', '$routeParams', '$window', 'PersonSvc', function($scope, $routeParams, $window, PersonSvc) {

    // TODO: replace with proper security
    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;


    $scope.refresh = function() {
        var personId = $routeParams.personId;
        if(personId > 0) {
            $scope.selectedPerson = PersonSvc.get({personId: personId});
        }
        else {
            $scope.selectedPerson = new PersonSvc();
            $scope.editMode = true;
        }
    }

    $scope.editAction = function() {
        console.log("editAction() - clicked");
        $scope.editMode = true;
    }

    $scope.cancelAction = function() {
        console.log("cancelAction() - clicked");
        $scope.editMode = false;

        if($routeParams.personId > 0) {
            $scope.refresh();
        }
        else {
            $window.location.href = '#/person/search';
        }
    }

    $scope.saveAction = function() {
        $scope.selectedPerson.$save(function(data) {
            console.log("responseMessages = " + data.responseMessages);
            var hasNoErrors = data.responseMessages.length === 0;
            if(hasNoErrors) {
                $window.location.href = '#/person/edit/' + data.model.personId;
                $scope.editMode = false;
                $scope.refresh();
            }
        });
    }

    $scope.refresh();
}]);

myAppControllers.controller('SurveyEditCtrl', ['$scope', '$routeParams', '$window', 'SurveySvc', function($scope, $routeParams, $window, SurveySvc) {

    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;


    $scope.refresh = function() {
        var surveyId = $routeParams.surveyId;
        if(surveyId > 0) {
            $scope.selectedSurvey = SurveySvc.get({surveyId: surveyId});
        }
        else {
            $scope.selectedSurvey = new SurveySvc();
            $scope.editMode = true;
        }
    }

    $scope.editAction = function() {
        console.log("editAction() - clicked");
        $scope.editMode = true;
    }

    $scope.cancelAction = function() {
        console.log("cancelAction() - clicked");
        $scope.editMode = false;

        if($routeParams.surveyId > 0) {
            $scope.refresh();
        }
        else {
            $window.location.href = '#/survey/search';
        }
    }

    $scope.saveAction = function() {
        $scope.selectedSurvey.$save(function(data) {
            console.log("responseMessages = " + data.responseMessages);
            var hasNoErrors = data.responseMessages.length === 0;
            if(hasNoErrors) {
                $window.location.href = '#/survey/search';
                $scope.editMode = false;
                $scope.refresh();
            }
        });
    }

    $scope.goBack = function(){
        $window.location.href = '#/survey/search';
        $scope.refresh();
    }

    $scope.refresh();
}]);

myAppControllers.controller('BeachCharEditCtrl', ['$scope', '$routeParams', '$window', 'BeachCharacterizationSvc', function($scope, $routeParams, $window, BeachCharacterizationSvc) {

    // TODO: replace with proper security
    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;


    $scope.refresh = function() {
        var dataSheetId = $routeParams.dataSheetId;
        if(dataSheetId > 0) {
            $scope.selectedBeachCharacterization = BeachCharacterizationSvc.get({dataSheetId: dataSheetId});
        }
        else {
            $scope.selectedBeachCharacterization = new BeachCharacterizationSvc();
            $scope.editMode = true;
        }
    }

    $scope.editAction = function() {
        console.log("editAction() - clicked");
        $scope.editMode = true;
    }

    $scope.cancelAction = function() {
        console.log("cancelAction() - clicked");
        $scope.editMode = false;

        if($routeParams.dataSheetId > 0) {
            $scope.refresh();
        }
        else {
            $window.location.href = '#/beachChar/edit/'+$routeParams.dataSheetId;
        }
    }

    $scope.saveAction = function() {
        $scope.selectedBeachCharacterization.$save(function(data) {
            console.log("responseMessages = " + data.responseMessages);
            var hasNoErrors = data.responseMessages.length === 0;
            if(hasNoErrors) {
                $window.location.href = '#/beachChar/edit/'+$routeParams.dataSheetId;
                $scope.editMode = false;
                $scope.refresh();
            }
        });
    }

    $scope.goBack = function(){
        $window.location.href = '#/beachCharacterization/search';
        $scope.refresh();
    }

    $scope.goToSurvey = function(id) {
        $window.location.href = '#/survey/edit/'+id;
    }

    $scope.refresh();
}]);

myAppControllers.controller('BirdCountEditCtrl', ['$scope', '$routeParams', '$window', 'BirdCountSvc', function($scope, $routeParams, $window, BirdCountSvc) {

    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;

    $scope.refresh = function() {
        var dataSheetId = $routeParams.dataSheetId;
        if(dataSheetId > 0) {
            $scope.selectedBirdCount = BirdCountSvc.get({dataSheetId: dataSheetId});
        }
    }

    $scope.goBack = function(){
        $window.location.href = '#/birdCount/search';
        $scope.refresh();
    }

    $scope.goToSurvey = function(id) {
        $window.location.href = '#/survey/edit/'+id;
    }

    $scope.refresh();
}]);

myAppControllers.controller('BCBirdEditCtrl', ['$scope', '$routeParams', '$window', 'BCBirdSvc', function($scope, $routeParams, $window, BCBirdSvc) {

    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;

    $scope.refresh = function() {
        var instanceId = $routeParams.instanceId;
        if(instanceId > 0) {
            $scope.selectedBCBird = BCBirdSvc.get({instanceId: instanceId});
        }
    }

    $scope.goBack = function(){
        $window.location.href = '#/bcBird/search';
        $scope.refresh();
    }

    $scope.goToDataSheet = function(id) {
        $window.location.href = '#/birdCount/edit/'+id;
    }

    $scope.refresh();
}]);

myAppControllers.controller('LitterBeachEditCtrl', ['$scope', '$routeParams', '$window', 'LitterBeachSvc', function($scope, $routeParams, $window, LitterBeachSvc) {

    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;

    $scope.refresh = function() {
        var dataSheetId = $routeParams.dataSheetId;
        if(dataSheetId > 0) {
            $scope.selectedLitterBeach = LitterBeachSvc.get({dataSheetId: dataSheetId});
        }
    }

    $scope.goBack = function(){
        $window.location.href = '#/litterBeach/search';
        $scope.refresh();
    }

    $scope.goToSurvey = function(id) {
        $window.location.href = '#/survey/edit/'+id;
    }

    $scope.refresh();
}]);

myAppControllers.controller('LBItemEditCtrl', ['$scope', '$routeParams', '$window', 'LBItemSvc', function($scope, $routeParams, $window, LBItemSvc) {

    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;

    $scope.refresh = function() {
        var instanceId = $routeParams.instanceId;
        if(instanceId > 0) {
            $scope.selectedLBItem = LBItemSvc.get({instanceId: instanceId});
        }
    }

    $scope.goBack = function(){
        $window.location.href = '#/lbItem/search';
        $scope.refresh();
    }

    $scope.goToDataSheet = function(id) {
        $window.location.href = '#/litterBeach/edit/'+id;
    }

    $scope.refresh();
}]);