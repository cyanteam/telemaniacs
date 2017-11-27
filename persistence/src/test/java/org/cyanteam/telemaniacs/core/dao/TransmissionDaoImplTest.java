package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.PersistenceContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.*;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.Sex;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Transmission DAO implementation
 * @author Michael Le
 */
@ContextConfiguration(classes = PersistenceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TransmissionDaoImplTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransmissionDao transmissionDao;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private UserDao userDao;

    private Transmission sampleTransmission1;
    private Transmission sampleTransmission2;
    private Channel sampleChannel;
    private User sampleUser;

    @Before
    @Transactional
    public void setUp() {
        sampleTransmission1 = TransmissionBuilder.sampleShawshankBuilder().build();
        transmissionDao.create(sampleTransmission1);

        sampleTransmission2 = TransmissionBuilder.sampleFootballBuilder().build();
        transmissionDao.create(sampleTransmission2);

        sampleChannel = createSampleChannel();
        channelDao.create(sampleChannel);

        sampleUser = createSampleUser();
        userDao.create(sampleUser);
    }

    @Test
    public void createTest() {
        int countBefore = countAll();

        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);

        int countAfter = countAll();
        assertThat(countAfter)
                .as("Number of items")
                .isEqualTo(countBefore + 1);

        assertThat(transmission.getId())
                .as("Correct item ID")
                .isNotNull()
                .isGreaterThan(0);

        Transmission transmissionDatabase = transmissionDao.findById(transmission.getId());
        assertThat(transmissionDatabase)
                .as("Correct values")
                .isEqualToComparingFieldByField(transmission);
    }

    @Test(expected = DataAccessException.class)
    public void createNullArgumentTest() {
        transmissionDao.create(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createNameNotNullTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().name(null).build();
        transmissionDao.create(transmission);
    }

    @Test
    public void createWithOccurrenceTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        TransmissionOccurrence occurrence = createSampleTransmissionOccurrence(transmission);
        transmission.setOccurrences(createEntityList(occurrence));
        transmissionDao.create(transmission);

        assertOccurrences(transmission, occurrence, false);
    }

    @Test
    public void createWithVotingTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        Voting voting = createSampleVoting(transmission);
        transmission.setVoting(createEntityList(voting));
        transmissionDao.create(transmission);

        assertVotings(transmission, voting, false);
    }

    @Test
    public void deleteTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);
        transmissionDao.delete(transmission);

        assertThat(countAll())
                .as("Number of transmissions after delete")
                .isEqualTo(2);
        assertSampleTransmissionsUnchanged();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullArgumentTest() {
        transmissionDao.delete(null);
    }

    @Test
    public void updateTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);

        transmission.setName("Ice Age 11");
        transmission.setDescription("The last episode");
        transmission.setLength(111);
        transmission.setAgeAvailability(AgeAvailability.AGE12);
        transmission.setLanguage("RU");
        transmission.setTransmissionType(TransmissionType.MOVIE);
        transmissionDao.update(transmission);

        assertThat(countAll())
                .as("Number of transmissions after update")
                .isEqualTo(3);
        assertThat(transmissionDao.findById(transmission.getId()))
                .as("Correct update")
                .isEqualToComparingFieldByField(transmission);
        assertSampleTransmissionsUnchanged();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullArgumentTest() {
        transmissionDao.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTransmissionNullIdTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);

        transmission.setId(null);
        transmissionDao.update(transmission);
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateTransmissionNullNameTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);

        transmission.setName(null);
        transmissionDao.update(transmission);
        entityManager.flush();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateInvalidTransmissionTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.update(transmission);
    }

    @Test
    public void updateWithOccurrenceTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);

        TransmissionOccurrence occurrence = createSampleTransmissionOccurrence(transmission);
        transmission.setOccurrences(createEntityList(occurrence));
        transmissionDao.update(transmission);
        entityManager.flush();

        assertOccurrences(transmission, occurrence, true);
    }

    @Test
    public void updateWithVotingTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);

        Voting voting = createSampleVoting(transmission);
        transmission.setVoting(createEntityList(voting));
        transmissionDao.update(transmission);
        entityManager.flush();

        assertVotings(transmission, voting, true);
    }


    private void assertSampleTransmissionsUnchanged() {
        assertTransmissionUnchanged(sampleTransmission1);
        assertTransmissionUnchanged(sampleTransmission2);
    }

    private void assertTransmissionUnchanged(Transmission transmission) {
        assertThat(transmissionDao.findById(transmission.getId()))
                .as("Transmission %s unchanged", transmission.getName())
                .isEqualToComparingFieldByField(transmission);
    }

    private void assertOccurrences(Transmission transmission, TransmissionOccurrence occurrence, boolean skipId) {
        Transmission transmissionDatabase = transmissionDao.findById(transmission.getId());
        List<TransmissionOccurrence> occurrencesDatabase = transmissionDatabase.getOccurrences();
        assertThat(occurrencesDatabase.size())
                .as("Number of related TransmissionOccurences")
                .isEqualTo(1);

        TransmissionOccurrence occurrenceDatabase = occurrencesDatabase.get(0);
        if (!skipId) {
            assertThat(occurrenceDatabase.getId())
                    .as("Correct TransmissionOccurences")
                    .isEqualTo(occurrence.getId());
        }
        assertThat(occurrenceDatabase)
                .as("Correct TransmissionOccurences")
                .isEqualToIgnoringGivenFields(occurrence, "id");
    }

    private void assertVotings(Transmission transmission, Voting voting, boolean skipId) {
        Transmission transmissionDatabase = transmissionDao.findById(transmission.getId());
        List<Voting> votingsDatabase = transmissionDatabase.getVoting();
        assertThat(votingsDatabase.size())
                .as("Number of related Votings")
                .isEqualTo(1);

        Voting votingDatabase = votingsDatabase.get(0);
        if (!skipId) {
            assertThat(votingDatabase.getId())
                    .as("Correct Votings")
                    .isEqualTo(voting.getId());
        }
        assertThat(votingDatabase)
                .as("Correct Votings")
                .isEqualToIgnoringGivenFields(voting, "id");
    }

    @Test
    public void findByIdTest() {
        Transmission transmissionDatabase = transmissionDao.findById(sampleTransmission1.getId());
        assertThat(transmissionDatabase)
                .isEqualToComparingFieldByField(sampleTransmission1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdNullParameterTest() {
        transmissionDao.findById(null);
    }

    @Test
    public void findByIdNonExistingTest() {
        Transmission transmissionDatabase = transmissionDao.findById(Long.MAX_VALUE);
        assertThat(transmissionDatabase).isNull();
    }

    @Test
    public void findByNameTest() {
        Transmission transmissionDatabase = transmissionDao.findByName(sampleTransmission1.getName());
        assertThat(transmissionDatabase)
                .as("Correct transmission returned by findByName")
                .isEqualToComparingFieldByField(sampleTransmission1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNameNullParameterTest() {
        transmissionDao.findByName(null);
    }


    private List<Transmission> findAll() {
        return entityManager
                .createQuery("SELECT t FROM Transmission t", Transmission.class)
                .getResultList();
    }

    private int countAll() {
        return findAll().size();
    }


    private Channel createSampleChannel() {
        Channel channel = new Channel();
        channel.setName("Discovery Channel");
        channel.setChannelType(ChannelType.DOCUMENTARY);
        channel.setLanguage("EN");

        return channel;
    }

    private User createSampleUser() {
        User user = new User();
        user.setUsername("novakjan");
        user.setPasswordHash("password");
        user.setSex(Sex.MALE);
        user.setAge(40);
        user.setEmail("jan@novak.net");

        return user;
    }

    private TransmissionOccurrence createSampleTransmissionOccurrence(Transmission transmission) {
        TransmissionOccurrence occurrence = new TransmissionOccurrence();
        occurrence.setPartName("First episode");
        occurrence.setChannel(sampleChannel);
        occurrence.setStartDate(LocalDateTime.now());
        occurrence.setTransmission(transmission);

        return occurrence;
    }

    private Voting createSampleVoting(Transmission transmission) {
        Voting voting = new Voting();
        voting.setIpAddress("127.0.0.1");
        voting.setRank(5);
        voting.setComment("I like it.");
        voting.setUser(sampleUser);
        voting.setTransmission(transmission);

        return voting;
    }

    private <T> List<T> createEntityList(T entity) {
        List<T> list = new ArrayList<>();
        list.add(entity);

        return list;
    }
}