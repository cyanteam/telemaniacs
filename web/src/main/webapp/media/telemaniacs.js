var telemaniacs = {
    /**
     * AngularJS application
     */
    app: function () {
        return angular.module('telemaniacsApp', [ 'ngRoute' ]);
    },

    /**
     * Adjusts containers top padding after resizing
     */
    adjustContent: function() {
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
        if (customHeader.length === 1) {
            $('#custom-header-placeholder').html(customHeader.html());
            customHeader.remove();
        }

        // Custom Panel
        var customPanel = $('section#custom-panel');
        if (customPanel.length === 1) {
            $('#custom-panel-placeholder').html(customPanel.html());
            customPanel.remove();
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