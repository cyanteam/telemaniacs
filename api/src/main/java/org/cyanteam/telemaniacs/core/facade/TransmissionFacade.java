package org.cyanteam.telemaniacs.core.facade;

import org.cyanteam.telemaniacs.core.dto.*;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author Simona Tinkova
 */
public interface TransmissionFacade {
    /**
     * New transmissionDTO into the system.
     *
     * @param transmissionCreateDTO represents entity that will be created into system.
     * @throws ConstraintViolationException if any constraints on columns
     *                                      are violated.
     */
    Long create(TransmissionCreateDTO transmissionCreateDTO) throws ConstraintViolationException;

    /**
     * The transmissionDTO will be deleted from system.
     *
     * @param id represents entity that will be deleted from system
     * @throws IllegalArgumentException if transmissionDTO for removing is not stored
     *                                  in the system or transmissionDTO is null.
     */
    void remove(Long id) throws IllegalArgumentException;

    /**
     * The transmissionDTO will be updated in the system.
     *
     * @param transmissionDTO the transmissionDTO that will be updated in the system
     * @return updated transmission ID
     * @throws ConstraintViolationException if any constraints on columns
     *                                      are violated.
     * @throws IllegalArgumentException     if transmissionDTO for updating is not in the system.
     */
    Long update(TransmissionDTO transmissionDTO) throws ConstraintViolationException, IllegalArgumentException;

    /**
     * Returns the transmissionDTO with the specific id.
     *
     * @param id the id of the transmissionDTO that will be returned
     * @return the transmissionDTO with the specific id
     * @throws IllegalArgumentException if id is null.
     */
    TransmissionDTO findById(Long id) throws IllegalArgumentException;

    /**
     * Returns the transmissionDTO with the specific name.
     *
     * @param name the name of the transmissionDTO that will be returned
     * @return the transmission with the specific name
     * @throws IllegalArgumentException if name is null.
     */
    TransmissionDTO findByName(String name) throws IllegalArgumentException;

    /**
     * @param type of the transmissionDTO
     * @return tre transmissionDTO with the specific type
     */
    List<TransmissionDTO> findByType(TransmissionType type);

    /**
     * Gets all transmissionsDTO
     *
     * @return all transmissionsDTO
     */
    List<TransmissionDTO> findAll();

    /**
     * Adds transmission occurrence
     *
     * @param occurrenceDTO Transmission occurrence
     */
    Long addOccurrence(TransmissionOccurrenceCreateDTO occurrenceDTO);

    /**
     * Updates transmission occurrence
     *
     * @param occurrenceDTO Transmission occurrence
     */
    void updateOccurrence(TransmissionOccurrenceCreateDTO occurrenceDTO);

    /**
     * Removes transmission occurrence
     *
     * @param occurrenceId Transmission occurrence
     */
    void removeOccurrence(Long occurrenceId);

    /**
     * Get all occurrences of a transmission
     *
     * @param transmissionDTO Transmission
     * @return List of all occurrences of given transmission
     */
    List<TransmissionOccurrenceDTO> getOccurrences(TransmissionDTO transmissionDTO);

    /**
     * Get all upcoming occurrences of a transmission
     *
     * @param transmissionDTO Transmission
     * @return List of all upcoming occurrences of given transmission
     */
    List<TransmissionOccurrenceDTO> getUpcomingOccurrences(TransmissionDTO transmissionDTO);

    /**
     * Get all votings of a transmission
     *
     * @param id Transmission
     * @return List of all votings of given transmission
     */
    List<VotingDTO> getVotings(Long id);

    /**
     * Get average voting of a transmission
     *
     * @param id Transmission ID
     * @return Average voting of given transmission, or null if no voting available
     */
    Double getAverageVoting(Long id);

    TransmissionOccurrenceCreateDTO getOccurrenceById(Long id) throws IllegalArgumentException;
}
