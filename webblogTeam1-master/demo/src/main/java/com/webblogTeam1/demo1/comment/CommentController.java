package com.webblogTeam1.demo1.comment;

import com.webblogTeam1.demo1.message.PostMessage;
import com.webblogTeam1.demo1.message.PostMessageDTO;
import com.webblogTeam1.demo1.message.PostMessageRepository;
import com.webblogTeam1.demo1.user.BlogUser;
import com.webblogTeam1.demo1.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostMessageRepository  postMessageRepository;
    private final UserRepository userRepository;


    @Autowired
    public CommentController(CommentRepository commentRepository, PostMessageRepository postMessageRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postMessageRepository = postMessageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/post/{postId}")
    public String profile(@PathVariable Long postId, @ModelAttribute("sessionUser") BlogUser sessionUser,  Model model) {
        PostMessage postMessage = postMessageRepository.findById(postId).orElseThrow();
        model.addAttribute("postMessage", postMessage);
        model.addAttribute("comment", new CommentDTO(""));
        return "post";
    }

    @PostMapping("/post/{postId}")
    public String comment(@Valid @ModelAttribute("comment") CommentDTO commentDTO,
                          @ModelAttribute("sessionUser") BlogUser sessionUser, @PathVariable Long postId, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/post/"+ postId;
        }

        Comment comment = new Comment(sessionUser, commentDTO.getText(), Instant.now());
        PostMessage temp = postMessageRepository.findById(postId).orElseThrow();
        comment.setPostMessage(temp);
        temp.addComment(comment);
        commentRepository.save(comment);
        postMessageRepository.save(temp);
        return "redirect:/post/"+ postId;
    }

    @PostMapping("/deletecomment/{commentId}")
    public String comment(@ModelAttribute("sessionUser") BlogUser sessionUser, @PathVariable Long commentId) {

        commentRepository.deleteById(commentId);
        return "redirect:/";
    }
}
