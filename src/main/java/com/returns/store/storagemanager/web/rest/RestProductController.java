package com.returns.store.storagemanager.web.rest;

import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.service.InProgressProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestProductController {

    private final InProgressProductService inProgressProductService;

    public RestProductController(InProgressProductService inProgressProductService) {

        this.inProgressProductService = inProgressProductService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductViewModel>> getAllProducts(){
        List<ProductViewModel> allProducts = this.inProgressProductService.getAllProducts();

        if (allProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(allProducts);
    }
}
