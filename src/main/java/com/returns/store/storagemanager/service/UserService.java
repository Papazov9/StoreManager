package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.bindings.UserRegistrationBinding;
import com.returns.store.storagemanager.model.entity.Role;
import com.returns.store.storagemanager.model.entity.UserEntity;
import com.returns.store.storagemanager.model.enums.RoleEnum;
import com.returns.store.storagemanager.repo.RoleRepo;
import com.returns.store.storagemanager.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final ReturnsUserDetailsService returnsUserDetailsService;

    public UserService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, ModelMapper modelMapper, ReturnsUserDetailsService returnsUserDetailsService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.returnsUserDetailsService = returnsUserDetailsService;
    }

    public void initFirstUser() {
        if (this.userRepo.count() == 0) {
            UserEntity adminUser = new UserEntity();
            Role adminRole = this.roleRepo.findRoleByRoleName(RoleEnum.ADMIN);

            adminUser
                    .setEmail("admin@admin.bg")
                    .setUsername("admin")
                    .setPassword(this.passwordEncoder.encode("adminReturns"))
                    .setRoles(List.of(adminRole));

            this.userRepo.saveAndFlush(adminUser);
        }
    }

    public boolean isUserExists(UserRegistrationBinding userRegistrationBinding) {
        Optional<UserEntity> user = this.userRepo.findByUsername(userRegistrationBinding.getUsername());
        return user.isPresent();
    }

    public void registerAndLogin(UserRegistrationBinding userRegistrationBinding) {
        Role member = this.roleRepo.findRoleByRoleName(RoleEnum.MEMBER);

        UserEntity user = this.modelMapper.map(userRegistrationBinding, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegistrationBinding.getPassword())).setRoles(List.of(member));
        this.userRepo.saveAndFlush(user);
        this.login(user.getUsername());
    }

    private void login(String username) {
        UserDetails userDetails = this.returnsUserDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }
}
