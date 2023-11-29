package com.webblogTeam1.demo1.message;

import com.webblogTeam1.demo1.comment.Comment;
import com.webblogTeam1.demo1.user.BlogUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post_message")
public class PostMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_name")
    private BlogUser user;


    private String text;

    private Instant postedAt;


    @OneToMany(mappedBy = "postMessage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    protected PostMessage() {
    }

    public PostMessage(BlogUser user, String text, Instant postedAt) {
        this.user = user;
        this.text = text;
        this.postedAt = postedAt;
        this.comments = new ArrayList<Comment>();
    }

    public void addComment(Comment c) {
        comments.add(c);
    }

    public void deleteComment(Long commentId) {
        Comment toDelete;
        for (Comment elem: comments) {
            if (elem.getId() == commentId){

                comments.remove(elem);
            }
        }
    }
}
