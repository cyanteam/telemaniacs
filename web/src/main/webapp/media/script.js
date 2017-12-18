var telemaniacs = {
    /**
     * Adjusts containers top padding after resizing
     */
    resizeSchedule: function() {
        $('#schedule').css('padding-top', $('header').height());
    },

    /**
     * Loads scripts
     */
    boot: function() {
        if ($('#schedule-layout').length !== 0) {
            this.resizeSchedule();
            $(window).resize(this.resizeSchedule);
        }

        // Initializes Bootstrap tooltips
        $('[data-toggle="tooltip"]').tooltip();
    }
};

telemaniacs.boot();