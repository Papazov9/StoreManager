package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.ScrapPallet;
import com.returns.store.storagemanager.repo.ScrapPalletRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapPalletService {

    private final ScrapPalletRepo scrapPalletRepo;

    public ScrapPalletService(ScrapPalletRepo scrapPalletRepo) {
        this.scrapPalletRepo = scrapPalletRepo;
    }

    public void initPallets() {
        if (this.scrapPalletRepo.count() == 0) {
            for (int i = 1; i <= 4; i++) {
                ScrapPallet pallet = new ScrapPallet();
                pallet.setName("Pallet_" + i);
                pallet.setReady(false);
                this.scrapPalletRepo.saveAndFlush(pallet);
            }
        }
    }

    public List<ScrapPallet> getScrapPallets() {
        return  this.scrapPalletRepo.findAll();
    }
}
