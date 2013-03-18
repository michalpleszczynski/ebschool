package com.ebschool.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ebschool.controller.MemberRegistration;
import com.ebschool.model.BasicUser;
import com.ebschool.util.Resources;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(BasicUser.class, MemberRegistration.class, Resources.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    MemberRegistration memberRegistration;

    @Inject
    Logger log;

    @Test
    public void testRegister() throws Exception {
        BasicUser newBasicUser = memberRegistration.getNewBasicUser();
        newBasicUser.setLogin("jane_doe_32");
        newBasicUser.setFirstName("Jane");
        newBasicUser.setLastName("Doe");
        newBasicUser.setEmail("jane@mailinator.com");
        newBasicUser.setPhoneNumber("2125551234");
        memberRegistration.register();
        assertNotNull(newBasicUser.getId());
        log.info(newBasicUser.getLogin() + " was persisted with id " + newBasicUser.getId());
    }

}
