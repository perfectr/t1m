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

myAppControllers.controller('EventSearchCtrl', ['$scope', '$window', 'EventSearchSvc', function($scope, $window, EventSearchSvc) {

    $scope.eventSearchSvc = EventSearchSvc;

    $scope.pageChanged = function() {
        EventSearchSvc.search();
    };

    $scope.searchAction = function() {
        // user clicking the search button always resets the pageNumber
        EventSearchSvc.searchCriteria.pageNumber = 1;
        $scope.pageChanged();
    }

    $scope.resetAction = function() {
        EventSearchSvc.reset();
        $scope.searchAction();
    }

    $scope.newAction = function() {
        $window.location.href = '#/event/edit/-1';
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

myAppControllers.controller('EventEditCtrl', ['$scope', '$routeParams', '$window', 'EventSvc', function($scope, $routeParams, $window, EventSvc) {

    // TODO: replace with proper security
    $scope.currentUser = {};
    $scope.currentUser.administrator = true;

    $scope.editMode = false;


    $scope.refresh = function() {
        var eventId = $routeParams.eventId;
        if(eventId > 0) {
            $scope.selectedEvent = EventSvc.get({eventId: eventId});
        }
        else {
            $scope.selectedEvent = new EventSvc();
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

        if($routeParams.eventId > 0) {
            // if not new reload the selected report to get rid of any edits
            $scope.refresh();
        }
        else {
            $window.location.href = '#/event/search';
        }
    }

    $scope.saveAction = function() {
        $scope.selectedEvent.$save(function(data) {
            console.log("responseMessages = " + data.responseMessages);
            var hasNoErrors = data.responseMessages.length === 0;
            if(hasNoErrors) {
                // redirect the browser with the save report's reportId so that new reports get switched to the right url
                $window.location.href = '#/event/search';
                $scope.editMode = false;
                $scope.refresh();
            }
        });
    }

    $scope.refresh();
}]);
