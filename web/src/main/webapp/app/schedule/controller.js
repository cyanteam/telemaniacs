telemaniacsApp.controller('ScheduleListController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    '$filter',
    'PageService',

    function ($scope, $route, $routeParams, $location, $filter, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('TV Schedule');

        var channelsView = 'all';
        if (pageService.isLoggedIn()) {
            if ($location.search()['channels'] === 'all') {
                channelsView = 'all';
            } else {
                channelsView = 'my';
            }
        }
        $scope.channelsView = channelsView;

        var offsetView = 0;
        var offsetQsa = parseInt($location.search()['offset']);
        if (!isNaN(offsetQsa)) {
            offsetView = offsetQsa;
        }
        $scope.offsetView = offsetView;


        pageService.getDataAsync('/schedule/offsetDate/' + offsetView).then(function (response) {
            $scope.currentDate = response['dateTime'];
        });

        var currentDate = Date.now();
        pageService.getDataAsync('/schedule/currentDate').then(function (response) {
            currentDate = Date.parse(response['dateTime']);
        });

        if (pageService.isLoggedIn()) {
            // Followed channels
            pageService.getDataAsync('/user/' + pageService.getUser().id + '/channels').then(function (response) {
                var followed = [];
                if (response !== null) {
                    for (var i = 0; i < response.length; i++) {
                        followed.push(response[i].id);
                    }
                }
                $scope.followedChannels = followed;
            });

            // Followed transmissions
            pageService.getDataAsync('/user/' + pageService.getUser().id + '/transmissions').then(function (response) {
                var followed = [];
                if (response !== null) {
                    for (var i = 0; i < response.length; i++) {
                        followed.push(response[i].id);
                    }
                }
                $scope.followedTransmissions = followed;
            });
        }

        var restUrl = 'schedule/';
        if (channelsView === 'my') {
            restUrl += '/user/' + pageService.getUser().id;
        }
        restUrl += '/date/' + offsetView;

        pageService.getDataAsync(restUrl).then(function (response) {
            $scope.telemaniacs = telemaniacs;
            $scope.timeline = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];

            // Group by time and current transmissions
            var schedules = response['channelSchedules'];
            var running = {};
            var progress = {};
            if (schedules.length > 0) {
                for (var i = 0; i < schedules.length; i++) {
                    var channelSchedule = schedules[i];

                    schedules[i].byTime = [];
                    for (var t = 0; t < $scope.timeline.length; t++) {
                        schedules[i].byTime[t] = [];
                    }

                    running[channelSchedule.channelId] = null;
                    progress[channelSchedule.channelId] = 0;

                    if (channelSchedule.transmissionOccurrences.length > 0) {
                        for (var j = 0; j < channelSchedule.transmissionOccurrences.length; j++) {
                            var occurrence = channelSchedule.transmissionOccurrences[j];
                            var hour = $filter('date')(occurrence.startDate, 'H');
                            schedules[i].byTime[hour].push(occurrence);

                            // Current transmission
                            var startTime = Date.parse(occurrence.startDate);
                            var endTime = startTime + occurrence.transmission.length * 60000;
                            var latestStartTime = -1;
                            if (running[channelSchedule.channelId] !== null) {
                                latestStartTime = Date.parse(running[channelSchedule.channelId].startTime);
                                if (isNaN(latestStartTime)) {
                                    latestStartTime = -1;
                                }
                            }

                            if (currentDate >= startTime && currentDate < endTime && latestStartTime < startTime) {
                                running[channelSchedule.channelId] = occurrence;
                                progress[channelSchedule.channelId] = Math.round((currentDate - startTime) / (endTime - startTime) * 100);
                            }
                        }
                    }
                }
            }

            $scope.schedules = schedules;
            $scope.running = running;
            $scope.progress = progress;
            console.log(progress);
        });


        $scope.followChannel = function (channelId) {
            var errorMessages = {
                'DataAccessException': 'Channel to follow does not exist!',
                'otherwise': 'Channel cannot be followed: {msg}'
            };

            pageService.sendDataAsync('channel/' + pageService.getUser().id + '/follow/' + channelId, 'POST', null, null,
                '/schedule', errorMessages, { 'channels': channelsView, 'offset': offsetView, 'generation': Date.now() });
        };

        $scope.unfollowChannel = function (channelId) {
            var errorMessages = {
                'DataAccessException': 'Channel to unfollow does not exist!',
                'otherwise': 'Channel cannot be unfollowed: {msg}'
            };

            pageService.sendDataAsync('channel/' + pageService.getUser().id + '/unfollow/' + channelId, 'POST', null, null,
                '/schedule', errorMessages, { 'channels': channelsView, 'offset': offsetView, 'generation': Date.now() });
        };

        $scope.followTransmission = function (transmissionId) {
            var errorMessages = {
                'DataAccessException': 'Transmission to follow does not exist!',
                'otherwise': 'Transmission cannot be followed: {msg}'
            };

            pageService.sendDataAsync('transmission/' + transmissionId + '/follow/' + pageService.getUser().id, 'POST', null, null,
                '/schedule', errorMessages, { 'channels': channelsView, 'offset': offsetView, 'generation': Date.now() });
        };

        $scope.unfollowTransmission = function (transmissionId) {
            var errorMessages = {
                'DataAccessException': 'Transmission to unfollow does not exist!',
                'otherwise': 'Transmission cannot be unfollowed: {msg}'
            };

            pageService.sendDataAsync('transmission/' + transmissionId + '/unfollow/' + pageService.getUser().id, 'POST', null, null,
                '/schedule', errorMessages, { 'channels': channelsView, 'offset': offsetView, 'generation': Date.now() });
        };
    }
]);