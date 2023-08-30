package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.ScrapPallet;
import com.returns.store.storagemanager.model.entity.ScrapProduct;
import com.returns.store.storagemanager.repo.ScrapPalletRepo;
import com.returns.store.storagemanager.repo.ScrapProductsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapPalletService {

    private final ScrapPalletRepo scrapPalletRepo;
    private final ScrapProductsRepo scrapProductsRepo;

    public ScrapPalletService(ScrapPalletRepo scrapPalletRepo, ScrapProductsRepo scrapProductsRepo) {
        this.scrapPalletRepo = scrapPalletRepo;
        this.scrapProductsRepo = scrapProductsRepo;
    }

    public void initPallets() {
        if (this.scrapPalletRepo.count() == 0) {
            for (int i = 1; i <= 4; i++) {
                ScrapPallet pallet = new ScrapPallet();
                pallet.setName("Pallet_" + i);
                pallet.setReady(false);
                pallet.setExported(false);
                this.scrapPalletRepo.saveAndFlush(pallet);
            }
        }
    }

    public List<ScrapPallet> getScrapPallets() {
        return  this.scrapPalletRepo.findAll();
    }

    public ScrapPallet findByName(String palletName) {
        return this.scrapPalletRepo.findByName(palletName).orElseThrow(() -> new IllegalArgumentException("Invalid pallet name!"));
    }

    public void setExportedTrue(ScrapPallet pallet) {
        pallet.setExported(true);
        this.scrapPalletRepo.save(pallet);
    }

    public void deleteAllProductsInPallet(ScrapPallet pallet) {

        clearScrapProducts(pallet);
        pallet.setExported(false);
        pallet.setReady(false);
        this.scrapPalletRepo.save(pallet);
    }

    private void clearScrapProducts(ScrapPallet pallet) {

        List<ScrapProduct> scrapProducts = pallet.getScrapProducts();
        for (ScrapProduct scrapProduct : scrapProducts) {
            scrapProduct.setScrapPallet(null);
            this.scrapProductsRepo.save(scrapProduct);
        }

        pallet.getScrapProducts().clear();

    }
}
