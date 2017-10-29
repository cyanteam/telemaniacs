package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;

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
     * Finds transmission occurrence by ID
     * @param id Transmission occurrence ID
     * @return TransmissionOccurrence entity
     * @throws IllegalArgumentException if ID is null
     */
    TransmissionOccurrence findById(Long id);
}
