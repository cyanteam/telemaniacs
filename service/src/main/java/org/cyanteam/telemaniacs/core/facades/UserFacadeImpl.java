package org.cyanteam.telemaniacs.core.facades;

import java.util.List;
import javax.inject.Inject;

import org.cyanteam.telemaniacs.core.dto.UserAuthenticationDTO;
import org.cyanteam.telemaniacs.core.dto.UserDTO;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.facade.UserFacade;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;

/**
 * Implementation of facade service for User.
 *
 * @author Miroslav Kubus
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Inject
    private UserService userService;

    @Inject
    private ObjectMapperService objectMapperService;

    @Override
    public void create(UserDTO userDTO, String password) {
        User user = prepareUser(userDTO);

        userService.create(user, password);
        userDTO.setId(user.getId());
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = prepareUser(userDTO);

        userService.update(user);
    }

    @Override
    public void remove(UserDTO userDTO) {
        User user = prepareUser(userDTO);

        userService.remove(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return objectMapperService.map(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Id of user cannot be null!");
        }

        User user = userService.findById(userId);

        return objectMapperService.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Id of user cannot be null!");
        }

        User user = userService.findByEmail(email);

        return objectMapperService.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username of user cannot be null!");
        }

        User user = userService.findByUserName(username);

        return objectMapperService.map(user, UserDTO.class);
    }

    @Override
    public boolean authenticate(UserAuthenticationDTO userAuthDTO) throws AuthenticationException {
        if (userAuthDTO == null) {
            throw new IllegalArgumentException("userAuthenticateDTO cannot be null");
        }

        User user = userService.findByEmail(userAuthDTO.getEmail());
        if (user == null) {
            throw new AuthenticationException("User with this email does not exist!");
        }

        return userService.authenticate(user, userAuthDTO.getPassword());
    }

    @Override
    public boolean isAdmin(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("userDTO cannot be null");
        }

        return userService.isAdmin(objectMapperService.map(userDTO, User.class));
    }

    private User prepareUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null!");
        }

        return objectMapperService.map(userDTO, User.class);
    }
}
