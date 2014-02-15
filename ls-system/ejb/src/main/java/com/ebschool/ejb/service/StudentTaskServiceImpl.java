package com.ebschool.ejb.service;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Grade;
import com.ebschool.ejb.model.StudentTask;
import com.ebschool.ejb.repo.GradeRepository;
import com.ebschool.ejb.repo.StudentTaskRepository;

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
@Local(StudentTaskService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class StudentTaskServiceImpl implements StudentTaskService {

    @Inject
    StudentTaskRepository studentTaskRepository;

    @Override
    public StudentTask getById(Long id) {
        return studentTaskRepository.getById(id);
    }

    @Override
    public StudentTask getByIdWithRelated(Long id, StudentTask.Related... related) {
        return studentTaskRepository.getByIdWithRelated(id, related);
    }

    @Override
    public Set<StudentTask> getAll() {
        return studentTaskRepository.getAll();
    }

    @Override
    public Set<StudentTask> getAllWithRelated(StudentTask.Related... related) {
        return studentTaskRepository.getAllWithRelated(related);
    }

    @Override
    public List<StudentTask> getTestsByClass(ClassInfo classInfo) {
        return studentTaskRepository.findWithNamedQuery(StudentTask.class, StudentTask.TESTS_BY_CLASS, with("classInfo", classInfo).parameters());
    }

    @Override
    public StudentTask create(StudentTask studentTask) {
        return studentTaskRepository.create(studentTask);
    }

    @Override
    public StudentTask update(StudentTask studentTask) {
        return studentTaskRepository.update(studentTask);
    }

    @Override
    public void delete(StudentTask... studentTasks) {
        studentTaskRepository.delete(studentTasks);
    }
}
