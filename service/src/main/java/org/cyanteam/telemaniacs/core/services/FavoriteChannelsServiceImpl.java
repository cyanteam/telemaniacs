package org.cyanteam.telemaniacs.core.services;

import java.util.ArrayList;
import java.util.List;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.User;
import org.springframework.stereotype.Service;

/**
 * Service for adding and removing favorite channels of user.
 * @author Miroslav Kubus
 */
@Service
public class FavoriteChannelsServiceImpl implements FavoriteChannelsService {

    @Override
    public void followChannel(Channel channel, User user) {
        List<Channel> favoriteChannels = user.getFavoriteChannels();

        if(!favoriteChannels.contains(channel)) {
            favoriteChannels.add(channel);
        }
        
        user.setFavoriteChannels(favoriteChannels);
    }

    @Override
    public void unfollowChannel(Channel channel, User user) {
        List<Channel> favoriteChannels = user.getFavoriteChannels();
        
        if(favoriteChannels.contains(channel)) {
            favoriteChannels.remove(channel);
        }
        
        user.setFavoriteChannels(favoriteChannels);
    }

    @Override
    public List<Channel> getFavoriteChannels(User user) {
        return user.getFavoriteChannels();
    }
    
}
