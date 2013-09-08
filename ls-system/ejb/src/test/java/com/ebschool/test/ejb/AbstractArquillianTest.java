package com.ebschool.test.ejb;

import com.ebschool.ejb.exception.DuplicatedUserException;
import com.ebschool.ejb.model.User;
import com.ebschool.ejb.repo.UserRepository;
import com.ebschool.ejb.security.Roles;
import com.ebschool.ejb.service.*;
import com.ebschool.ejb.utils.Identifiable;
import com.ebschool.test.ejb.utils.DataBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

/**
 * User: michau
 * Date: 9/7/13
 */
@RunWith(Arquillian.class)
@TransactionManagement(TransactionManagementType.BEAN)
@Transactional(manager = "java:jboss/UserTransaction", value = TransactionMode.DISABLED)
public abstract class AbstractArquillianTest {

    @EJB
    protected UserServiceLocal userService;

    @EJB
    protected ParentServiceLocal parentService;

    @EJB
    protected TeacherServiceLocal teacherService;

    @EJB
    protected StudentServiceLocal studentService;

    @EJB
    protected LevelServiceLocal levelService;

    @EJB
    protected ClassInfoServiceLocal classInfoService;

    @EJB
    protected GradeServiceLocal gradeService;

    @Resource(lookup = "java:jboss/UserTransaction")
    protected UserTransaction userTransaction;

    @Deployment
    protected static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(UserRepository.class.getPackage())
                .addPackage(Roles.class.getPackage())
                .addPackage(UserServiceLocal.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addPackage(DuplicatedUserException.class.getPackage())
                .addPackage(AbstractArquillianTest.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

        return ejb;
    }

    protected void restartTransaction() throws Exception {
        userTransaction.commit();
        userTransaction.begin();
    }

}
