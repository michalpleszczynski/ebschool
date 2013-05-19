package com.ebschool.service;

import com.ebschool.model.ClassInfo;

/**
 * User: michau
 * Date: 5/18/13
 */
public interface ClassInfoServiceLocal extends ClassInfoServiceRemote{

    public ClassInfo update(ClassInfo classInfo);

}
