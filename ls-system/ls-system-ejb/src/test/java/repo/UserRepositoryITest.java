package repo;

import com.ebschool.model.*;
import com.ebschool.repo.UserRepository;
import com.ebschool.utils.Identifiable;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.*;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataBuilder;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import java.io.File;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 4/16/13
 * Time: 9:24 PM
 */
//TODO: Use the rollback only approach instead of recreating schema
@RunWith(Arquillian.class)
//@Cleanup(phase = TestExecutionPhase.NONE, strategy = CleanupStrategy.STRICT)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
//@CreateSchema({"sql-scripts/schema.sql"})
public class UserRepositoryITest {

//    @Resource(lookup = "java:jboss/datasources/TestDS")
//    DataSource testDateSource;

//    protected IDatabaseTester dbTester;

    @Inject
    UserRepository userRepository;

//    @Deployment
//    public static Archive<?> createDeploymentPackage() {
//        return ShrinkWrap.create(JavaArchive.class, "test.jar")
//                .addPackage(Identifiable.class.getPackage())
//                .addPackage(User.class.getPackage())
//                .addPackage(UserRepository.class.getPackage())
//                .addPackage(DataBuilder.class.getPackage())
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//                .addAsManifestResource("test-persistence.xml", "persistence.xml")
//                .addAsResource("test-hibernate.cfg.xml");
//    }


    @Deployment
//    @OverProtocol("Servlet 3.0")
    public static Archive<?> createDeploymentPackage() {

//        File[] libs = Maven.resolver()
//                .loadPomFromFile("pom.xml").resolve("org.hsqldb:hsqldb")
//                .withTransitivity().asFile();

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(UserRepository.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

//        WebArchive ear = ShrinkWrap.create(WebArchive.class, "test.war")
////                .addAsLibraries(libs)
//                .addAsLibrary(ejb)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//                // without that this class does not appear in the archive
//                .addAsLibrary(ShrinkWrap.create(JavaArchive.class)
//                        .addClass(UserRepositoryITest.class));

        return ejb;
    }

//    @BeforeClass
//    public static void init() throws Exception{
//        Class.forName("org.hsqldb.jdbc.JDBCDriver");
//    }

//    @Before
//    public void setUp() throws Exception {
//
//        Configuration configuration = new Configuration();
//        configuration.configure("test-hibernate.cfg.xml");
//
//        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//        configuration.buildSessionFactory(serviceRegistry);
//
////        SchemaExport schemaExport = new SchemaExport(configuration);
////        schemaExport.create(true, true);
//
////        dbTester = new DataSourceDatabaseTester(testDateSource);
////
////        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(
////                "src/test/resources/datasets/dbunit-test-dataset.xml"));
////        dbTester.setDataSet(dataSet);
////        dbTester.onSetup();
//    }

        @Test
//    @ShouldMatchDataSet("datasets/dbunit-test-dataset-expected.xml")
//    @UsingDataSet("datasets/dbunit-test-dataset.xml")
        @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void getByIdTest() throws Exception {
        Student student = (Student)userRepository.getById(1L);
        assertNotNull(student);
        assertEquals("name", student.getFirstName());
    }

    @Test
//    @UsingDataSet("datasets/dbunit-test-dataset.xml")
    @ApplyScriptBefore({"sql-scripts/cleanup.sql","sql-scripts/schema.sql","datasets/mysql-dataset.sql"})
    public void createTest() throws Exception {
        Student student = new Student();
        DetailedInfo detailedInfo = new DetailedInfo();
        Address address = new Address();
        address.setCity("Krakow");
        address.setZipCode("30-000");
        address.setStreet("Rynek");
        address.setCountry("Polska");
        detailedInfo.setAddress(address);
        detailedInfo.setIdentificationNumber("2345465456");
        detailedInfo.setDateOfBirth(345665767);
        detailedInfo.setDateJoined(65767677);
//        DetailedInfo detailedInfo1 = new DetailedInfo();
//        detailedInfo1.setAddress(address);
//        detailedInfo1.setIdentificationNumber("2345465422");
//        detailedInfo1.setDateOfBirth(345665767);
//        detailedInfo1.setDateJoined(65767677);
        student.setFirstName("fname");
        student.setLastName("lname");
        student.setDetailedInfo(detailedInfo);
        Level level = new Level();
        level.setName("pre-intermediate");
//        ClassInfo classInfo = new ClassInfo();
//        classInfo.setDescription("desc");
//        classInfo.setWhen(54657667);
//        classInfo.setLevel(level);
//        level.getClasses().add(classInfo);
//        classInfo.setWhere("there");
//        student.getClasses().add(classInfo);
//        Teacher teacher = new Teacher();
//        teacher.setActive(true);
        student.setActive(true);
//        teacher.setDetailedInfo(detailedInfo1);
//        teacher.setFirstName("fname1");
//        teacher.setLastName("lname1");
//        teacher.setEmail("this@email.com");
        student.setEmail("other@email.com");
//        teacher.setLogin("login1");
        student.setLogin("login2122");
        student.setPassword("ff12bbd8c907af067070211d87bdf098be17375b");
        student.setPhoneNumber("34776756");
        student.setLevel(level);
        assertNotNull(student);
        userRepository.create(student);
    }

}
