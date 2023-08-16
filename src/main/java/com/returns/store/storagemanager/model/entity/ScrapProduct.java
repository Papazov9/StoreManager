package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "scrap_products")
public class ScrapProduct extends Product{

    @Column(name = "moved_to_scrap")
    private LocalDateTime movedToScrap;
    @ManyToOne(fetch = FetchType.LAZY)
    private ScrapPallet scrapPallet;
    public ScrapProduct() {

    }

    public LocalDateTime getMovedToScrap() {
        return movedToScrap;
    }

    public ScrapProduct setMovedToScrap(LocalDateTime movedToScrap) {
        this.movedToScrap = movedToScrap;
        return this;
    }

    public ScrapPallet getScrapPallet() {
        return scrapPallet;
    }

    public ScrapProduct setScrapPallet(ScrapPallet scrapPallet) {
        this.scrapPallet = scrapPallet;
        return this;
    }
}
