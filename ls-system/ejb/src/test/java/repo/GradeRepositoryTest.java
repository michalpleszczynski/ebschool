package repo;

import com.ebschool.model.Grade;
import com.ebschool.model.Student;
import com.ebschool.model.Test;
import com.ebschool.repo.GradeRepository;
import com.ebschool.repo.UserRepository;
import com.ebschool.security.Roles;
import com.ebschool.utils.Identifiable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import utils.DataBuilder;

import javax.inject.Inject;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 5/16/13
 * Time: 6:28 PM
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
@Transactional(manager = "java:jboss/UserTransaction")
public class GradeRepositoryTest {

    @Inject
    GradeRepository gradeRepository;

    @Inject
    UserRepository userRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(Grade.class.getPackage())
                .addPackage(Roles.class.getPackage())
                .addPackage(GradeRepository.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

        return ejb;
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void getByIdTest() throws Exception {
        Grade grade = gradeRepository.getById(1L);
        assertNotNull(grade);
        assertEquals("this is a comment", grade.getComment());
        assertEquals(76, grade.getPercentage());
        assertEquals(3, grade.getWeight());
        Student student = grade.getStudent();
        assertNotNull(student);
        assertEquals(1L, student.getId());
        Test test = grade.getTest();
        assertNotNull(test);
        assertEquals(1L, test.getId());
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void createTest() throws Exception {
        Grade grade = DataBuilder.buildGrade();
        assertNotNull(grade);
        Student student = (Student)userRepository.getById(1L);
        assertNotNull(student);
        grade.setStudent(student);
        Grade returnedGrade = gradeRepository.create(grade);
        assertEquals(returnedGrade, grade);
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void deleteTest() throws Exception {
        Set<Grade> grades = gradeRepository.getAll();
        assertNotNull(grades);
        assertEquals(6, grades.size());
        Iterator iterator = grades.iterator();
        Grade grade1 = (Grade)iterator.next();
        Grade grade2 = (Grade)iterator.next();
        gradeRepository.delete(grade1, grade2);
        grades = gradeRepository.getAll();
        assertEquals(4, grades.size());
        gradeRepository.deleteAll();
        grades = gradeRepository.getAll();
        assertEquals(0, grades.size());
    }
}
