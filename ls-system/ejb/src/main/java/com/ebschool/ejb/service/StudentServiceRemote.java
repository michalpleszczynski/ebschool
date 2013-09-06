package com.ebschool.ejb.service;

import com.ebschool.ejb.model.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/17/13
 */
//@RolesAllowed({Roles.TEACHER})
public interface StudentServiceRemote {

    @PermitAll
    public Student getStudentById(Long id);
    public Set<Student> getAll();

    @PermitAll
    public List<Student> getStudentsByClass(ClassInfo classInfo);
    public List<Student> getStudentsByTeacher(Teacher teacher);
//    @RolesAllowed({Roles.PARENT, Roles.TEACHER})
    public List<Student> getStudentsByParent(Parent parent);
    public List<Student> getStudentsByLevel(Level level);

}
