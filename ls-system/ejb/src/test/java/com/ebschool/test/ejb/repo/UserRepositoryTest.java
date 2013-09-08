package com.ebschool.test.ejb.repo;

import com.ebschool.ejb.model.*;
import com.ebschool.ejb.repo.ClassInfoRepository;
import com.ebschool.ejb.repo.LevelRepository;
import com.ebschool.ejb.repo.UserRepository;
import com.ebschool.ejb.security.Roles;
import com.ebschool.ejb.utils.Identifiable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ebschool.test.ejb.utils.DataBuilder;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static com.ebschool.ejb.utils.QueryParameter.*;
/**
 * User: michau
 * Date: 4/16/13
 * Time: 9:24 PM
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
@Transactional(manager = "java:jboss/UserTransaction")
public class UserRepositoryTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ClassInfoRepository classInfoRepository;

    @Inject
    private LevelRepository  levelRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(UserRepository.class.getPackage())
                .addPackage(Roles.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

        return ejb;
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void getByIdTest() throws Exception {
        User user = userRepository.getById(1L);
        assertTrue(Student.class.isAssignableFrom(user.getClass()));
        Student student = (Student) user;
        assertNotNull(student);
        assertEquals("name", student.getFirstName());

        User user2 = userRepository.getById(2L);
        assertTrue(Teacher.class.isAssignableFrom(user2.getClass()));
        Teacher teacher = (Teacher) user2;
        assertNotNull(teacher);
        assertEquals("other_name", teacher.getFirstName());

        User user3 = userRepository.getById(3L);
        assertTrue(Parent.class.isAssignableFrom(user3.getClass()));
        Parent parent = (Parent) user3;
        assertNotNull(parent);
        assertEquals("nameParent", parent.getFirstName());
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void createTest() throws Exception {
        Student student = DataBuilder.buildStudent();
        assertNotNull(student);
        Student returnedStudent = (Student)userRepository.create(student);
        assertEquals(returnedStudent, student);

        Teacher teacher = DataBuilder.buildTeacher();
        assertNotNull(teacher);
        Teacher returnedTeacher = (Teacher)userRepository.create(teacher);
        assertEquals(returnedTeacher, teacher);

        Parent parent = DataBuilder.buildParent();
        assertNotNull(parent);
        Parent returnedParent = (Parent)userRepository.create(parent);
        assertEquals(returnedParent, parent);
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void deleteTest() throws Exception {
        Set<User> users = userRepository.getAll(User.class);
        assertNotNull(users);
        assertEquals(4, users.size());
        User user = userRepository.getById(2L);
        assertNotNull(user);
        userRepository.delete(user);
        users = userRepository.getAll(User.class);
        assertNotNull(users);
        assertEquals(3, users.size());
        User user2 = userRepository.getById(1L);
        User user3 = userRepository.getById(3L);
        userRepository.delete(user2, user3);
        users = userRepository.getAll(User.class);
        assertNotNull(users);
        assertEquals(1, users.size());
        userRepository.deleteAll(User.class);
        users = userRepository.getAll(User.class);
        assertTrue(users.isEmpty());
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void testGetAndDeleteByClass() throws Exception {
        Set<Student> allStudents = userRepository.getAll(Student.class);
        Set<Teacher> allTeachers = userRepository.getAll(Teacher.class);
        Set<Parent> allParent = userRepository.getAll(Parent.class);
        assertNotNull(allParent);
        assertNotNull(allTeachers);
        assertNotNull(allStudents);
        assertEquals(5, allStudents.size());
        assertEquals(2, allTeachers.size());
        assertEquals(1, allParent.size());
        userRepository.deleteAll(Student.class);
        userRepository.deleteAll(Teacher.class);
        userRepository.deleteAll(Parent.class);
        allStudents = userRepository.getAll(Student.class);
        allTeachers = userRepository.getAll(Teacher.class);
        allParent = userRepository.getAll(Parent.class);
        assertTrue(allStudents.isEmpty());
        assertTrue(allTeachers.isEmpty());
        assertTrue(allParent.isEmpty());
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void testGetUserByCriteria() throws Exception {
        // get students by class
        ClassInfo classInfo = classInfoRepository.getById(1L);
//        List<Student> studentsOfClass1 = userRepository.getStudentsByClass(classInfo);
        List<Student> studentsOfClass1 = userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_CLASS, with("classInfo", classInfo).parameters());
        assertNotNull(studentsOfClass1);
        assertEquals(2, studentsOfClass1.size());
        Student studentFromClass1_1 = userRepository.getStudentById(1L);
        Student studentFromClass1_2 = userRepository.getStudentById(7L);
        assertTrue(studentsOfClass1.contains(studentFromClass1_1) && studentsOfClass1.contains(studentFromClass1_2));

        // get students by teacher
        Teacher teacher = userRepository.getTeacherById(8L);
//        List<Student> studentsOfTeacher1 = userRepository.getStudentsByTeacher(teacher);
        List<Student> studentsOfTeacher1 = userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_TEACHER, with("teacher", teacher).parameters());
        assertNotNull(studentsOfTeacher1);
        assertEquals(5, studentsOfTeacher1.size());
        Set<Student> allStudents = userRepository.getAll(Student.class);
        for (Student s : allStudents){
            assertTrue(studentsOfTeacher1.contains(s));
        }

        // get teachers by class
//        List<Teacher> teachersOfClass1 = userRepository.getTeachersByClass(classInfo);
        List<Teacher> teachersOfClass1 = userRepository.findWithNamedQuery(Teacher.class, Teacher.TEACHERS_BY_CLASS, with("classInfo", classInfo).parameters());
        assertNotNull(teachersOfClass1);
        assertEquals(2, teachersOfClass1.size());
        Set<Teacher> allTeachers = userRepository.getAll(Teacher.class);
        for (Teacher t : allTeachers){
            assertTrue(teachersOfClass1.contains(t));
        }

        // get students by parent
        Parent parent = userRepository.getParentById(3L);
//        List<Student> kidsOfParent1 = userRepository.getStudentsByParent(parent);
        List<Student> kidsOfParent1 = userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_PARENT, with("parent", parent).parameters());
        assertNotNull(kidsOfParent1);
        assertEquals(3, kidsOfParent1.size());
        Student kid1 = userRepository.getStudentById(1L);
        Student kid2 = userRepository.getStudentById(6L);
        Student kid3 = userRepository.getStudentById(7L);
        assertTrue(kidsOfParent1.contains(kid1) && kidsOfParent1.contains(kid2) && kidsOfParent1.contains(kid3));

        // get students by level
        Level level = levelRepository.getById(3L);
//        List<Student> studentsOfLevel1 = userRepository.getStudentsByLevel(level);
        List<Student> studentsOfLevel1 = userRepository.findWithNamedQuery(Student.class, Student.STUDENTS_BY_LEVEL, with("level", level).parameters());
        assertNotNull(studentsOfLevel1);
        assertEquals(2, studentsOfLevel1.size());
        Student studentOfLevel1_1 = userRepository.getStudentById(4L);
        Student studentOfLevel1_2 = userRepository.getStudentById(7L);
        assertTrue(studentsOfLevel1.contains(studentOfLevel1_1) && studentsOfLevel1.contains(studentOfLevel1_2));

        // get user by login
        List<User> user = userRepository.findWithNamedQuery(User.class, User.USER_BY_LOGIN_AND_PASSWORD, with("login", "default_login2").parameters(), 1);
        assertNotNull(user);
        assertEquals(1, user.size());
        assertEquals(4, user.get(0).getId());
    }

}