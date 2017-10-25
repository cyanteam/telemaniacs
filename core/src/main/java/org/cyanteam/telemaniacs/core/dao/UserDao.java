package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.User;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Interface for CRUD operations for entity user.
 * @author Miroslav Kubus
 */
public interface UserDao {
    /**
     * Persists new user into database.
     * 
     * @param user represents entity to be persisted into database.
     * @throws ConstraintViolationException if any constraints on columns 
     * are violated.
     */
    void create(User user) throws ConstraintViolationException;
    
    /**
     * Remove user entity from database.
     *
     * @param user represents user to be removed from database.     
     * @throws IllegalArgumentException if user for removing is not stored 
     * in the database or user is null. 
     */
    void remove(User user) throws IllegalArgumentException;

    /**
     * Update persisted entity in the database.
     *
     * @param user represents persisted entity to be updated.
     * @return merged user object from database and user.
     * @throws ConstraintViolationException if any constraints on columns 
     * are violated.
     */
    User update(User user) throws ConstraintViolationException;

    /**
     * Return the user entity with specific id.
     *
     * @param id represents id of the user entity.
     * @return the user entity associated with the given id.
     */
    User findById(Long id);

    /**
     * Return user with given email.
     *
     * @param email represents email of searched user.
     * @return user from database with given email. 
     * @throws IllegalArgumentException if email is null. 
     */
    User findByEmail(String email) throws IllegalArgumentException;
    
    /**
     * Return user with given username.
     *
     * @param username represents username of searched user.
     * @return user from database with given username.
     * @throws IllegalArgumentException if username is null. 
     */
    User findByUsername(String username) throws IllegalArgumentException;
    
    /**
     * Return all Users in the database.
     *
     * @return all user entities from the database.
     */
    List<User> findAll();
}