package userstory;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataBuilder;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 5/19/13
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
@TransactionManagement(TransactionManagementType.BEAN)
@Transactional(manager = "java:jboss/UserTransaction", value = TransactionMode.DISABLED)
public class StudentITest {

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

    // it looks like applyScriptBefore annotation automatically wraps method in transaction, so to manage
    // transactions explicitly we need to commit at the beginning and start at the end
    // not elegant but probably will sometime be fixed in arquillian
    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void testStudentLifecycle() throws Exception {
        userTransaction.commit();
        userTransaction.begin();

        // Student enrolls to school
        Student newStudent = DataBuilder.buildStudent();
        newStudent = (Student)userService.create(newStudent);
        Level level = levelService.getByName("elementary");
        newStudent.setLevel(level);
        classInfoService.addStudent(newStudent, classInfoService.getById(1L));

        // Participates in classes, gets grades
        gradeService.giveGrade(newStudent, DataBuilder.buildGrade());
        Grade grade2 = new Grade();
        grade2.setComment("second grade");
        grade2.setPercentage((byte) 99);
        grade2.setWeight((byte) 2);
        gradeService.giveGrade(newStudent, grade2);
        userTransaction.commit();
        userTransaction.begin();

        // check if the changes were saved properly
        Student savedStudent = (Student)userService.getByLogin(DataBuilder.getLastLogin());
        assertNotNull(savedStudent);
        assertEquals(newStudent, savedStudent);

        Level returnedLevel = savedStudent.getLevel();
        assertNotNull(returnedLevel);
        assertEquals("elementary", returnedLevel.getName());

        Set<ClassInfo> savedClasses = savedStudent.getClasses();
        assertNotNull(savedClasses);
        assertEquals(1, savedClasses.size());
        assertEquals(1L, savedClasses.iterator().next().getId());

        List<Student> studentsOfClass = studentService.getStudentsByClass(classInfoService.getById(1L));
        assertNotNull(studentsOfClass);
        assertTrue(studentsOfClass.contains(savedStudent));

        List<Grade> savedGrades = gradeService.getGradesByStudent(savedStudent);
        assertNotNull(savedGrades);
        assertEquals(2, savedGrades.size());
        Grade grade1 = savedGrades.get(0);
        assertEquals(grade1.getComment(), DataBuilder.DEFAULT_GRADE_COMMENT);
        assertEquals(grade1.getPercentage(), DataBuilder.DEFAULT_GRADE_PERCENTAGE);
        assertEquals(grade1.getWeight(), DataBuilder.DEFAULT_GRADE_WEIGHT);
        grade2 = savedGrades.get(1);
        assertEquals(grade2.getComment(), "second grade");
        assertEquals(grade2.getPercentage(), (byte)99);
        assertEquals(grade2.getWeight(), (byte)2);

        // Student finished his school, his account first is disabled
        userService.disableAccount(savedStudent);
        assertFalse(savedStudent.isActive());

        // and then after sometime deleted completely
        userService.delete(savedStudent);

        userTransaction.commit();
        userTransaction.begin();

        // check if student was properly deleted along with his grades and from his classes
        Student deletedStudent = (Student)userService.getByLogin(DataBuilder.getLastLogin());
        assertNull(deletedStudent);

        assertNull(gradeService.getById(grade1.getId()));
        assertNull(gradeService.getById(grade2.getId()));

        studentsOfClass = studentService.getStudentsByClass(classInfoService.getById(1L));
        assertNotNull(studentsOfClass);
        assertFalse(studentsOfClass.contains(savedStudent));
    }

}
