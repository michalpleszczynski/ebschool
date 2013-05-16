package repo;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.User;
import com.ebschool.repo.ClassInfoRepository;
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
 * Date: 5/13/13
 * Time: 2:58 PM
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
public class ClassInfoRepositoryTest {

    @Inject
    ClassInfoRepository classInfoRepository;

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
        ClassInfo classInfo = classInfoRepository.getById(1L);
        assertNotNull(classInfo);
        assertEquals("this is a description", classInfo.getDescription());
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void createTest() throws Exception {
        ClassInfo classInfo = DataBuilder.buildClass();
        assertNotNull(classInfo);
        ClassInfo returnedClass = classInfoRepository.create(classInfo);
        assertEquals(returnedClass, classInfo);
    }

    @Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void deleteTest() throws Exception {
        Set<ClassInfo> classes = classInfoRepository.getAll();
        assertNotNull(classes);
        assertEquals(3, classes.size());
        ClassInfo classInfo = classInfoRepository.getById(3L);
        classInfoRepository.delete(classInfo);
        classes = classInfoRepository.getAll();
        assertNotNull(classes);
        assertEquals(2, classes.size());
        classInfoRepository.deleteAll();
        classes = classInfoRepository.getAll();
        assertNotNull(classes);
        assertTrue(classes.isEmpty());
    }
}
