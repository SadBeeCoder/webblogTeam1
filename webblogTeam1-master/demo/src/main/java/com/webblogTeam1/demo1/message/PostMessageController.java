package com.webblogTeam1.demo1.message;

import com.webblogTeam1.demo1.user.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.time.Instant;

@Controller
public class PostMessageController {

    private final PostMessageRepository postMessageRepository;

    @Autowired
    public PostMessageController(PostMessageRepository postMessageRepository) {
        this.postMessageRepository = postMessageRepository;
    }

    @GetMapping("/message")
    public String message(Model model) {
        model.addAttribute("postMessage", new PostMessageDTO("",""));
        return "message";
    }


    @PostMapping("/message")
    public String message(@Valid @ModelAttribute("message") PostMessageDTO postMessageDTO,
                          @ModelAttribute("sessionUser") BlogUser sessionUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "message";
        }

        PostMessage postMessage = new PostMessage(sessionUser, postMessageDTO.getText(), Instant.now());
        postMessageRepository.save(postMessage);

        return "redirect:/";
    }

    @PostMapping("/messageDelete")
    public String delete(@RequestParam long messageId, @ModelAttribute("sessionUser") BlogUser sessionUser) {
        PostMessage postMessage = postMessageRepository.findById(messageId).orElseThrow();
        if(postMessage.getUser() != sessionUser) {
            throw new IllegalArgumentException("nein!");
        }

        postMessageRepository.delete(postMessage);

        return "redirect:/" ;
//        + UriComponentsBuilder.fromPath("/profile").pathSegment(sessionUser.getUsername()).toUriString()
    }

}
