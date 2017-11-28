package org.cyanteam.telemaniacs.core.facade;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.dto.UserVotingDto;
import org.cyanteam.telemaniacs.core.dto.VotingDTO;

import java.util.List;

/**
 * User profile facade
 * Encapsulates user profile interaction services
 *
 * @author Michael Le
 */
public interface UserProfileFacade {
    /**
     * Starts following channel by specified user
     *
     * @param userId    User ID
     * @param channelId ID of channel to be followed
     */
    void followChannel(Long userId, Long channelId);

    /**
     * Stops following channel by specified user
     *
     * @param userId    User ID
     * @param channelId ID of channel to be unfollowed
     */
    void unfollowChannel(Long userId, Long channelId);

    /**
     * Gets all favorite channels of a user
     *
     * @param userId User ID
     * @return List of favorite channels
     */
    List<ChannelDTO> getFavoriteChannels(Long userId);

    /**
     * Starts following transmission by specified user
     *
     * @param userId         User ID
     * @param transmissionId ID of transmission to be followed
     */
    void followTransmission(Long userId, Long transmissionId);

    /**
     * Stops following transmission by specified user
     *
     * @param userId         User ID
     * @param transmissionId ID of transmission to be unfollowed
     */
    void unfollowTransmission(Long userId, Long transmissionId);

    /**
     * Gets all favorite transmissions of a user
     *
     * @param userId User ID
     * @return List of favorite transmissions
     */
    List<TransmissionDTO> getFavoriteTransmissionsByUser(Long userId);

    /**
     * Gets all favorite upcoming transmission in maxTimeSpan window
     *
     * @param userId      User ID
     * @param maxTimeSpan Maximum time span for returning transmissions
     * @return List of upcoming transmissions
     */
    List<TransmissionDTO> getUpcomingFavoriteTransmissionsByUser(Long userId, int maxTimeSpan);

    /**
     * Creates or updates a voting
     *
     * @param userId         User ID
     * @param transmissionId TransmissionID
     */
    void vote(Long userId, Long transmissionId, UserVotingDto userVoting);

    /**
     * Remove voting
     *
     * @param votingId Voting to be deleted
     */
    void removeVoting(Long votingId);

    /**
     * Get votings by user
     *
     * @param userId User ID
     * @return Votings
     */
    List<VotingDTO> getVotingsByUser(Long userId);
}
