package com.ebschool.ejb.service;

import com.ebschool.ejb.exception.DuplicatedUserException;
import com.ebschool.ejb.model.*;
import com.ebschool.ejb.repo.UserRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
@Local(StudentServiceLocal.class)
@Remote(StudentServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class StudentServiceImpl implements StudentServiceLocal {

    @Inject
    UserRepository userRepository;

    @Override
    public Student getStudentById(Long id) {
        return userRepository.getStudentById(id);
    }

    @Override
    public Set<Student> getAll() {
        return userRepository.getAll(Student.class);
    }

    @Override
    public Student create(Student student) {
        return userRepository.create(student);
    }

    @Override
    public List<Student> getStudentsByClass(ClassInfo classInfo) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_CLASS, with("classInfo", classInfo).parameters());
    }

    @Override
    public List<Student> getStudentsByTeacher(Teacher teacher) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_TEACHER, with("teacher", teacher).parameters());
    }

    @Override
    public List<Student> getStudentsByParent(Parent parent) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_PARENT, with("parent", parent).parameters());
    }

    @Override
    public List<Student> getStudentsByLevel(Level level) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_LEVEL, with("level", level).parameters());
    }

}
