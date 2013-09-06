package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.User;
import com.ebschool.ejb.security.Roles;

/**
 * User: michau
 * Date: 6/12/13
 */
public interface RoleRepository {

    public void assignRole(User user, Roles.Role role);
    public void unassignRole(User user, Roles.Role role);

}
