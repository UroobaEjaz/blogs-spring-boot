//package com.open_platform.open_eye_platform.Admin;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.open_platform.open_eye_platform.Posts.Post;
//import com.open_platform.open_eye_platform.Users.User;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//
//import java.util.List;
//
//@Entity
//public class Admin_info {
//
//
//    //id, name , post, user
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private int admin_id;
//
//
//    @Size(min=2)
//    private String name;
//
//
//    @OneToMany(mappedBy = "Admin_info")
//    private List<Post> post;
//
//
//
//    public Admin_info(int admin_id, List<Post> post) {
//        this.admin_id = admin_id;
//        this.post = post;
//    }
//
//    protected Admin_info(){
//
//    }
//
//    public int getAdmin_id() {
//        return admin_id;
//    }
//
//    public void setId(int admin_id) {
//        this.admin_id = admin_id;
//    }
//
//    public List<Post> getPost() {
//        return post;
//    }
//
//    public void setPost(List<Post> post) {
//        this.post = post;
//    }
//
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Admin_info{" +
//                "admin_id=" + admin_id +
//                ", name='" + name + '\'' +
//                ", post=" + post +
//
//                '}';
//    }
//}
