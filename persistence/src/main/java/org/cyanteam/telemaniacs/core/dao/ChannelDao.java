package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.enums.ChannelType;

import java.util.List;

/**
 * DAO for Channel
 *
 * @author Tomas Milota
 */
public interface ChannelDao {

    /**
     * Persist new channel.
     *
     * @param channel entity that will be persisted
     * @throws IllegalArgumentException if channel is null
     */
    Channel create(Channel channel);

    /**
     * Removes channel
     *
     * @param channel entity that will be removed
     * @throws IllegalArgumentException if channel is null
     * @throws IllegalArgumentException if channel is not stored in the database
     */
    Channel remove(Channel channel);

    /**
     * Updates persisted channel.
     *
     * @param channel entity that will be updated
     * @throws IllegalArgumentException if channel is null
     * @throws IllegalArgumentException if channel is not stored in the database
     */
    Channel update(Channel channel);

    /**
     * Returns channel by id.
     *
     * @param id ID of the entity
     * @return found entity
     * @throws IllegalArgumentException if id is null
     */
    Channel findById(Long id);

    /**
     * Returns channel by name.
     *
     * @param name name of the entity
     * @return found entity
     */
    Channel findByName(String name);

    /**
     * Returns all stored channels.
     *
     * @return all stored channels
     */
    List<Channel> findAll();

    /**
     *
     * @param type of the channel
     * @return found entity
     */
    Channel findByType(ChannelType type);

}
