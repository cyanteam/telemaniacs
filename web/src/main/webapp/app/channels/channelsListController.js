telemaniacsApp.controller('ChannelsListController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.requireAdmin();
        pageService.consumeMessages();
        pageService.setPageName('Channel Administration');

        pageService.getDataAsync('/channel/').then(function (response) {
            $scope.channels = response;
            console.log($scope.channels);
        });
        
        $scope.delete = function (channel) {
            console.log('Delete');
            var errorMessages = {
                'DataAccessException': 'Channel for deletion does not exist!',
                'otherwise': 'Channel cannot be deleted: {msg}'
            };
            
            pageService.sendDataAsync('channel/' + channel.id, 'DELETE', channel, 'Channel was deleted.',
                    'admin/channels/', errorMessages);
        };
    }
]);