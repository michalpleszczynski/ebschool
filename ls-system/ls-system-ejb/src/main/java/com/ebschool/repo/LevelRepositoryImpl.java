package com.ebschool.repo;

import com.ebschool.model.Level;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:29 PM
 */
public class LevelRepositoryImpl extends GenericRepositoryImpl<Level, Long> implements LevelRepository {
    public LevelRepositoryImpl(Class<Level> clazz) {
        super(Level.class);
    }
}
