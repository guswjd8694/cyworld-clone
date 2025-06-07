package com.cyworld.cyworld_clone.controller;

import com.cyworld.cyworld_clone.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserAttributes(HttpSession session, Model model) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");

        if(loginUser != null) {
            String name = loginUser.getName();
            String userName = loginUser.getUsername();
            String gender = loginUser.getGender();
            String birthday = loginUser.getBirthday();
            String email = loginUser.getEmail();


            model.addAttribute("name", name);
            model.addAttribute("userName", userName);
            model.addAttribute("gender", gender);
            model.addAttribute("birthday", birthday);
            model.addAttribute("email", email);

            System.out.println("홈 진입: " + session.getAttribute("name"));
        }
    }
}
