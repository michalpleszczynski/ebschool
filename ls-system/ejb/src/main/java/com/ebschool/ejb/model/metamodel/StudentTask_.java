package com.ebschool.ejb.model.metamodel;

import com.ebschool.ejb.model.ClassInfo;
import com.ebschool.ejb.model.StudentTask;
import com.ebschool.ejb.model.StudentTask.TaskType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.joda.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StudentTask.class)
public abstract class StudentTask_ {

	public static volatile SingularAttribute<StudentTask, Long> id;
	public static volatile SingularAttribute<StudentTask, ClassInfo> classInfo;
	public static volatile SingularAttribute<StudentTask, String> description;
	public static volatile SingularAttribute<StudentTask, LocalDateTime> when;
	public static volatile SingularAttribute<StudentTask, TaskType> type;

}

