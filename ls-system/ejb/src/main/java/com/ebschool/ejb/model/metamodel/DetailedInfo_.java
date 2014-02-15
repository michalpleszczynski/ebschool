package com.ebschool.ejb.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ebschool.ejb.model.Address;
import com.ebschool.ejb.model.DetailedInfo;
import org.joda.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DetailedInfo.class)
public abstract class DetailedInfo_ {

	public static volatile SingularAttribute<DetailedInfo, LocalDate> dateOfBirth;
	public static volatile SingularAttribute<DetailedInfo, Long> id;
	public static volatile SingularAttribute<DetailedInfo, Address> address;
	public static volatile SingularAttribute<DetailedInfo, LocalDate> dateJoined;
	public static volatile SingularAttribute<DetailedInfo, String> identificationNumber;

}

