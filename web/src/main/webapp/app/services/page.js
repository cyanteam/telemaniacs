telemaniacsApp.factory('PageService', function() {
    var _title = '';
    var _pageName = '';
    var _useSchedulerLayout = false;

    return {
        getTitle: function () {
            var title = 'Telemaniacs (TM)';
            if (_title !== '') {
                title = _title + ' - ' + title;
            }

            return title;
        },

        setTitle: function (title) { _title = title; },

        getPageName: function () { return _pageName; },

        setPageName: function (pageName) { _pageName = pageName; },

        isSchedulerLayoutUsed: function () { return _useSchedulerLayout; },

        useSchedulerLayout: function () { _useSchedulerLayout = true; }
    };
});