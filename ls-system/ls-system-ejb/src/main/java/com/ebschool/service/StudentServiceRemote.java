package com.ebschool.service;

import com.ebschool.model.*;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/17/13
 */
public interface StudentServiceRemote {

    public Student getStudentById(Long id);
    public Set<Student> getAllStudents();

    public List<Student> getStudentsByClass(ClassInfo classInfo);
    public List<Student> getStudentsByTeacher(Teacher teacher);
    public List<Student> getStudentsByParent(Parent parent);
    public List<Student> getStudentsByLevel(Level level);

}
