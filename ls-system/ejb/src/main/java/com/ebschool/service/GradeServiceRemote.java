package com.ebschool.service;

import com.ebschool.model.Grade;
import com.ebschool.model.Student;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface GradeServiceRemote {

    public Grade getById(Long id);
    public Set<Grade> getAll();
    public List<Grade> getGradesByStudent(Student student);

    public void delete(Grade... grades);
    public Grade update(Grade grade);

    public Grade giveGrade(Student student, Grade grade);

}
