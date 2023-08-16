package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.entity.FixProduct;
import com.returns.store.storagemanager.model.entity.InProgressProduct;
import com.returns.store.storagemanager.model.entity.ScrapProduct;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/move")
public class MoveController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final FixProductService fixProductService;
    private final InProgressProductService inProgressProductService;
    private final ScrapProductService scrapProductService;
    private final RackService rackService;

    public MoveController(ProductService productService, ModelMapper modelMapper, FixProductService fixProductService, InProgressProductService inProgressProductService, ScrapProductService scrapProductService, RackService rackService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.fixProductService = fixProductService;
        this.inProgressProductService = inProgressProductService;
        this.scrapProductService = scrapProductService;
        this.rackService = rackService;
    }

    @GetMapping("/fix/{id}")
    public String moveToFix(@PathVariable Long id) {
        InProgressProduct productById = this.inProgressProductService.findProductById(id);
        FixProduct prod = this.modelMapper.map(productById, FixProduct.class);
        this.fixProductService.addProductToBeFixed(prod);

        return "redirect:/products/fix";
    }

    @GetMapping("/scrap/{id}/{palletName}")
    public String moveToScrap(@PathVariable Long id, @PathVariable String palletName) {
        InProgressProduct productById = this.inProgressProductService.findProductById(id);

        this.scrapProductService.addProductToScrap(productById, palletName);

        return "redirect:/products/scrap";
    }

    @PostMapping("/selling/{id}/{rackName}/{rackNumber}")
    public String moveForSale(@PathVariable Long id, @PathVariable String rackName, @PathVariable Integer rackNumber) {
        InProgressProduct productById = this.inProgressProductService.findProductById(id);
        SellingProduct sellingProduct = new SellingProduct();
        sellingProduct = this.productService.addProductForSale(sellingProduct,productById, rackName, rackNumber);
        this.rackService.updateRack(sellingProduct, rackName, rackNumber);

        return "products-sale";
    }
}
