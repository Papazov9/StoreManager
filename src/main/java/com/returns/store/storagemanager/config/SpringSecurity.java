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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/", "/home", "/user/login", "/user/register", "/user/login-error").permitAll()
                                .requestMatchers("/static/**", "/js/**", "/css/**", "/img/**", "/videos/**").permitAll()
                                .requestMatchers("/admin/**", "/api/users", "/products/edit/**").hasRole(RoleEnum.ADMIN.name())
                                .anyRequest().authenticated()
                ).formLogin(form -> form
                        .loginPage("/user/login")
                        .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                        .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                        .defaultSuccessUrl("/products/progress", true)
                        .failureForwardUrl("/user/login-error"))
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID"));
        return http.build();

    }

    @Bean
    public ReturnsUserDetailsService userDetailsService(UserRepo userRepo) {
        return new ReturnsUserDetailsService(userRepo);
    }
}
