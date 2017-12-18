package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.*;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.facade.ScheduleFacade;
import org.cyanteam.telemaniacs.core.services.*;
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
    private UserService userService;

    @Inject
    private FavoriteChannelsService favoriteChannelsService;

    @Inject
    private ScheduleService scheduleService;

    @Inject
    private ObjectMapperService objectMapperService;

    @Override
    public ScheduleDTO getSchedule(LocalDate day) {
        List<Channel> channels = channelService.findAll();
        Schedule schedule = scheduleService.getSchedule(channels, day);

        return objectMapperService.map(schedule, ScheduleDTO.class);
    }

    @Override
    public ScheduleDTO getSchedule(Long userId, LocalDate day) {
        User user = userService.findById(userId);
        List<Channel> channels = favoriteChannelsService.getFavoriteChannels(user);
        Schedule schedule = scheduleService.getSchedule(channels, day);

        return objectMapperService.map(schedule, ScheduleDTO.class);
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
}
