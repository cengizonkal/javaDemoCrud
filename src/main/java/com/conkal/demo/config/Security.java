package com.conkal.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class Security {


  @Bean
  UserDetailsService userDetailsService() {
    UserDetails user1 = User
        .withUsername("admin") //hardcoded user
        .password("admin") //hardcoded password
        .roles("ADMIN")
        .passwordEncoder(passwordEncoder()::encode)
        .build();
    return new InMemoryUserDetailsManager(user1);
  }


  @Bean
  SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(
            auth -> auth.anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable);

    return http.build();
  }

  //password encoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}