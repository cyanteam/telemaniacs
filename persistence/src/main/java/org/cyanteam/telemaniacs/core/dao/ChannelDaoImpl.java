package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Tomas Milota
 */
@Transactional
@Repository
public class ChannelDaoImpl implements ChannelDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Channel create(Channel channel) {
        if(channel == null) {
            throw new IllegalArgumentException("You are trying to persist null channel.");
        }

        entityManager.persist(channel);
        return channel;
    }

    @Override
    public Channel remove(Channel channel) {
        if(channel == null) {
            throw new IllegalArgumentException("You are trying to remove null channel.");
        }
        if(channel.getId() == null) {
            throw new IllegalArgumentException("You are trying to remove not persisted channel.");
        }

        entityManager.remove(findById(channel.getId()));
        return channel;
    }

    @Override
    public Channel update(Channel channel) {
        if(channel == null) {
            throw new IllegalArgumentException("You are trying to update null channel.");
        }
        if (findById(channel.getId()) == null) {
            throw new IllegalArgumentException("Channel is not stored in the datebase.");
        }

        entityManager.merge(channel);
        return channel;
    }

    @Override
    public Channel findById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Parameter id cannot be null.");
        }

        return entityManager.find(Channel.class, id);
    }

    @Override
    public Channel findByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Parameter name cannot be null.");
        }

        return entityManager
                .createQuery("SELECT ch FROM Channel ch WHERE ch.name = :name", Channel.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Channel> findByType(ChannelType type){
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        return entityManager
                .createQuery("SELECT ch FROM Channel ch WHERE ch.channelType = :type", Channel.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Channel> findAll() {
        return entityManager
                .createQuery("SELECT ch FROM Channel ch", Channel.class)
                .getResultList();
    }
}
