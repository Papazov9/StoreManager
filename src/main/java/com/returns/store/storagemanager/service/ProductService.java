package com.returns.store.storagemanager.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.returns.store.storagemanager.model.bindings.CSVBindingObject;
import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.model.entity.ScrapProduct;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.model.view.ProductViewModel;
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
    private final ScrapProductsRepo scrapProductsRepo;

    public ProductService(ModelMapper modelMapper, SellingProductRepo productRepo, ScrapProductsRepo scrapProductsRepo) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
        this.scrapProductsRepo = scrapProductsRepo;
    }

    public boolean importCSVFile(MultipartFile file) throws ProductAlreadyExists {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<CSVBindingObject> products;
        try (Reader inputStreamReader = new InputStreamReader(file.getInputStream())) {
            products = new CsvToBeanBuilder<CSVBindingObject>(inputStreamReader)
                    .withType(CSVBindingObject.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (CSVBindingObject s : products) {
            Optional<SellingProduct> product = this.productRepo.findByReturnItemId(s.getReturnItemId());

            if (product.isPresent()) {
                throw new ProductAlreadyExists("Product with id : %s already exists!", s.getReturnItemId());
            }
            SellingProduct sellingProduct = modelMapper.map(s, SellingProduct.class);
            this.productRepo.saveAndFlush(sellingProduct);
        }
        return true;
    }

    public List<ProductViewModel> getAllProducts() {
        List<SellingProduct> all = this.productRepo.findAll();
        return all
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .map(p -> p.setShortenDescription(p.getDescription())).toList();
    }



    public List<ProductViewModel> searchProduct(SearchProductBinding productBinding){
        List<ProductViewModel> productViewModels = this.productRepo
                .findAll(new ProductSpecification(productBinding))
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .map(p -> p.setShortenDescription(p.getDescription()))
                .toList();
        return productViewModels;
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
