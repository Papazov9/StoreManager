package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }
}
