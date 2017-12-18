telemaniacsApp.controller('upcomingTransmissionsController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.requireLogin();
        pageService.consumeMessages();
        pageService.setPageName('Upcoming favorite transmissions');

        var timeSpan = 60 * 60 * 24;

        pageService.getDataAsync('/user/' + pageService.getUser().id + '/upcoming-' + timeSpan).then(function (response) {
            $scope.upcomingTransmissions = response;
        });


    }
])