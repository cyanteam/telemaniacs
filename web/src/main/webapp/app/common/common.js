telemaniacsApp.controller('CommonController', [
    '$scope',
    '$timeout',
    'PageService',

    function ($scope, $timeout, pageService) {
        $scope.pageService = pageService;
        $scope.$on('$viewContentLoaded', function () {
            $timeout(function () {
                telemaniacs.boot();
            });
        });
    }
]);

telemaniacsApp.component('tmHeader', {
    templateUrl: 'app/common/header.html',
    bindings: {
        pageName: '<'
    }
});