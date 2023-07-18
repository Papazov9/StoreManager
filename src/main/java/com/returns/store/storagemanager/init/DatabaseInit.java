package com.returns.store.storagemanager.init;

import com.returns.store.storagemanager.service.RoleService;
import com.returns.store.storagemanager.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public DatabaseInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        this.roleService.initRoles();
        this.userService.initFirstUser();
    }
}
