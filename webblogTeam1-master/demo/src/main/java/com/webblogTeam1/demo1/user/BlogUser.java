package com.webblogTeam1.demo1.user;

import com.webblogTeam1.demo1.Role;
import com.webblogTeam1.demo1.message.PostMessage;
import com.webblogTeam1.demo1.comment.Comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "User_Data")
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<PostMessage> postmessages;

    private Role role;

    public BlogUser() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    public BlogUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.REGISTEREDUSER;
        this.comments = new ArrayList<Comment>();
        this.postmessages = new ArrayList<PostMessage>();
    }


    public String getPassword() {
        return password;
    }

    public List<PostMessage> getMessages() {
        return postmessages;
    }


}
