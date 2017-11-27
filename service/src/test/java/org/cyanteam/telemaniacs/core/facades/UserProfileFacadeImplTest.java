package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.dto.UserVotingDto;
import org.cyanteam.telemaniacs.core.dto.VotingDTO;
import org.cyanteam.telemaniacs.core.entities.*;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.Sex;
import org.cyanteam.telemaniacs.core.facade.UserProfileFacade;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.cyanteam.telemaniacs.core.services.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cyanteam.telemaniacs.core.utils.ListUtils.createList;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for user profile facade
 * @author Michael Le
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileFacadeImplTest {
    @Inject
    @Spy
    private ObjectMapperService mapper;

    @Mock
    private UserService userService;

    @Mock
    private ChannelService channelService;

    @Mock
    private TransmissionService transmissionService;

    @Mock
    private FavoriteChannelsService favoriteChannelsService;

    @Mock
    private FavoriteTransmissionsService favoriteTransmissionsService;

    @Mock
    private VotingService votingService;

    @InjectMocks
    // Must be instantiated directly, because @Transactional overrides proxy
    private UserProfileFacade userProfileFacade = new UserProfileFacadeImpl();

    private User user;
    private Channel channel;
    private Transmission transmission;
    private Voting voting;
    private Voting votingWithoutId;

    private final int timespan = 10;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        user = createTestUser();
        channel = createTestChannel();
        transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        voting = createTestVoting(user, transmission);
        votingWithoutId = createTestVoting(user, transmission);
        votingWithoutId.setId(null);

        when(userService.findUserById(user.getId()))
                .thenReturn(user);
        when(channelService.findById(channel.getId()))
                .thenReturn(channel);
        when(transmissionService.getTransmissionById(transmission.getId()))
                .thenReturn(transmission);
        when(favoriteChannelsService.getFavoriteChannels(user))
                .thenReturn(createList(channel));
        when(favoriteTransmissionsService.getFavoriteTransmissionsByUser(user))
                .thenReturn(createList(transmission));
        when(favoriteTransmissionsService.getUpcomingFavoriteTransmissionsByUser(eq(user), any()))
                .thenReturn(createList(transmission));
        when(votingService.getVotingById(voting.getId()))
                .thenReturn(voting);
        when(votingService.getVotingByUser(user))
                .thenReturn(createList(voting));
    }

    @Test
    public void followChannelTest() {
        when(userService.findUserById(any()))
                .thenReturn(user);
        User u = userService.findUserById(user.getId());

        userProfileFacade.followChannel(user.getId(), channel.getId());
        verify(favoriteChannelsService).followChannel(channel, user);
    }

    @Test
    public void unfollowChannelTest() {
        userProfileFacade.unfollowChannel(user.getId(), channel.getId());
        verify(favoriteChannelsService).unfollowChannel(channel, user);
    }

    @Test
    public void getFavoriteChannelsTest() {
        List<ChannelDTO> channels = userProfileFacade.getFavoriteChannels(user.getId());
        verify(favoriteChannelsService).getFavoriteChannels(user);
    }

    @Test
    public void followTransmissionTest() {
        userProfileFacade.followTransmission(user.getId(), transmission.getId());
        verify(favoriteTransmissionsService).followTransmission(transmission, user);
    }

    @Test
    public void unfollowTransmissionTest() {
        userProfileFacade.unfollowTransmission(user.getId(), transmission.getId());
        verify(favoriteTransmissionsService).unfollowTransmission(transmission, user);
    }

    @Test
    public void getFavoriteTransmissionsByUserTest() {
        List<TransmissionDTO> transmissions = userProfileFacade.getFavoriteTransmissionsByUser(user.getId());
        verify(favoriteTransmissionsService).getFavoriteTransmissionsByUser(user);

        TransmissionDTO transmissionDto = transmissions.get(0);
        checkTransmissionMapping(transmissionDto, transmission);
    }

    @Test
    public void getUpcomingFavoriteTransmissionsByUser() {
        List<TransmissionDTO> transmissions = userProfileFacade
                .getUpcomingFavoriteTransmissionsByUser(user.getId(), timespan);
        verify(favoriteTransmissionsService).getUpcomingFavoriteTransmissionsByUser(user, Duration.ofSeconds(10));

        TransmissionDTO transmissionDto = transmissions.get(0);
        checkTransmissionMapping(transmissionDto, transmission);
    }

    @Test
    public void voteCreateTest() {
        UserVotingDto userVoting = createUserVoting();
        when(votingService.getVotingByUser(user)).thenReturn(createList());

        userProfileFacade.vote(user.getId(), transmission.getId(), userVoting);
        verify(votingService).createVoting(argThat(new ArgumentMatcher<Voting>() {
            @Override
            public boolean matches(Object argument) {
                Voting voting = (Voting) argument;
                return checkUserVoting(voting, userVoting)
                        && voting.getId() == null
                        && voting.getUser().equals(user)
                        && voting.getTransmission().equals(transmission);
            }
        }));
    }

    @Test
    public void voteUpdateTest() {
        UserVotingDto userVoting = createUserVoting();
        when(votingService.getVotingByUser(user)).thenReturn(createList(voting));

        userProfileFacade.vote(user.getId(), transmission.getId(), userVoting);
        verify(votingService).updateVoting(argThat(new ArgumentMatcher<Voting>() {
            @Override
            public boolean matches(Object argument) {
                Voting voting = (Voting) argument;
                return checkUserVoting(voting, userVoting)
                        && Objects.equals(voting.getId(), voting.getId())
                        && voting.getUser().equals(user)
                        && voting.getTransmission().equals(transmission);
            }
        }));
    }

    @Test
    public void removeVotingTest() {
        userProfileFacade.removeVoting(voting.getId());
        verify(votingService).removeVoting(voting);
    }

    @Test
    public void getVotingsByUserTest() {
        List<VotingDTO> votings = userProfileFacade.getVotingsByUser(user.getId());
        verify(votingService).getVotingByUser(user);

        VotingDTO votingDto = votings.get(0);
        checkVotingMapping(votingDto, voting);
    }

    private User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Jan Novak");
        user.setEmail("jan.novak@gmail.com");
        user.setSex(Sex.MALE);
        user.setAge(22);
        user.setPasswordHash("ABCDE");

        return user;
    }

    private UserVotingDto createUserVoting() {
        UserVotingDto userVoting = new UserVotingDto();
        userVoting.setRank(5);
        userVoting.setComment("Comment");

        return userVoting;
    }

    private Channel createTestChannel() {
        Channel channel = new Channel();
        channel.setId(1L);
        channel.setName("Discovery Channel");
        channel.setLanguage("EN");
        channel.setChannelType(ChannelType.DOCUMENTARY);

        return channel;
    }

    private Voting createTestVoting(User user, Transmission transmission) {
        Voting voting = new Voting();
        voting.setId(1L);
        voting.setUser(user);
        voting.setTransmission(transmission);
        voting.setRank(2);
        voting.setComment("Updated");

        return voting;
    }

    private void checkTransmissionMapping(TransmissionDTO transmissionDto, Transmission transmission) {
        assertThat(transmissionDto.getId()).isEqualTo(transmission.getId());
        assertThat(transmissionDto.getName()).isEqualTo(transmission.getName());
        assertThat(transmissionDto.getDescription()).isEqualTo(transmission.getDescription());
        assertThat(transmissionDto.getLength()).isEqualTo(transmission.getLength());
        assertThat(transmissionDto.getTransmissionType()).isEqualTo(transmission.getTransmissionType());
        assertThat(transmissionDto.getLanguage()).isEqualTo(transmission.getLanguage());
    }

    private void checkVotingMapping(VotingDTO votingDto, Voting voting) {
        assertThat(votingDto.getId()).isEqualTo(voting.getId());
        assertThat(voting.getUser()).isEqualTo(voting.getUser());
        assertThat(voting.getTransmission()).isEqualTo(voting.getTransmission());
        assertThat(voting.getRank()).isEqualTo(voting.getRank());
        assertThat(voting.getComment()).isEqualTo(voting.getComment());
    }

    private boolean checkUserVoting(Voting voting, UserVotingDto userVoting) {
        return voting.getRank() == userVoting.getRank()
                && voting.getComment().equals(userVoting.getComment());
    }
}