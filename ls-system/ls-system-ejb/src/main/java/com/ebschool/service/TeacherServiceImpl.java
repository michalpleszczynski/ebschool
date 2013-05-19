package com.ebschool.service;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Teacher;
import com.ebschool.repo.UserRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.ebschool.utils.QueryParameter.*;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(TeacherServiceLocal.class)
@Remote(TeacherServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TeacherServiceImpl implements TeacherServiceLocal {

    @Inject
    UserRepository userRepository;

    @Override
    public Teacher getTeacherById(Long id) {
        return userRepository.getTeacherById(id);
    }

    @Override
    public Set<Teacher> getAllTeachers() {
        return userRepository.getAllTeachers();
    }

    @Override
    public List<Teacher> getTeachersByClass(ClassInfo classInfo) {
        return userRepository.findWithNamedQuery(Teacher.class, Teacher.TEACHERS_BY_CLASS, with("classInfo", classInfo).parameters());
    }

}
