package org.cyanteam.telemaniacs.core.facades;


import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.Schedule;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.facade.ScheduleFacade;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.ScheduleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cyanteam.telemaniacs.core.utils.ListUtils.createList;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tomas Milota
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleFacadeImplTest {

    @Inject
    @Spy
    private ObjectMapperService mapper;

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleFacade scheduleFacade = new ScheduleFacadeImpl();

    private ChannelDTO hbo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        hbo = new ChannelDTO();
        hbo.setName("HBO");
        hbo.setLanguage("EN");
        hbo.setChannelType(ChannelType.MOVIE);
    }

    @Test
    public void getScheduleTest() {
        LocalDateTime start = LocalDateTime.of(2017, 11, 26, 10, 0);
        LocalDateTime end = LocalDateTime.of(2017, 11, 27, 15, 30);
        when(scheduleService.getSchedule(anyCollection(), eq(start), eq(end))).thenReturn(new Schedule());

        scheduleFacade.getSchedule(createList(hbo), start, end);
        verify(scheduleService).getSchedule(argThat(getChannelMatcher()), eq(start), eq(end));
    }

    private ArgumentMatcher<List<Channel>> getChannelMatcher() {
        return new ArgumentMatcher<List<Channel>>() {
            @Override
            public boolean matches(Object argument) {
                List<Channel> channels = (List<Channel>) argument;
                assertThat(channels).hasSize(1);
                Channel channel = channels.get(0);
                return hbo.getName().equals(channel.getName())
                        && hbo.getLanguage().equals(channel.getLanguage())
                        && hbo.getChannelType() == channel.getChannelType();
            }
        };
    }

}
