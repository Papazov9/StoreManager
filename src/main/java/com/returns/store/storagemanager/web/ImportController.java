package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.bindings.RackBinding;
import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.service.InProgressProductService;
import com.returns.store.storagemanager.service.ProductService;
import com.returns.store.storagemanager.service.RackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/returns")
public class ImportController {

    private final ProductService productService;

    private final InProgressProductService inProgressProductService;
    private final RackService rackService;

    public ImportController(ProductService productService, InProgressProductService inProgressProductService, RackService rackService) {
        this.productService = productService;
        this.inProgressProductService = inProgressProductService;
        this.rackService = rackService;
    }


    @GetMapping("/import")
    public String importFile(){
        return "import";
    }

    @PostMapping("/import")
    public String importFile(@RequestParam("file") MultipartFile file) throws ProductAlreadyExists{
        this.inProgressProductService.importCSVFile(file);
        return "redirect:/products/progress";
    }

    @GetMapping("/import/rack")
    public String importRack(){return "import-rack";}

    @GetMapping("/import/error")
    public String importError(){return "import-error";}

    @PostMapping("/import/rack")
    public String importRack(@Valid RackBinding rackBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || this.rackService.isRackExists(rackBinding)) {
            redirectAttributes.addFlashAttribute("rackBinding", rackBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rackBinding", bindingResult);
        }

        this.rackService.addRack(rackBinding);
        return "redirect:/rack/all";
    }

    @ModelAttribute
    public RackBinding rackBinding() {
        return new RackBinding();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductAlreadyExists.class)
    public String productNotFound() {
        return "import-error";
    }
}
