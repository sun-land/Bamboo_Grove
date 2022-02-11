package com.sparta.team6project.controller;

import com.sparta.team6project.dto.SignupRequestDto;
import com.sparta.team6project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/user/signup")
    public void registerUser(SignupRequestDto requestDto){
        userService.registerUser(requestDto);

    }
}
