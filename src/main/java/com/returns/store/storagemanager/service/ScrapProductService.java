package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.ScrapProduct;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.repo.ScrapProductsRepo;
import com.returns.store.storagemanager.repo.SellingProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapProductService {

    private final SellingProductRepo productRepo;
    private final ScrapProductsRepo scrapProductsRepo;

    private final ModelMapper modelMapper;

    public ScrapProductService(SellingProductRepo productRepo, ScrapProductsRepo scrapProductsRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.scrapProductsRepo = scrapProductsRepo;
        this.modelMapper = modelMapper;
    }

    public void addProductToScrap(ScrapProduct prod) {
        this.productRepo.deleteById(prod.getId());
        this.scrapProductsRepo.saveAndFlush(prod);
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
