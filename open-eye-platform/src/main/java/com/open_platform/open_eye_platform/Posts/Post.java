package com.open_platform.open_eye_platform.Posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.open_platform.open_eye_platform.Users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Post {
    //for the post, generate the id, description, User_id,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int post_id;

    @Size(min=10)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)   //Lazy loading means that the related entity is not immediately fetched from the database when the owning entity is loaded.
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User User;






    public Post(int post_id, String description) {
        this.post_id=post_id;
        this.description = description;

    }

    protected Post(){

    }

    public int getId() {
        return post_id;
    }

    public void setId(int post_id) {
        this.post_id=post_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }



    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", description='" + description + '\'' +

                '}';
    }
}
