package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.time.Semester;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * User: michau
 * Date: 9/20/13
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class SemesterRepositoryImpl extends GenericRepositoryImpl<Semester, Long> implements SemesterRepository {

    public SemesterRepositoryImpl(){
        super(Semester.class);
    }

}
