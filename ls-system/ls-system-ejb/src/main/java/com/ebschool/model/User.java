package com.ebschool.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ebschool.utils.Identifiable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "basic_user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "login"}))
@Inheritance(strategy = InheritanceType.JOINED)
public class User  implements Identifiable {

    @Id
    @GeneratedValue
    private long id;

    @Size(min = 3, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
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

    @Size(min = 40, max = 40)
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
}