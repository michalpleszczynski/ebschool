package com.ebschool.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * User: michau
 * Date: 3/20/13
 * Time: 9:20 PM
 */
@Entity
@Table(name = "parent")
public class Parent extends User {

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Student> childrenAccounts;

    public Set<Student> getChildrenAccounts(){
        return childrenAccounts;
    }

    public void setChildrenAccounts(Set<Student> childrenAccounts){
        this.childrenAccounts = childrenAccounts;
    }

}
