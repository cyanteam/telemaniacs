package org.cyanteam.telemaniacs.core.dao;

import org.assertj.core.util.Lists;
import org.cyanteam.telemaniacs.core.PersistenceContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.Gender;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.cyanteam.telemaniacs.core.helpers.TransmissionOccurrenceBuilder;
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
import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * TransmissionOccurrence tests
 * @author Simona Tinkova
 */

@ContextConfiguration(classes = PersistenceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TransmissionOccurrenceDaoTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private TransmissionOccurrenceDao transmissionOccurrenceDao;

	@Autowired
	private TransmissionDao transmissionDao;

	@Autowired
	private UserDao userDao;

	private Channel channel1;
	private Channel channel2;
	private TransmissionOccurrence occurrence1;
	private TransmissionOccurrence occurrence2;
	private Transmission transmission1;
	private Transmission transmission2;

	@Before
	@Transactional
	public void init() {
		prepareChannel1();
		channelDao.create(channel1);

		prepareTransmission1();
		transmissionDao.create(transmission1);

		occurrence1 = new TransmissionOccurrence();
		occurrence1.setPartName("Test occurrence name 1");
		occurrence1.setStartDate(LocalDateTime.now());
		occurrence1.setRerun(false);
		occurrence1.setTransmission(transmission1);
		occurrence1.setChannel(channel1);

		transmissionOccurrenceDao.create(occurrence1);

		prepareChannel2();
		channelDao.create(channel2);

		prepareTransmission2();
		transmissionDao.create(transmission2);

		occurrence2 = new TransmissionOccurrence();
		occurrence2.setPartName("Test occurrence name 2");
		occurrence2.setStartDate(LocalDateTime.now());
		occurrence2.setRerun(true);
		occurrence2.setTransmission(transmission2);
		occurrence2.setChannel(channel2);
	}

	@Test
	public void createOccurence(){
		transmissionOccurrenceDao.create(occurrence2);
		TransmissionOccurrence acOccurence = transmissionOccurrenceDao.findById(occurrence2.getId());

		assertThat(occurrence2.getId()).isNotNull();
		assertThat(occurrence2).isEqualToComparingFieldByField(acOccurence);
	}

	@Test(expected= ConstraintViolationException.class)
	public void createWithNullTimeTest() {
		occurrence2.setStartDate(null);
		transmissionOccurrenceDao.create(occurrence2);
	}

	@Test(expected= DataAccessException.class)
	public void createWithSetIdTest() {
		occurrence2.setId(Long.MIN_VALUE);
		transmissionOccurrenceDao.create(occurrence2);
	}


	@Test(expected= DataAccessException.class)
	public void createWithNullOccurrenceTest() {
		transmissionOccurrenceDao.create(null);
	}

	@Test
	public void removeTest() {
		transmissionOccurrenceDao.create(occurrence2);
		transmissionOccurrenceDao.remove(occurrence2);

		assertThat(findAll())
				.hasSize(1)
				.containsOnly(occurrence1);
	}

	@Test(expected = DataAccessException.class)
	public void removeNonExistingTest() {
		transmissionOccurrenceDao.remove(occurrence2);
	}

	@Test(expected = DataAccessException.class)
	public void removeNullTest() {
		transmissionOccurrenceDao.remove(null);
	}

	@Test
	public void updateTest() {
		transmissionOccurrenceDao.create(occurrence2);
		occurrence2.setPartName("Updated name");

		transmissionOccurrenceDao.update(occurrence2);
		TransmissionOccurrence acTransmission = transmissionOccurrenceDao.findById(occurrence2.getId());

		assertThat(acTransmission).isEqualToComparingFieldByFieldRecursively(occurrence2);
	}

	@Test(expected = ConstraintViolationException.class)
	public void updateWithNullTimeTest() {
		transmissionOccurrenceDao.create(occurrence2);
		occurrence2.setStartDate(null);

		transmissionOccurrenceDao.update(occurrence2);
		entityManager.flush();
	}


	@Test(expected = DataAccessException.class)
	public void updateWithSetIdTest() {
		transmissionOccurrenceDao.create(occurrence2);
		occurrence2.setId(Long.MAX_VALUE);

		transmissionOccurrenceDao.update(occurrence2);
		entityManager.flush();
	}

	@Test(expected = DataAccessException.class)
	public void updateNullTest() {
		transmissionOccurrenceDao.update(null);
	}

	@Test
	public void findByIdTest() {
		transmissionOccurrenceDao.create(occurrence2);

		TransmissionOccurrence acOccurance = transmissionOccurrenceDao.findById(occurrence2.getId());

		assertThat(acOccurance).isEqualToComparingFieldByFieldRecursively(occurrence2);
	}

	@Test
	public void findByIdNonExistingIdTest() {
		TransmissionOccurrence acOccurrance = transmissionOccurrenceDao.findById(transmission1.getId() + Long.valueOf(10));

		assertThat(acOccurrance).isNull();
	}

	@Test(expected = DataAccessException.class)
	public void findByIdNullIdTest() {
		transmissionOccurrenceDao.findById(null);
	}

	@Test
	public void findByChannelAndDateTest() {
		Channel channel = prepareChannel3();
		channelDao.create(channel);

		Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
		transmissionDao.create(transmission);

		TransmissionOccurrenceBuilder builder = new TransmissionOccurrenceBuilder()
				.channel(channel)
				.transmission(transmission);

		TransmissionOccurrence occurrence1 = builder.startDate(2017, 11, 1, 1, 2, 3).build();
		transmissionOccurrenceDao.create(occurrence1);

		TransmissionOccurrence occurrence2 = builder.startDate(2017, 11, 2, 15, 16, 17).build();
		transmissionOccurrenceDao.create(occurrence2);

		TransmissionOccurrence occurrence3 = builder.startDate(2017, 11, 3, 1, 2, 3).build();
		transmissionOccurrenceDao.create(occurrence3);

		TransmissionOccurrence occurrence4 = builder.channel(channel1).build();
		transmissionOccurrenceDao.create(occurrence4);

		LocalDateTime start = LocalDateTime.of(2017, 11, 2, 14, 0, 0);
		List<TransmissionOccurrence> occurrences = transmissionOccurrenceDao.findByChannelAndDate(channel, start);

		assertThat(occurrences.size()).isEqualTo(2);
		assertThat(occurrences).containsExactlyInAnyOrder(occurrence2, occurrence3);
	}

	@Test
	public void findByTransmissionAndDateTest() {
		Channel channel = prepareChannel3();
		channelDao.create(channel);

		Transmission transmission1 = TransmissionBuilder.sampleIceAgeBuilder().build();
		transmissionDao.create(transmission1);

		Transmission transmission2 = TransmissionBuilder.sampleShawshankBuilder().build();
		transmissionDao.create(transmission2);

		TransmissionOccurrenceBuilder builder = new TransmissionOccurrenceBuilder()
				.channel(channel)
				.transmission(transmission1);

		TransmissionOccurrence occurrence1 = builder.startDate(2017, 11, 1, 1, 2, 3).build();
		transmissionOccurrenceDao.create(occurrence1);

		TransmissionOccurrence occurrence2 = builder.startDate(2017, 11, 2, 15, 16, 17).build();
		transmissionOccurrenceDao.create(occurrence2);

		TransmissionOccurrence occurrence3 = builder.startDate(2017, 11, 3, 1, 2, 3).build();
		transmissionOccurrenceDao.create(occurrence3);

		TransmissionOccurrence occurrence4 = builder.transmission(transmission2).build();
		transmissionOccurrenceDao.create(occurrence4);

		LocalDateTime start = LocalDateTime.of(2017, 11, 2, 14, 0, 0);
		List<TransmissionOccurrence> occurrences = transmissionOccurrenceDao.findByTransmissionAndDate(transmission1, start);

		assertThat(occurrences.size()).isEqualTo(2);
		assertThat(occurrences).containsExactlyInAnyOrder(occurrence2, occurrence3);
	}

	@Test
	public void findByUserAndDateTest() {
		Channel channel = prepareChannel3();
		channelDao.create(channel);

		Transmission transmission1 = TransmissionBuilder.sampleIceAgeBuilder().build();
		transmissionDao.create(transmission1);

		Transmission transmission2 = TransmissionBuilder.sampleShawshankBuilder().build();
		transmissionDao.create(transmission2);

		TransmissionOccurrenceBuilder builder = new TransmissionOccurrenceBuilder()
				.channel(channel)
				.transmission(transmission1);

		TransmissionOccurrence occurrence1 = builder.startDate(2017, 11, 1, 1, 2, 3).build();
		transmissionOccurrenceDao.create(occurrence1);

		TransmissionOccurrence occurrence2 = builder
				.transmission(transmission1)
				.startDate(2017, 11, 2, 15, 16, 17)
				.build();
		transmissionOccurrenceDao.create(occurrence2);

		TransmissionOccurrence occurrence3 = builder
				.transmission(transmission2)
				.startDate(2017, 11, 3, 1, 2, 3)
				.build();
		transmissionOccurrenceDao.create(occurrence3);

		User user = prepareUser();
		user.setFavoriteTransmissions(Lists.newArrayList(transmission1, transmission2));
		userDao.create(user);

		LocalDateTime start = LocalDateTime.of(2017, 11, 3, 0, 0);
		List<TransmissionOccurrence> occurrences = transmissionOccurrenceDao.findByUserAndDate(user, start);

		assertThat(occurrences).hasSize(1);
		assertThat(occurrences).containsExactly(occurrence3);
	}

	private void prepareChannel1(){
		channel1 = new Channel();
		channel1.setLanguage("CZECH");
		channel1.setName("Test name channel 1");
		channel1.setChannelType(ChannelType.CHILDREN);
		channel1.setTransmissionOccurrences(new ArrayList<>());
	}

	private void prepareChannel2(){
		channel2 = new Channel();
		channel2.setLanguage("ENGLISH");
		channel2.setName("Test name channel 2");
		channel2.setChannelType(ChannelType.SPORT);
		channel2.setTransmissionOccurrences(new ArrayList<>());
	}

	private void prepareTransmission1() {
		transmission1 = new Transmission();
		transmission1.setAgeAvailability(AgeAvailability.AGE15);
		transmission1.setDescription("Test transmission description 1");
		transmission1.setLanguage("CZECH");
		transmission1.setLength(120);
		transmission1.setName("Test transmission name 1");
		transmission1.setTransmissionType(TransmissionType.MOVIE);
		transmission1.setOccurrences(new ArrayList<>());
	}

	private void prepareTransmission2() {
		transmission2 = new Transmission();
		transmission2.setAgeAvailability(AgeAvailability.AGE12);
		transmission2.setDescription("Test transmission description 2");
		transmission2.setLanguage("ENGLISH");
		transmission2.setLength(60);
		transmission2.setName("Test transmission name 2");
		transmission2.setTransmissionType(TransmissionType.TV_SERIES);
		transmission2.setOccurrences(new ArrayList<>());
	}

	private List<TransmissionOccurrence> findAll() {
		return entityManager
				.createQuery("SELECT t FROM TransmissionOccurrence t", TransmissionOccurrence.class)
				.getResultList();
	}

	private Channel prepareChannel3() {
		Channel channel = new Channel();
		channel.setName("Third channel");
		channel.setLanguage("CZ");
		channel.setChannelType(ChannelType.DOCUMENTARY);
		return channel;
	}

	private User prepareUser() {
		User user = new User();
		user.setUsername("user");
		user.setEmail("user@mail.com");
		user.setAge(20);
		user.setGender(Gender.MALE);
		user.setPasswordHash("passwordHash");
		return user;
	}
}
