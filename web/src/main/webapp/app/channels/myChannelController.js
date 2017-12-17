telemaniacsApp.controller('MyChannelController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('My Channel');

        pageService.getDataAsync('/channel/').then(function (response) {
            $scope.channels = response;
            console.log($scope.channels);
        });

        $scope.follow = function (channel) {
            console.log('Delete');
            var errorMessages = {
                'DataAccessException': 'Channel to follow does not exist!',
                'otherwise': 'Channel cannot be followed: {msg}'
            };

            pageService.sendDataAsync('channel/1/follow/' + channel.id, 'POST', null, 'Channel was added to favourite.',
                '/user-profile/channels', errorMessages);
        };
    }
]);