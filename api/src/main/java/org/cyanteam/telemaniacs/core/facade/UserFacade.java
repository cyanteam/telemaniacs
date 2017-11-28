package org.cyanteam.telemaniacs.core.facade;

import java.util.List;
import javax.naming.AuthenticationException;

import org.cyanteam.telemaniacs.core.dto.UserAuthenticationDTO;
import org.cyanteam.telemaniacs.core.dto.UserDTO;

/**
 * Interface for user operations.
 * Encapsulates user services.
 *
 * @author Miroslav Kubus
 */
public interface UserFacade {

    /**
     * Create new user in the system
     *
     * @param userDTO  to be created/registered
     * @param password of new user
     */
    void create(UserDTO userDTO, String password);

    /**
     * Update user information
     *
     * @param userDTO to be updated
     */
    void update(UserDTO userDTO);

    /**
     * Remove registered user from the system
     *
     * @param userDTO to be removed/unregistered
     */
    void remove(UserDTO userDTO);

    /**
     * Returns all users
     *
     * @return all users
     */
    List<UserDTO> findAll();

    /**
     * Returns the user by specific ID
     *
     * @param userId specific id of the user
     * @return found user if id is valid, null othewise
     */
    UserDTO findById(Long userId);

    /**
     * Returns the user by specific email
     *
     * @param email specific email of the user
     * @return found user if email is valid, null othewise
     */
    UserDTO findByEmail(String email);

    /**
     * Returns the user by specific username
     *
     * @param username specific username of the user
     * @return found user if username is valid, null othewise
     */
    UserDTO findByUsername(String username);

    /**
     * Checks if given username and password is valid
     *
     * @param userAuth
     * @return true if condtions are met, false otherwise
     * @throws AuthenticationException in case of error while authenticate
     */
    boolean authenticate(UserAuthenticationDTO userAuth) throws AuthenticationException;

    /**
     * Checks if given user is an administrator
     *
     * @param userDTO specific user to check
     * @return true if user is admin, false otherwise
     */
    boolean isAdmin(UserDTO userDTO);
}
