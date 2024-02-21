package com.example.ShoppingCartProject.Utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class Configurations {
    @Bean
    public XSSFWorkbook xssfWorkbook(){
        try {
            FileInputStream fileInputStream = new FileInputStream("InventoryDetails.xlsx");
            return new XSSFWorkbook(fileInputStream);
        }catch (Exception e){
            e.printStackTrace();
            return new XSSFWorkbook();
        }
    }
}
