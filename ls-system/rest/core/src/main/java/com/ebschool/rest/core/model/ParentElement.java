package com.ebschool.rest.core.model;

import com.ebschool.ejb.model.Parent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "parent")
public class ParentElement extends UserElement {

    private Set<Long> childrenAccounts;

    public ParentElement() {
        super();
    }

    public ParentElement (Parent parent) {
        super(parent);
    }

    public Set<Long> getChildrenAccounts() {
        return childrenAccounts;
    }

    @XmlElementWrapper(name = "children")
    @XmlElement(name = "student")
    public void setChildrenAccounts(Set<Long> childrenAccounts) {
        this.childrenAccounts = childrenAccounts;
    }
}
