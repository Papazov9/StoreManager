package com.returns.store.storagemanager.model.entity;

import com.returns.store.storagemanager.model.enums.SizeEnum;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "racks")
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rack_name", unique = true, nullable = false)
    private String rackName;

    @Column(nullable = false)
    private Integer quantity;

    @Transient
    private Integer nextFree;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @OneToMany(fetch = FetchType.EAGER)
    private Map<Long, SellingProduct> products;

    public Rack(){
        this.products = new HashMap<>();
        this.nextFree = 1;
    }


    public boolean addProduct(SellingProduct product) {
        if (this.products.size() == this.quantity){
            return false;
        }
        else {
            if (this.products.containsKey(product.getId())){
                return false;
            }
            this.products.put(product.getId(), product);
        }
        this.nextFree++;
        return true;
    }

    public Long getId() {
        return id;
    }

    public Rack setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRackName() {
        return rackName;
    }

    public Rack setRackName(String rackName) {
        this.rackName = rackName;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Rack setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getNextFree() {
        return nextFree;
    }

    public Rack setNextFree(Integer nextFree) {
        this.nextFree = nextFree;
        return this;
    }

    public SizeEnum getSize() {
        return size;
    }

    public Rack setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public Map<Long, SellingProduct> getProducts() {
        return products;
    }

    public Rack setProducts(Map<Long, SellingProduct> products) {
        this.products = products;
        return this;
    }

    public boolean isFull() {
        return this.products.size() == this.quantity;
    }
}
