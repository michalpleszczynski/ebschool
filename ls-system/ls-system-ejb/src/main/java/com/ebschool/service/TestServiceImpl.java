package com.ebschool.service;

import com.ebschool.model.Test;
import com.ebschool.repo.TestRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(TestServiceLocal.class)
@Remote(TestServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TestServiceImpl implements TestServiceLocal {

    @Inject
    TestRepository testRepository;

    @Override
    public Test getById(Long id) {
        return testRepository.getById(id);
    }

    @Override
    public Set<Test> getAll() {
        return testRepository.getAll();
    }

    @Override
    public Test create(Test test) {
        return testRepository.create(test);
    }

    @Override
    public Test update(Test test) {
        return testRepository.update(test);
    }

    @Override
    public void delete(Test... tests) {
        testRepository.delete(tests);
    }
}
