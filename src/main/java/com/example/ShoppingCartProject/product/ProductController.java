package com.example.ShoppingCartProject.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    //API Layer
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

//    @GetMapping("/get-one")
//    public Product getProductsById(HttpServletRequest request){
//        Long id = Long.valueOf(request.getParameter("id"));
//        return productService.findProductByProductID(id);
//    }

//    @GetMapping("/get-product-by-name")
//    public Product getProductByName(HttpServletRequest request){
//        String name = request.getParameter("productName");
//        return productService.findProductByProductName(name);
//    }

    @PostMapping("/admin/add-or-edit")
    public ResponseEntity<Product> addOrEditProduct(@RequestBody Product givenProduct) {
        int noOfRowsUpdated = productService.save(givenProduct);
        if (noOfRowsUpdated == 0) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity(givenProduct, HttpStatus.OK);
        }
    }
}
