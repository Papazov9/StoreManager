package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.entity.Rack;
import com.returns.store.storagemanager.model.enums.SizeEnum;
import com.returns.store.storagemanager.model.view.RackViewModel;
import com.returns.store.storagemanager.model.view.RackViewResponseEntity;
import com.returns.store.storagemanager.service.RackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/getFirstFree/{productSize}")
    public ResponseEntity<RackViewResponseEntity> getFirstFree(@PathVariable String productSize) {

        RackViewResponseEntity result = this.rackService.findFirstFreeRackNumberBySize(SizeEnum.valueOf(productSize));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findSuitable/{productSize}/{rackName}/{rackNumber}")
    public ResponseEntity<RackViewResponseEntity> findSuitable(@PathVariable String productSize, @PathVariable String rackName, @PathVariable int rackNumber, Model model) {

        RackViewResponseEntity result = this.rackService.findDifferentRackNumber(SizeEnum.valueOf(productSize), rackName, rackNumber);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/details/{rackName}")
    public String allRacks(@PathVariable String rackName, Model model) {

        model.addAttribute("rack", this.rackService.getRackByName(rackName));

        return "rack-details";
    }

    @GetMapping("/products")
    public String getRacksWithProducts(Model model){
        model.addAttribute("allRacksWithProducts", this.rackService.getAllRacksWithProducts());
        return "racks-products";
    }
}
