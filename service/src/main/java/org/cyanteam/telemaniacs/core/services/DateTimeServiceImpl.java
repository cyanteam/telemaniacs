package org.cyanteam.telemaniacs.core.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Standard date time service implementation
 * @author Michael Le
 */
@Service
public class DateTimeServiceImpl implements DateTimeService {
    @Override
    public LocalDateTime getCurrent() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime get(int year, int month, int day, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
