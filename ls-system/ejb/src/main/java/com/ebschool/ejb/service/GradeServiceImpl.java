package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.repo.GradeRepository;
import com.ebschool.ejb.repo.UserRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.ebschool.ejb.utils.QueryParameter.*;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(GradeService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GradeServiceImpl implements GradeService {

    @Inject
    GradeRepository gradeRepository;

    @Inject
    UserRepository userRepository;

    @Override
    public Grade getById(Long id) {
        return gradeRepository.getById(id);
    }

    @Override
    public Set<Grade> getAll() {
        return gradeRepository.getAll();
    }

    @Override
    public List<Grade> getGradesByStudent(Student student) {
        return gradeRepository.findWithNamedQuery(Grade.class, Grade.GRADES_BY_STUDENT, with("student", student).parameters());
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

    @Override
    public Grade giveGrade(Student student, Grade grade) {
        student = userRepository.getStudentById(student.getId());
        grade.setStudent(student);
        grade = gradeRepository.create(grade);
        return grade;
    }
}
