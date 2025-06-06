package com.cyworld.cyworld_clone.controller.user;

import com.cyworld.cyworld_clone.dto.UserDto;
import com.cyworld.cyworld_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;


    @GetMapping("/signup")
    public String signup(){
        return "/user/signup";
    }

    @GetMapping("/check-username")
    @ResponseBody
    public String checkUsername(@RequestParam String username){
        System.out.println("입력한 username = " + username);

        if(userService.isUsernameAvailable(username)) {
            return "OK";
        }
        else {
            return "DUPLICATE";
        }
    }


    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute UserDto userDto){
        userService.registerUser(userDto);
        System.out.println("signupSubmit 호출됨");

        return "redirect:/login";
    }
}
