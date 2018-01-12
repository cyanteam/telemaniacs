telemaniacsApp.controller('ChannelsCreateController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('Channel Administration');

        $scope.channelTypes = [
            { id: 'MOVIE', name: 'Movie' },
            { id: 'DOCUMENTARY', name: 'Documentary' },
            { id: 'COMMERCE', name: 'Commerce' },
            { id: 'MUSIC', name: 'Music' },
            { id: 'SPORT', name: 'Sport' },
            { id: 'CHILDREN', name: 'Children' }
        ];

        $scope.channel = {
            'id': null,
            'name': '',
            'channelType': 'MOVIE',
            'language': 'EN'
        };

        if (pageService.isEditing($route)) {
            pageService.getDataAsync('channel/' + $routeParams.id).then(function (channel) {
                if (channel == null) {
                    $location.path('admin/channels/create/');
                    return;
                }

                $scope.channel = channel;
            });
        }

        $scope.save = function (channel) {
            var errorMessages = {
                'TvManagerDataAccessException': 'Channel with the same name already exists.',
                'JpaSystemException': 'Channel with the same name already exists.',
                'ValidationException': 'Invalid channel state.',
                'otherwise': 'Category channot be created: {msg}'
            };

            if (!pageService.isEditing($route)) {
                pageService.sendDataAsync('channel/add/', 'POST', channel, 'Channel was added.',
                    'admin/channels/', errorMessages);
            } else {
                pageService.sendDataAsync('channel/' + channel.id, 'PUT', channel, 'Channel was updated.',
                    'admin/channels/', errorMessages);
            }
        };
    }
]);