package com.ebschool.service;

import com.ebschool.model.Parent;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.model.User;

import java.util.Set;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 4:28 PM
 */
public interface UserServiceLocal extends UserServiceRemote {

    public User create(User user);
    public void delete(User... users);

}
