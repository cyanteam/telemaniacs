telemaniacsApp.config(function($routeProvider) {
    $routeProvider
        .when('/schedule', {
            controller: 'ScheduleListController',
            templateUrl: 'app/schedule/list.html'
        })

        .when('/details/:id', {
            controller: 'TransmissionDetailsController',
            templateUrl: 'app/transmission/detail.html'
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
        
        .when('/admin/channels', {
            controller: 'ChannelsListController',
            templateUrl: 'app/channels/list.html'
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

        .when('/admin/transmissions', {
            controller: 'TransmissionsListController',
            templateUrl: 'app/transmission/list.html'
        })

        .when('/admin/occurrences/:id', {
            controller: 'TransmissionOccurrencesListController',
            templateUrl: 'app/transmissionOccurrence/list.html'
        })
        
        .when('/shows/find', {
            controller: 'TransmissionsFindController',
            templateUrl: 'app/transmission/find.html'
        })

        .when('/user-profile/channels', {
            controller: 'MyChannelController',
            templateUrl: 'app/channels/myChannelList.html'
        })

        .otherwise({ redirectTo: '/schedule' });
});