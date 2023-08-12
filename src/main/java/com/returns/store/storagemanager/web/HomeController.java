package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.model.view.ProductViewModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            return "redirect:/products/progress";
        }

        return "index";
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
