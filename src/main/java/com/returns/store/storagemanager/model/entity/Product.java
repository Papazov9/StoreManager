package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "return_item_id", unique = true, nullable = false)
    private String returnItemId;

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public Product setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }
}
