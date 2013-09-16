package com.ebschool.ejb.service;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Student;
import com.ebschool.ejb.model.Teacher;

import java.util.Collection;
import java.util.Set;

/**
 * User: michau
 * Date: 5/18/13
 */
public interface ClassInfoService {

    public ClassInfo getById(Long id);
    public ClassInfo create(ClassInfo classInfo);
    public void delete(ClassInfo... classes);
    public ClassInfo update(ClassInfo classInfo);

    public Set<ClassInfo> getAll();
    public Set<ClassInfo> getByIds(Collection<Long> ids);

    public ClassInfo addStudent(Student student, ClassInfo classInfo);
    public ClassInfo removeStudent(Student student, ClassInfo classInfo);
    public ClassInfo addTeacher(Teacher teacher, ClassInfo classInfo);
    public ClassInfo removeTeacher(Teacher teacher, ClassInfo classInfo);

}
