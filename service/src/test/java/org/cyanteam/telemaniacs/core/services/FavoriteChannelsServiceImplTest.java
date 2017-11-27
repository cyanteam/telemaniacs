package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.helpers.ChannelBuilder;
import org.cyanteam.telemaniacs.core.helpers.UserBuilder;
import org.cyanteam.telemaniacs.core.dao.UserDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Tests for implementation of favorite channels service.
 * @author Miroslav Kubus
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FavoriteChannelsServiceImplTest {
    @Mock
    private UserDao userDao;
    
    @InjectMocks
    private final FavoriteChannelsService favoriteChannelsService = new FavoriteChannelsServiceImpl();
    
    private User youngUser;
    private User adultUser;
    private Channel channel1;
    private Channel channel2;
       
    @Before
    public void setup() throws ServiceException {  
        MockitoAnnotations.initMocks(this);

        channel1 = ChannelBuilder
                .disneyChannel()
                .build();
        channel2 = ChannelBuilder
                .hbo()
                .build();
        List<Channel> helpList = new ArrayList<>();
        helpList.add(channel1);
        helpList.add(channel2);
        youngUser = UserBuilder
                .sampleYoungUserBuilder()
                .id(1L)
                .favouriteChannels(helpList)
                .build();
        adultUser = UserBuilder
                .sampleAdultUserBuilder()
                .id(2L)
                .build();
    }
    
    @Before
    public void initMocks() {               
        doAnswer((Answer<Object>) (InvocationOnMock invocation) -> {
            Object argument = invocation.getArguments()[0];
            if(argument == null) {
                throw new IllegalArgumentException();
            }
            
            User user = (User) argument;
            if(user.getId() == null || user.getEmail() == null){
                throw new IllegalArgumentException();
            }
            
            return null;
        }).when(userDao).update(any(User.class));
           
        when(userDao.findById(1L))
            .thenReturn(youngUser);
        
        when(userDao.findById(2L))
            .thenReturn(adultUser);
    }
    
    
    @Test
    public void getFavoriteChannelsTest() {
        List<Channel> expectedChannels = Arrays.asList(channel1, channel2);
        
        List<Channel> actualChannels = favoriteChannelsService.getFavoriteChannels(youngUser);

        assertThat(actualChannels).isEqualTo(expectedChannels);
    }
    
    @Test
    public void getEmptyFavoriteChannelsTest() {
        List<Channel> expectedChannels = new ArrayList<>();
        
        List<Channel> actualChannels = favoriteChannelsService.getFavoriteChannels(adultUser);
        
        assertThat(actualChannels).isEqualTo(expectedChannels);
    }
    
    @Test    
    public void unfollowChannelTest() {
        List<Channel> expectedChannels = Arrays.asList(channel1);
        
        favoriteChannelsService.unfollowChannel(channel2, youngUser);
        List<Channel> actualChannels = youngUser.getFavoriteChannels();
        
        assertThat(actualChannels).isEqualTo(expectedChannels);
    }
    
    @Test  
    public void followChannelTest() {
        List<Channel> expectedChannels = Arrays.asList(channel1);
        
        favoriteChannelsService.followChannel(channel1, adultUser);
        List<Channel> actualChannels = adultUser.getFavoriteChannels();
        
        assertThat(actualChannels).isEqualTo(expectedChannels);
    }    
    
    @Test  
    public void followAlreadyFollowedChannelTest() {
        List<Channel> expectedChannels = Arrays.asList(channel1, channel2);
        
        favoriteChannelsService.followChannel(channel1, youngUser);
        List<Channel> actualChannels = youngUser.getFavoriteChannels();
        
        assertThat(actualChannels).isEqualTo(expectedChannels);
    }

    @Test  
    public void unfollowNotFollowedChannelTest() {
        List<Channel> expectedChannels = Arrays.asList();
        
        favoriteChannelsService.unfollowChannel(channel1, adultUser);
        List<Channel> actualChannels = adultUser.getFavoriteChannels();
        
        assertThat(actualChannels).isEqualTo(expectedChannels);
    }      
}
