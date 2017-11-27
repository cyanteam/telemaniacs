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
     * @param user User
     * @param encryptedPassword encryptedPassword of user
     */
    void createUser(User user, String encryptedPassword);

    /**
     * Removes user
     * @param user User
     */
    void removeUser(User user);

    /**
     * Updates user
     * @param user User
     */
    void updateUser(User user);

    /**
     * Retrieves user by ID
     * @param id ID
     * @return User with given ID
     */
    User findUserById(Long id);

    /**
     * Retrieves user by email
     * @param email email
     * @return User with given email
     */
    User findUserByEmail(String email);

    /**
     * Retrieves user by username
     * @param name username
     * @return User with given name
     */
    User findUserByUserName(String name);

    /**
     * Retrieves all stored users
     * @return List of all users
     */
    List<User> findAllUsers();

    /**
     * Check if user is admin
     * @param user to be checked
     * @return true if user is admin, false otherwise
     */
    boolean isAdmin(User user);
    
   /**
    * Authenticate user
    * @param user to be authenticated
    * @param password of the user
    * @return true if user is authenticated, false otherwise
    */
    boolean authenticate(User user, String password);
}
