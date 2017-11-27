package org.cyanteam.telemaniacs.core.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Date time service
 * @author Michael Le
 */
public interface DateTimeService {
    /**
     * Gets current date time
     * @return Current date time
     */
    LocalDateTime getCurrent();

    /**
     * Get instance of specified date time
     * @param year Year
     * @param month Month
     * @param day Day
     * @param hour Hour
     * @param minute Minute
     * @param second Second
     * @return Instance of date time
     */
    LocalDateTime get(int year, int month, int day, int hour, int minute, int second);
}
