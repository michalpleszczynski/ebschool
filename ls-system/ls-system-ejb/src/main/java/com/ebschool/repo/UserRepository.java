package com.ebschool.repo;

import com.ebschool.model.*;

import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 4/13/13
 * Time: 3:23 PM
 */
public interface UserRepository extends GenericRepository<User, Long> {

    public Student getStudentById(Long id);

    public Teacher getTeacherById(Long id);

    public Parent getParentById(Long id);

    public List<Student> getStudentsByClass(ClassInfo classInfo);

    public List<Student> getStudentsByTeacher(Teacher teacher);

    public List<Teacher> getTeachersByClass(ClassInfo classInfo);

//    public List<Teacher> getTeachersByStudent(Student student);

    public List<Student> getStudentsByParent(Parent parent);

    public List<Student> getStudentsByLevel(Level level);

    public Set<Student> getAllStudents();

    public Set<Teacher> getAllTeachers();

    public Set<Parent> getAllParents();

    public void deleteAllStudents();

    public void deleteAllTeachers();

    public void deleteAllParents();

}
