package com.sparta.team6project.controller;

import com.sparta.team6project.dto.SignupRequestDto;
import com.sparta.team6project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto){

        userService.registerUser(requestDto);
    }


}
