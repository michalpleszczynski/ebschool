package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Parent;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.repo.UserRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
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
    public Parent addChildrenAccounts(Parent parent, Student... students) {
        Parent p = userRepository.getParentById(parent.getId());
        if (p.getChildrenAccounts() == null){
            p.setChildrenAccounts(new HashSet(Arrays.asList(students)));
        } else {
            p.getChildrenAccounts().addAll(Arrays.asList(students));
        }
        return userRepository.update(p);
    }

    @Override
    public Parent removeChildrenAccounts(Parent parent, Student... students) {
        Parent p = userRepository.getParentById(parent.getId());
        if (p.getChildrenAccounts() == null){
            return p;
        }
        p.getChildrenAccounts().removeAll(Arrays.asList(students));
        return userRepository.update(p);
    }
}
