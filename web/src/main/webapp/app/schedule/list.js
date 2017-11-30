telemaniacsApp.controller('ScheduleListController', [
    '$scope',
    'PageService',

    function ($scope, pageService) {
        pageService.setPageName('TV Schedule');
        pageService.useSchedulerLayout();
    }
]);