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
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
public class UserRepositoryITest {

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
        Student student = (Student)userRepository.getById(1L);
        assertNotNull(student);
        assertEquals("name", student.getFirstName());
    }

    @Test
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
        student.setFirstName("fname");
        student.setLastName("lname");
        student.setDetailedInfo(detailedInfo);
        Level level = new Level();
        level.setName("pre-intermediate");
        student.setActive(true);
        student.setEmail("other@email.com");
        student.setLogin("login2122");
        student.setPassword("ff12bbd8c907af067070211d87bdf098be17375b");
        student.setPhoneNumber("34776756");
        student.setLevel(level);
        assertNotNull(student);
        Student returnedStudent = (Student)userRepository.create(student);
        assertEquals(returnedStudent, student);
    }

}