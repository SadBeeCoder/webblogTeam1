package com.webblogTeam1.demo1;

import com.webblogTeam1.demo1.comment.Comment;
import com.webblogTeam1.demo1.comment.CommentRepository;
import com.webblogTeam1.demo1.message.PostMessage;
import com.webblogTeam1.demo1.message.PostMessageRepository;
import com.webblogTeam1.demo1.user.BlogUser;
import com.webblogTeam1.demo1.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.Instant;

@Controller
public class HomeController {

    private final PostMessageRepository postMessageRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public HomeController(PostMessageRepository postMessageRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postMessageRepository = postMessageRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    public void generateDummyData() {

        //erstellen des objekts (java seite)
        BlogUser user1 = new BlogUser("user_name1", "123456");
        BlogUser user2 = new BlogUser("user_name2", "password12");
        BlogUser user3 = new BlogUser("user_name3", "pass45");

        //erstellen des objekts (java seite)
        PostMessage post1 = new PostMessage(user1, "test test test", Instant.now());
        PostMessage post2 = new PostMessage(user2, "check check check",Instant.now());
        PostMessage post3 = new PostMessage(user3, "dieser user hat viel zu erzählen in seinem blog",Instant.now());

        //erstellen des objekts (java seite)
        Comment comment1 = new Comment(user1, "1test comment ttest comment test comment ", Instant.now());
        Comment comment2 = new Comment(user1, "2checktest checktest checktest",Instant.now());
        Comment comment3 = new Comment(user1, "3ich ",Instant.now());
        Comment comment4 = new Comment(user2, "4ich ",Instant.now());
        Comment comment5 = new Comment(user2, "5ich ",Instant.now());
        Comment comment6 = new Comment(user2, "6ich ",Instant.now());
        Comment comment7 = new Comment(user3, "7ich ",Instant.now());
        Comment comment8 = new Comment(user3, "8ich ",Instant.now());
        Comment comment9 = new Comment(user3, "9ich ",Instant.now());

        user1.setRole( Role.ADMIN );

        //den post mit dem kommentar bekannt machen und adden
        post1.addComment(comment1);
        post1.addComment(comment4);
        post1.addComment(comment7);

        post2.addComment(comment2);
        post2.addComment(comment5);
        post2.addComment(comment8);

        post3.addComment(comment3);
        post3.addComment(comment6);
        post3.addComment(comment9);

        //den kommentar mit dem post bekannt machen
        comment1.setPostMessage(post1);
        comment2.setPostMessage(post2);
        comment3.setPostMessage(post3);
        comment4.setPostMessage(post1);
        comment5.setPostMessage(post2);
        comment6.setPostMessage(post3);
        comment7.setPostMessage(post1);
        comment8.setPostMessage(post2);
        comment9.setPostMessage(post3);

        //datenbanken seite, alle dummydaten speichern und in die Database einspeisen
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        postMessageRepository.save(post1);
        postMessageRepository.save(post2);
        postMessageRepository.save(post3);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
        commentRepository.save(comment4);
        commentRepository.save(comment5);
        commentRepository.save(comment6);
        commentRepository.save(comment7);
        commentRepository.save(comment8);
        commentRepository.save(comment9);

    }

    @GetMapping("/")
    public String home(@ModelAttribute("sessionUser") BlogUser sessionUser, Model model) {
        model.addAttribute("messages", postMessageRepository.findAllByOrderByPostedAtDesc());
        return "home";
    }

}
