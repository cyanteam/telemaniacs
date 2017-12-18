telemaniacsApp.controller('LoginController', [
    '$scope',
    '$rootScope',
    'PageService',

    function ($scope, $rootScope, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('Log In');

        $scope.userAuthenticate = {
            email: '',
            password: ''
        };
        $scope.login = function (userAuthenticate) {
            pageService.login(userAuthenticate);
        };
    },

]);

telemaniacsApp.controller('LogoutController', [
    '$location',
    'PageService',

    function ($location, pageService) {
        pageService.consumeMessages()
        pageService.logout();

        $location.path('/');
    }

]);

telemaniacsApp.controller('RegistrationController', [
    '$scope',
    'PageService',
    function ($scope, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('User registration');

        $scope.genderTypes = [
            {id: 'MALE', name: 'Male'},
            {id: 'FEMALE', name: 'Female'}
        ];

        $scope.userCreate = {
            'id': null,
            'username': '',
            'encryptedPassword': '',
            'email': '',
            'age': undefined,
            'gender': 'MALE',
            'isAdmin': false
        };

        $scope.register = function (userCreate) {
            var errorMessages = {
                'TvManagerDataAccessException': 'User with the same name already exists.',
                'JpaSystemException': 'User with the same name already exists.',
                'ValidationException': 'Invalid user state.',
                'otherwise': 'User cannot be created: {msg}'
            };

            pageService.sendDataAsync('user/add', 'POST', userCreate, 'User was created', 'login', errorMessages);
        }
    }
]);