package org.cyanteam.telemaniacs.core.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.cyanteam.telemaniacs.core.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class accessing persistence context for CRUD operations for user class.
 * It implements UserDao interaface.
 * @author Miroslav Kubus
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void remove(User user) {
        if(user == null){
            throw new IllegalArgumentException("You try to delete NULL user!");
        }
        
        entityManager.remove(findById(user.getId()));
    }

    @Override
    public void update(User user) {        
        if(user == null) {
            throw new IllegalArgumentException("You try to update NULL user!");
        }
        
        if (findById(user.getId()) == null) {
            throw new IllegalArgumentException("Unknown user to update with "
                    + "id: " + user.getId() + "!");
        }
        
        entityManager.merge(user);
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
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
    public User findByUsername(String username) {
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
        
        return Collections.unmodifiableList(allUsersQuery.getResultList());
    }
}