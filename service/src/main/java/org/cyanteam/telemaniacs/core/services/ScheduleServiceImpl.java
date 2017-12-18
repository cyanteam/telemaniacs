package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dto.Schedule;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public Schedule getSchedule(Collection<Channel> channels, LocalDate day) {
        if (day == null) {
            throw new IllegalArgumentException("Day cannot be null.");
        }

        LocalDateTime lowerBound = LocalDateTime.of(day, LocalTime.MIDNIGHT);
        LocalDateTime upperBound = LocalDateTime.of(day.plusDays(1), LocalTime.MIDNIGHT);
        Schedule schedule = new Schedule();
        for (Channel channel : channels) {
            List<TransmissionOccurrence> occurrences = channel.getTransmissionOccurrences().stream()
                    .filter(o -> o.getStartDate().compareTo(lowerBound) >= 0)
                    .filter(o -> o.getStartDate().compareTo(upperBound) < 0)
                    .collect(Collectors.toList());
            schedule.addChannelSchedule(channel, occurrences);
        }

        return schedule;
    }

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
