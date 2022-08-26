package io.itaiit.controller;

import io.itaiit.data.UserRepository;
import io.itaiit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author itaiit
 * @date 2022/8/25 23:58
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String registrationForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        User save = userRepository.save(form.toUser(NoOpPasswordEncoder.getInstance()));
        return "redirect:/login";
    }


}
