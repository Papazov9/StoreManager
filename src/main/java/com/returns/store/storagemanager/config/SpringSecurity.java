package com.returns.store.storagemanager.config;

import com.returns.store.storagemanager.model.enums.RoleEnum;
import com.returns.store.storagemanager.repo.UserRepo;
import com.returns.store.storagemanager.service.ReturnsUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/", "/home", "/user/login", "/user/register", "/user/login-error").permitAll(),
            authorize.requestMatchers("/static/**", "/js/**", "/css/**", "/img/**", "/videos/**").permitAll(),
            authorize.requestMatchers("/admin/**", "/api/users", "/products/edit/**").hasRole(RoleEnum.ADMIN.name()),
            authorize.anyRequest().authenticated();
        }).formLogin(withDefaults())
        return http.build();

    }

    @Bean
    public ReturnsUserDetailsService userDetailsService(UserRepo userRepo) {
        return new ReturnsUserDetailsService(userRepo);
    }
}
