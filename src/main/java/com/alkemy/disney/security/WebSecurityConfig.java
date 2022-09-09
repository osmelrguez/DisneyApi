package com.alkemy.disney.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception{
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/characters/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/api/movies/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/api/genres/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/characters").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/movies").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/genres").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/characters/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/movies/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/genres/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
