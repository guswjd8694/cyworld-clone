package com.cyworld.cyworld_clone.controller.user;

import com.cyworld.cyworld_clone.dao.UserDao;
import com.cyworld.cyworld_clone.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }


    @PostMapping("/login")
    public String loginSubmit(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        try {
            UserDto user = userDao.findByUsername(username);

            if(user != null && user.getPassword().equals(password)) {
                session.setAttribute("isLogin", true);
                session.setAttribute("loginUser", user);
                session.setAttribute("name", user.getName());
                session.setAttribute("username", user.getUsername());

                System.out.println("로그인 성공! 세션 저장: " + user.getName() + ", " + user.getUsername());

                return "redirect:/home";
            }
            else {
                model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");

                return "/user/login";
            }
        }
        catch (Exception e) {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");

            return "/user/login";
        }
    }
}
