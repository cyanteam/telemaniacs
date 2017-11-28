package org.cyanteam.telemaniacs.core.services;

import org.assertj.core.util.Lists;
import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dao.UserDao;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.helpers.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Tomas Milota
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Inject
    @InjectMocks
    private UserService userService;

    private User user;
    private User admin;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = UserBuilder
                .sampleYoungUserBuilder()
                .id(1L)
                .build();
        admin = UserBuilder
                .sampleAdminBuilder()
                .id(2L)
                .build();
    }

    @Test
    public void createUserTest() {
        userService.create(user, "pass");
        verify(userDao).create(user);
    }

    @Test
    public void updateUserTest() {
        userService.update(user);
        verify(userDao).update(user);
    }

    @Test
    public void removeUserTest() {
        userService.remove(user);
        verify(userDao).remove(user);
    }

    @Test
    public void findUserByIdTest() {
        when(userDao.findById(1L)).thenReturn(user);

        User result = userService.findById(1L);
        verify(userDao).findById(1L);
        assertThat(result).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    public void findUserByUsernameTest() {
        when(userDao.findByUsername("username")).thenReturn(user);

        User result = userService.findByUserName("username");
        verify(userDao).findByUsername("username");
        assertThat(result).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    public void findUserByEmailTest() {
        when(userDao.findByEmail("email")).thenReturn(user);

        User result = userService.findByEmail("email");
        verify(userDao).findByEmail("email");
        assertThat(result).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    public void findAllUsersTest() {
        when(userDao.findAll()).thenReturn(Lists.newArrayList(user, admin));

        List<User> result = userService.findAll();
        verify(userDao).findAll();
        assertThat(result).isNotNull();
        assertThat(result).containsExactlyInAnyOrder(user, admin);
    }

    @Test
    public void authenticateTest() {
        userService.create(user, "somePassword");

        assertThat(userService.authenticate(user, "somePassword")).isTrue();
    }

    @Test
    public void authenticateNullPasswordTest() {
        assertThat(userService.authenticate(user, null)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void authenticateUnsetPasswordTest() {
        user.setPasswordHash(null);
        userService.authenticate(user, "password");
    }

    @Test
    public void isAdminTest() {
        when(userDao.findById(user.getId())).thenReturn(user);
        assertThat(userService.isAdmin(user)).isFalse();

        when(userDao.findById(admin.getId())).thenReturn(admin);
        assertThat(userService.isAdmin(admin)).isTrue();
    }
}
