package org.cyanteam.telemaniacs.core.services;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Matchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.VotingDao;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.cyanteam.telemaniacs.core.helpers.UserBuilder;
import org.cyanteam.telemaniacs.core.helpers.VotingBuilder;
import org.cyanteam.telemaniacs.core.utils.TvManagerDataAccessException;

/**
 * Tests for class VotingServiceImpl.
 * @author Miroslav Kubus
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class VotingServiceImplTest {
    
    @Mock
    private VotingDao votingDao;
    
    @InjectMocks
    private final VotingService votingService = new VotingServiceImpl();
    
    private final VotingBuilder votingBuilder = new VotingBuilder();
    private Voting badVoting;
    private User youngUser;
    private User adultUser;
    private Transmission sportTransmission;
    private Voting mediumVoting;
    private Voting goodVoting;
    
    @Before
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        
        adultUser = UserBuilder
                .sampleAdultUserBuilder()
                .build();
        sportTransmission = TransmissionBuilder
                .sampleFootballBuilder()
                .build();
        youngUser = UserBuilder
                .sampleYoungUserBuilder()
                .build();
        badVoting = VotingBuilder
                .sampleLowVotingBuilder()
                .user(youngUser)
                .transmission(sportTransmission)
                .build();
        mediumVoting = VotingBuilder
                .sampleMediumVotingBuilder()
                .user(youngUser)
                .transmission(sportTransmission)
                .build();
        goodVoting = VotingBuilder
                .sampleHighVotingBuilder()
                .user(youngUser)
                .transmission(sportTransmission)
                .build();
        
        votingDao.create(mediumVoting);
        votingDao.create(goodVoting);
    }
    
    @Before
    public void initMocks() {
        doAnswer((Answer<Object>) (InvocationOnMock invocation) -> {
            Object argument = invocation.getArguments()[0];
            if(argument == null) {
                throw new IllegalArgumentException();
            }
            
            Voting voting = (Voting) argument;
            if(voting.getId() != null){
                throw new IllegalArgumentException();
            }
            
            voting.setId(Long.valueOf(1));
            return null;
        }).when(votingDao).create(any(Voting.class));
        
        doAnswer((Answer<Object>) (InvocationOnMock invocation) -> {
            Object argument = invocation.getArguments()[0];
            if(argument == null) {
                throw new IllegalArgumentException();
            }
            
            Voting voting = (Voting) argument;
            if(voting.getId() == null){
                throw new IllegalArgumentException();
            }
            
            return null;
        }).when(votingDao).remove(any(Voting.class));
        
        doAnswer((Answer<Object>) (InvocationOnMock invocation) -> {
            Object argument = invocation.getArguments()[0];
            if(argument == null) {
                throw new IllegalArgumentException();
            }
            
            Voting voting = (Voting) argument;
            if(voting.getId() == null || voting.getIpAddress() == null){
                throw new IllegalArgumentException();
            }
            
            if(voting.getRank() < 0 || voting.getRank() > 5) {
                throw new IllegalArgumentException();
            }
            
            return null;
        }).when(votingDao).update(any(Voting.class));
        
        when(votingDao.findByTransmission(sportTransmission))
            .thenReturn(Arrays.asList(mediumVoting, goodVoting));
        
        when(votingDao.findByUser(youngUser))
            .thenReturn(Arrays.asList(badVoting, mediumVoting, goodVoting));
        
        when(votingDao.findByUser(adultUser))
            .thenReturn(null);
        
        when(votingDao.findById(Long.valueOf(1)))
            .thenReturn(mediumVoting);
        
        when(votingDao.findAll())
            .thenReturn(Arrays.asList(badVoting, mediumVoting, goodVoting));
    }
    
    @Test
    public void getByIdTest() throws TvManagerDataAccessException{
        Voting voting = votingService.getVotingById(Long.valueOf(1));
        
        assertThat(voting).isNotNull();
        assertThat(voting).isEqualToComparingFieldByField(mediumVoting);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getByIdNullTest() throws TvManagerDataAccessException{
        votingService.getVotingById(null);
    }
    
    @Test
    public void getByUserTest() throws TvManagerDataAccessException{
        List<Voting> votings = votingService.getVotingByUser(youngUser);
        
        assertThat(votings).isNotNull();
        assertThat(votings.size()).isEqualTo(3);
        assertThat(votings).containsSequence(badVoting, mediumVoting, goodVoting);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getByNullUserTest() throws TvManagerDataAccessException{
        votingService.getVotingByUser(null);
    }
    
    @Test
    public void getByNonExistingUserTest() throws TvManagerDataAccessException{
        List<Voting> voting = votingService.getVotingByUser(adultUser);
        
        assertThat(voting).isNull();
    }
    
    @Test
    public void getByTransmissionTest() throws TvManagerDataAccessException{
        List<Voting> votings = votingService.getVotingsByTransmission(sportTransmission);
        
        assertThat(votings).isNotNull();
        assertThat(votings.size()).isEqualTo(2);
        assertThat(votings).containsSequence(mediumVoting, goodVoting);
    }
    
    @Test
    public void getAllTest() throws TvManagerDataAccessException{
        List<Voting> votings = votingService.getAllVotings();
        
        assertThat(votings).isNotNull();
        assertThat(votings.size()).isEqualTo(3);
        assertThat(votings).containsSequence(badVoting, mediumVoting, goodVoting);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getByNullTransmissionTest() throws TvManagerDataAccessException{
        votingService.getVotingsByTransmission(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createNullTest() throws TvManagerDataAccessException {
        votingService.createVoting(null);
    }
    
    @Test
    public void createTest() throws TvManagerDataAccessException {
        votingService.createVoting(badVoting);
        
        assertThat(badVoting.getId()).isNotNull();
    }
    
    @Test(expected = TvManagerDataAccessException.class)
    public void createWitSetIdTest() throws TvManagerDataAccessException {
        Voting newVoting = votingBuilder.id(1L).build();
        
        votingService.createVoting(newVoting);
    }
    
    @Test(expected = TvManagerDataAccessException.class)
    public void removeWithIdNullTest() throws TvManagerDataAccessException {
        Voting newVoting = votingBuilder.id(null).build();

        votingService.removeVoting(newVoting);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullTest() throws TvManagerDataAccessException {
        votingService.removeVoting(null);
    }
    
    @Test(expected = TvManagerDataAccessException.class)
    public void removeTest() throws TvManagerDataAccessException {
        Voting toBeRemoved = goodVoting;

        when(votingDao.findById(toBeRemoved.getId())).thenReturn(toBeRemoved);

        assertThat(votingDao.findById(toBeRemoved.getId())).isNotNull();
        assertThat(votingDao.findById(toBeRemoved.getId())).isEqualTo(toBeRemoved);

        votingService.removeVoting(toBeRemoved);
        votingService.getVotingById(toBeRemoved.getId());
    }
    
    @Test
    public void updateValidTest() throws TvManagerDataAccessException {
        badVoting.setId(Long.MIN_VALUE);
        badVoting.setComment("NewComment");
        
        votingService.updateVoting(badVoting);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateNullTest() throws TvManagerDataAccessException {
        votingService.updateVoting(null);
    }

    @Test(expected = TvManagerDataAccessException.class)
    public void updateNullIpAddressTest() throws TvManagerDataAccessException {
        Voting voting = votingBuilder
                .id(5l)
                .ipAddress(null)
                .build();
        
        votingService.updateVoting(voting);
    }

    @Test(expected = TvManagerDataAccessException.class)
    public void updateNullIdTest() throws TvManagerDataAccessException {
        Voting voting = votingBuilder
                .id(null)
                .build();

        votingService.updateVoting(voting);
    }
    
    @Test(expected = TvManagerDataAccessException.class)
    public void updateHigherRankTest() throws TvManagerDataAccessException {
        Voting voting = votingBuilder
                .rank(100)
                .build();

        votingService.updateVoting(voting);
    }
    
    @Test(expected = TvManagerDataAccessException.class)
    public void updateLowerRankTest() throws TvManagerDataAccessException {
        Voting voting = votingBuilder
                .rank(-5)
                .build();

        votingService.updateVoting(voting);
    }
}
