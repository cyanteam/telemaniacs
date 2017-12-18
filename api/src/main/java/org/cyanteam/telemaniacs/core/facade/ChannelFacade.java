package org.cyanteam.telemaniacs.core.facade;

import org.cyanteam.telemaniacs.core.dto.ChannelCreateDTO;
import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.enums.ChannelType;

import java.util.List;

/**
 * Interface for channel operations.
 * Encapsulates channel services.
 *
 * @author Tomas Milota
 */
public interface ChannelFacade {

    /**
     * Persist new channel.
     *
     * @param channel entity that will be persisted
     * @throws IllegalArgumentException if channel is null
     * @return ID of created channel
     */
    Long create(ChannelCreateDTO channel);

    /**
     * Updates persisted channel.
     *
     * @param channel entity that will be updated
     * @throws IllegalArgumentException if channel is null
     * @throws IllegalArgumentException if channel is not stored in the database
     */
    void update(ChannelDTO channel);

    /**
     * Removes channel
     *
     * @param channel entity that will be removed
     * @throws IllegalArgumentException if channel is null
     * @throws IllegalArgumentException if channel is not stored in the database
     */
    void remove(ChannelDTO channel);

    /**
     * Returns channel by id.
     *
     * @param id ID of the entity
     * @return found entity
     * @throws IllegalArgumentException if id is null
     */
    ChannelDTO findById(Long id);

    /**
     * Returns channel by name.
     *
     * @param name name of the entity
     * @return found entity
     */
    ChannelDTO findByName(String name);

    /**
     * Returns list of channels with given type.
     *
     * @param type type of the channel
     * @return found entities
     */
    List<ChannelDTO> findAllOfType(ChannelType type);

    /**
     * Returns all stored channels.
     *
     * @return all stored channels
     */
    List<ChannelDTO> findAll();

}
