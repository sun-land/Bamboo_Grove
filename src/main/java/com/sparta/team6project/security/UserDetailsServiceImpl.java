package com.sparta.team6project.security;

import com.sparta.team6project.model.User;
import com.sparta.team6project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl의 loadUserByUsername가 작동함");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username을 찾을수 없습니다."));

        return new UserDetailsImpl(user);
    }
}
