package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Level;
import com.ebschool.ejb.repo.LevelRepository;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.ebschool.ejb.utils.QueryParameter.with;

/**
 * User: michau
 * Date: 5/19/13
 */
@Stateless
@Local(LevelService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class LevelServiceImpl implements LevelService {

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

    @Override
    public Level getByName(String name){
        List<Level> levels = levelRepository.findWithNamedQuery(Level.class, Level.LEVEL_BY_NAME, with("name", name).parameters(), 1);
        return levels.isEmpty() ? null : levels.get(0);
    }

}
