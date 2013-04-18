package com.ebschool.repo;

import com.ebschool.model.Grade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:26 PM
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GradeRepositoryImpl extends GenericRepositoryImpl<Grade, Long> implements GradeRepository {

    public GradeRepositoryImpl() {
        super(Grade.class);
    }
}
