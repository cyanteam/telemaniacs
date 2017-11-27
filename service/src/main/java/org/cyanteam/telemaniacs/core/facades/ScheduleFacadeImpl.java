package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.Schedule;
import org.cyanteam.telemaniacs.core.dto.ScheduleDTO;
import org.cyanteam.telemaniacs.core.dto.UserDTO;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.facade.ScheduleFacade;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author Simona Tinkova
 */
@Service
@Transactional
public class ScheduleFacadeImpl implements ScheduleFacade {

	@Inject
	private ScheduleService scheduleService;

	@Inject
	private ObjectMapperService objectMapperService;

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


}
