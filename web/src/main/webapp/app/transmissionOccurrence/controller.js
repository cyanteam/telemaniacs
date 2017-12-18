telemaniacsApp.controller('TransmissionOccurrencesListController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.requireAdmin();
        pageService.consumeMessages();
        pageService.setPageName('Occurrence Administration');

        pageService.getDataAsync('/transmission/'+ $routeParams.id).then(function (response) {
            $scope.transmission = response;
            $scope.occurrences = response.occurrences;
            console.log($scope.occurrences);
        });

        $scope.delete = function (occurrence, transmissionId) {
            console.log('Delete');
            console.log(occurrence);
            var errorMessages = {
                'DataAccessException': 'Occurrence for deletion does not exist!',
                'otherwise': 'Occurrence cannot be deleted: {msg}'
            };

            pageService.sendDataAsync('transmission/occurrence/' + occurrence.id, 'DELETE', null, 'Occurrence was deleted.',
                'admin/occurrences/' + transmissionId, errorMessages, { 'generation': Date.now() });
        };

    }
]);


telemaniacsApp.controller('TransmissionOccurrencesCreateController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    '$filter',
    'PageService',

    function ($scope, $route, $routeParams, $location, $filter, pageService) {
        pageService.requireAdmin();
        pageService.consumeMessages();
        pageService.setPageName('Occurrence Administration');

        $scope.transmission = {
            'id': null,
            'name': '',
            'description': '',
            'length': '',
            'language': 'EN',
            'transmissionType': 'MOVIE'
        };

        $scope.transmissionOccurrence = {
            'id': null,
            'channelId': 1,
            'transmissionId': '',
            'partName': '',
            'startDate': '',
            'isRerun': ''
        };

        pageService.getDataAsync('channel/').then(function (response) {
            $scope.channels = response;
        });

        if (pageService.isEditing($route)) {
            pageService.getDataAsync('transmission/occurrence/' + $routeParams.id).then(function (response) {
                if (response == null || response === '') {
                    $location.path('admin/transmissions');
                    return;
                }
                $scope.transmissionOccurrence = response;

                var startDate = new Date(Date.parse(response.startDate));
                $scope.transmissionOccurrence.startDate = $filter('date')(startDate, 'yyyy-MM-dd HH:mm');
                $scope.transmissionOccurrence.isRerun = response.rerun;

                pageService.getDataAsync('transmission/' + response.transmissionId).then(function (transmission) {
                    $scope.transmission = transmission;
                    $scope.transmissionOccurrence.transmissionId = $scope.transmission.id;
                });
            });
        } else {
            pageService.getDataAsync('transmission/' + $routeParams.transmissionId).then(function (response) {
                if (response == null || response === '') {
                    $location.path('admin/transmissions');
                    return;
                }

                $scope.transmission = response;
                $scope.transmissionOccurrence.transmissionId = $scope.transmission.id;
            });
        }

        $scope.save = function (transmissionOccurrence) {
            var errorMessages = {
                'TvManagerDataAccessException': 'Transmission occurrence with the same name already exists.',
                'JpaSystemException': 'Transmission occurrence with the same name already exists.',
                'ValidationException': 'Invalid transmission occurrence state.',
                'otherwise': 'Category cannot be created: {msg}'
            };

            var startDate = new Date(Date.parse(transmissionOccurrence.startDate));
            transmissionOccurrence.startDate = startDate.toISOString();

            if (!pageService.isEditing($route)) {
                pageService.sendDataAsync('transmission/occurrence/add/', 'POST', transmissionOccurrence, 'Transmission occurrence was added.',
                    'admin/occurrences/' + transmissionOccurrence.transmissionId, errorMessages);
            } else {
                console.log(transmissionOccurrence);

                pageService.sendDataAsync('transmission/occurrence/' + transmissionOccurrence.id, 'PUT', transmissionOccurrence,
                    'Transmission occurrence was updated.', 'admin/occurrences/' + transmissionOccurrence.transmissionId, errorMessages);
            }
        };
    }
]);
