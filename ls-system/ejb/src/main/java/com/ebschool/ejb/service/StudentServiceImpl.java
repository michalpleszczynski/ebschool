package com.ebschool.ejb.service;

import com.ebschool.ejb.model.*;
import com.ebschool.ejb.repo.UserRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static com.ebschool.ejb.utils.QueryParameter.*;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(StudentService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class StudentServiceImpl implements StudentService {

    @Inject
    UserRepository userRepository;

    @Override
    public Student getById(Long id, Student.Related... related) {
        return userRepository.getStudentById(id, related);
    }

    @Override
    public Student getByLogin(String login, Student.Related... related) {
        String query = Student.USER_BY_LOGIN;
        if (related.length == 0){
            List<Student> student = userRepository.findWithNamedQuery(User.class, query, with("login", login).parameters(), 1);
            return student.isEmpty() ? null : student.get(0);
        } else {
            List<Student.Related> enumList = Arrays.asList(related);
            query = Student.queriesWithRelated.get(EnumSet.of(enumList.get(0), enumList.subList(1, enumList.size()).toArray(new Student.Related[0])));
        }
        List<Student> student = userRepository.findWithNamedQuery(Student.class, query, with("login", login).parameters(), 1);
        return student.isEmpty() ? null : student.get(0);
    }

    @Override
    public Set<Student> getAll(Student.Related... related) {
        return userRepository.getAll(Student.class, related);
    }

    @Override
    public Student create(Student student) {
        return userRepository.create(student);
    }

    @Override
    public List<Student> getByClass(ClassInfo classInfo) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_CLASS, with("classInfo", classInfo).parameters());
    }

    @Override
    public List<Student> getByTeacher(Teacher teacher) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_TEACHER, with("teacher", teacher).parameters());
    }

    @Override
    public List<Student> getByParent(Parent parent) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_PARENT, with("parent", parent).parameters());
    }

    @Override
    public List<Student> getByLevel(Level level) {
        return userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_LEVEL, with("level", level).parameters());
    }

}
