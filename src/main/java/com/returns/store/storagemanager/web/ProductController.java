package com.returns.store.storagemanager.web;


import com.returns.store.storagemanager.model.bindings.EditBindingModel;
import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.model.entity.InProgressProduct;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.model.view.SoldProductsView;
import com.returns.store.storagemanager.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final InProgressProductService inProgressProductService;
    private final FixProductService fixProductService;
    private final ScrapProductService scrapProductService;

    private final ScrapPalletService scrapPalletService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, InProgressProductService inProgressProductService, FixProductService fixProductService, ScrapProductService scrapProductService, ScrapPalletService scrapPalletService, ModelMapper modelMapper) {
        this.productService = productService;
        this.inProgressProductService = inProgressProductService;
        this.fixProductService = fixProductService;
        this.scrapProductService = scrapProductService;
        this.scrapPalletService = scrapPalletService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/progress")
    public String products(Model model) {
        model.addAttribute("allProducts", inProgressProductService.getAllProducts());
        return "products";
    }

    @GetMapping("/scrap")
    public String scrapProducts(Model model) {
        model.addAttribute("scrapPallets", scrapPalletService.getScrapPallets());
        return "scrapProducts";
    }

    @GetMapping("/fix")
    public String fixProducts(Model model) {
        model.addAttribute("fixProducts", this.fixProductService.getProducts());
        return "fix-products";
    }

    @GetMapping("/sale")
    public String saleProducts(Model model) {
        model.addAttribute("saleProducts", this.productService.getNotSoldProducts());

        return "products-sale";
    }


    @GetMapping("/sale/{productId}")
    public String saleProductById(@PathVariable Long productId) {
        this.productService.saleProduct(productId);
        return "redirect:/products/sold";
    }

    @GetMapping("/search")
    public String searchProduct(@Valid SearchProductBinding productBinding,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("productBinding", productBinding);
            redirectAttributes.addAttribute(
                    "org.springframework.validation.BindingResult.productBinding", bindingResult);
            return "products";
        }

        if(!productBinding.isEmpty()){
            List<ProductViewModel> productsFound = this.inProgressProductService.searchProduct(productBinding);

            if(!productsFound.isEmpty()){
                redirectAttributes.addFlashAttribute("productsFound", productsFound);
            }else{
                redirectAttributes.addFlashAttribute("noProductsFound", true);

            }

        }

        return "redirect:/products/progress";

    }


    @GetMapping("/sold")
    public String soldProduct(Model model){
        List<SoldProductsView> soldProducts = this.productService.getSoldProducts();
        model.addAttribute("soldProducts", soldProducts);

        return "sold-products";
    }

    @GetMapping("/move/{id}")
    public String moveProduct(@PathVariable Long id, Model model){

        model.addAttribute("productId", id);
        return "move-product";
    }

    @GetMapping("/edit/{id}")
    public String editProductInProgress(@PathVariable Long id, Model model) {

        InProgressProduct productById = this.inProgressProductService.findProductById(id);
        EditBindingModel productModel = this.modelMapper.map(productById, EditBindingModel.class);

        model.addAttribute("productModel", productModel);

        return "edit-product";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @Valid EditBindingModel editBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.inProgressProductService.isProductExists(id)) {
            redirectAttributes.addAttribute("editBindingModel", editBindingModel);
            redirectAttributes.addAttribute("org.springframework.validation.BindingResult.editBindingModel", bindingResult);

            return "redirect:/products/edit/{id}";
        }

        this.inProgressProductService.editProduct(id, editBindingModel);

        return "redirect:/products/progress";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.inProgressProductService.deleteProductById(id);
        return "successful-delete";
    }

    @GetMapping("/successful-delete")
    public String successfulDelete() {

        return "successful-delete";
    }

    @ModelAttribute("productBinding")
    public SearchProductBinding productBinding(){
        return new SearchProductBinding();
    }

    @ModelAttribute("productsFound")
    public List<ProductViewModel> productsFound(){
        return new ArrayList<>();
    }


}
