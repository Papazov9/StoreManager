package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scrap_pallets")
public class ScrapPallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "is_ready")
    private boolean isReady;

    @Column(name = "pallet_exported")
    private Boolean exported;

    public static final int MINIMAL_QUANTITY = 1;

    @OneToMany(mappedBy = "scrapPallet",fetch = FetchType.EAGER)
    private List<ScrapProduct> scrapProducts;

    public ScrapPallet() {
        this.isReady = false;
    }

    public void addProduct(ScrapProduct product) {
        if (this.scrapProducts == null) {
            this.scrapProducts = new ArrayList<>();
        }

        this.scrapProducts.add(product);
        if (this.scrapProducts.size() >= MINIMAL_QUANTITY) {
            this.isReady = true;
        }
    }

    public Long getId() {
        return id;
    }

    public ScrapPallet setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ScrapPallet setName(String name) {
        this.name = name;
        return this;
    }

    public List<ScrapProduct> getScrapProducts() {
        return scrapProducts;
    }

    public ScrapPallet setScrapProducts(List<ScrapProduct> scrapProducts) {
        this.scrapProducts = scrapProducts;
        return this;
    }

    public boolean isReady() {
        return isReady;
    }

    public ScrapPallet setReady(boolean ready) {
        isReady = ready;
        return this;
    }

    public Boolean isExported() {
        return exported;
    }

    public ScrapPallet setExported(Boolean exported) {
        this.exported = exported;
        return this;
    }
}
