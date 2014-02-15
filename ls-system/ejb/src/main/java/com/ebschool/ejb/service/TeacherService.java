package com.ebschool.ejb.service;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Teacher;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface TeacherService {

    public Teacher getById(Long id);
    public Set<Teacher> getAll();

    public Teacher create(Teacher teacher);

    public List<Teacher> getByClass(ClassInfo classInfo);

}
