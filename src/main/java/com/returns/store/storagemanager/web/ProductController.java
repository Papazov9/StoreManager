package com.returns.store.storagemanager.web;


import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

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
            model.addAttribute("productsFound", this.productService.searchProduct(productBinding));
        }

        System.out.println(productBinding);
        return "redirect:/products/progress";

    }

    @ModelAttribute("productBinding")
    public SearchProductBinding productBinding(){
        return new SearchProductBinding();
    }

}
