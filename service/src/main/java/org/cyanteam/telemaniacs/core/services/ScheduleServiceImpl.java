package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dto.Schedule;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TV schedule service implementation
 *
 * @author Michael Le
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public Schedule getSchedule(Collection<Channel> channels, LocalDateTime start, LocalDateTime end) {
        if (start == null) {
            throw new IllegalArgumentException("start cannot be null.");
        }

        Schedule schedule = new Schedule();
        for (Channel channel : channels) {
            List<TransmissionOccurrence> occurrences = channel.getTransmissionOccurrences().stream()
                    .filter(o -> o.getStartDate().compareTo(start) >= 0)
                    .filter(o -> end == null || getEndDate(o).compareTo(end) <= 0)
                    .collect(Collectors.toList());
            schedule.addChannelSchedule(channel, occurrences);
        }

        return schedule;
    }

    @Override
    public LocalDateTime getEndDate(TransmissionOccurrence occurrence) {
        return occurrence.getStartDate().plusSeconds(occurrence.getTransmission().getLength());
    }
}
