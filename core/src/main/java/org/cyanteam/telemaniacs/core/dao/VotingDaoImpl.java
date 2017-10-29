package org.cyanteam.telemaniacs.core.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;

/**
 * Class accessing persistence context for CRUD operations for voting class.
 * It implements VotingDao interaface.
 * @author Miroslav Kubus
 */
public class VotingDaoImpl implements VotingDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void create(Voting voting) {
        entityManager.persist(voting);
    }

    @Override
    public void remove(Voting voting) {
        if(voting == null){
            throw new IllegalArgumentException("You try to delete NULL voting!");
        }
        
        entityManager.remove(findById(voting.getId()));    
    }

    @Override
    public void update(Voting voting) {
        if(voting == null) {
            throw new IllegalArgumentException("You try to update NULL voting!");
        }
        
        if (findById(voting.getId()) == null) {
            throw new IllegalArgumentException("Unknown voting to update with "
                    + "id: " + voting.getId() + "!");
        }
        
        entityManager.merge(voting);
    }

    @Override
    public Voting findById(Long id) {
        return entityManager.find(Voting.class, id);
    }

    @Override
    public List<Voting> findByUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("You try to find voting by NULL user!"); 
        }
        
        String queryString = "SELECT v FROM Voting v WHERE v.user = :userId";
        TypedQuery<Voting> query;
        
        query = entityManager
                    .createQuery(queryString, Voting.class)
                    .setParameter("userId", user);
        
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public List<Voting> findAll() {
        TypedQuery<Voting> allVotingsQuery;
        String queryString = "SELECT v FROM Voting v";
        
        allVotingsQuery = entityManager.createQuery(queryString, Voting.class);
        
        return Collections.unmodifiableList(allVotingsQuery.getResultList());   
    }
}
