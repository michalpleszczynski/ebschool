package com.ebschool.service;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;

import java.util.Collection;
import java.util.Set;

/**
 * User: michau
 * Date: 5/18/13
 */
public interface ClassInfoServiceRemote {

    public ClassInfo getById(Long id);
    public ClassInfo create(ClassInfo classInfo);
    public void delete(ClassInfo... classes);

    public Set<ClassInfo> getAll();
    public Set<ClassInfo> getByIds(Collection<Long> ids);

    public ClassInfo addStudent(Student student, ClassInfo classInfo);
    public ClassInfo removeStudent(Student student, ClassInfo classInfo);
    public ClassInfo addTeacher(Teacher teacher, ClassInfo classInfo);
    public ClassInfo removeTeacher(Teacher teacher, ClassInfo classInfo);

}
