package com.returns.store.storagemanager.model.entity;

import com.returns.store.storagemanager.model.enums.RoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    public Role(){}

    public Long getId() {
        return id;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public RoleEnum getRoleName() {
        return roleName;
    }

    public Role setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
        return this;
    }
}
