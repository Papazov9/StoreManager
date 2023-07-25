package com.returns.store.storagemanager.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.returns.store.storagemanager.model.bindings.CSVBindingObject;
import com.returns.store.storagemanager.model.entity.Product;
import com.returns.store.storagemanager.model.entity.ProductInProgress;
import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.repo.ProductInProgressRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductInProgressRepo productRepo;
    public ProductService(ModelMapper modelMapper, ProductInProgressRepo productRepo) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
    }

    public boolean importCSVFile(MultipartFile file) {
        try (Reader inputStreamReader = new InputStreamReader(file.getInputStream())) {
            List<CSVBindingObject> products = new CsvToBeanBuilder(inputStreamReader)
                    .withType(CSVBindingObject.class)
                    .build()
                    .parse();

            List<ProductInProgress> mappedResultList = products.stream().map(p -> modelMapper.map(p, ProductInProgress.class)).toList();

            mappedResultList.forEach(s -> {
                Optional<Product> product = this.productRepo.findById(s.getId());

                if (product.isPresent()) {
                    throw new ProductAlreadyExists("Product with id : " + s.getId() + "already exists!", s.getId());
                }
                this.productRepo.saveAndFlush(s);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
