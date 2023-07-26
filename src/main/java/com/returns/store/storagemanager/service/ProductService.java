package com.returns.store.storagemanager.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.returns.store.storagemanager.model.bindings.CSVBindingObject;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.model.view.ProductViewModel;
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
    public ProductService(ModelMapper modelMapper, SellingProductRepo productRepo) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
    }

    public boolean importCSVFile(MultipartFile file) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try (Reader inputStreamReader = new InputStreamReader(file.getInputStream())) {
            List<CSVBindingObject> products = new CsvToBeanBuilder(inputStreamReader)
                    .withType(CSVBindingObject.class)
                    .build()
                    .parse();

            products.forEach(s -> {
                Optional<SellingProduct> product = this.productRepo.findByReturnItemId(s.getReturnItemId());

                if (product.isPresent()) {
                    throw new ProductAlreadyExists("Product with id : %s already exists!", s.getReturnItemId());
                }
                SellingProduct sellingProduct = modelMapper.map(s, SellingProduct.class);
                this.productRepo.saveAndFlush(sellingProduct);
            });

            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductViewModel> getAllProducts(){
        List<SellingProduct> all = this.productRepo.findAll();
        return all
                .stream().map(this::productViewModelMapper).toList();
    }

    private ProductViewModel productViewModelMapper(SellingProduct product){
        ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);

        if(product.getDescription().length() > 20){
            productViewModel.setShortenDescription(product.getDescription().substring(0, 21) + "...");
        }else{
            productViewModel.setShortenDescription(product.getDescription());
        }

        return productViewModel;

    }

}
