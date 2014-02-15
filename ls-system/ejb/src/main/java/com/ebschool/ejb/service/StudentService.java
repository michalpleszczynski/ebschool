package com.ebschool.ejb.service;

import com.ebschool.ejb.model.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/17/13
 */
public interface StudentService {

    public Student getById(Long id, Student.Related... related);
    public Student getByLogin(String login, Student.Related... related);
    public Set<Student> getAll(Student.Related... related);

    public Student create(Student student);

    public List<Student> getByClass(ClassInfo classInfo);
    public List<Student> getByTeacher(Teacher teacher);
    public List<Student> getByParent(Parent parent);
    public List<Student> getByLevel(Level level);

}
