package com.ebschool.ejb.service;

import com.ebschool.ejb.exception.DuplicatedUserException;
import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Teacher;
import com.ebschool.ejb.model.User;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface TeacherServiceRemote {

    public Teacher getTeacherById(Long id);
    public Set<Teacher> getAll();

    public Teacher create(Teacher teacher);

    public List<Teacher> getTeachersByClass(ClassInfo classInfo);

}
