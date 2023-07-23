package com.returns.store.storagemanager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/returns")
///returns/import
public class ImportController {

    @GetMapping("/import")
    public String importFile(){
        return "import";
    }

}
