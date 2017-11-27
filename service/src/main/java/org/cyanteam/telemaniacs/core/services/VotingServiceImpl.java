package org.cyanteam.telemaniacs.core.services;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.cyanteam.telemaniacs.core.dao.VotingDao;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.utils.TvManagerDataAccessException;
import org.springframework.stereotype.Service;

/**
 * Service layer for voting entity.
 * @author Miroslav Kubus
 */
@Service
public class VotingServiceImpl implements VotingService {
    
    @Inject
    private VotingDao votingDao;
    
    @Override
    public void createVoting(Voting voting) throws TvManagerDataAccessException {
        if(voting == null) {
            throw new IllegalArgumentException("Voting cannot be null!");
        }
        
        try {
            votingDao.create(voting);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            throw new TvManagerDataAccessException("Error while creating voting!");
        } 
    }

    @Override
    public void updateVoting(Voting voting) throws TvManagerDataAccessException {
        if(voting == null) {
            throw new IllegalArgumentException("Voting cannot be null!");
        }
        
        try {
            votingDao.update(voting);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new TvManagerDataAccessException("Error while updating voting!");
        }
    }

    @Override
    public void removeVoting(Voting voting) throws TvManagerDataAccessException {
        if(voting == null) {
            throw new IllegalArgumentException("Voting cannot be null!");
        }
        
        try {
            votingDao.remove(voting);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new TvManagerDataAccessException("Error while deleting voting!");
        }
    }

    @Override
    public List<Voting> getVotingsByTransmission(Transmission transmission) throws TvManagerDataAccessException {
        if(transmission == null) {
            throw new IllegalArgumentException("Transmission cannot be null!");
        }
        
        try {
            return votingDao.findByTransmission(transmission);
        } catch (IllegalArgumentException e) {
            throw new TvManagerDataAccessException("Error while updating voting!");
        }
    }

    @Override
    public List<Voting> getVotingByUser(User user) throws TvManagerDataAccessException {
        if(user == null) {
            throw new IllegalArgumentException("Transmission cannot be null!");
        }
        
        try {
            return votingDao.findByUser(user);
        } catch (IllegalArgumentException e) {
            throw new TvManagerDataAccessException("Error while updating voting!");
        }
    }
    
    @Override
    public List<Voting> getAllVotings() throws TvManagerDataAccessException {        
        try {
            return votingDao.findAll();
        } catch (IllegalArgumentException e) {
            throw new TvManagerDataAccessException("Error while updating voting!");
        }
    }
    
    @Override
    public Voting getVotingById(Long id) throws TvManagerDataAccessException {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        
        try {
            return votingDao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new TvManagerDataAccessException("Error while getting voting by Id!");
        }
    }
}
