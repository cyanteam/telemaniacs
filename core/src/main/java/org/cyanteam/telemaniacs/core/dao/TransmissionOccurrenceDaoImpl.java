package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of transmission occurrence DAO using database.
 * @author Michael Le
 */
@Transactional
@Repository
public class TransmissionOccurrenceDaoImpl implements TransmissionOccurrenceDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(TransmissionOccurrence transmissionOccurrence) {
        if (transmissionOccurrence == null) {
            throw new IllegalArgumentException("transmissionOccurrence parameter cannot be null.");
        }

        entityManager.persist(transmissionOccurrence);
    }

    @Override
    public void remove(TransmissionOccurrence transmissionOccurrence) {
        if (transmissionOccurrence == null) {
            throw new IllegalArgumentException("transmissionOccurrence parameter cannot be null.");
        }

        entityManager.remove(findById(transmissionOccurrence.getId()));
    }

    @Override
    public void update(TransmissionOccurrence transmissionOccurrence) {
        if (transmissionOccurrence == null) {
            throw new IllegalArgumentException("transmissionOccurrence parameter cannot be null.");
        }

        if (findById(transmissionOccurrence.getId()) == null) {
            throw new IllegalArgumentException("TransmissionOccurrence does not exist.");
        }

        entityManager.merge(transmissionOccurrence);
    }

    @Override
    public TransmissionOccurrence findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("id parameter cannot be null.");
        }

        return entityManager.find(TransmissionOccurrence.class, id);
    }
}
