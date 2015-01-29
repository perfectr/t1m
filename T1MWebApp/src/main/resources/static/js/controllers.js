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


myAppControllers.controller('ContactCtrl', ['$scope', function($scope) {

}]);

myAppControllers.controller('PersonSearchCtrl', ['$scope', '$window', 'PersonSearchSvc', function($scope, $window, PersonSearchSvc) {

    $scope.personSearchSvc = PersonSearchSvc;

    $scope.pageChanged = function() {
        PersonSearchSvc.search();
    };

    $scope.searchAction = function() {
        // user clicking the search button always resets the pageNumber
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
            // if not new reload the selected report to get rid of any edits
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
                // redirect the browser with the save report's reportId so that new reports get switched to the right url
                $window.location.href = '#/person/edit/' + data.model.personId;
                $scope.editMode = false;
                $scope.refresh();
            }
        });
    }

    $scope.refresh();
}]);

myAppControllers.controller('SurveySearchCtrl', ['$scope', '$window', 'SurveySearchSvc', function($scope, $window, SurveySearchSvc) {

    $scope.surveySearchSvc = SurveySearchSvc;

    $scope.pageChanged = function() {
        SurveySearchSvc.search();
    };

    $scope.searchAction = function() {
        // user clicking the search button always resets the pageNumber
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

    $scope.pageChanged();
}]);

myAppControllers.controller('SurveyEditCtrl', ['$scope', '$routeParams', '$window', 'SurveySvc', function($scope, $routeParams, $window, SurveySvc) {

    // TODO: replace with proper security
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
            // if not new reload the selected report to get rid of any edits
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
                // redirect the browser with the save report's reportId so that new reports get switched to the right url
                $window.location.href = '#/survey/search';
                $scope.editMode = false;
                $scope.refresh();
            }
        });
    }

    $scope.refresh();
}]);

myAppControllers.controller('BirdCountSearchCtrl', ['$scope', '$window', 'BirdCountSearchSvc', function($scope, $window, BirdCountSearchSvc) {

    $scope.birdCountSearchSvc = BirdCountSearchSvc;

    $scope.pageChanged = function() {
        BirdCountSearchSvc.search();
    };

    $scope.searchAction = function() {
        // user clicking the search button always resets the pageNumber
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

    $scope.pageChanged();
}]);