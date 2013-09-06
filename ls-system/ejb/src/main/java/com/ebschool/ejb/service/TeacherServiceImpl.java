package com.ebschool.ejb.service;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Teacher;
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
@Local(TeacherServiceLocal.class)
@Remote(TeacherServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TeacherServiceImpl implements TeacherServiceLocal {

    @Inject
    UserRepository userRepository;

    @Override
    public Teacher getTeacherById(Long id) {
        return userRepository.getTeacherById(id);
    }

    @Override
    public Set<Teacher> getAll() {
        return userRepository.getAllTeachers();
    }

    @Override
    public List<Teacher> getTeachersByClass(ClassInfo classInfo) {
        return userRepository.findWithNamedQuery(Teacher.class, Teacher.TEACHERS_BY_CLASS, with("classInfo", classInfo).parameters());
    }

}
