package repo;

import com.ebschool.model.Parent;
import com.ebschool.model.Student;
import com.ebschool.model.Teacher;
import com.ebschool.model.User;
import com.ebschool.repo.UserRepository;
import com.ebschool.utils.Identifiable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataBuilder;

import javax.inject.Inject;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 4/16/13
 * Time: 9:24 PM
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(UserRepository.class.getPackage())
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
        Set<User> users = userRepository.getAll();
        assertNotNull(users);
        assertEquals(4, users.size());
        User user = userRepository.getById(2L);
        assertNotNull(user);
        userRepository.delete(user);
        users = userRepository.getAll();
        assertNotNull(users);
        assertEquals(3, users.size());
        User user2 = userRepository.getById(1L);
        User user3 = userRepository.getById(3L);
        userRepository.delete(user2, user3);
        users = userRepository.getAll();
        assertNotNull(users);
        assertEquals(1, users.size());
        userRepository.deleteAll();
        users = userRepository.getAll();
        assertTrue(users.isEmpty());
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void testGetAndDeleteByClass() throws Exception {
        Set<Student> allStudents = userRepository.getAllStudents();
        Set<Teacher> allTeachers = userRepository.getAllTeachers();
        Set<Parent> allParent = userRepository.getAllParents();
        assertNotNull(allParent);
        assertNotNull(allTeachers);
        assertNotNull(allStudents);
        assertEquals(5, allStudents.size());
        assertEquals(2, allTeachers.size());
        assertEquals(1, allParent.size());
        userRepository.deleteAllStudents();
        userRepository.deleteAllTeachers();
        userRepository.deleteAllParents();
        allStudents = userRepository.getAllStudents();
        allTeachers = userRepository.getAllTeachers();
        allParent = userRepository.getAllParents();
        assertTrue(allStudents.isEmpty());
        assertTrue(allTeachers.isEmpty());
        assertTrue(allParent.isEmpty());
    }

}