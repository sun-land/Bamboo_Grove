package com.sparta.team6project.controller;

import com.sparta.team6project.RequestDto.SignupRequestDto;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto){

        userService.registerUser(requestDto);
    }

    // 로그인 username 확인용 API
    @GetMapping("/logincheck")
    public HashMap<String, String> loginCheck(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.loginCheck(userDetails);
    }


}
