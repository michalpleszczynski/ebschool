package com.ebschool.repo;

import com.ebschool.model.*;
import com.ebschool.repo.GenericRepositoryImpl;
import com.ebschool.repo.UserRepository;

import javax.ejb.*;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:31 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserRepositoryImpl extends GenericRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<Student> getStudentsByClass(ClassInfo classInfo) {
        TypedQuery<Student> q = entityManager.createQuery(
                "SELECT s FROM " + Student.class.getSimpleName()
                + " AS s WHERE :classInfo MEMBER OF s.classes", Student.class);
        q.setParameter("classInfo", classInfo);
        return q.getResultList();
    }

    @Override
    public List<Student> getStudentsByTeacher(Teacher teacher) {
        TypedQuery<Student> q = entityManager.createQuery("" +
                "SELECT s FROM " + Student.class.getSimpleName()
                + " AS s INNER JOIN s.classes AS c"
                + " WHERE :teacher MEMBER OF c.teachers", Student.class);
        q.setParameter("teacher", teacher);
        return q.getResultList();
    }

    @Override
    public List<Teacher> getTeachersByClass(ClassInfo classInfo) {
        TypedQuery<Teacher> q = entityManager.createQuery(
                "SELECT t FROM " + Teacher.class.getSimpleName()
                + " AS t WHERE :classInfo MEMBER OF s.classes", Teacher.class);
        q.setParameter("classInfo", classInfo);
        return q.getResultList();

    }

    @Override
    public List<Teacher> getTeachersByStudent(Student student) {
        TypedQuery<Teacher> q = entityManager.createQuery(
                "SELECT t FROM " + Teacher.class.getSimpleName()
                + " AS t INNER JOIN t.classes AS c"
                + " WHERE :student MEMBER OF c.students", Teacher.class);
        q.setParameter("student", student);
        return q.getResultList();
    }

    @Override
    public List<Student> getStudentsByParent(Parent parent) {
        TypedQuery<Student> q = entityManager.createQuery(
                "SELECT s FROM " + Student.class.getSimpleName()
                + " AS s, " + Parent.class.getSimpleName() + " AS p "
                + "WHERE p = :parent AND "
                + " s MEMBER OF p.childrenAccounts", Student.class);
        q.setParameter("parent", parent);
        return q.getResultList();
    }

    @Override
    public List<Student> getStudentsByLevel(Level level) {
        TypedQuery<Student> q = entityManager.createQuery(
                "SELECT s FROM " + Student.class.getSimpleName()
                + " AS s WHERE s.level = :level", Student.class);
        q.setParameter("level", level);
        return q.getResultList();
    }

}
