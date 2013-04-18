import com.ebschool.model.Student;
import com.ebschool.model.User;
import com.ebschool.repo.UserRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * User: michau
 * Date: 4/16/13
 * Time: 9:24 PM
 */
@RunWith(Arquillian.class)
public class UserRepositoryITest {

    @Inject
    UserRepository userRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(User.class.getPackage())
                .addPackage(UserRepository.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml");
    }

    @Test
    @UsingDataSet("datasets/dbunit-test-dataset.xml")
//    @DataSource(value = "java:/TestDS")
//    @ShouldMatchDataSet("datasets/dbunit-test-dataset-expected.xml")
    public void someTest() throws Exception {
        assertNotNull(userRepository);
//        Student student = (Student)userRepository.getById(1L);
//        assertEquals("name", student.getFirstName());
    }

}
