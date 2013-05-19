package other;

import com.ebschool.model.*;
import com.ebschool.repo.UserRepository;
import com.ebschool.service.*;
import com.ebschool.utils.Identifiable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import utils.DataBuilder;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 5/19/13
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
@TransactionManagement(TransactionManagementType.BEAN)
@Transactional(manager = "java:jboss/UserTransaction", value = TransactionMode.DISABLED)
public class EntityRelationshipITest {

    @EJB
    UserServiceLocal userService;

    @EJB
    LevelServiceLocal levelService;

    @EJB
    ClassInfoServiceLocal classInfoService;

    @EJB
    GradeServiceLocal gradeService;

    @EJB
    StudentServiceLocal studentService;

    @EJB
    TestServiceLocal testService;

    @Resource(lookup = "java:jboss/UserTransaction")
    UserTransaction userTransaction;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(UserRepository.class.getPackage())
                .addPackage(UserServiceLocal.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

        return ejb;
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void testEntityRelationship() throws Exception {
        userTransaction.commit();
        userTransaction.begin();

        // While creating a class with a new not-yet persisted (level,student,teacher and test), those entities
        // should be persisted. We don't have to check for the levels existence before because persisting an already existing
        // entity would throw an exception - no exception == wasn't in db
        ClassInfo newClass = DataBuilder.buildClass();
        Student newStudent = DataBuilder.buildStudent();
        Teacher newTeacher = DataBuilder.buildTeacher();
        Test newTest = DataBuilder.buildTest();

        // level already set in DataBuilder
        newClass.getTeachers().add(newTeacher);
        newClass.getStudents().add(newStudent);
        newClass.getTests().add(newTest);
        newTest.setClassInfo(newClass);

        // test we will acquire by querying by classInfo
        String levelName = newClass.getLevel().getName();
        String teachersLogin = newTeacher.getLogin();
        String studentsLogin = newStudent.getLogin();

        ClassInfo returedClass = classInfoService.create(newClass);
        assertEquals(newClass, returedClass);

        userTransaction.commit();
        userTransaction.begin();

        Teacher savedTeacher = (Teacher)userService.getUserByLoginAndPassword(teachersLogin, DataBuilder.DEFAULT_PASSWORD);
        assertNotNull(savedTeacher);
        assertEquals(newTeacher, savedTeacher);

        Student savedStudent = (Student)userService.getUserByLoginAndPassword(studentsLogin, DataBuilder.DEFAULT_PASSWORD);
        assertNotNull(savedStudent);
        assertEquals(newStudent, savedStudent);

        Level savedLevel = levelService.getByName(levelName);
        assertNotNull(savedLevel);

        List<Test> savedTests = testService.getTestsByClass(classInfoService.getById(returedClass.getId()));
        assertNotNull(savedTests);
        assertEquals(1, savedTests.size());
        Test savedTest = savedTests.get(0);
        assertEquals(newTest, savedTest);
    }

}