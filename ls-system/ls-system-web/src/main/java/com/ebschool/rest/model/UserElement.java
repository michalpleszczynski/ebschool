package com.ebschool.rest.model;

import com.ebschool.model.User;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: michau
 * Date: 5/22/13
 */
@XmlRootElement(name = "user")
public abstract class UserElement {

    private Long id;
    private String login;
    private String email;
    private boolean active;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public UserElement() {}

    public UserElement (User user) {
        setId(user.getId());
        setActive(user.isActive());
        setEmail(user.getEmail());
        setFirstName(user.getFirstName());
        setLogin(user.getLogin());
        setLastName(user.getLastName());
        setPhoneNumber(user.getPhoneNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
