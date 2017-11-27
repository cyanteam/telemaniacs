package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
    public List<TransmissionOccurrence> findByChannelAndDate(Channel channel, LocalDateTime start) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null");
        }

        String queryString =
                "SELECT o FROM TransmissionOccurrence o " +
                "WHERE o.channel = :channel AND o.startDate >= :start";
        Query query = entityManager
                .createQuery(queryString, TransmissionOccurrence.class)
                .setParameter("channel", channel)
                .setParameter("start", start);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public List<TransmissionOccurrence> findByTransmissionAndDate(Transmission transmission, LocalDateTime start) {
        if (transmission == null) {
            throw new IllegalArgumentException("Transmission cannot be null");
        }

        String queryString =
                "SELECT o FROM TransmissionOccurrence o " +
                        "WHERE o.transmission = :transmission AND o.startDate >= :start";
        Query query = entityManager
                .createQuery(queryString, TransmissionOccurrence.class)
                .setParameter("transmission", transmission)
                .setParameter("start", start);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public List<TransmissionOccurrence> findByUserAndDate(User user, LocalDateTime start) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        String queryString =
                "SELECT o FROM User u " +
                        "LEFT JOIN u.favoriteTransmissions t " +
                        "LEFT JOIN t.occurrences o " +
                        "WHERE u = :user AND o.startDate >= :start";
        Query query = entityManager
                .createQuery(queryString, TransmissionOccurrence.class)
                .setParameter("user", user)
                .setParameter("start", start);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public TransmissionOccurrence findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("id parameter cannot be null.");
        }

        return entityManager.find(TransmissionOccurrence.class, id);
    }
}
