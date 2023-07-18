package com.returns.store.storagemanager.config;

import com.returns.store.storagemanager.model.enums.RoleEnum;
import com.returns.store.storagemanager.repo.UserRepo;
import com.returns.store.storagemanager.service.ReturnsUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/", "/home", "/user/login", "/user/register", "/user/login-error").permitAll()
                .requestMatchers("/static/**", "/js/**", "/css/**", "/img/**", "/videos/**").permitAll()
//                .requestMatchers("/admin/**", "/api/users", "/products/edit/**").hasRole(RoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/", true)
                .failureForwardUrl("/user/login-error")
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");

        return http.build();

    }

    @Bean
    public ReturnsUserDetailsService userDetailsService(UserRepo userRepo) {
        return new ReturnsUserDetailsService(userRepo);
    }
}
