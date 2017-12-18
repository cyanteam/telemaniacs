var telemaniacs = {
    /**
     * AngularJS application
     */
    app: function () {
        return angular.module('telemaniacsApp', [ 'ngRoute', 'ngCookies' ]);
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

        // Initializes Bootstrap tooltips
        $('[data-toggle="tooltip"]').tooltip();
    }
};

var telemaniacsApp = telemaniacs.app();