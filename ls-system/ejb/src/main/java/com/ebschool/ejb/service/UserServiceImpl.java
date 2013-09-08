package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Parent;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.model.Teacher;
import com.ebschool.ejb.model.User;
import com.ebschool.ejb.repo.RoleRepository;
import com.ebschool.ejb.repo.UserRepository;
import com.ebschool.ejb.security.Roles;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.ebschool.ejb.utils.QueryParameter.*;

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

    @Inject
    RoleRepository roleRepository;

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
    public <T extends User> Set<T> getAll(Class<T> type) {
        return userRepository.getAll(type);
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
        assignOrUnassignRole(user, true);
    }

    @Override
    public void disableAccount(User user) {
        User existingUser = userRepository.getById(user.getId());
        if (existingUser != null){
            user.setActive(false);
        }
        assignOrUnassignRole(user, false);
    }

    private void assignOrUnassignRole(User user, boolean isAssign){
        Roles.Role role = getRoleForUser(user);
        if (isAssign){
            roleRepository.assignRole(user, role);
        } else {
            roleRepository.unassignRole(user, role);
        }
    }

    private Roles.Role getRoleForUser(User user){
        if (Student.class.isAssignableFrom(user.getClass())){
            return Roles.Role.STUDENT;
        }
        if (Teacher.class.isAssignableFrom(user.getClass())){
            return Roles.Role.TEACHER;
        }
        if (Parent.class.isAssignableFrom(user.getClass())){
            return Roles.Role.PARENT;
        }
        return null;
    }
}
