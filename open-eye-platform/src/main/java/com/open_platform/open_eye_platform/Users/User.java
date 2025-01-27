package com.open_platform.open_eye_platform.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.open_platform.open_eye_platform.Posts.Post;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.*;
import jdk.jfr.Name;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "users_info")

public class User {
    //Assigning User
    //1. id, name, birthdate, post

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int user_id;



    @Size(min = 5, message = "It should have at least 5 characters")
    private String firstName;

    @Size(min=2, message = "It should be more than 2 characters")
    private String lastName;

    @Size(min=5 )
    private String userName;


    @Past
    private LocalDateTime birthdate;


    @OneToMany(mappedBy = "User")
    @JsonIgnore
    private List<Post> post;

    //Add Enum, that contains Either user and admin variables
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;


    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 255, message = "Password must be at least 8 characters long")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&)")
    private String password;




//constructor


    public User(int user_id, String firstName, String lastName,String userName, LocalDateTime birthdate,String email) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.birthdate = birthdate;
        this.email = email;
    }

    protected User() {

    }

    public int getId() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", birthdate=" + birthdate +
                ", post=" + post +
                ", userRole=" + userRole +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
