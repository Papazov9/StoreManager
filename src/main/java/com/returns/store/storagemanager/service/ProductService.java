package com.returns.store.storagemanager.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.returns.store.storagemanager.model.bindings.EditBindingModel;
import com.returns.store.storagemanager.model.entity.InProgressProduct;
import com.returns.store.storagemanager.model.entity.ScrapProduct;
import com.returns.store.storagemanager.model.exceptions.ProductNotFoundException;
import com.returns.store.storagemanager.model.bindings.CSVBindingObject;
import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.model.view.SellingProductView;
import com.returns.store.storagemanager.repo.InProgressProductRepo;
import com.returns.store.storagemanager.repo.ProductSpecification;
import com.returns.store.storagemanager.repo.ScrapProductsRepo;
import com.returns.store.storagemanager.repo.SellingProductRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

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

    public void addProductForSale(SellingProduct sellingProduct, String rackName, Integer rackNumber) {
        this.inProgressProductRepo.deleteById(sellingProduct.getId());
        sellingProduct.setRackName(rackName)
                .setRackNumber(rackNumber);
        this.productRepo.saveAndFlush(sellingProduct);
    }

    public List<SellingProductView> getProducts() {
        List<SellingProduct> all = this.productRepo.findAll();
        return all
                .stream()
                .map(p -> this.modelMapper.map(p, SellingProductView.class))
                .map(p -> p.setShortenDescription(p.getDescription()))
                .toList();

    }
}
