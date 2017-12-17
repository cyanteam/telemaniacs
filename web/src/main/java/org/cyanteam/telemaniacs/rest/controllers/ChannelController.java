package org.cyanteam.telemaniacs.rest.controllers;

import java.util.List;
import org.cyanteam.telemaniacs.core.dto.ChannelCreateDTO;
import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.facade.ChannelFacade;
import org.cyanteam.telemaniacs.rest.exceptions.ResourceNotFoundException;
import org.cyanteam.telemaniacs.rest.exceptions.ValidationException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.facade.UserProfileFacade;

@RestController
@RequestMapping(Url.CHANNEL)
public class ChannelController {
    @Inject
    private ChannelFacade channelFacade;
    
    @Inject
    private UserProfileFacade userProfileFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ChannelDTO> getAllChannels() {
        List<ChannelDTO> channels = channelFacade.findAll();
        
        if (channels == null) {
            throw new ResourceNotFoundException("list of all channels");
        }

        return channels;
    }
    
    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public List<ChannelDTO> getChannelsByType(@PathVariable("type") String type) {
        List<ChannelDTO> channels = channelFacade.findAllOfType(ChannelType.valueOf(type));
        
        if (channels == null) {
            throw new ResourceNotFoundException("channels of type " + type);
        }

        return channels;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ChannelDTO getChannel(@PathVariable("id") long id) {
        ChannelDTO channelDTO = channelFacade.findById(id);
        if (channelDTO == null) {
            throw new ResourceNotFoundException("Channel", id);
        }

        return channelDTO;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDTO createChannel(@RequestBody @Valid ChannelCreateDTO channelDTO,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid channel state.");
        }

        Long id = channelFacade.create(channelDTO);
        return channelFacade.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDTO updateChannel(@PathVariable("id") long id,
                                    @RequestBody @Valid ChannelDTO channelDTO,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid channel state.");
        }

        channelDTO.setId(id);
        channelFacade.update(channelDTO);
        return channelDTO;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDTO deleteChannel(@PathVariable("id") long id) {
        ChannelDTO channelToRemove = channelFacade.findById(id);
        channelFacade.remove(channelToRemove);
        
        return channelToRemove;
    }
    
    @RequestMapping(value = "/{userId}/follow/{channelId}", method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDTO followChannel(@PathVariable("userId") long userId, 
            @PathVariable("channelId") long channelId ) {        
        
        userProfileFacade.followChannel(userId, channelId);
        
        return channelFacade.findById(channelId);
    }
    
    @RequestMapping(value = "/{userId}/unfollow/{channelId}", method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDTO unfollowChannel(@PathVariable("userId") long userId, 
            @PathVariable("channelId") long channelId ) {
        userProfileFacade.unfollowChannel(userId, channelId);
        
        return channelFacade.findById(channelId);
    }
}
