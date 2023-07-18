package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.Role;
import com.returns.store.storagemanager.model.entity.UserEntity;
import com.returns.store.storagemanager.model.user.ReturnsUserDetails;
import com.returns.store.storagemanager.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ReturnsUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public ReturnsUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " was not found!"));
    }

    private UserDetails map(UserEntity user) {
        return new ReturnsUserDetails(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(this::mapRoles).toList()
        );
    }

    private GrantedAuthority mapRoles(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getRoleName().name());
    }
}
