package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.dto.UserVotingDto;
import org.cyanteam.telemaniacs.core.dto.VotingDTO;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.facade.UserProfileFacade;
import org.cyanteam.telemaniacs.core.services.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;

@Service
@Transactional
public class UserProfileFacadeImpl implements UserProfileFacade {
    @Inject
    private ObjectMapperService mapper;

    @Inject
    private UserService userService;

    @Inject
    private ChannelService channelService;

    @Inject
    private TransmissionService transmissionService;

    @Inject
    private FavoriteChannelsService favoriteChannelsService;

    @Inject
    private FavoriteTransmissionsService favoriteTransmissionsService;

    @Inject
    private VotingService votingService;

    @Override
    public void followChannel(Long userId, Long channelId) {
        User user = userService.findById(userId);
        Channel channel = channelService.findById(channelId);

        favoriteChannelsService.followChannel(channel, user);
    }

    @Override
    public void unfollowChannel(Long userId, Long channelId) {
        User user = userService.findById(userId);
        Channel channel = channelService.findById(channelId);

        favoriteChannelsService.unfollowChannel(channel, user);
    }

    @Override
    public List<ChannelDTO> getFavoriteChannels(Long userId) {
        User user = userService.findById(userId);
        List<Channel> channels = favoriteChannelsService.getFavoriteChannels(user);

        return mapper.map(channels, ChannelDTO.class);
    }

    @Override
    public void followTransmission(Long userId, Long transmissionId) {
        User user = userService.findById(userId);
        Transmission transmission = transmissionService.findById(transmissionId);

        favoriteTransmissionsService.followTransmission(transmission, user);
    }

    @Override
    public void unfollowTransmission(Long userId, Long transmissionId) {
        User user = userService.findById(userId);
        Transmission transmission = transmissionService.findById(transmissionId);

        favoriteTransmissionsService.unfollowTransmission(transmission, user);
    }

    @Override
    public List<TransmissionDTO> getFavoriteTransmissionsByUser(Long userId) {
        User user = userService.findById(userId);
        List<Transmission> transmissions = favoriteTransmissionsService.getFavoriteTransmissionsByUser(user);

        return mapper.map(transmissions, TransmissionDTO.class);
    }

    @Override
    public List<TransmissionDTO> getUpcomingFavoriteTransmissionsByUser(Long userId, int maxTimeSpan) {
        User user = userService.findById(userId);
        List<Transmission> transmissions
                = favoriteTransmissionsService.getUpcomingFavoriteTransmissionsByUser(user, Duration.ofSeconds(maxTimeSpan));

        return mapper.map(transmissions, TransmissionDTO.class);
    }

    @Override
    public void vote(Long userId, Long transmissionId, UserVotingDto userVoting) {
        User user = userService.findById(userId);
        Transmission transmission = transmissionService.findById(transmissionId);

        Voting voting;
        try {
            voting = votingService.findByUser(user).stream()
                    .filter(v -> v.getTransmission().equals(transmission))
                    .findFirst().orElse(new Voting());
        } catch (Exception e) {
            voting = new Voting();
        }
        setVoting(voting, user, transmission, userVoting);

        if (voting.getId() == null) {
            votingService.create(voting);
            return;
        }

        votingService.update(voting);
    }

    @Override
    public void removeVoting(Long votingId) {
        Voting voting = votingService.findById(votingId);
        votingService.remove(voting);
    }

    @Override
    public List<VotingDTO> getVotingsByUser(Long userId) {
        User user = userService.findById(userId);
        List<Voting> votings = votingService.findByUser(user);

        return mapper.map(votings, VotingDTO.class);
    }

    private Voting setVoting(Voting voting, User user, Transmission transmission, UserVotingDto userVoting) {
        voting.setUser(user);
        voting.setTransmission(transmission);
        voting.setRank(userVoting.getRank());
        voting.setComment(userVoting.getComment());

        return voting;
    }
}
