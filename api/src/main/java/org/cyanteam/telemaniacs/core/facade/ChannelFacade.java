package org.cyanteam.telemaniacs.core.facade;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.enums.ChannelType;

import java.util.List;

/**
 * Interface for channel operations.
 * Encapsulates channel services.
 * @author Tomas Milota
 */
public interface ChannelFacade {

    /**
     * Persist new channel.
     *
     * @param channel entity that will be persisted
     * @throws IllegalArgumentException if channel is null
     */
    void createChannel(ChannelDTO channel);

    /**
     * Updates persisted channel.
     *
     * @param channel entity that will be updated
     * @throws IllegalArgumentException if channel is null
     * @throws IllegalArgumentException if channel is not stored in the database
     */
    void updateChannel(ChannelDTO channel);

    /**
     * Removes channel
     *
     * @param channel entity that will be removed
     * @throws IllegalArgumentException if channel is null
     * @throws IllegalArgumentException if channel is not stored in the database
     */
    void removeChannel(ChannelDTO channel);

    /**
     * Returns channel by id.
     *
     * @param id ID of the entity
     * @return found entity
     * @throws IllegalArgumentException if id is null
     */
    ChannelDTO findChannelById(Long id);

    /**
     * Returns channel by name.
     *
     * @param name name of the entity
     * @return found entity
     */
    ChannelDTO findChannelByName(String name);

    /**
     * Returns list of channels with given type.
     *
     * @param type type of the channel
     * @return found entities
     */
    List<ChannelDTO> findChannelsByType(ChannelType type);

    /**
     * Returns all stored channels.
     *
     * @return all stored channels
     */
    List<ChannelDTO> findAllChannels();
    
}
