package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.Role;
import com.returns.store.storagemanager.model.enums.RoleEnum;
import com.returns.store.storagemanager.repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public void initRoles() {
        if (this.roleRepo.count() == 0) {
            Arrays.stream(RoleEnum.values()).forEach( r -> {
                Role currentRole = new Role();
                currentRole.setRoleName(r);
                this.roleRepo.saveAndFlush(currentRole);
            } );
        }
    }
}
