package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.view.RackViewModel;
import com.returns.store.storagemanager.service.RackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rack")
public class RackController {

    private final RackService rackService;

    public RackController(RackService rackService) {
        this.rackService = rackService;
    }

    @GetMapping("/all")
    public String allRacks(Model model) {

        model.addAttribute("allRacks", this.rackService.getAllRacks());

        return "all-racks";
    }
}
