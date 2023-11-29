package com.webblogTeam1.demo1.profile;

import com.webblogTeam1.demo1.user.BlogUser;
import com.webblogTeam1.demo1.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        BlogUser user = userRepository.findByUsername(username).orElseThrow();
        model.addAttribute("user", user);
        return "profile";
    }
}
