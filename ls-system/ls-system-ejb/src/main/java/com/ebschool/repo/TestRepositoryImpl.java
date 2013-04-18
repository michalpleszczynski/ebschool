package com.ebschool.repo;

import com.ebschool.model.Test;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:29 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TestRepositoryImpl extends GenericRepositoryImpl<Test, Long> implements TestRepository{

    public TestRepositoryImpl() {
        super(Test.class);
    }
}
