package org.cyanteam.telemaniacs.core.facade;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.ScheduleDTO;
import org.cyanteam.telemaniacs.core.dto.UserDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Simona Tinkova
 */
public interface ScheduleFacade {
    ScheduleDTO getSchedule(LocalDate day);

    ScheduleDTO getSchedule(Long userId, LocalDate day);

    ScheduleDTO getSchedule(Collection<ChannelDTO> channels, LocalDateTime start, LocalDateTime end);

    ScheduleDTO getUserSchedule(UserDTO user, LocalDateTime from, LocalDateTime to);

    long getDay();
}
