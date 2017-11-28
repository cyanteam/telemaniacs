package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.facade.ChannelFacade;
import org.cyanteam.telemaniacs.core.services.ChannelService;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cyanteam.telemaniacs.core.utils.ListUtils.createList;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tomas Milota
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ChannelFacadeImplTest {

    @Inject
    @Spy
    private ObjectMapperService mapper;

    @Mock
    private ChannelService channelService;

    @InjectMocks
    private ChannelFacade channelFacade = new ChannelFacadeImpl();

    private ChannelDTO channelDTO;
    private Channel channelEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        channelDTO = new ChannelDTO();
        channelDTO.setName("hbo");
        channelDTO.setLanguage("EN");
        channelDTO.setChannelType(ChannelType.MOVIE);

        channelEntity = new Channel();
        channelEntity.setName("hbo");
        channelEntity.setLanguage("EN");
        channelEntity.setChannelType(ChannelType.MOVIE);
    }

    @Test
    public void createChannelTest() {
        channelFacade.create(channelDTO);
        verify(channelService).create(argThat(getChannelMatcher()));
    }

    @Test
    public void updateChannelTest() {
        channelFacade.update(channelDTO);
        verify(channelService).update(argThat(getChannelMatcher()));
    }

    @Test
    public void removeChannelTest() {
        channelFacade.remove(channelDTO);
        verify(channelService).remove(argThat(getChannelMatcher()));
    }

    @Test
    public void findChannelByIdTest() {
        when(channelService.findById(1L)).thenReturn(channelEntity);

        ChannelDTO result = channelFacade.findById(1L);
        verify(channelService).findById(1L);
        assertThat(result).isEqualToComparingFieldByFieldRecursively(channelDTO);
    }

    @Test
    public void findChannelByNameTest() {
        when(channelService.findByName("hbo")).thenReturn(channelEntity);

        ChannelDTO result = channelFacade.findByName("hbo");
        verify(channelService).findByName("hbo");
        assertThat(result).isEqualToComparingFieldByFieldRecursively(channelDTO);
    }

    @Test
    public void findChannelsByTypeTest() {
        when(channelService.getChannelsByType(ChannelType.MOVIE))
                .thenReturn(createList(channelEntity));

        List<ChannelDTO> result = channelFacade.findAllOfType(ChannelType.MOVIE);
        verify(channelService).getChannelsByType(ChannelType.MOVIE);
        assertThat(result).containsExactly(channelDTO);
    }

    @Test
    public void findAllChannelsTest() {
        when(channelService.findAll())
                .thenReturn(createList(channelEntity));

        List<ChannelDTO> result = channelFacade.findAll();
        verify(channelService).findAll();
        assertThat(result).containsExactly(channelDTO);
    }

    private ArgumentMatcher<Channel> getChannelMatcher() {
        return new ArgumentMatcher<Channel>() {
            @Override
            public boolean matches(Object argument) {
                Channel channel = (Channel) argument;
                return channelDTO.getName().equals(channel.getName())
                        && channelDTO.getLanguage().equals(channel.getLanguage())
                        && channelDTO.getChannelType() == channel.getChannelType();
            }
        };
    }

}
