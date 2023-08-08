package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.FixProduct;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.repo.FixProductRepo;
import com.returns.store.storagemanager.repo.InProgressProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixProductService {

    private final InProgressProductRepo inProgressProductRepo;
    private final FixProductRepo fixProductRepo;
    private final ModelMapper modelMapper;

    public FixProductService(InProgressProductRepo productRepo, FixProductRepo fixProductRepo, ModelMapper modelMapper) {
        this.inProgressProductRepo = productRepo;
        this.fixProductRepo = fixProductRepo;
        this.modelMapper = modelMapper;
    }

    public void addProductToBeFixed(FixProduct prod) {
        this.inProgressProductRepo.deleteById(prod.getId());
        this.fixProductRepo.saveAndFlush(prod);
    }

    public List<ProductViewModel> getProducts() {
        return this.fixProductRepo
                .findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .map(p -> p.setShortenDescription(p.getDescription()))
                .toList();
    }
}
