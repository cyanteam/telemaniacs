package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dto.UserDao;
import org.cyanteam.telemaniacs.core.entities.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Tomas Milota
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.create(user);
    }

    @Override
    public void removeUser(User user) {
        userDao.remove(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String name) {
        return userDao.findByUsername(name);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }
}
