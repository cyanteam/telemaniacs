package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dto.ChannelDao;
import org.cyanteam.telemaniacs.core.entities.Channel;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Simona Tinkova
 */
public class ChannelServiceImpl implements ChannelService {
	@Inject
	private ChannelDao channelDao;

	@Override
	public void create(Channel channel) {
		channelDao.create(channel);
	}

	@Override
	public void remove(Channel channel) {
		channelDao.remove(channel);
	}

	@Override
	public void update(Channel channel) {
		channelDao.update(channel);
	}

	@Override
	public Channel findById(Long id) {
		return channelDao.findById(id);
	}

	@Override
	public Channel findByName(String name) {
		return channelDao.findByName(name);
	}

	@Override
	public List<Channel> findAll() {
		return channelDao.findAll();
	}
}
