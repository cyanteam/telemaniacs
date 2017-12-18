package org.cyanteam.telemaniacs.core.services;

import java.time.Duration;
import java.util.List;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;

/**
 * Interface describes methods for
 * work with favorite transmissions.
 *
 * @author Miroslav Kubus
 */
public interface FavoriteTransmissionsService {
    /**
     * User will start following specific transmission
     *
     * @param transmission to be followed
     * @param user         who wants to follow transmission
     */
    void followTransmission(Transmission transmission, User user);

    /**
     * User will stop following specific transmission
     *
     * @param transmission to be unfollowed
     * @param user         who wants to unfollow transmission
     */
    void unfollowTransmission(Transmission transmission, User user);

    /**
     * Merhod returns all favorite transmission of specific user
     *
     * @param user whose favorite transmissions will be returned
     * @return list of favorite transmission of user
     */
    List<Transmission> getFavoriteTransmissionsByUser(User user);

    /**
     * Method returns all favorite transmissions in maxTimeSpan window
     *
     * @param user        whose favorite transmissions will be returned
     * @param maxTimeSpan maximum time span for returning transmissions
     * @return list of favorite transmission of user in time span
     */
    List<Transmission> getUpcomingFavoriteTransmissionsByUser(User user, Duration maxTimeSpan);
}
