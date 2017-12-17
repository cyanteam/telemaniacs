package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.*;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.facade.ScheduleFacade;
import org.cyanteam.telemaniacs.core.services.ChannelService;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Simona Tinkova
 */
@Service
@Transactional
public class ScheduleFacadeImpl implements ScheduleFacade {
    private static final LocalDate BASE_DATE = LocalDate.of(2017, 1, 1);

    @Inject
    private ChannelService channelService;

    @Inject
    private ScheduleService scheduleService;

    @Inject
    private ObjectMapperService objectMapperService;

    @Override
    public ScheduleDTO getSchedule(LocalDate day) {
        List<Channel> channels = channelService.findAll();
        Schedule schedule = scheduleService.getSchedule(channels, day);

        ScheduleDTO mapped = objectMapperService.map(schedule, ScheduleDTO.class);
        return mapStartDate(schedule, mapped);
    }

    @Override
    public ScheduleDTO getSchedule(Collection<ChannelDTO> channels, LocalDateTime start, LocalDateTime end) {
        Schedule schedule;
        schedule = scheduleService.getSchedule(objectMapperService.map(channels, Channel.class), start, end);

        return objectMapperService.map(schedule, ScheduleDTO.class);
    }

    @Override
    public ScheduleDTO getUserSchedule(UserDTO user, LocalDateTime from, LocalDateTime to) {
        Schedule schedule;
        schedule = scheduleService.getSchedule(objectMapperService.map(user.getFavouriteChannels(), Channel.class), from, to);

        return objectMapperService.map(schedule, ScheduleDTO.class);
    }

    @Override
    public long getDay() {
        return DAYS.between(BASE_DATE, LocalDate.now());
    }

    private ScheduleDTO mapStartDate(Schedule schedule, ScheduleDTO scheduleDTO) {
        List<ScheduleChannelDTO> channelSchedules = scheduleDTO.getChannelSchedules();
        for (int i = 0; i < channelSchedules.size(); i++) {
            List<ScheduleTransmissionOccurrenceDTO> occurrences = channelSchedules.get(i).getTransmissionOccurrences();
            for (int j = 0; j < occurrences.size(); j++) {
                occurrences.get(j).setStartDate(
                        schedule.getChannelSchedules().get(i)
                                .getTransmissionOccurrences().get(j)
                                .getStartDate()
                );
            }
        }

        return scheduleDTO;
    }
}
