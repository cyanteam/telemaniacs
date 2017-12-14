package org.cyanteam.telemaniacs.rest.controllers;

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

@RestController
@RequestMapping(Url.CHANNEL)
public class ChannelController {
    @Inject
    private ChannelFacade channelFacade;

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
}
