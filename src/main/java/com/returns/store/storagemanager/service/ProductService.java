package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.InProgressProduct;
import com.returns.store.storagemanager.model.exceptions.ProductNotFoundException;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.model.view.SellingProductView;
import com.returns.store.storagemanager.repo.InProgressProductRepo;
import com.returns.store.storagemanager.repo.ScrapProductsRepo;
import com.returns.store.storagemanager.repo.SellingProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final SellingProductRepo productRepo;
    private final InProgressProductRepo inProgressProductRepo;
    private final ScrapProductsRepo scrapProductsRepo;

    public ProductService(ModelMapper modelMapper, SellingProductRepo productRepo, InProgressProductRepo inProgressProductRepo, ScrapProductsRepo scrapProductsRepo) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
        this.inProgressProductRepo = inProgressProductRepo;
        this.scrapProductsRepo = scrapProductsRepo;
    }

    public SellingProduct findProductById(Long id) {
        return this.productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public SellingProduct addProductForSale(SellingProduct sellingProduct, InProgressProduct productById, String rackName, Integer rackNumber) {
        this.inProgressProductRepo.deleteById(productById.getId());
        sellingProduct = mapSellingProduct(sellingProduct, productById,rackName, rackNumber);
        this.productRepo.saveAndFlush(sellingProduct);
        return sellingProduct;
    }

    public List<SellingProductView> getProducts() {
        List<SellingProduct> all = this.productRepo.findAll();
        return all
                .stream()
                .map(p -> this.modelMapper.map(p, SellingProductView.class))
                .map(p -> p.setShortenDescription(p.getDescription()))
                .toList();

    }

    private SellingProduct mapSellingProduct(SellingProduct sellingProduct, InProgressProduct productById, String rackName, Integer rackNumber) {
        return (SellingProduct) sellingProduct
                .setRackName(rackName)
                .setRackNumber(rackNumber)
                .setAsin(productById.getAsin())
                .setQuantity(productById.getQuantity())
                .setCategory(productById.getCategory())
                .setCondition(productById.getCondition())
                .setCurrencyCode(productById.getCurrencyCode())
                .setDepartment(productById.getDepartment())
                .setEan(productById.getEan())
                .setLpn(productById.getLpn())
                .setTotalRetail(productById.getTotalRetail())
                .setReturnItemId(productById.getReturnItemId())
                .setPalletId(productById.getPalletId())
                .setSubCategory(productById.getSubCategory())
                .setDescription(productById.getDescription());
    }
}
