package com.ebschool.service;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Teacher;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface TeacherServiceRemote {

    public Teacher getTeacherById(Long id);
    public Set<Teacher> getAll();

    public List<Teacher> getTeachersByClass(ClassInfo classInfo);

}
