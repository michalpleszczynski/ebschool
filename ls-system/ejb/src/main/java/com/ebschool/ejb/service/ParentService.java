package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Parent;
import com.ebschool.ejb.model.Student;

import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface ParentService {

    public Parent getById(Long id);
    public Set<Parent> getAll();

    public Parent create(Parent parent);

    public Parent addChildAccount(Student student, Parent parent);
    public Parent removeChildAccount(Student student, Parent parent);

}
