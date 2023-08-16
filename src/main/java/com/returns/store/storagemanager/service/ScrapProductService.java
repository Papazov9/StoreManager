package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.InProgressProduct;
import com.returns.store.storagemanager.model.entity.ScrapPallet;
import com.returns.store.storagemanager.model.entity.ScrapProduct;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.repo.InProgressProductRepo;
import com.returns.store.storagemanager.repo.ScrapPalletRepo;
import com.returns.store.storagemanager.repo.ScrapProductsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScrapProductService {

    private final InProgressProductRepo inProgressProductRepo;
    private final ScrapProductsRepo scrapProductsRepo;

    private final ScrapPalletRepo scrapPalletRepo;
    private final ModelMapper modelMapper;

    public ScrapProductService(InProgressProductRepo productRepo, ScrapProductsRepo scrapProductsRepo, ScrapPalletRepo scrapPalletRepo, ModelMapper modelMapper) {
        this.inProgressProductRepo = productRepo;
        this.scrapProductsRepo = scrapProductsRepo;
        this.scrapPalletRepo = scrapPalletRepo;
        this.modelMapper = modelMapper;
    }

    public void addProductToScrap(InProgressProduct prod, String palletName) {
        this.inProgressProductRepo.deleteById(prod.getId());
        ScrapProduct scrapProduct = new ScrapProduct();
        scrapProduct = mapToScrap(prod, scrapProduct);

        Optional<ScrapPallet> pallet = this.scrapPalletRepo.findByName(palletName);

        if (pallet.isPresent()) {
            scrapProduct.setScrapPallet(pallet.get());
            pallet.get().addProduct(scrapProduct);
            this.scrapPalletRepo.saveAndFlush(pallet.get());
            this.scrapProductsRepo.saveAndFlush(scrapProduct);
        }

    }

    private ScrapProduct mapToScrap(InProgressProduct prod, ScrapProduct scrapProduct) {
        return (ScrapProduct) scrapProduct.setMovedToScrap(LocalDateTime.now())
                .setQuantity(prod.getQuantity())
                .setDescription(prod.getDescription())
                .setSubCategory(prod.getSubCategory())
                .setPalletId(prod.getPalletId())
                .setLpn(prod.getLpn())
                .setEan(prod.getEan())
                .setAsin(prod.getAsin())
                .setCategory(prod.getCategory())
                .setReturnItemId(prod.getReturnItemId())
                .setTotalRetail(prod.getTotalRetail())
                .setCurrencyCode(prod.getCurrencyCode())
                .setDepartment(prod.getDepartment())
                .setCondition(prod.getCondition());
    }

    public List<ProductViewModel> getScrapProducts() {
        List<ScrapProduct> all = this.scrapProductsRepo.findAll();
        return all
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .map(p -> p.setShortenDescription(p.getDescription()))
                .toList();
    }
}
