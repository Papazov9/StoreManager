package com.returns.store.storagemanager.web;


import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import com.returns.store.storagemanager.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/progress")
    private String products(Model model) {
        model.addAttribute("allProducts", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/scrap")
    private String scrapProducts(Model model) {
        model.addAttribute("scrapProducts", productService.getScrapProducts());
        return "scrapProducts";
    }

    @GetMapping("/search")
    private String searchProduct(@Valid SearchProductBinding productBinding,
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
            List<ProductViewModel> productsFound = this.productService.searchProduct(productBinding);

            if(productsFound.size() > 0){
                redirectAttributes.addFlashAttribute("productsFound", productsFound);
            }else{
                redirectAttributes.addFlashAttribute("noProductsFound", true);

            }

        }

        return "redirect:/products/progress";

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
