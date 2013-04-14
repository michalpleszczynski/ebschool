package com.ebschool.repo;

import com.ebschool.model.*;

import javax.persistence.Query;
import java.util.List;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:31 PM
 */
public class UserRepositoryImpl extends GenericRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(Class<User> clazz) {
        super(User.class);
    }

    @Override
    public List<Student> getStudentsByClass(ClassInfo classInfo) {
        Query q = entityManager.createQuery("select * from " + Student.class.getSimpleName()
                + " s where :classInfo in s.classes");
        q.setParameter("classInfo", classInfo);
        return (List<Student>) q.getResultList();
    }

    @Override
    public List<Student> getStudentsByTeacher(Teacher teacher) {
        //TODO: implement!
        return null;
    }

    @Override
    public List<Teacher> getTeachersByClass(ClassInfo classInfo) {
        Query q = entityManager.createQuery("select * from " + Teacher.class.getSimpleName()
                + " s where :classInfo in s.classes");
        q.setParameter("classInfo", classInfo);
        return (List<Teacher>) q.getResultList();

    }

    @Override
    public List<Teacher> getTeachersByStudent(Student student) {
        //TODO: implement!
        return null;
    }

    @Override
    public List<Student> getStudentsByParent(Parent parent) {
        //TODO: implement!
        return null;
    }

    @Override
    public List<Student> getStudentsByLevel(Level level) {
        //TODO: implement!
        return null;
    }

}
