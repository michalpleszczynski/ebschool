package com.ebschool.service;

import com.ebschool.model.Grade;
import com.ebschool.repo.GradeRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(GradeServiceLocal.class)
@Remote(GradeServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GradeServiceImpl implements GradeServiceLocal{

    @Inject
    GradeRepository gradeRepository;

    @Override
    public Grade getById(Long id) {
        return gradeRepository.getById(id);
    }

    @Override
    public Set<Grade> getAll() {
        return gradeRepository.getAll();
    }

    @Override
    public Grade create(Grade grade) {
        return gradeRepository.create(grade);
    }

    @Override
    public void delete(Grade... grades) {
        gradeRepository.delete(grades);
    }

    @Override
    public Grade update(Grade grade) {
        return gradeRepository.update(grade);
    }
}
