package com.example.ShoppingCartProject.Utils;

import com.example.ShoppingCartProject.product.Product;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class InventoryDetailsInExcelSheet {
    @Autowired
    XSSFWorkbook workbook;

    public void updateInventoryDetailsInExcelSheet(Product existingProduct, Product givenProduct) {
        if(workbook.getSheet("InventoryDetails") == null) {
            workbook.createSheet("InventoryDetails");
            workbook.getSheet("InventoryDetails").createRow(0).
                    createCell(0).setCellValue("Id");
            workbook.getSheet("InventoryDetails").getRow(0).
                    createCell(1).setCellValue("Product Name");
            workbook.getSheet("InventoryDetails").getRow(0).
                    createCell(2).setCellValue("Quantity left");
        }

        workbook.getSheet("InventoryDetails").
                getRow(existingProduct.getId().intValue()).getCell(2).setCellValue(givenProduct.getQuantity());
        try {
            FileOutputStream outputStream = new FileOutputStream("InventoryDetails.xlsx");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addInventoryDetailsInExcelSheet(Product product) {
        if(workbook.getSheet("InventoryDetails") == null) {
            workbook.createSheet("InventoryDetails");
            workbook.getSheet("InventoryDetails").createRow(0).
                    createCell(0).setCellValue("Id");
            workbook.getSheet("InventoryDetails").getRow(0).
                    createCell(1).setCellValue("Product Name");
            workbook.getSheet("InventoryDetails").getRow(0).
                    createCell(2).setCellValue("Quantity left");
        }
        int currentRow = workbook.getSheet("InventoryDetails").getLastRowNum() + 1;
        workbook.getSheet("InventoryDetails").
                createRow(currentRow).
                createCell(0).setCellValue(currentRow);
        workbook.getSheet("InventoryDetails").
                getRow(currentRow).
                createCell(1).setCellValue(product.getProductName());
        workbook.getSheet("InventoryDetails").
                getRow(currentRow).
                createCell(2).setCellValue(product.getQuantity());
        try {
            FileOutputStream outputStream = new FileOutputStream("InventoryDetails.xlsx");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
