package com.ebschool.ejb.service;

import com.ebschool.ejb.model.Level;

import java.util.Set;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface LevelServiceRemote {

    public Level getById(Long id);
    public Set<Level> getAll();
    public Level getByName(String name);

    public Level create(Level level);
    public Level update(Level level);
    public void delete(Level... levels);
    
}
