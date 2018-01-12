var telemaniacs = {
    currentRank: 0,

    getRank: function () {
        return this.currentRank;
    },

    /**
     * AngularJS application
     */
    app: function () {
        return angular
            .module('telemaniacsApp', [ 'ngRoute', 'ngCookies' ])
            .run(["$rootScope", "PageService", function ($rootScope, pageService) {
                $rootScope.$on("$routeChangeStart", function (evt, to, from) {
                    if (to.$$route.originalPath.startsWith("/admin")) {
                        pageService.requireAdmin();
                    }
                });
            }]);
    },

    /**
     * Adjusts containers top padding after resizing
     */
    adjustContent: function() {
        $('body').attr('id', '');
        if ($('main').attr('id') === 'schedule') {
            $('body').attr('id', 'schedule-layout');
        }

        this.resizeSchedule();
        if ($('#schedule-layout').find('main').length !== 0) {
            this.resizeSchedule();
            $(window).resize(this.resizeSchedule);
        }
    },

    resizeSchedule: function () {
        $('#schedule-layout').find('main').css('padding-top', $('header').height());
    },

    /**
     * Place navigation
     */
    placeNavigation: function () {
        // Custom Header
        var customHeader = $('section#custom-header');
        var customHeaderPlaceholder = $('#custom-header-placeholder');
        var customHeaderSize = $(customHeader).attr('data-size');

        $('#page-header-name').removeClass();
        $('#page-header-custom').removeClass()

        customHeaderPlaceholder.html('');
        if (customHeader.length === 1) {
            if (typeof customHeaderSize === typeof undefined || customHeaderSize === null) {
                customHeaderSize = 0;
            }

            $('#page-header-name')
                .addClass('col-md-' + (12 - customHeaderSize));

            $('#page-header-custom')
                .addClass('col-md-' + customHeaderSize);

            customHeaderPlaceholder.html(customHeader.html());
            customHeader.hide();
        }

        // Custom Panel
        var customPanel = $('section#custom-panel');
        var customPanelPlaceholder = $('#custom-panel-placeholder');

        customPanelPlaceholder.html('');
        if (customPanel.length === 1) {
            customPanelPlaceholder.html(customPanel.html());
            customPanel.hide();
        }
    },

    rank: function () {
        if ($('#rank-bar').length === 0) {
            return;
        }

        for (var i = 1; i <= 5; i++) this.rankStar(i);
    },

    rankStar: function (n) {
        var context = this;

        $('#rank-star-' + n).click(function () {
            return false;
        });

        $('#rank-star-' + n).hover(function () {
            for (var j = 1; j <= 5; j++) {
                $('#rank-star-' + j + ' i').removeClass();
                $('#rank-star-' + j + ' i').addClass('fa');

                if (j <= n) $('#rank-star-' + j + ' i').addClass('fa-star');
                else $('#rank-star-' + j + ' i').addClass('fa-star-o');
            }
        });
        $('#rank-star-' + n).mouseleave(function() {
            for (var j = 1; j <= 5; j++) {
                $('#rank-star-' + j + ' i').removeClass();
                $('#rank-star-' + j + ' i').addClass('fa');

                if (j <= context.getRank()) $('#rank-star-' + j + ' i').addClass('fa-star');
                else $('#rank-star-' + j + ' i').addClass('fa-star-o');
            }
        });
    },

    /**
     * Channel type name
     */
    channelTypeName: function(type) {
        switch (type) {
            case 'SPORT': return 'Sport';
            case 'MUSIC': return 'Music';
            case 'DOCUMENTARY': return 'Documentary';
            case 'MOVIE': return 'Movie';
            case 'COMMERCE': return 'Commerce';
            case 'CHILDREN': return 'Children';
        }
    },

    /**
     * Transmission type name
     */
    transmissionTypeName: function(type) {
        switch (type) {
            case 'MOVIE': return 'Movie';
            case 'TV_SERIES': return 'TV Series';
            case 'TV_SHOW': return 'TV Show';
            case 'SPORT_EVENT': return 'Sport';
            case 'DOCUMENTARY': return 'Documentary';
        }
    },

    /**
     * Age availability name
     */
    ageAvailabilityName: function(type) {
        switch (type) {
            case 'AGE12': return '12+';
            case 'AGE15': return '15+';
            case 'AGE18': return '18+';
            default: return '';
        }
    },

    /**
     * Loads scripts
     */
    boot: function () {
        this.placeNavigation();
        this.adjustContent();
        this.rank();

        // Initializes Bootstrap tooltips
        $('[data-toggle="tooltip"]').tooltip();

        $('.datepicker').datetimepicker({
            format:'Y-m-d H:m'
        });

        $('#show-picker').click(function() {
            $('.datepicker').datetimepicker('show');
        });
    }
};

var telemaniacsApp = telemaniacs.app();