package com.ebschool.service;

import com.ebschool.model.Grade;

import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface GradeServiceRemote {

    public Grade getById(Long id);
    public Set<Grade> getAll();

    public Grade create(Grade grade);
    public void delete(Grade... grades);
    public Grade update(Grade grade);

}
