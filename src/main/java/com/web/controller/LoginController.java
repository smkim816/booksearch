package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping({"", "/"})
    public String localhost() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "redirect:book/list?query= &page=1";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "redirect:login";
    }
}
