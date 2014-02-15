package com.ebschool.ejb.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ebschool.ejb.utils.Identifiable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "basic_user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "login"}))
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "findUserByLogin", query = "SELECT u FROM User as u WHERE u.login = :login"),
        // entity.find(User.class, id) in case of a Parent joined students (children) to the result returning multiple results for 1 id
        @NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.id = :id")
})
public abstract class User  implements Identifiable, Serializable {

    public enum UserType{
        ADMIN, STUDENT, TEACHER, PARENT
    }

    public static final String USER_BY_LOGIN = "findUserByLogin";
    public static final String USER_BY_ID = "findUserById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 25)
    @Pattern(regexp = "[A-Za-z0-9_]*", message = "must contain only letters, digits and underscores")
    @Column(length = 25, nullable = false, unique = true)
    private String login;

    // TODO: remove underscore and digits from pattern and change test data
    @Size(min = 2, max = 15)
    @Pattern(regexp = "[A-Za-z0-9 _]*", message = "must contain only letters and spaces")
    @Column(name = "first_name", length = 15, nullable = false)
    private String firstName;

    @Size(min = 3, max = 20)
    @Pattern(regexp = "[A-Za-z0-9 _]*", message = "must contain only letters and spaces")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @NotEmpty
    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Size(min = 7, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;

    @Column(nullable = false, length = 32, name = "password_")
    private String password;

    @Column(nullable = false)
    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    @Override
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null ||
                !User.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final User user = (User) object;
        if (!Objects.equals(getFirstName(), user.getFirstName()))
            return false;
        if (!Objects.equals(getLastName(), user.getLastName()))
            return false;
        if (!Objects.equals(getLogin(), user.getLogin()))
            return false;
        if (!Objects.equals(getEmail(), user.getEmail()))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getLogin() != null ? getLogin().hashCode() : 0);
        result = result*37 + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return getFirstName() + " " + getLastName() + ": " + getLogin();
    }
}