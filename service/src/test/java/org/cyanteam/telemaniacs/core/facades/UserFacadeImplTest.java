package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.UserAuthenticationDTO;
import org.cyanteam.telemaniacs.core.dto.UserDTO;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.helpers.UserBuilder;
import org.cyanteam.telemaniacs.core.services.DozerObjectMapperServiceImpl;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.UserServiceImpl;

import java.util.Arrays;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for user facade implementation.
 *
 * @author Miroslav Kubus
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserFacadeImplTest {
    @Mock
    private UserServiceImpl userService;

    @Spy
    @Inject
    private final ObjectMapperService objectMapperService = new DozerObjectMapperServiceImpl();

    @InjectMocks
    private final UserFacadeImpl userFacade = new UserFacadeImpl();

    private User youngUser;
    private User adultUser;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        youngUser = UserBuilder
                .sampleYoungUserBuilder()
                .id(1L)
                .username("Young User")
                .build();
        adultUser = UserBuilder
                .sampleAdultUserBuilder()
                .id(2L)
                .build();

        when(userService.findById(1L)).thenReturn(youngUser);
        when(userService.findByEmail("adultUser@test.com")).thenReturn(adultUser);
        when(userService.findAll()).thenReturn(Arrays.asList(adultUser, youngUser));
        when(userService.findByUserName("Young User")).thenReturn(youngUser);
    }

    @Test
    public void createUserTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("TestName");

        userFacade.create(userDTO, youngUser.getPasswordHash());
        verify(userService).create(any(User.class), any(String.class));
    }

    @Test
    public void updateUserTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("TestName");

        userFacade.update(userDTO);
        verify(userService).update(any(User.class));
    }

    @Test
    public void removeUserTest() {
        UserDTO userDTO = new UserDTO();

        userFacade.remove(userDTO);
        verify(userService).remove(any(User.class));
    }

    @Test
    public void authenticateUserTest() throws AuthenticationException {
        UserAuthenticationDTO userAuthDTO = new UserAuthenticationDTO();
        userAuthDTO.setId(1L);
        userAuthDTO.setEmail("adultUser@test.com");
        userAuthDTO.setPassword("testPassword");

        userFacade.authenticate(userAuthDTO);
        verify(userService).authenticate(any(User.class), any(String.class));
    }

    @Test
    public void findUserByIdTest() {
        userFacade.findById(1L);
        verify(userService, atLeastOnce()).findById(1L);
    }

    @Test
    public void findUserByEmailTest() {
        userFacade.findByEmail("adultUser@test.com");
        verify(userService, atLeastOnce()).findByEmail("adultUser@test.com");
    }

    @Test
    public void findUserByUserNameTest() {
        userFacade.findByUsername("Young User");
        verify(userService, atLeastOnce()).findByUserName("Young User");
    }

    @Test
    public void isAdminTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("NotAdmin");
        userDTO.setIsAdmin(false);

        userFacade.isAdmin(userDTO);
        verify(userService).isAdmin(any(User.class));
    }

    @Test
    public void getAllUsersTest() {
        userFacade.findAll();
        verify(userService, atLeastOnce()).findAll();
    }
}
