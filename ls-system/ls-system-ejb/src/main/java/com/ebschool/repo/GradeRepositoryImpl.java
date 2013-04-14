package com.ebschool.repo;

import com.ebschool.model.Grade;

/**
 * User: michau
 * Date: 4/14/13
 * Time: 5:26 PM
 */
public class GradeRepositoryImpl extends GenericRepositoryImpl<Grade, Long> implements GradeRepository {
    public GradeRepositoryImpl(Class<Grade> clazz) {
        super(Grade.class);
    }
}
