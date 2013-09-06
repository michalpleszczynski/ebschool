package com.ebschool.ejb.repo;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.Level;
import com.ebschool.ejb.model.Teacher;

import java.util.List;

/**
 * User: michau
 * Date: 4/13/13
 * Time: 3:27 PM
 */
public interface ClassInfoRepository extends GenericRepository<ClassInfo, Long> {

    public List<ClassInfo> getClassesByTeacher(Teacher teacher);

    public List<ClassInfo> getClassesByLevel(Level level);

}
