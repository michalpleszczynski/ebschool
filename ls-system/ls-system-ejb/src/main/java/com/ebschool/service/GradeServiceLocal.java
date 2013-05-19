package com.ebschool.service;

import com.ebschool.model.Grade;

/**
 * User: michau
 * Date: 5/19/13
 */
public interface GradeServiceLocal extends GradeServiceRemote {

    public Grade create(Grade grade);

}
