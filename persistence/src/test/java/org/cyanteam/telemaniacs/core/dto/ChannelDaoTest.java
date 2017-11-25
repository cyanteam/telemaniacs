package org.cyanteam.telemaniacs.core.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import org.cyanteam.telemaniacs.core.PersistenceContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;

/**
 * Test class for ChannelDao.
 * @author Miroslav Kubus
 */
@ContextConfiguration(classes = PersistenceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChannelDaoTest {
    @PersistenceContext
    public EntityManager em;

    @Autowired
    public ChannelDao channelDao;

    @Autowired
    public TransmissionOccurrenceDao transmissionOccurrenceDao;

    @Autowired
    public TransmissionDao transmissionDao;

    private Channel channel1;
    private Channel channel2;
    private TransmissionOccurrence occurence1;
    private Transmission transmission1;

    @Before
    @Transactional
    public void init() {
        prepareTransmission();
        transmissionDao.create(transmission1);

        channel1 = new Channel();
        channel1.setLanguage("CZECH");
        channel1.setName("Test czech channel");
        channel1.setChannelType(ChannelType.COMMERCE);
        channel1.setTransmissionOccurrences(new ArrayList<>());

        channelDao.create(channel1);

        prepareTransmissionOccurrence();
        transmissionOccurrenceDao.create(occurence1);

        List<TransmissionOccurrence> occurrenceList1 = new ArrayList<>();
        occurrenceList1.add(occurence1);

        channel2 = new Channel();
        channel2.setLanguage("SLOVAK");
        channel2.setName("Test slovak channel");
        channel2.setChannelType(ChannelType.SPORT);
        channel2.setTransmissionOccurrences(occurrenceList1);
    }

    @Test
    public void createTest() {
        channelDao.create(channel2);
        Channel actualChannel = channelDao.findById(channel2.getId());

        assertThat(channel2.getId()).isNotNull();
        assertThat(actualChannel).isEqualToComparingFieldByFieldRecursively(channel2);
    }

    @Test(expected= DataAccessException.class)
    public void createWithSetIdTest() {
        channel2.setId(Long.MIN_VALUE);
        channelDao.create(channel2);
    }

    @Test(expected= DataAccessException.class)
    public void createWithNonUniqueNameTest() {
        channel2.setName(channel1.getName());
        channelDao.create(channel2);
    }

    @Test(expected= ConstraintViolationException.class)
    public void createWithNullNameTest() {
        channel2.setName(null);
        channelDao.create(channel2);
    }

    @Test(expected= DataAccessException.class)
    public void createWithNullChannelTest() {
        channelDao.create(null);
    }

    @Test
    public void removeTest() {
        channelDao.create(channel2);

        channelDao.remove(channel2);

        assertThat(channelDao.findAll())
                .hasSize(1)
                .containsOnly(channel1);
    }

    @Test(expected = DataAccessException.class)
    public void removeNonExistingTest() {
        channelDao.remove(channel2);
    }

    @Test(expected = DataAccessException.class)
    public void removeNullTest() {
        channelDao.remove(null);
    }

    @Test
    public void updateTest() {
        channelDao.create(channel2);
        channel2.setName("Updated name");

        channelDao.update(channel2);
        Channel actualChannel2 = channelDao.findById(channel2.getId());


        assertThat(actualChannel2).isEqualToComparingFieldByFieldRecursively(channel2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateWithNullNameTest() {
        channelDao.create(channel2);
        channel2.setName(null);

        channelDao.update(channel2);
        em.flush();
    }

    @Test(expected = PersistenceException.class)
    public void updateWithNonUniqueNameTest() {
        channelDao.create(channel2);
        channel2.setName(channel1.getName());

        channelDao.update(channel2);
        em.flush();
    }

    @Test(expected = DataAccessException.class)
    public void updateWithSetIdTest() {
        channelDao.create(channel2);
        channel2.setId(Long.MAX_VALUE);

        channelDao.update(channel2);
        em.flush();
    }

    @Test(expected = DataAccessException.class)
    public void updateNullTest() {
        channelDao.update(null);
    }

    @Test
    public void findByIdTest() {
        channelDao.create(channel2);

        Channel actualChannel = channelDao.findById(channel2.getId());

        assertThat(actualChannel).isEqualToComparingFieldByFieldRecursively(channel2);
    }

    @Test
    public void findByIdNonExistingIdTest() {
        Channel actualChannel = channelDao.findById(channel1.getId() + Long.valueOf(10));

        assertThat(actualChannel).isNull();
    }

    @Test(expected = DataAccessException.class)
    public void findByIdNullIdTest() {
        channelDao.findById(null);
    }

    @Test
    public void findByNameTest() {
        channelDao.create(channel2);

        Channel actualChannel = channelDao.findByName(channel2.getName());

        assertThat(actualChannel).isEqualToComparingFieldByFieldRecursively(channel2);
    }

    @Test(expected = DataAccessException.class)
    public void findByNameNonExistingNameTest() {
        channelDao.findByName(channel2.getName());
    }

    @Test(expected = DataAccessException.class)
    public void findByNameNullNameTest() {
        channelDao.findByName(null);
    }

    @Test
    public void findAllChannel() {
        channelDao.create(channel2);

        List<Channel> actualChannels = channelDao.findAll();

        assertThat(actualChannels)
                .hasSize(2)
                .contains(channel1, channel2);
    }

    private void prepareTransmission() {
        transmission1 = new Transmission();
        transmission1.setAgeAvailability(AgeAvailability.AGE12);
        transmission1.setDescription("Test description");
        transmission1.setLanguage("CZECH");
        transmission1.setLength(123);
        transmission1.setName("Test name");
        transmission1.setTransmissionType(TransmissionType.MOVIE);
        transmission1.setOccurrences(new ArrayList<>());
    }

    private void prepareTransmissionOccurrence() {
        occurence1 = new TransmissionOccurrence();
        occurence1.setPartName("Test episode");
        occurence1.setStartDate(LocalDateTime.now());
        occurence1.setRerun(false);
        occurence1.setTransmission(transmission1);
        occurence1.setChannel(channel1);
    }
}
