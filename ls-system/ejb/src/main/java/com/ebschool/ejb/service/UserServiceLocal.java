package com.ebschool.ejb.service;

import com.ebschool.ejb.model.User;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 4:28 PM
 */
public interface UserServiceLocal extends UserServiceRemote {

    public User create(User user);
    public void delete(User... users);

}
