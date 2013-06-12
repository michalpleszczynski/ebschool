package com.ebschool.service;

import com.ebschool.model.Parent;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.model.User;
import com.ebschool.repo.RoleRepository;
import com.ebschool.repo.UserRepository;
import com.ebschool.security.Roles;

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

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        // TODO: change all this to encryption
        if (user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
        }
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
