package com.ebschool.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ebschool.utils.Identifiable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

//TODO: add hashing of the password at some level
@Entity
@Table(name = "basic_user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "login"}))
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "findUserByLoginAndPassword", query = "SELECT u FROM User as u WHERE u.login = :login")
})
public abstract class User  implements Identifiable, Serializable {

    private static final long serialVersionUID = 1010L;

    public static final String USER_BY_LOGIN_AND_PASSWORD = "findUserByLoginAndPassword";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 25)
    @Pattern(regexp = "[A-Za-z0-9]*", message = "must contain only letters and digits")
    @Column(length = 25, nullable = false, unique = true)
    private String login;

    @Size(min = 2, max = 15)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    @Column(name = "first_name", length = 15, nullable = false)
    private String firstName;

    @Size(min = 3, max = 20)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return (getFirstName() != null ? getFirstName().equals(user.getFirstName()) : user.getFirstName() == null) &&
                (getLastName() != null ? getLastName().equals(user.getLastName()) : user.getLastName() == null) &&
                (getLogin() != null ? getLogin().equals(user.getLogin()) : user.getLogin() == null) &&
                (getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() ==null);
    }

    @Override
    public int hashCode(){
        return getLogin() != null ? getLogin().hashCode() : 0;
    }
}