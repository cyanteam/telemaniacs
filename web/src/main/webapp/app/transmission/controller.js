telemaniacsApp.controller('TransmissionDetailsController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.setPageName('Show Details');
        pageService.getDataAsync('transmission/' + $routeParams.id).then(function (transmission) {
            $scope.transmission = transmission;
            console.log($scope.transmission);
        });
    }
]);

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
            {id: 'MOVIE', name: 'Movie'},
            {id: 'TV_SERIES', name: 'TV Series'},
            {id: 'TV_SHOW', name: 'TV Show'},
            {id: 'SPORT_EVENT', name: 'Sport'},
            {id: 'DOCUMENTARY', name: 'Documentary'}
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

telemaniacsApp.controller('TransmissionsListController', [
    '$scope',
    'PageService',
    function ($scope, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('Transmission Administration');

        pageService.getDataAsync('transmission').then(function (transmissions) {
            $scope.transmissions = transmissions;
        });

        $scope.delete = function (transmission) {
            var errorMessages = {
                'otherwise': 'Transmission cannot be deleted: {msg}'
            };

            pageService.sendDataAsync('transmission/' + transmission.id, 'DELETE', transmission, 'Transmission was deleted.',
                    'admin/transmissions/', errorMessages);

        };
    }
]);

telemaniacsApp.controller('TransmissionsFindController', [
    '$scope',
    'PageService',
    
    function ($scope, pageService) {
        console.log('TransmissionFindController');
        pageService.consumeMessages();
        pageService.setPageName('Find Show');
        
        $scope.channelTypes = [
            { id: 'MOVIE', name: 'Movie' },
            { id: 'DOCUMENTARY', name: 'Documentary' },
            { id: 'COMMERCE', name: 'Commerce' },
            { id: 'MUSIC', name: 'Music' },
            { id: 'SPORT', name: 'Sport' },
            { id: 'CHILDREN', name: 'Children' }
        ];
        
        $scope.findByName = function (name) {
            var errorMessages = {
                'otherwise': 'Transmission cannot be found: {msg}'
            };

            pageService.sendDataAsync('transmission/name/' + name, 'GET', name, 'Transmissions found.',
                    'shows/find', errorMessages);
        };
        
        $scope.findByType = function (type) {
            var errorMessages = {
                'otherwise': 'Transmission cannot be found: {msg}'
            };

            pageService.sendDataAsync('transmission/type/' + type, 'GET', type, 'Transmissions found.',
                    'shows/find', errorMessages);
        };
    }
]);