package com.webblogTeam1.demo1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registration", new RegistrationDTO("", "", ""));
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registration") RegistrationDTO registration, BindingResult bindingResult) {

        if(!registration.getPassword1().equals(registration.getPassword2())) {
            bindingResult.addError(new FieldError("registration", "password2", "passwords are not matching"));
        }

        if(userRepository.existsByUsername(registration.getUsername())) {
            bindingResult.addError(new FieldError("registration", "username", "username already in use"));
        }

        if(bindingResult.hasErrors()) {
            return "register";
        }

        // Everything okay with signing up!!!
        BlogUser user = new BlogUser(registration.getUsername(), registration.getPassword1());
        userRepository.save(user);

        return "redirect:/login";
    }
}
