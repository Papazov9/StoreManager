package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.entity.ScrapProduct;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelExportService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ScrapProduct> products;

    public ExcelExportService(List<ScrapProduct> products) {
        this.products = products;
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Scrap_Products");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Return Item Id", style);
        createCell(row, 1, "ASIN", style);
        createCell(row, 2, "Category", style);
        createCell(row, 3, "Condition", style);
        createCell(row, 4, "Currency Code", style);
        createCell(row, 5, "Department", style);
        createCell(row, 6, "Description", style);
        createCell(row, 7, "EAN", style);
        createCell(row, 8, "LPN", style);
        createCell(row, 9, "Pallet ID", style);
        createCell(row, 10, "Quantity", style);
        createCell(row, 11, "Sub Category", style);
        createCell(row, 12, "Total Retail", style);
    }

    private void createCell(Row row, int columnCount, Object name, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (name instanceof Integer) {
            cell.setCellValue((Integer) name);
        } else if (name instanceof Boolean) {
            cell.setCellValue((Boolean) name);
        } else if (name instanceof Long) {
            cell.setCellValue((Long) name);
        } else if (name instanceof Double) {
            cell.setCellValue((Double) name);
        } else {
            cell.setCellValue((String) name);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ScrapProduct product : this.products) {
            Row row = sheet.createRow(rowCount++);

            int colCount = 0;

            createCell(row, colCount++, product.getReturnItemId(), style);
            createCell(row, colCount++, product.getAsin(), style);
            createCell(row, colCount++, product.getCategory(), style);
            createCell(row, colCount++, product.getCondition(), style);
            createCell(row, colCount++, product.getCurrencyCode(), style);
            createCell(row, colCount++, product.getDepartment(), style);
            createCell(row, colCount++, product.getDescription(), style);
            createCell(row, colCount++, product.getEan(), style);
            createCell(row, colCount++, product.getLpn(), style);
            createCell(row, colCount++, product.getPalletId(), style);
            createCell(row, colCount++, product.getQuantity(), style);
            createCell(row, colCount++, product.getSubCategory(), style);
            createCell(row, colCount, product.getTotalRetail(), style);
        }
    }

    public void export(HttpServletResponse response, String palletName) throws IOException {
        writeHeaderLine();
        writeDataLines();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=scrap-products-" + palletName + ".xls");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
