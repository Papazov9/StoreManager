package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.entity.ScrapPallet;
import com.returns.store.storagemanager.model.exceptions.ProductAlreadyExists;
import com.returns.store.storagemanager.service.ExcelExportService;
import com.returns.store.storagemanager.service.ScrapPalletService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/export")
public class ExportController {
    private final ScrapPalletService scrapPalletService;

    public ExportController(ScrapPalletService scrapPalletService) {
        this.scrapPalletService = scrapPalletService;
    }

    @GetMapping("/{palletName}")
    public void exportExcel(@PathVariable String palletName, HttpServletResponse response) throws IOException {
        ResponseEntity<StreamingResponseBody> responseEntity;
        ScrapPallet pallet = this.scrapPalletService.findByName(palletName);
        ExcelExportService excelExportService = new ExcelExportService(pallet.getScrapProducts());

        excelExportService.export(response, palletName);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IOException.class)
    public String productNotFound() {
        return "import-error";
    }
}
