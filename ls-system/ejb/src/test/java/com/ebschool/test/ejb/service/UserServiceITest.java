package com.ebschool.test.ejb.service;

import com.ebschool.test.ejb.AbstractArquillianServiceDataTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 * User: michau
 * Date: 5/19/13
 */
@Ignore
@RunWith(Arquillian.class)
@CleanupUsingScript(value = "sql-scripts/cleanup.sql")
public class UserServiceITest extends AbstractArquillianServiceDataTest {

}
