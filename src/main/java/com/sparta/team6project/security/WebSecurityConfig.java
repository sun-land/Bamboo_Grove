package com.sparta.team6project.security;

import com.sparta.team6project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/h2-console/**","/detail.html");
    }
    // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository)) // AuthenticationManager
                .authorizeRequests()
                .antMatchers("/post/write").authenticated()
                .antMatchers("/posts/**").authenticated()
                .antMatchers("/posts/comments/**").authenticated()
                .antMatchers("/comments/**").authenticated()
                .anyRequest().permitAll();
    }
}
