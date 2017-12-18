package org.cyanteam.telemaniacs.rest.controllers;

import org.cyanteam.telemaniacs.core.dto.DateTimeDTO;
import org.cyanteam.telemaniacs.core.dto.ScheduleDTO;
import org.cyanteam.telemaniacs.core.facade.ScheduleFacade;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(Url.SCHEDULE)
public class ScheduleController {
    @Inject
    private ScheduleFacade scheduleFacade;

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ScheduleDTO getSchedule(@PathVariable("date") long offset) {
        return scheduleFacade.getSchedule(getDate(offset));
    }

    @RequestMapping(value = "/user/{user}/date/{date}", method = RequestMethod.GET)
    public ScheduleDTO getSchedule(@PathVariable("user") long userId, @PathVariable("date") long offset) {
        return scheduleFacade.getSchedule(userId, getDate(offset));
    }

    @RequestMapping(value = "/offsetDate/{offset}", method = RequestMethod.GET)
    public DateTimeDTO getOffsetDate(@PathVariable("offset") long offset) {
        return new DateTimeDTO(getDate(offset).format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @RequestMapping(value = "/currentDate", method = RequestMethod.GET)
    public DateTimeDTO getCurrentDate() {
        return new DateTimeDTO(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    private LocalDate getDate(long offset) {
        return LocalDate.from(LocalDateTime.now()).plusDays(offset);
    }
}
