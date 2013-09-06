package com.ebschool.ejb.service;

import com.ebschool.ejb.model.ClassInfo;

/**
 * User: michau
 * Date: 5/18/13
 */
public interface ClassInfoServiceLocal extends ClassInfoServiceRemote{

    public ClassInfo update(ClassInfo classInfo);

}
