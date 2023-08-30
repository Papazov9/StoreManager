package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.bindings.EditBindingModel;
import com.returns.store.storagemanager.model.entity.*;
import com.returns.store.storagemanager.model.exceptions.ProductNotFoundException;
import com.returns.store.storagemanager.model.view.SellingProductView;
import com.returns.store.storagemanager.model.view.SoldProductsView;
import com.returns.store.storagemanager.repo.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final SellingProductRepo productRepo;
    private final InProgressProductRepo inProgressProductRepo;

    private final FixProductRepo fixProductRepo;
    private final ScrapProductsRepo scrapProductsRepo;
    private final RackRepo rackRepo;

    public ProductService(ModelMapper modelMapper, SellingProductRepo productRepo, InProgressProductRepo inProgressProductRepo, FixProductRepo fixProductRepo, ScrapProductsRepo scrapProductsRepo, RackRepo rackRepo) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
        this.inProgressProductRepo = inProgressProductRepo;
        this.fixProductRepo = fixProductRepo;
        this.scrapProductsRepo = scrapProductsRepo;
        this.rackRepo = rackRepo;
    }

    public SellingProduct findProductById(Long id) {
        return this.productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public SellingProduct addProductForSale(SellingProduct sellingProduct, InProgressProduct productById, String rackName, Integer rackNumber) {
        this.inProgressProductRepo.deleteById(productById.getId());
        sellingProduct = mapSellingProduct(sellingProduct, productById, rackName, rackNumber);
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

    public void saleProduct(Long productId) {
        Optional<SellingProduct> optProduct = this.productRepo.findById(productId);
        Optional<Rack> rack = this.rackRepo.findByRackName(optProduct.get().getRackName());
        if (rack.isPresent()) {
            rack.get().removeProduct(optProduct.get());
            SellingProduct sellingProduct = optProduct.get();
            sellingProduct.setSold(true);
            sellingProduct.setSaleTime(LocalDateTime.now());
            this.productRepo.saveAndFlush(sellingProduct);
            this.rackRepo.saveAndFlush(rack.get());
        }
    }

    public List<SellingProductView> getNotSoldProducts() {
        return this.productRepo.findBySoldIsFalse()
                .stream()
                .map(p -> this.modelMapper.map(p, SellingProductView.class))
                .map(p -> p.setShortenDescription(p.getDescription()))
                .toList();
    }

    public List<SoldProductsView> getSoldProducts() {
        return this.productRepo.findBySoldIsTrueOrderBySaleTimeDesc()
                .stream()
                .map(this::sellingToSoldProductMapper)
                .toList();
    }

    public void editProduct(Long id, EditBindingModel editBindingModel) {
//        SellingProduct productById = findProductById(id);
//        mapFromModelToEntity(editBindingModel, productById);
        SellingProduct sellingProduct = this.modelMapper.map(editBindingModel, SellingProduct.class);
        if (checkNewRackConfig(sellingProduct)) {

        }
        this.productRepo.saveAndFlush(sellingProduct);
    }

    private boolean checkNewRackConfig(SellingProduct sellingProduct) {
        Optional<Rack> rack = this.rackRepo.findByRackName(sellingProduct.getRackName());

        if (rack.isPresent()) {

        }
        return false;
    }

    public boolean isProductExists(Long id) {
        return this.productRepo.findById(id).isPresent();
    }

    public void handleProduct(Long id) {
        Optional<FixProduct> fixById = this.fixProductRepo.findById(id);
        Optional<SellingProduct> sellingProduct = this.productRepo.findById(id);
        InProgressProduct result = new InProgressProduct();

        if (fixById.isPresent()) {
            FixProduct fixProduct = fixById.get();
            result = map(result, fixProduct);
            this.fixProductRepo.deleteById(id);
        } else if (sellingProduct.isPresent()) {
            SellingProduct product = sellingProduct.get();

            Optional<Rack> byRackName = this.rackRepo.findByRackName(product.getRackName());
            if (byRackName.isPresent()) {
                byRackName.get().removeProduct(product);
                this.rackRepo.saveAndFlush(byRackName.get());
            }

            result = map(result, product);
            this.productRepo.deleteById(product.getId());
        }

        if (!result.getAsin().isEmpty()) {
            this.inProgressProductRepo.saveAndFlush(result);
        }
    }

    private InProgressProduct map(InProgressProduct result, Product fixProduct) {
        return (InProgressProduct) result.setAsin(fixProduct.getAsin())
                .setCondition(fixProduct.getCondition())
                .setDepartment(fixProduct.getDepartment())
                .setCategory(fixProduct.getCategory())
                .setLpn(fixProduct.getLpn())
                .setTotalRetail(fixProduct.getTotalRetail())
                .setPalletId(fixProduct.getPalletId())
                .setReturnItemId(fixProduct.getReturnItemId())
                .setCurrencyCode(fixProduct.getCurrencyCode())
                .setSubCategory(fixProduct.getSubCategory())
                .setQuantity(fixProduct.getQuantity())
                .setDescription(fixProduct.getDescription())
                .setEan(fixProduct.getEan());
    }

    private SoldProductsView sellingToSoldProductMapper(SellingProduct sellingProduct){
        SoldProductsView soldProduct = this.modelMapper.map(sellingProduct, SoldProductsView.class);
        soldProduct.setShortenDescription(soldProduct.getDescription());
        soldProduct.setSaleTimeString(soldProduct.getSaleTime());
        soldProduct.setPreviousRackPosition(sellingProduct.getRackName(), sellingProduct.getRackNumber());
        return soldProduct;
    }

}
