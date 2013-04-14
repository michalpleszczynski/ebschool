package com.ebschool.repo;

import com.ebschool.model.Test;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:29 PM
 */
public class TestRepositoryImpl extends GenericRepositoryImpl<Test, Long> implements TestRepository{
    public TestRepositoryImpl(Class<Test> clazz) {
        super(Test.class);
    }
}
