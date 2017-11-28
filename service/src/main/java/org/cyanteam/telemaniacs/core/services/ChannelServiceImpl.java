package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dao.ChannelDao;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.utils.TvManagerDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Simona Tinkova
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelDao channelDao;

    @Override
    public Channel create(Channel channel) throws TvManagerDataAccessException {
        if (channel == null) {
            throw new IllegalArgumentException("Channel is null.");
        }
        try {
            channelDao.create(channel);
            return channel;
        } catch (Throwable e) {
            throw new TvManagerDataAccessException("Cannot create channel named " + channel.getName() + " with id" + channel.getId(), e);
        }
    }

    @Override
    public Channel findById(Long id) throws TvManagerDataAccessException {
        if (id == null) {
            throw new IllegalArgumentException("Channel id is null.");
        }
        try {
            return channelDao.findById(id);
        } catch (Throwable e) {
            throw new TvManagerDataAccessException("Cannot find channel with id  " + id, e);
        }
    }

    @Override
    public Channel findByName(String name) throws TvManagerDataAccessException {
        if ((name == null) || (name.isEmpty())) {
            throw new IllegalArgumentException("Channel name is empty or null.");
        }
        try {
            return channelDao.findByName(name);
        } catch (Throwable e) {
            throw new TvManagerDataAccessException("Cannot find channel named  " + name, e);
        }
    }

    @Override
    public List<Channel> findAll() throws TvManagerDataAccessException {
        try {
            return channelDao.findAll();
        } catch (Throwable e) {
            throw new TvManagerDataAccessException("Could not receive list of channels", e);
        }
    }

    @Override
    public List<Channel> getChannelsByType(ChannelType type) {
        if (type == null) {
            throw new IllegalArgumentException("Channel type is null.");
        }
        try {
            return channelDao.findByType(type);
        } catch (Throwable e) {
            throw new TvManagerDataAccessException("Cannot find channel with type  " + type, e);
        }
    }

    @Override
    public Channel update(Channel channel) throws TvManagerDataAccessException {
        if (channel == null) {
            throw new IllegalArgumentException("Channel is null.");
        }
        try {
            channelDao.update(channel);
            return channel;
        } catch (Throwable e) {
            throw new TvManagerDataAccessException("Cannot update channel named " + channel.getName()
                    + " with id" + channel.getId(), e);
        }
    }

    @Override
    public void remove(Channel channel) throws TvManagerDataAccessException {
        if (channel == null) {
            throw new IllegalArgumentException("Channel is null.");
        }
        try {
            channelDao.remove(channel);
        } catch (Throwable e) {
            throw new TvManagerDataAccessException("Cannot deleteAll channel named " + channel.getName()
                    + " with id" + channel.getId(), e);
        }
    }


}
