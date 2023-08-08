package com.returns.store.storagemanager.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.returns.store.storagemanager.model.bindings.CSVBindingObject;
import com.returns.store.storagemanager.model.bindings.EditBindingModel;
import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.model.entity.InProgressProduct;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.model.exceptions.ProductNotFoundException;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.repo.InProgressProductRepo;
import com.returns.store.storagemanager.repo.ProductSpecification;
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
public class InProgressProductService {


    private final ModelMapper modelMapper;
    private final InProgressProductRepo inProgressProductRepo;

    public InProgressProductService(ModelMapper modelMapper, InProgressProductRepo inProgressProductRepo) {
        this.modelMapper = modelMapper;
        this.inProgressProductRepo = inProgressProductRepo;
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
            Optional<InProgressProduct> product = this.inProgressProductRepo.findByReturnItemId(s.getReturnItemId());

            if (product.isPresent()) {
                throw new ProductAlreadyExists("Product with id : %s already exists!", s.getReturnItemId());
            }
            InProgressProduct inProgressProduct = modelMapper.map(s, InProgressProduct.class);
            this.inProgressProductRepo.saveAndFlush(inProgressProduct);
        }
        return true;
    }

    public List<ProductViewModel> getAllProducts() {
        List<InProgressProduct> all = this.inProgressProductRepo.findAll();
        return all
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .map(p -> p.setShortenDescription(p.getDescription())).toList();
    }

    public List<ProductViewModel> searchProduct(SearchProductBinding productBinding){
        List<ProductViewModel> productViewModels = this.inProgressProductRepo
                .findAll(new ProductSpecification(productBinding))
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .map(p -> p.setShortenDescription(p.getDescription()))
                .toList();
        return productViewModels;
    }

    public InProgressProduct findProductById(Long id) {
        return this.inProgressProductRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void editProduct(Long id, EditBindingModel editBindingModel) {
        InProgressProduct productById = findProductById(id);
        productById.setAsin(editBindingModel.getAsin())
                .setCategory(editBindingModel.getCategory())
                .setCondition(editBindingModel.getCondition())
                .setDepartment(editBindingModel.getDepartment())
                .setEan(editBindingModel.getEan())
                .setCurrencyCode(editBindingModel.getCurrencyCode())
                .setDescription(editBindingModel.getDescription())
                .setLpn(editBindingModel.getLpn())
                .setReturnItemId(editBindingModel.getReturnItemId())
                .setQuantity(editBindingModel.getQuantity())
                .setTotalRetail(editBindingModel.getTotalRetail())
                .setSubCategory(editBindingModel.getSubCategory())
                .setPalletId(editBindingModel.getPalletId());

        this.inProgressProductRepo.saveAndFlush(productById);
    }

    public void deleteProductById(Long id) {
        //check if exists
        InProgressProduct productById = this.findProductById(id);
        this.inProgressProductRepo.deleteById(productById.getId());
    }

    public boolean isProductExists(Long id) {
        return this.inProgressProductRepo.findById(id).isPresent();
    }
}
