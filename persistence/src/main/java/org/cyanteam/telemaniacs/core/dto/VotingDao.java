package org.cyanteam.telemaniacs.core.dto;

import java.util.List;
import javax.validation.ConstraintViolationException;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.springframework.dao.DataAccessException;

/**
 * Interface for CRUD operations for entity voting.
 * @author Miroslav Kubus
 */
public interface VotingDao {
    
    /**
     * Persists new voting into database.
     * 
     * @param voting represents entity to be persisted into database.
     * @throws ConstraintViolationException if any constraints on columns 
     * are violated.
     */
    void create(Voting voting);
    
    /**
     * Remove voting entity from database.
     *
     * @param voting represents voting to be removed from database.     
     * @throws IllegalArgumentException if voting for removing is not stored 
     * in the database or voting is null. 
     */
    void remove(Voting voting);

    /**
     * Update persisted entity in the database.
     *
     * @param voting represents persisted entity to be updated.
     * @throws ConstraintViolationException if any constraints on columns 
     * are violated.
     * @throws IllegalArgumentException if voting for updating is not in the database.
     */
    void update(Voting voting);

    /**
     * Return the voting entity with specific id.
     *
     * @param id represents id of the voting entity.
     * @return the voting entity associated with the given id.
     */
    Voting findById(Long id);
    
    /**
     * Return votings with given user.
     *
     * @param user represents user of searched votings.
     * @return list of votings of specific user from database.
     * @throws DataAccessException if user is null.
     */
    List<Voting> findByUser(User user);

    /**
     * Return all votings of specified transmission
     *
     * @param transmission Transmission
     * @return Votings of specified transmission
     * @throws DataAccessException if transmission is null or invalid
     */
    List<Voting> findByTransmission(Transmission transmission);
    
    /**
     * Return all votings in the database.
     *
     * @return all voting entities from the database.
     */
    List<Voting> findAll();
}
