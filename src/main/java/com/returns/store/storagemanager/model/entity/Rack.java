package com.returns.store.storagemanager.model.entity;

import com.returns.store.storagemanager.model.enums.SizeEnum;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Column(name = "next_free")
    private Integer nextFree;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @OneToMany(fetch = FetchType.EAGER)
    private Map<Integer, SellingProduct> products;

    public Rack() {
        this.nextFree = 1;
    }

    public boolean addProduct(SellingProduct product) {
        if (this.products == null) {
            this.products = new HashMap<>();
        }
        if (this.products.size() == this.quantity){
            this.nextFree = -1;
            return false;
        }
        else {
            if (this.products.containsKey(product.getRackNumber())){
                return false;
            }
            this.products.put(product.getRackNumber(), product);
        }
        setNewNextFree();
        return true;
    }

    public boolean removeProduct(SellingProduct product) {
        if (this.products == null) {
            this.products = new HashMap<>();
            return true;
        }

        if (this.products.containsKey(product.getRackNumber())) {
            if (product.getRackNumber() < this.nextFree) {
                this.nextFree = product.getRackNumber();
            }

            this.products.remove(product.getRackNumber());
            return true;
        }

        return false;
    }

    private void setNewNextFree() {
        Integer previousBusy = null;
        for (Integer current: this.products.keySet().stream().sorted().toList()) {
            if (previousBusy == null) {
                previousBusy = current;
            } else  {
                if (current - previousBusy > 1) {
                    this.nextFree = previousBusy + 1;
                    break;
                }
                else {
                    previousBusy = current;
                }
            }
        }
        if (previousBusy != null) {
            this.nextFree = previousBusy + 1;
        }
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

    public Map<Integer, SellingProduct> getProducts() {
        return products;
    }

    public Rack setProducts(Map<Integer, SellingProduct> products) {
        this.products = products;
        return this;
    }

    public boolean isFull() {
        return this.products.size() == this.quantity;
    }
}
