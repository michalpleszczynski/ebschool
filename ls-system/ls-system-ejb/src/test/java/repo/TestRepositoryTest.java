package repo;

import com.ebschool.model.ClassInfo;
import com.ebschool.model.Test;
import com.ebschool.repo.ClassInfoRepository;
import com.ebschool.repo.TestRepository;
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
 * Author: mpleszczynski
 * Date: 5/17/13
 * Time: 1:23 PM
 */
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
@Transactional(manager = "java:jboss/UserTransaction")
public class TestRepositoryTest {

    @Inject
    TestRepository testRepository;

    @Inject
    ClassInfoRepository classInfoRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(Test.class.getPackage())
                .addPackage(Roles.class.getPackage())
                .addPackage(TestRepository.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

        return ejb;
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void testGetById() throws Exception {
        Test test = testRepository.getById(1L);
        assertNotNull(test);
        assertEquals("this is a test", test.getDescription());
        ClassInfo testClass = test.getClassInfo();
        testClass = classInfoRepository.getById(testClass.getId());
        assertNotNull(testClass);
        assertEquals(1L, testClass.getId());
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void testCreate() throws Exception {
        Test test = DataBuilder.buildTest();
        ClassInfo classInfo = classInfoRepository.getById(1L);
        test.setClassInfo(classInfo);
        Test returnedTest = testRepository.create(test);
        assertEquals(returnedTest, test);
    }

    @org.junit.Test
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-big-dataset.sql"})
    public void testDelete() throws Exception {
        Set<Test> tests = testRepository.getAll();
        assertNotNull(tests);
        assertEquals(4, tests.size());
        Iterator iterator = tests.iterator();
        Test test1 = (Test)iterator.next();
        Test test2 = (Test)iterator.next();
        testRepository.delete(test1, test2);
        tests = testRepository.getAll();
        assertEquals(2, tests.size());
        testRepository.deleteAll();
        tests = testRepository.getAll();
        assertEquals(0, tests.size());
    }

}
