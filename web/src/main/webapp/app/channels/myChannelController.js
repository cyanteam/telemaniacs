telemaniacsApp.controller('MyChannelController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.requireLogin();
        pageService.consumeMessages();
        pageService.setPageName('Followed Channels');

        pageService.getDataAsync('/user/' + pageService.getUser().id + '/channels').then(function (response) {
            var followed = [];
            if (response !== null) {
                for (var i = 0; i < response.length; i++) {
                    followed.push(response[i].id);
                }
            }
            $scope.followed = followed;
        });

        pageService.getDataAsync('/channel/').then(function (response) {
            $scope.channels = response;
            console.log($scope.channels);
        });

        $scope.follow = function (channel) {
            console.log('Follow');
            var errorMessages = {
                'DataAccessException': 'Channel to follow does not exist!',
                'otherwise': 'Channel cannot be followed: {msg}'
            };

            pageService.sendDataAsync('channel/' + pageService.getUser().id + '/follow/' + channel.id, 'POST', null, 'Channel was added to favourite.',
                '/user-profile/channels', errorMessages);
        };

        $scope.unfollow = function (channel) {
            var errorMessages = {
                'DataAccessException': 'Channel to follow does not exist!',
                'otherwise': 'Channel cannot be followed: {msg}'
            };

            pageService.sendDataAsync('channel/' + pageService.getUser().id + '/unfollow/' + channel.id, 'POST', null, 'Channel is unfollowed.',
                '/user-profile/channels', errorMessages);
        };
    }
]);