package com.ebschool.service;

import com.ebschool.model.User;
import com.ebschool.repo.UserRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.ebschool.utils.QueryParameter.*;

/**
 * User: michau
 * Date: 5/18/13
 */
@Stateless
@Local(UserServiceLocal.class)
@Remote(UserServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserServiceImpl implements UserServiceLocal{

    @Inject
    UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public void delete(User... users) {
        userRepository.delete(users);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByLogin(String login) {
        List<User> user =  userRepository.findWithNamedQuery(User.class, User.USER_BY_LOGIN_AND_PASSWORD, with("login", login).parameters(), 1);
        return user.isEmpty() ? null : user.get(0);
    }

    @Override
    public Set<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void signUp(User user) {
        User existingUser = userRepository.getById(user.getId());
        if (existingUser == null){
            userRepository.create(user);
        }
        user.setActive(true);
        //TODO: set random password, send an email to user
    }

    @Override
    public void disableAccount(User user) {
        User existingUser = userRepository.getById(user.getId());
        if (existingUser != null){
            user.setActive(false);
        }
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        // TODO: change all this to encryption
        if (user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
        }
    }
}
