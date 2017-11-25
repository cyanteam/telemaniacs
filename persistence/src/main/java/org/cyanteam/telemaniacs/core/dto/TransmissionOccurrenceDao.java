package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DAO for transmission occurrence.
 * @author Michael Le
 */
public interface TransmissionOccurrenceDao {
    /**
     * Creates a new transmission occurrence
     * @param transmissionOccurrence TransmissionOccurrence entity
     * @throws IllegalArgumentException if transmissionOccurrence is null
     */
    void create(TransmissionOccurrence transmissionOccurrence);

    /**
     * Removes the transmission occurrence
     * @param transmissionOccurrence TransmissionOccurrence entity
     * @throws IllegalArgumentException if transmissionOccurrence is null
     */
    void remove(TransmissionOccurrence transmissionOccurrence);

    /**
     * Updates the transmission occurrence
     * @param transmissionOccurrence TransmissionOccurrence entity
     * @throws IllegalArgumentException if transmissionOccurrence is null
     * @throws IllegalArgumentException if transmissionOccurrence does not exist
     */
    void update(TransmissionOccurrence transmissionOccurrence);

    /**
     * Finds all transmissions by channel and date
     * @param channel Channel
     * @param start Start date time
     * @return All transmissions on given channel since start date
     * @throws IllegalArgumentException if channel is null
     */
    List<TransmissionOccurrence> findByChannelAndDate(Channel channel, LocalDateTime start);

    /**
     * Finds all transmissions by transmission and date
     * @param transmission Transmission
     * @param start Start date time
     * @return All transmissions on given channel since start date
     * @throws IllegalArgumentException if transmission is null
     */
    List<TransmissionOccurrence> findByTransmissionAndDate(Transmission transmission, LocalDateTime start);

    /**
     * Finds all transmissions by user and date
     * @param user User
     * @param start Start date time
     * @return All transmissions on given user since start date
     * @throws IllegalArgumentException if user is null
     */
    List<TransmissionOccurrence> findByUserAndDate(User user, LocalDateTime start);

    /**
     * Finds transmission occurrence by ID
     * @param id Transmission occurrence ID
     * @return TransmissionOccurrence entity
     * @throws IllegalArgumentException if ID is null
     */
    TransmissionOccurrence findById(Long id);
}
