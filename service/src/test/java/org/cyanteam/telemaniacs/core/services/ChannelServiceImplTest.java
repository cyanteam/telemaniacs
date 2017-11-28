package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dao.ChannelDao;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.helpers.ChannelBuilder;
import org.cyanteam.telemaniacs.core.utils.TvManagerDataAccessException;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cyanteam.telemaniacs.core.utils.ListUtils.createList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Simona Tinkova
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ChannelServiceImplTest {

    @Mock
    private ChannelDao channelDao;

    @Autowired
    @InjectMocks
    private ChannelService channelService = new ChannelServiceImpl();

    private Channel channel1;
    private Channel channel2;
    private Channel channel3;

    private Long counter = 10L;
    private Map<Long, Channel> channels = new HashMap<>();

    @Before
    public void prepareTestChannels() {
        channels.clear();
        channel1 = ChannelBuilder.nova().build();
        channel2 = ChannelBuilder.hbo().build();
        channel3 = ChannelBuilder.disneyChannel().build();

        channel1.setId(1L);
        channel2.setId(2L);
        channel3.setId(3L);

        channels.put(1L, channel1);
        channels.put(2L, channel2);
        channels.put(3L, channel3);
    }

    @Before
    public void beforeClass() throws ServiceContextConfiguration {
        MockitoAnnotations.initMocks(this);

        when(channelDao.create(any(Channel.class))).then(invoke -> {
            Channel mockedChannel = invoke.getArgumentAt(0, Channel.class);
            if (mockedChannel.getId() != null) {
                throw new IllegalArgumentException("Channel already exist");
            }
            if (mockedChannel.getName() == null) {
                throw new IllegalArgumentException("Channel name can't be null");
            }
            if (checkChannelsNameDuplicity(mockedChannel.getName(), -1L)) {
                throw new IllegalArgumentException("Channel name already exist");
            }
            long index = counter;
            mockedChannel.setId(index);
            channels.put(index, mockedChannel);
            counter++;
            return mockedChannel;
        });

        when(channelDao.update(any(Channel.class))).then(invoke -> {
            Channel mockedChannel = invoke.getArgumentAt(0, Channel.class);
            if (mockedChannel.getId() == null) {
                throw new IllegalArgumentException("Channel was not created yet.");
            }
            if (mockedChannel.getName() == null) {
                throw new IllegalArgumentException("Channel name can't be null");
            }
            if (checkChannelsNameDuplicity(mockedChannel.getName(), mockedChannel.getId())) {
                throw new IllegalArgumentException("Channel name already exist");
            }
            channels.replace(mockedChannel.getId(), mockedChannel);
            return mockedChannel;
        });

        when(channelDao.remove(any(Channel.class))).then(invoke -> {
            Channel mockedChannel = invoke.getArgumentAt(0, Channel.class);
            if (mockedChannel.getId() == null) {
                throw new IllegalArgumentException("Channel is not in Database.");
            }
            channels.remove(mockedChannel.getId(), mockedChannel);
            return mockedChannel;
        });

        when(channelDao.findAll()).thenReturn(createList(channel1, channel2, channel3));


        when(channelDao.findById(anyLong())).then(invoke -> {
            Long index = invoke.getArgumentAt(0, Long.class);
            if (index == null) {
                throw new IllegalArgumentException("id is null");
            }
            return channels.get(index);
        });

    }

    @Test
    public void createNewChannel() throws DataAccessException {
        int sizeBefore = channels.size();
        Channel channel = ChannelBuilder.disneyChannel().name("Disney 2").build();
        channelService.create(channel);
        assertThat(channels.values()).hasSize(sizeBefore + 1)
                .contains(channel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullChannel() {
        channelService.create(null);
    }

    @Test(expected = DataAccessException.class)
    public void createChannelNullName() {
        channelService.create(new Channel());
    }

    @Test(expected = DataAccessException.class)
    public void createExistingChannel() {
        Channel channel = ChannelBuilder.disneyChannel().build();
        Channel anotherChannel = ChannelBuilder.disneyChannel().build();
        channelService.create(channel);
        channelService.create(anotherChannel);
    }

    @Test(expected = DataAccessException.class)
    public void createChannelWithIdNotNull() {
        Channel channel = ChannelBuilder.disneyChannel().build();
        channel.setId(1L);
        channelService.create(channel);
    }

    @Test
    public void updateChannel() throws DataAccessException {
        channel1.setName("updated channel");
        channelService.update(channel1);

        Channel updated = channels.get(channel1.getId());

        assertThat(updated.getName()).isEqualTo("updated channel");
        assertThat(updated).isEqualToComparingFieldByField(channel1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullChannel() {
        channelService.update(null);
    }

    @Test(expected = DataAccessException.class)
    public void updateChannelNullName() {
        channel1.setName(null);
        channelService.update(channel1);
    }


    @Test(expected = TvManagerDataAccessException.class)
    public void updateChannelWithNullId() {
        Channel channel = ChannelBuilder.disneyChannel().build();
        channelService.update(channel);
    }

    @Test(expected = DataAccessException.class)
    public void updateChannelWithDuplicateName() {
        channel1.setName(channel2.getName());
        channelService.update(channel1);
    }


    @Test
    public void deleteChannel() throws DataAccessException {
        int sizeBefore = channels.values().size();
        channelService.remove(channel1);

        assertThat(channels.values()).hasSize(sizeBefore - 1)
                .doesNotContain(channel1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullChannel() {
        channelService.remove(null);
    }


    @Test(expected = DataAccessException.class)
    public void deleteChannelWithNullId() {
        Channel channel = ChannelBuilder.disneyChannel().build();
        channelService.remove(channel);
    }

    @Test
    public void deleteChannelNotInDB() throws DataAccessException {
        int sizeBefore = channels.values().size();
        Channel channel = ChannelBuilder.disneyChannel().name("Disney 2").build();
        channel.setId(counter * 2L);
        channelService.remove(channel);

        assertThat(channels.values()).hasSize(sizeBefore)
                .doesNotContain(channel);

    }

    @Test
    public void findAllChannels() throws DataAccessException {
        assertThat(channelService.findAll())
                .containsExactlyInAnyOrder(channel1, channel2, channel3);
    }


    @Test
    public void findChannelById() throws DataAccessException {
        assertThat(channelService.findById(channel1.getId()))
                .isEqualToComparingFieldByField(channel1);
    }

    @Test
    public void findChannelByIdNotInDB() throws DataAccessException {
        assertThat(channelService.findById(10000L)).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findChannelByNullId() {
        channelService.findById(null);
    }


    private boolean checkChannelsNameDuplicity(String name, long id) {
        for (Channel m : channels.values()) {
            if (m.getName().equals(name) && id != m.getId()) {
                return true;
            }
        }
        return false;
    }

}
