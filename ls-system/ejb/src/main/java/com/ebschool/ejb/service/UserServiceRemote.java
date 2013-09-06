package com.ebschool.ejb.service;

import com.ebschool.ejb.model.User;

import java.util.Set;

/**
 * User: michau
 * Date: 5/17/13
 * Time: 7:59 PM
 */
public interface UserServiceRemote {

    public User getById(Long id);
    public User getByLogin(String login);
    public Set<User> getAll();

    public User update(User user);

    public void signUp(User user);
    public void disableAccount(User user);

    public void changePassword(User user, String oldPassword, String newPassword);

}
