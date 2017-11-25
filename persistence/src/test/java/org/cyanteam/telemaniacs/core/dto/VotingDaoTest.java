package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.PersistenceContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.Sex;
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
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for VotingDao.
 *
 * @author Tomas Milota
 */
@ContextConfiguration(classes = PersistenceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class VotingDaoTest {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private VotingDao votingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransmissionDao transmissionDao;

    private User user;
    private Voting voting;
    private Voting anotherVoting;

    @Before
    @Transactional
    public void setUp() {
        user = new User();
        user.setUsername("user");
        user.setEmail("user@mail.com");
        user.setAge(20);
        user.setSex(Sex.MALE);
        user.setPasswordHash("passwordHash");
        userDao.create(user);

        Transmission transmission = TransmissionBuilder.sampleShawshankBuilder().build();
        transmissionDao.create(transmission);

        voting = new Voting();
        voting.setComment("some comment");
        voting.setRank(3);
        voting.setIpAddress("127.0.0.0");
        voting.setUser(user);
        voting.setTransmission(transmission);

        anotherVoting= new Voting();
        anotherVoting.setComment("some comment");
        anotherVoting.setRank(3);
        anotherVoting.setIpAddress("127.0.0.0");
        anotherVoting.setUser(user);
        anotherVoting.setTransmission(transmission);
    }

    @Test
    public void createVoting() {
        votingDao.create(voting);
        assertThat(voting.getId()).isNotNull();

        Voting actualVoting = votingDao.findById(voting.getId());
        assertThat(actualVoting).isEqualToComparingFieldByFieldRecursively(actualVoting);
    }

    @Test(expected = DataAccessException.class)
    public void createNullVoting() {
        votingDao.create(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createWithNullIpAddress() {
        voting.setIpAddress(null);
        votingDao.create(voting);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createWithNonValidIpAddress() {
        voting.setIpAddress("0.0.0.");
        votingDao.create(voting);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createWithNonValidRank() {
        voting.setRank(6);
        votingDao.create(voting);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createWithNegativeRank() {
        voting.setRank(-1);
        votingDao.create(voting);
    }

    @Test(expected = DataAccessException.class)
    public void createWithNullUser() {
        voting.setUser(null);
        votingDao.create(voting);
    }

    @Test(expected = DataAccessException.class)
    public void createWithNullTransmission() {
        voting.setTransmission(null);
        votingDao.create(voting);
    }

    @Test
    public void updateVoting() {
        votingDao.create(voting);

        voting.setRank(5);
        votingDao.update(voting);

        Voting actualVoting = votingDao.findById(voting.getId());
        assertThat(actualVoting).isEqualToComparingFieldByFieldRecursively(voting);
    }

    @Test(expected = DataAccessException.class)
    public void updateNullVoting() {
        votingDao.update(null);
    }


    @Test(expected = ConstraintViolationException.class)
    public void updateWithNullIpAddress() {
        votingDao.create(voting);
        voting.setIpAddress(null);
        votingDao.update(voting);

        em.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateWithNonValidIpAddress() {
        votingDao.create(voting);
        voting.setIpAddress("0.0.0.");
        votingDao.update(voting);

        em.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateWithNonValidRank() {
        votingDao.create(voting);
        voting.setRank(6);
        votingDao.update(voting);

        em.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateWithNegativeRank() {
        votingDao.create(voting);
        voting.setRank(-1);
        votingDao.update(voting);

        em.flush();
    }

    @Test(expected = PersistenceException.class)
    public void updateWithNullUser() {
        votingDao.create(voting);
        voting.setUser(null);
        votingDao.update(voting);

        em.flush();
    }

    @Test(expected = PersistenceException.class)
    public void updateWithNullTransmission() {
        votingDao.create(voting);
        voting.setTransmission(null);
        votingDao.update(voting);

        em.flush();
    }

    @Test
    public void removeVoting() {
        votingDao.create(voting);
        assertThat(userDao.findAll().size()).isEqualTo(1);

        votingDao.remove(voting);
        assertThat(votingDao.findAll().size()).isEqualTo(0);
    }

    @Test(expected = DataAccessException.class)
    public void removeNullVoting() {
        votingDao.remove(null);
    }

    @Test(expected = DataAccessException.class)
    public void remoteNonPersistedVoting() {
        votingDao.remove(voting);
    }

    @Test
    public void findAll() {
        assertThat(votingDao.findAll()).isNotNull().isEmpty();

        votingDao.create(voting);
        votingDao.create(anotherVoting);

        assertThat(votingDao.findAll()).containsExactlyInAnyOrder(voting, anotherVoting);
    }

    @Test
    public void findById() {
        votingDao.create(voting);
        votingDao.create(anotherVoting);

        Voting actualVoting = votingDao.findById(voting.getId());
        assertThat(actualVoting).isEqualToComparingFieldByFieldRecursively(voting);
    }

    @Test
    public void findUserByNonExistingId() {
        assertThat(votingDao.findById(1L)).isNull();
    }

    @Test(expected = DataAccessException.class)
    public void findByNullId() {
        votingDao.findById(null);
    }

    @Test
    public void findByUser() {
        assertThat(votingDao.findByUser(user)).isEmpty();

        votingDao.create(voting);
        votingDao.create(anotherVoting);

        assertThat(votingDao.findByUser(user)).containsExactlyInAnyOrder(voting, anotherVoting);
    }

    @Test(expected = DataAccessException.class)
    public void findByNullUser() {
        votingDao.findByUser(null);
    }

    @Test
    public void findByTransmissionTest() {
        Transmission transmission = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmissionDao.create(transmission);

        Voting iceAgeVoting = new Voting();
        iceAgeVoting.setComment("some comment");
        iceAgeVoting.setRank(4);
        iceAgeVoting.setIpAddress("127.0.0.0");
        iceAgeVoting.setUser(user);
        iceAgeVoting.setTransmission(transmission);
        votingDao.create(iceAgeVoting);

        List<Voting> votings = votingDao.findByTransmission(transmission);
        assertThat(votings.size()).isEqualTo(1);
        assertThat(votings.get(0)).isEqualToComparingFieldByField(iceAgeVoting);
    }

    @Test(expected = DataAccessException.class)
    public void findByTransmissionNullTest() {
        votingDao.findByTransmission(null);
    }

}