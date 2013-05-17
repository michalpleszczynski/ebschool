package com.ebschool.service;

import com.ebschool.model.Parent;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.model.User;

import java.util.Set;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 4:28 PM
 */
public interface UserService {

    public User getById();
    public User create(User object);
    public User update(User object);
    public void delete(User... objects);

    public Student getStudentById(Long id);
    public Teacher getTeacherById(Long id);
    public Parent getParentById(Long id);

    public Set<User> getAllUsers();
    public Set<Student> getAllStudents();
    public Set<Teacher> getAllTeachers();
    public Set<Parent> getAllParents();

}
