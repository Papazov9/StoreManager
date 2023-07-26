package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/returns")
///returns/import
public class ImportController {

    private final ProductService productService;

    public ImportController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/import")
    public String importFile(){
        return "import";
    }

    @PostMapping("/import")
    public String importFile(@RequestParam("file") MultipartFile file) throws ProductAlreadyExists{
        productService.importCSVFile(file);
        return "products";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductAlreadyExists.class)
    public String productNotFound() {
        return "import-error";
    }
}
