package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.*;
import com.ebschool.ejb.model.time.ClassTime;
import com.ebschool.ejb.model.time.Semester;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClassInfo.class)
public abstract class ClassInfo_ {

	public static volatile SingularAttribute<ClassInfo, Long> id;
	public static volatile SetAttribute<ClassInfo, Student> students;
	public static volatile SingularAttribute<ClassInfo, Level> level;
	public static volatile SetAttribute<ClassInfo, StudentTask> studentTasks;
	public static volatile SingularAttribute<ClassInfo, String> description;
	public static volatile SingularAttribute<ClassInfo, ClassTime> when;
	public static volatile SingularAttribute<ClassInfo, String> where;
	public static volatile SetAttribute<ClassInfo, Teacher> teachers;
	public static volatile SingularAttribute<ClassInfo, Semester> semester;

}

