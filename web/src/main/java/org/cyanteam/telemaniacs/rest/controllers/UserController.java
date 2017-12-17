package org.cyanteam.telemaniacs.rest.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.dto.UserAuthenticationDTO;
import org.cyanteam.telemaniacs.core.dto.UserDTO;
import org.cyanteam.telemaniacs.core.dto.UserVotingDto;
import org.cyanteam.telemaniacs.core.facade.UserFacade;
import org.cyanteam.telemaniacs.core.facade.UserProfileFacade;
import org.cyanteam.telemaniacs.rest.exceptions.ResourceNotFoundException;
import org.cyanteam.telemaniacs.rest.exceptions.ValidationException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for user.
 * @author Miroslav Kubus
 */
@RestController
@RequestMapping(Url.USER)
public class UserController {
    @Inject
    private UserProfileFacade userProfileFacade;
    @Inject
    private UserFacade userFacade;
    
    @RequestMapping(value = "/{userId}/upcoming-{timeSpan}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TransmissionDTO> getUpcomingTransmissions(@PathVariable("userId") long userId, @PathVariable("timeSpan") int timeSpan) {
        List<TransmissionDTO> upcomingTransmissions = userProfileFacade
                .getUpcomingFavoriteTransmissionsByUser(userId, timeSpan);
        
        if (upcomingTransmissions == null) {
            throw new ResourceNotFoundException("Upcoming transmisson of user", userId);
        }

        return upcomingTransmissions;
    }
    
    @RequestMapping(value = "/{userId}/vote-for/{transmissionId}", method = RequestMethod.PUT, 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void setUserVoting(@PathVariable("userId") long userId, @PathVariable("transmissionId") long transmissionId,
            @RequestBody @Valid UserVotingDto voting, BindingResult bindingResult) {
            
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid voting state.");
        }

        userProfileFacade.vote(userId, transmissionId, voting);
    }
    
    @RequestMapping(value = "/{userId}/channels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ChannelDTO> getFavoriteChannels(@PathVariable("userId") long userId) {
        List<ChannelDTO> favoriteChannels = userProfileFacade
                .getFavoriteChannels(userId);
        
        if (favoriteChannels == null) {
            throw new ResourceNotFoundException("Favorite channels of user", userId);
        }        
        
        return favoriteChannels;
    }
        
    @RequestMapping(value = "/{userId}/transmissions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TransmissionDTO> getFavoriteTransmissions(@PathVariable("userId") long userId) {
        List<TransmissionDTO> favoriteTransmissions = userProfileFacade
                .getFavoriteTransmissionsByUser(userId);
        
        if (favoriteTransmissions == null) {
            throw new ResourceNotFoundException("Favorite transmissions of user", userId);
        }        
        
        return favoriteTransmissions;
    }

    @RequestMapping(value = "/email/{email}/", method = RequestMethod.GET)
    public UserDTO getUserByEmail(@PathVariable("email") String email) {
        UserDTO user = userFacade.findByEmail(email);
        return user;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean authenticateUser(@RequestBody @Valid UserAuthenticationDTO user) {
        try {
            return userFacade.authenticate(user);
        } catch (AuthenticationException | NoResultException e) {
            throw new ResourceNotFoundException("User with email");
        }
    }
}
