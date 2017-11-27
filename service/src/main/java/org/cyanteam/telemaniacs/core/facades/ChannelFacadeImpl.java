package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.facade.ChannelFacade;
import org.cyanteam.telemaniacs.core.services.ChannelService;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Tomas Milota
 */
@Service
public class ChannelFacadeImpl implements ChannelFacade {

    @Inject
    private ChannelService channelService;
    @Inject
    private ObjectMapperService objectMapperService;

    @Override
    public void createChannel(ChannelDTO channelDTO) {
        Channel channel = prepareChannel(channelDTO);

        channelService.create(channel);
        channelDTO.setId(channel.getId());
    }

    @Override
    public void updateChannel(ChannelDTO channelDTO) {
        Channel channel = prepareChannel(channelDTO);

        channelService.update(channel);
    }

    @Override
    public void removeChannel(ChannelDTO channelDTO) {
        Channel channel = prepareChannel(channelDTO);

        channelService.remove(channel);
    }

    @Override
    public ChannelDTO findChannelById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id of channel cannot be null!");
        }

        Channel channel = channelService.findById(id);

        return objectMapperService.map(channel, ChannelDTO.class);
    }

    @Override
    public ChannelDTO findChannelByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name of channel is null or empty!");
        }

        Channel channel = channelService.findByName(name);

        return objectMapperService.map(channel, ChannelDTO.class);
    }

    @Override
    public List<ChannelDTO> findChannelsByType(ChannelType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type of channel cannot be null!");
        }

        List<Channel> channels = channelService.getChannelsByType(type);

        return objectMapperService.map(channels, ChannelDTO.class);
    }

    @Override
    public List<ChannelDTO> findAllChannels() {
        List<Channel> channels = channelService.findAll();

        return objectMapperService.map(channels, ChannelDTO.class);
    }

    private Channel prepareChannel(ChannelDTO channelDTO) {
        if (channelDTO == null) {
            throw new IllegalArgumentException("ChannelDTO cannot be null!");
        }

        return objectMapperService.map(channelDTO, Channel.class);
    }
}
