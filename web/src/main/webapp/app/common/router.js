telemaniacsApp.config(function($routeProvider) {
    $routeProvider
        .when('/schedule', {
            controller: 'ScheduleListController',
            templateUrl: 'app/schedule/list.html'
        })

        .when('/admin/channels/create', {
            controller: 'ChannelsCreateController',
            templateUrl: 'app/channels/edit.html'
        })

        .when('/admin/channels/edit/:id', {
            controller: 'ChannelsCreateController',
            templateUrl: 'app/channels/edit.html',
            edit: true
        })

        .when('/admin/transmissions/create', {
            controller: 'TransmissionsCreateController',
            templateUrl: 'app/transmission/edit.html'
        })

        .when('/admin/transmissions/edit/:id', {
            controller: 'TransmissionsCreateController',
            templateUrl: 'app/transmission/edit.html',
            edit: true
        })

        .otherwise({ redirectTo: '/schedule' });
});