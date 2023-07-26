package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String importFile(@RequestParam("file") MultipartFile file) {
        if (productService.importCSVFile(file)) {
            return "home";
        }

        return "import-error";
    }

}
