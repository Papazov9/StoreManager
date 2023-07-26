package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/progress")
    private String products() {
        return "products";
    }

}
