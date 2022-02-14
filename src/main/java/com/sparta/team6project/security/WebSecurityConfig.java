package com.sparta.team6project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
        http.cors().configurationSource(corsConfigurationSource());
        http.csrf().disable();

        http.authorizeRequests()
                // 게시글 작성 인증
                //.antMatchers("/post/write").authenticated()
                // 게시글 수정, 삭제 인증
                //.antMatchers("/posts/**").authenticated()
                // 댓글 작성 인증
                //.antMatchers("/posts/comments/**").authenticated()
                // 댓글 수정, 삭제 인증
                //.antMatchers("/comments/**").authenticated()

                // 그 외 어떤 요청이든 허가
                .anyRequest().permitAll()
                .and()
                    // 로그인 기능
                    .formLogin()
                    // 로그인 view 제공 (GET /user/login)
                    .loginPage("/user/login")
                    // 로그인 처리 (POST /user/login)
                    .loginProcessingUrl("/user/login")
                    // 로그인 성공 시 URL
                    .defaultSuccessUrl("/")
                    // 로그인 실패 시 URL
                    .failureUrl("/user/login?error")
                    .permitAll()
                .and()
                    // 로그아웃 기능
                    .logout()
                    // 로그아웃 처리 URL
                    .logoutUrl("/user/logout")
                    .permitAll();
    }
    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
