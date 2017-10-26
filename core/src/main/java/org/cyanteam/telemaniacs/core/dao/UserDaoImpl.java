package org.cyanteam.telemaniacs.core.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.cyanteam.telemaniacs.core.entities.User;

/**
 * Class accessing persistence context for CRUD operations for user class.
 * It implements UserDao interaface.
 * @author Miroslav Kubus
 */
public class UserDaoImpl implements UserDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void create(User user) throws ConstraintViolationException {
        entityManager.persist(user);
    }

    @Override
    public void remove(User user) throws IllegalArgumentException {
        if(user == null){
            throw new IllegalArgumentException("You try to delete NULL user!");
        }
        
        entityManager.remove(findById(user.getId()));
    }

    @Override
    public User update(User user) 
            throws ConstraintViolationException, IllegalArgumentException {
        
        if (findById(user.getId()) == null) {
            throw new IllegalArgumentException("Unknown user to update with "
                    + "id: " + user.getId() + "!");
        }
        
        User mergedUser = entityManager.merge(user);
        
        return mergedUser;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) throws IllegalArgumentException {
        if(email == null) {
            throw new IllegalArgumentException("You try to find user by NULL email!"); 
        }
        
        String queryString = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query;
        
        query = entityManager
                    .createQuery(queryString,User.class)
                    .setParameter("email", email);
        
        return query.getSingleResult();
    }

    @Override
    public User findByUsername(String username) throws IllegalArgumentException {
        if(username == null) {
            throw new IllegalArgumentException("You try to find user by NULL username!"); 
        }
        
        String queryString = "SELECT u FROM User u WHERE u.username = :username";
        TypedQuery<User> query;
        
        query = entityManager
                    .createQuery(queryString, User.class)
                    .setParameter("username", username);
        
        return query.getSingleResult();    
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> allUsersQuery;
        String queryString = "SELECT u FROM User u";
        
        allUsersQuery = entityManager.createQuery(queryString, User.class);
        
        return allUsersQuery.getResultList();
    }
}
