package com.sparta.team6project.service;


import com.sparta.team6project.RequestDto.SignupRequestDto;
import com.sparta.team6project.model.User;
import com.sparta.team6project.repository.UserRepository;
import com.sparta.team6project.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User registerUser(SignupRequestDto requestDto){
        String username = requestDto.getUsername();

        // 입력값 유효성 검사
        isValidUser(requestDto);

        // username 중복검사
        Optional<User> sameUser = userRepository.findByUsername(username);
        if(sameUser.isPresent()){
            throw new IllegalArgumentException("중복된 USERNAME이 존재합니다.");
        }

        //패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password);
        return userRepository.save(user);
    }

    // 로그인 유저네임 확인
    public HashMap<String, String > loginCheck(UserDetailsImpl userDetails) {

        String username = null;
        // 로그인 식별하기
        if(userDetails != null) {
            username = userDetails.getUsername();
        }

        HashMap<String, String> loginUsername = new HashMap<>();
        loginUsername.put("loginUser", username);
        return loginUsername;
    }


    // 유효성 검사 메소드
    private void isValidUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String confirmPassword = requestDto.getConfirmPassword();

        // 아이디 유효성 검사
        Pattern usernamePattern = Pattern.compile("^[0-9a-zA-Z]*$");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        if(username.length()<4 || username.length()>12 || !usernameMatcher.matches()) {
            throw new IllegalArgumentException("아이디는 영어,숫자를 사용하여 4~12자로 입력해주세요.");
        }

        // 비밀번호 유효성 검사
        Pattern passwordPattern = Pattern.compile("^[\\S]*$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(password.length()<8 || password.length()>16 || !passwordMatcher.matches()) {
            throw new IllegalArgumentException("비밀번호는 영어,숫자,특수문자를 사용하여 8~16자로 입력해주세요.");
        }

        // password 일치여부
        if(!password.equals(confirmPassword)){
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
    }


}
