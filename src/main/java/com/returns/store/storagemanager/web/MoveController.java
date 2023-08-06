package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.entity.FixProduct;
import com.returns.store.storagemanager.model.entity.ScrapProduct;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.service.FixProductService;
import com.returns.store.storagemanager.service.ProductService;
import com.returns.store.storagemanager.service.ScrapProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/move")
public class MoveController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final FixProductService fixProductService;

    private final ScrapProductService scrapProductService;

    public MoveController(ProductService productService, ModelMapper modelMapper, FixProductService fixProductService, ScrapProductService scrapProductService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.fixProductService = fixProductService;
        this.scrapProductService = scrapProductService;
    }

    @GetMapping("/fix/{id}")
    public String moveToFix(@PathVariable Long id) {
        SellingProduct productById = this.productService.findProductById(id);
        FixProduct prod = this.modelMapper.map(productById, FixProduct.class);
        this.fixProductService.addProductToBeFixed(prod);

        return "redirect:/products/fix";
    }

    @GetMapping("/scrap/{id}")
    public String moveToScrap(@PathVariable Long id) {
        SellingProduct productById = this.productService.findProductById(id);
        ScrapProduct prod = this.modelMapper.map(productById, ScrapProduct.class);
        this.scrapProductService.addProductToScrap(prod);

        return "redirect:/products/scrap";
    }
}
