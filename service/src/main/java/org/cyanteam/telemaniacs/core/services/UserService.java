package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.entities.User;

import java.util.List;

/**
 * Service for User
 *
 * @author Tomas Milota
 */
public interface UserService {

    /**
     * Creates new user
     *
     * @param user              User
     * @param encryptedPassword encryptedPassword of user
     */
    void create(User user, String encryptedPassword);

    /**
     * Removes user
     *
     * @param user User
     */
    void remove(User user);

    /**
     * Updates user
     *
     * @param user User
     */
    void update(User user);

    /**
     * Retrieves user by ID
     *
     * @param id ID
     * @return User with given ID
     */
    User findById(Long id);

    /**
     * Retrieves user by email
     *
     * @param email email
     * @return User with given email
     */
    User findByEmail(String email);

    /**
     * Retrieves user by username
     *
     * @param name username
     * @return User with given name
     */
    User findByUserName(String name);

    /**
     * Retrieves all stored users
     *
     * @return List of all users
     */
    List<User> findAll();

    /**
     * Check if user is admin
     *
     * @param user to be checked
     * @return true if user is admin, false otherwise
     */
    boolean isAdmin(User user);

    /**
     * Authenticate user
     *
     * @param user     to be authenticated
     * @param password of the user
     * @return true if user is authenticated, false otherwise
     */
    boolean authenticate(User user, String password);
}
