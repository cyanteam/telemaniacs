package org.cyanteam.telemaniacs.core.services;

import java.util.List;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.User;

/**
 * Interface describes methods for
 * work with favorite channels.
 *
 * @author Miroslav Kubus
 */
public interface FavoriteChannelsService {
    /**
     * User will start following specific channel
     *
     * @param channel to be followed
     * @param user    who wants to follow channel
     */
    void followChannel(Channel channel, User user);

    /**
     * User will stop following specific channel
     *
     * @param channel to be unfollowed
     * @param user    who wants to unfollow channel
     */
    void unfollowChannel(Channel channel, User user);

    /**
     * Method returns all favorite channels of user
     *
     * @param user whose channel will be returned
     * @return list of favorite channels
     */
    List<Channel> getFavoriteChannels(User user);
}
