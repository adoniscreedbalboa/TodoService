package com.webapp.Todo.Security;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.function.Function;

@Configuration
public class Authenitication {

    @Bean
    public InMemoryUserDetailsManager createuserdetails(){


        UserDetails userDetails= getUser("fghj","fghj");
        UserDetails userDetails1= getUser("asdf","asdf");
        return new InMemoryUserDetailsManager(userDetails,userDetails1);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails getUser(String username,String password){
        Function<String,String> passwordEncoder=input->passwordEncoder().encode(input);
        return User.builder().passwordEncoder(passwordEncoder).username(username)
                .password(password).roles("USER","ADMIN").build();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpRequest) throws Exception {
        httpRequest.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        httpRequest.formLogin(Customizer.withDefaults());
        httpRequest.csrf().disable();
        httpRequest.headers().frameOptions().disable();

        return httpRequest.build();
    }

}
