package org.cyanteam.telemaniacs.core.services;

import java.util.List;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.utils.TvManagerDataAccessException;

/**
 * Interface for voting services.
 * @author Miroslav Kubus
 */
public interface VotingService {
    
    /**
     * Create a new voting
     * @param voting to be created
     * @throws IllegalArgumentException if voting is null
     * @throws TvManagerDataAccessException in case of any error during service
     */
    void createVoting(Voting voting) throws TvManagerDataAccessException;

    /**
     * Update voting
     * @param voting to be updated
     * @throws IllegalArgumentException if voting is null 
     * @throws TvManagerDataAccessException in case of any error during service
     */
    void updateVoting(Voting voting) throws TvManagerDataAccessException;

    /**
     * Remove voting
     * @param voting to be deleted
     * @throws IllegalArgumentException if voting is null
     * @throws TvManagerDataAccessException in case of any error during service 
     */
    void removeVoting(Voting voting) throws TvManagerDataAccessException;

    /**
     * Get votings by transmission
     * @param transmission which votings will be returned
     * @return Votings of given transmission
     * @throws IllegalArgumentException if transmission is null
     * @throws TvManagerDataAccessException in case of any error during service 
     */
    List<Voting> getVotingsByTransmission(Transmission transmission) throws TvManagerDataAccessException;

    /**
     * Get voting by user
     * @param user whose votings will be returned
     * @return Votings of given user
     * @throws IllegalArgumentException if user is null
     * @throws TvManagerDataAccessException in case of any error during service 
     */
    List<Voting> getVotingByUser(User user) throws TvManagerDataAccessException;
    
    /**
     * Get voting by id
     * @param id of voting to be returned
     * @return voting of given id
     * @throws TvManagerDataAccessException in case of any error during service 
     */
    Voting getVotingById(Long id) throws TvManagerDataAccessException;
        
    /**
     * Get all votings 
     * @return all votings
     * @throws TvManagerDataAccessException in case of any error during service 
     */
    List<Voting> getAllVotings() throws TvManagerDataAccessException;
}
