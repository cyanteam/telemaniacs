/**
 * Created by tinko on 12/17/2017.
 */
telemaniacsApp.controller('MyTransmissionController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.requireLogin();
        pageService.consumeMessages();
        pageService.setPageName('Followed Shows');

        pageService.getDataAsync('/user/' + pageService.getUser().id + '/transmissions').then(function (response) {
            $scope.transmissions = response;
            console.log($scope.transmissions);
        });

        $scope.unfollow = function (transmission) {
            console.log('Unfollow');
            var errorMessages = {
                'DataAccessException': 'Transmission to unfollow does not exist!',
                'otherwise': 'Transmission cannot be unfollowed: {msg}'
            };

            pageService.sendDataAsync('transmission/'  + transmission.id + '/unfollow/' + pageService.getUser().id, 'POST', null, 'Transmission was removed from favourites.',
                '/user-profile/shows', errorMessages);
        };
    }
]);