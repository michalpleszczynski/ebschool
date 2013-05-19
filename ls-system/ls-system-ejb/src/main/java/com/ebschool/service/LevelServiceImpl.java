package com.ebschool.service;

import com.ebschool.model.Level;
import com.ebschool.repo.LevelRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(LevelServiceLocal.class)
@Remote(LevelServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LevelServiceImpl implements LevelServiceLocal {

    @Inject
    LevelRepository levelRepository;

    @Override
    public Level getById(Long id) {
        return levelRepository.getById(id);
    }

    @Override
    public Set<Level> getAll() {
        return levelRepository.getAll();
    }

    @Override
    public Level create(Level level) {
        return levelRepository.create(level);
    }

    @Override
    public Level update(Level level) {
        return levelRepository.update(level);
    }

    @Override
    public void delete(Level... levels) {
        levelRepository.delete(levels);
    }

}
