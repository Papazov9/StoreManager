package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.Role;
import com.returns.store.storagemanager.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByRoleName(RoleEnum admin);
}
