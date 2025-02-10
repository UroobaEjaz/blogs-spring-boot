package com.urooba.springbootlearning.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "users_info")

public class User implements UserDetails {
    //Assigning User
    //1. id, name, birthdate, post

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;



    @Size(min = 5, message = "It should have at least 5 characters")
    private String firstName;

    @Size(min=2, message = "It should be more than 2 characters")
    private String lastName;

    @Size(min=5 )
    @Column(name = "user_name")
    private String username;

    @Past
    private LocalDateTime birthdate;


    @OneToMany(mappedBy = "User" ,  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> post;

    //Add Enum, that contains Either user and admin variables
//    @Enumerated(EnumType.STRING)
//    private UserRole userRole;

    @NotNull
    @Column
    private String roles;


    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;


    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 255, message = "Password must be at least 8 characters long")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&)")
    private String password;




//constructor


    public User(Long user_id, String firstName, String lastName,String username, LocalDateTime birthdate,String email, String roles) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.birthdate = birthdate;
        this.email = email;
        this.roles = roles;
    }

    public User() {

    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long user_id) {
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

    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(roles.split(",")).stream()
                .map(role -> (GrantedAuthority) () ->  role.trim().toUpperCase()) // Ensuring correct ROLE_ prefix
                .collect(Collectors.toList());
    }

    public  String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", birthdate=" + birthdate +
                ", post=" + post +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
