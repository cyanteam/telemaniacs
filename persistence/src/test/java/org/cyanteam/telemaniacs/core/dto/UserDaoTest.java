package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.PersistenceContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.enums.Sex;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for UserDao.
 *
 * @author Tomas Milota
 */
@ContextConfiguration(classes = PersistenceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoTest {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private UserDao userDao;

    private User user;
    private User anotherUser;

    @Before
    @Transactional
    public void setUp() {
        user = new User();
        user.setUsername("user");
        user.setEmail("user@mail.com");
        user.setAge(20);
        user.setSex(Sex.MALE);
        user.setPasswordHash("passwordHash");

        anotherUser = new User();
        anotherUser.setUsername("anotherUser");
        anotherUser.setEmail("anotherUser@mail.com");
        anotherUser.setAge(25);
        anotherUser.setSex(Sex.FEMALE);
        anotherUser.setPasswordHash("passwordHash");
        anotherUser.setAdminRights();
    }

    @Test
    public void createUser() {
        userDao.create(user);
        assertThat(user.getId()).isNotNull();

        User actualUser = userDao.findById(user.getId());
        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test(expected = DataAccessException.class)
    public void createUserWithNonUniqueName() {
        anotherUser.setUsername(user.getUsername());
        userDao.create(user);
        userDao.create(anotherUser);
    }

    @Test(expected = DataAccessException.class)
    public void createNullUser() {
        userDao.create(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createUserWithNullName() {
        user.setUsername(null);
        userDao.create(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createUserWithNullEmail() {
        user.setEmail(null);
        userDao.create(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createUserWithNullPasswordHash() {
        user.setPasswordHash(null);
        userDao.create(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createUserWithNullSex() {
        user.setSex(null);
        userDao.create(user);
    }

    @Test
    public void updateUser() {
        userDao.create(user);
        user.setUsername("new username");
        userDao.update(user);

        User actualUser = userDao.findById(user.getId());
        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test(expected = PersistenceException.class)
    public void updateUserWithNonUniqueName() {
        userDao.create(user);
        userDao.create(anotherUser);
        user.setUsername(anotherUser.getUsername());
        userDao.update(user);

        em.flush();
    }

    @Test(expected = DataAccessException.class)
    public void updateNullUser() {
        userDao.update(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateUserWithNullName() {
        userDao.create(user);
        user.setUsername(null);
        userDao.update(user);

        em.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateUserWithNullEmail() {
        userDao.create(user);
        user.setEmail(null);
        userDao.update(user);

        em.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateUserWithNullPasswordHash() {
        userDao.create(user);
        user.setPasswordHash(null);
        userDao.update(user);

        em.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateUserWithNullSex() {
        userDao.create(user);
        user.setSex(null);
        userDao.update(user);

        em.flush();
    }

    @Test
    public void removeUser() {
        userDao.create(user);
        assertThat(userDao.findAll().size()).isEqualTo(1);

        userDao.remove(user);
        assertThat(userDao.findAll().size()).isEqualTo(0);
    }

    @Test(expected = DataAccessException.class)
    public void remoteNullUser() {
        userDao.remove(null);
    }

    @Test(expected = DataAccessException.class)
    public void remoteNonPersistedUser() {
        userDao.remove(user);
    }

    @Test
    public void findAllUsers() {
        assertThat(userDao.findAll()).isNotNull().isEmpty();

        userDao.create(user);
        userDao.create(anotherUser);

        assertThat(userDao.findAll()).containsExactlyInAnyOrder(user, anotherUser);
    }

    @Test
    public void findUserById() {
        userDao.create(user);

        User actualUser = userDao.findById(user.getId());
        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    public void findUserByNonExistingId() {
        assertThat(userDao.findById(1L)).isNull();
    }

    @Test(expected = DataAccessException.class)
    public void findUserByNullId() {
        userDao.findById(null);
    }

    @Test
    public void findUserByName() {
        userDao.create(user);

        User actualUser = userDao.findByUsername(user.getUsername());
        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test(expected = DataAccessException.class)
    public void findUserByNonExistingName() {
        userDao.findByUsername("");
    }

    @Test(expected = DataAccessException.class)
    public void findUserByNullName() {
        userDao.findByUsername(null);
    }

    @Test
    public void findUserByEmail() {
        userDao.create(user);

        User actualUser = userDao.findByEmail(user.getEmail());
        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test(expected = DataAccessException.class)
    public void findUserByNonExistingEmail() {
        userDao.create(user);
        userDao.findByEmail("test");
    }

    @Test(expected = DataAccessException.class)
    public void findUserByNullEmail() {
        userDao.findByEmail(null);
    }
}