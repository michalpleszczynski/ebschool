package com.ebschool.repo;

import com.ebschool.model.User;
import com.ebschool.security.Roles;

/**
 * User: michau
 * Date: 6/12/13
 */
public interface RoleRepository {

    public void assignRole(User user, Roles.Role role);
    public void unassignRole(User user, Roles.Role role);

}
