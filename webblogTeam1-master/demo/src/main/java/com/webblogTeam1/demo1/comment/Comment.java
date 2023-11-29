package com.webblogTeam1.demo1.comment;


import com.webblogTeam1.demo1.message.PostMessage;
import com.webblogTeam1.demo1.user.BlogUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "post_Message")
    private PostMessage postMessage;

    @ManyToOne()
    @JoinColumn(name = "user_name")
    private BlogUser user;

    @NotBlank(message = "Name is required")
    private String text;


    private Instant postedAt;

    protected Comment() {
    }

    public Comment(BlogUser user, String text, Instant postedAt) {
        this.user = user;
        this.text = text;
        this.postedAt = postedAt;
    }

}
