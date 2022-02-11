package com.sparta.team6project.service;

import com.sparta.team6project.dto.SignupRequestDto;
import com.sparta.team6project.model.User;
import com.sparta.team6project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User registerUser(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        // username 중복검사
        Optional<User> sameUser = userRepository.findByUsername(username);
        if(sameUser.isPresent()){
            throw new IllegalArgumentException("중복된 USERNAME이 존재합니다.");
        }
        // password 일치여부
        if(!requestDto.getPassword().equals(requestDto.getConfirmPassword())){
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        //패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password);
        return userRepository.save(user);
    }
}
