telemaniacsApp.controller('TransmissionsCreateController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('Transmission Administration');

        $scope.transmissionTypes = [
            { id: 'MOVIE', name: 'Movie' },
            { id: 'TV_SERIES', name: 'TV Series' },
            { id: 'TV_SHOW', name: 'TV Show' },
            { id: 'SPORT_EVENT', name: 'Sport' },
            { id: 'DOCUMENTARY', name: 'Documentary' }
        ];

        $scope.transmission = {
            'id': null,
            'name': '',
            'description': '',
            'length': '',
            'language': 'EN',
            'transmissionType': 'MOVIE'
        };

        if (pageService.isEditing($route)) {
            pageService.getDataAsync('transmission/' + $routeParams.id).then(function (transmission) {
                if (transmission == null) {
                    $location.path('admin/transmission/create/');
                    return;
                }

                $scope.transmission = transmission;
            });
        }

        $scope.save = function (transmission) {
            var errorMessages = {
                'TvManagerDataAccessException': 'Transmission with the same name already exists.',
                'JpaSystemException': 'Transmission with the same name already exists.',
                'ValidationException': 'Invalid transmission state.',
                'otherwise': 'Transmission cannot be created: {msg}'
            };

            if (!pageService.isEditing($route)) {
                pageService.sendDataAsync('transmission/add/', 'POST', transmission, 'Transmission was added.',
                    'admin/transmissions/', errorMessages);
            } else {
                pageService.sendDataAsync('transmission/' + transmission.id, 'PUT', transmission, 'Transmission was updated.',
                    'admin/transmissions/', errorMessages);
            }
        };
    }
]);