package io.itaiit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author itaiit
 * @date 2022/8/23 15:00
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
