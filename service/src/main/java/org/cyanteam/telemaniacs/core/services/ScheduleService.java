package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dto.Schedule;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * TV schedule service
 *
 * @author Michael Le
 */
public interface ScheduleService {
    Schedule getSchedule(Collection<Channel> channels, LocalDate day);

    /**
     * Gets schedule for specified channels and period
     *
     * @param channels Collection of channels
     * @param start    Start date
     * @param end      End date or null if unrestricted
     * @return Schedule
     */
    Schedule getSchedule(Collection<Channel> channels, LocalDateTime start, LocalDateTime end);

    /**
     * Gets end date time of transmission occurrence
     *
     * @param occurrence Transmission occurrence
     * @return End of occurrence
     */
    LocalDateTime getEndDate(TransmissionOccurrence occurrence);
}
