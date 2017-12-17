telemaniacsApp.controller('LoginController', [
    '$scope',
    '$rootScope',
    'PageService',

    function ($scope, $rootScope, pageService) {
        pageService.consumeMessages()

        $scope.userAuthenticate = {
            email: '',
            password: ''
        };
        $scope.login = function (userAuthenticate) {
            pageService.login(userAuthenticate);
        };
    },

])

telemaniacsApp.controller('LogoutController', [
    'PageService',
    '$location',

    function (pageService, $location) {
        pageService.consumeMessages()

        pageService.logout();
    }

])