package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.model.StudentTask;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface GradeService {

    public Grade create(Grade grade);

    public Grade getById(Long id);
    public Grade getByIdWithRelated(Long id, Grade.Related... related);
    public Set<Grade> getAll();
    public Set<Grade> getAllWithRelated(Grade.Related... related);

    public List<Grade> getGradesByStudent(Student student);

    public void delete(Grade... grades);
    public Grade update(Grade grade);

    public Grade giveGrade(Student student, Grade grade);

}
