package com.ebschool.repo;

import com.ebschool.model.Level;

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
public class LevelRepositoryImpl extends GenericRepositoryImpl<Level, Long> implements LevelRepository {

    public LevelRepositoryImpl() {
        super(Level.class);
    }
}
