telemaniacsApp.controller('CommonController', [
    '$scope',
    '$timeout',
    'PageService',

    function ($scope, $timeout, pageService) {
        $scope.pageService = pageService;
        $scope.$on('$viewContentLoaded', function () {
            $timeout(function () {
                telemaniacs.boot();
            }, 30);
        });
    }
]);

telemaniacsApp.component('tmHeader', {
    templateUrl: 'app/common/header.html',
    bindings: {
        pageService: '<'
    }
});

telemaniacsApp.directive('tmAlerts', function () {
    return {
        templateUrl: 'app/common/alerts.html'
    };
});