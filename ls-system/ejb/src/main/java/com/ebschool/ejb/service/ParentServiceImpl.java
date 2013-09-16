package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Parent;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.repo.UserRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(ParentService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ParentServiceImpl implements ParentService {

    @Inject
    UserRepository userRepository;

    @Override
    public Parent getById(Long id) {
        return userRepository.getParentById(id);
    }

    @Override
    public Set<Parent> getAll() {
        return userRepository.getAll(Parent.class);
    }

    @Override
    public Parent create(Parent parent) {
        return userRepository.create(parent);
    }

    @Override
    public Parent addChildAccount(Student student, Parent parent) {
        Parent p = userRepository.getParentById(parent.getId());
        p.getChildrenAccounts().add(student);
        return userRepository.update(p);
    }

    @Override
    public Parent removeChildAccount(Student student, Parent parent) {
        Parent p = userRepository.getParentById(parent.getId());
        p.getChildrenAccounts().remove(student);
        return userRepository.update(p);
    }
}
