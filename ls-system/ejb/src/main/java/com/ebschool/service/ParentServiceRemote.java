package com.ebschool.service;

import com.ebschool.model.Parent;
import com.ebschool.model.Student;

import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface ParentServiceRemote {

    public Parent getById(Long id);
    public Set<Parent> getAll();

    public Parent addChildAccount(Student student, Parent parent);
    public Parent removeChildAccount(Student student, Parent parent);
}
