package com.cyworld.cyworld_clone.controller.home;

import com.cyworld.cyworld_clone.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home/index";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");

        if(loginUser != null) {
            return "home/home";
        }
        else {
            return "user/login";
        }
    }
}
