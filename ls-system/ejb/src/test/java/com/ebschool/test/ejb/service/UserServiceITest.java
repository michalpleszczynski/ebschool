package com.ebschool.test.ejb.service;

import com.ebschool.ejb.model.User;
import com.ebschool.ejb.security.Roles;
import com.ebschool.ejb.service.UserService;
import com.ebschool.ejb.utils.Identifiable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import com.ebschool.test.ejb.utils.DataBuilder;

/**
 * User: michau
 * Date: 5/19/13
 */
@Ignore
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
public class UserServiceITest {

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Identifiable.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(Roles.class.getPackage())
                .addPackage(UserService.class.getPackage())
                .addPackage(DataBuilder.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("test-hibernate.cfg.xml");

        return ejb;
    }

}
