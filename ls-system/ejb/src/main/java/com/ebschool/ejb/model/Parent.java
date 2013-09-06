package com.ebschool.ejb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: michau
 * Date: 3/20/13
 * Time: 9:20 PM
 */
@Entity
@Table(name = "parent")
public class Parent extends User implements Serializable {

    private static final long serialVersionUID = 1006L;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "parent_student",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> childrenAccounts;

    public Parent(){
        childrenAccounts = new HashSet<Student>();
    }

    public Set<Student> getChildrenAccounts(){
        return childrenAccounts;
    }

    public void setChildrenAccounts(Set<Student> childrenAccounts){
        this.childrenAccounts = childrenAccounts;
    }

}
