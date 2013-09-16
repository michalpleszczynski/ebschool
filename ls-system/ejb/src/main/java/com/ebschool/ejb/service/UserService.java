package com.ebschool.ejb.service;

import com.ebschool.ejb.model.User;

import java.util.Set;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 4:28 PM
 */
public interface UserService {

    public User create(User user);
    public void delete(User... users);

    public User getById(Long id);
    public User getByLogin(String login);
    public <T extends User> Set<T> getAll(Class<T> type);

    public User update(User user);

    public void signUp(User user);
    public void disableAccount(User user);

}
