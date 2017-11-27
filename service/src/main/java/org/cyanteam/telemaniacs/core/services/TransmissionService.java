package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Transmission service interface
 * @author Michael Le
 */
@Service
public interface TransmissionService {
    /**
     * Creates a new transmission
     * @param transmission Transmission
     */
    void createTransmission(Transmission transmission);

    /**
     * Updates a new transmission
     * @param transmission Transmission
     */
    void updateTransmission(Transmission transmission);

    /**
     * Removes a new transmission
     * @param transmission Transmission
     */
    void removeTransmission(Transmission transmission);

    /**
     * Gets a transmission by ID
     * @param id Transmission ID
     * @return Transmission with given ID
     */
    Transmission getTransmissionById(Long id);

    /**
     * Gets a transmission by ID
     * @param name Transmission name
     * @return Transmission with given name
     */
    Transmission getTransmissionByName(String name);

    /**
     * Gets transmissions by type
     * @param type Transmission type
     * @return Transmissions with given type
     */
    List<Transmission> getTransmissionsByType(TransmissionType type);

    /**
     * Gets all transmissions
     * @return all transmissions
     */
    List<Transmission> findAllTransmissions();

    /**
     * Adds transmission occurrence
     * @param occurrence Transmission occurrence
     */
    void addOccurrence(TransmissionOccurrence occurrence);

    /**
     * Updates transmission occurrence
     * @param occurrence Transmission occurrence
     */
    void updateOccurrence(TransmissionOccurrence occurrence);

    /**
     * Removes transmission occurrence
     * @param occurrence Transmission occurrence
     */
    void removeOccurrence(TransmissionOccurrence occurrence);

    /**
     * Get all occurrences of a transmission
     * @param transmission Transmission
     * @return List of all occurrences of given transmission
     */
    List<TransmissionOccurrence> getOccurrences(Transmission transmission);

    /**
     * Get all upcoming occurrences of a transmission
     * @param transmission Transmission
     * @return List of all upcoming occurrences of given transmission
     */
    List<TransmissionOccurrence> getUpcomingOccurrences(Transmission transmission);

    /**
     * Get all votings of a transmission
     * @param transmission Transmission
     * @return List of all votings of given transmission
     */
    List<Voting> getVotings(Transmission transmission);

    /**
     * Get average voting of a transmission
     * @param transmission Transmission
     * @return Average voting of given transmission, or null if no voting available
     */
    Double getAverageVoting(Transmission transmission);
}
