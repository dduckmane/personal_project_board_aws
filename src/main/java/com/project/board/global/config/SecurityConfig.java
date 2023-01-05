package com.project.board.global.config;

import com.project.board.global.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity//스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)// secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
@RequiredArgsConstructor
public class SecurityConfig{
    private final PrincipalOauth2UserService userService;


    @Bean//스프링 필터에 이 빈을 등록
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .ignoringAntMatchers("/api/**")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ;
        httpSecurity
                .logout()
                .and()
                .authorizeRequests()

                .antMatchers("/user/**").authenticated()
                .anyRequest().permitAll()

                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(userService)
        ;
        return httpSecurity.build();
    }




}
