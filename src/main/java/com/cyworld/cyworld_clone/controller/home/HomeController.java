package com.cyworld.cyworld_clone.controller.home;

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
        Boolean isLogin = (Boolean) session.getAttribute("isLogin");

        if(isLogin != null && isLogin) {
            String name = (String) session.getAttribute("name");
            String userName = (String) session.getAttribute("userName");

            model.addAttribute("name", name);
            model.addAttribute("userName", userName);

            System.out.println("홈 진입: " + session.getAttribute("name"));

            return "home/home";
        }
        else {
            return "user/login";
        }

    }
}
