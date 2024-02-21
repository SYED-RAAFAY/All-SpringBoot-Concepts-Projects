package com.example.ShoppingCartProject.product;

import com.example.ShoppingCartProject.Utils.InventoryDetailsInExcelSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    private InventoryDetailsInExcelSheet inventoryDetailsInExcelSheet;
    @Autowired
    public void InventoryDetailsInExcelSheet(InventoryDetailsInExcelSheet inventoryDetailsInExcelSheet){
        this.inventoryDetailsInExcelSheet = inventoryDetailsInExcelSheet;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
//    public Product findProductByProductID(Long productID) {
//        return productRepository.findProductById(productID);
//    }
//
//    public Product findProductByProductName(String productName){
//        return productRepository.findProductByProductName(productName);
//    }

    public int save (Product givenProduct){
        List<Product> productInInventory = productRepository.
                findProductByProductName(givenProduct.getProductName());
        if(productInInventory.isEmpty()){
            System.out.println("Product does not exist");
            inventoryDetailsInExcelSheet.addInventoryDetailsInExcelSheet(givenProduct);
            return productRepository.saveNewProduct(givenProduct);
        }else {
            System.out.println(productInInventory.get(0).toString());
            System.out.println("Product already exists");
            inventoryDetailsInExcelSheet.updateInventoryDetailsInExcelSheet(productInInventory.get(0),givenProduct);
            givenProduct.setId(productInInventory.get(0).getId());
            return productRepository.editProduct(givenProduct);
        }
    }

//    public void decreaseInventoryOfProduct(List<CartItem> cartItemList){
//        for(CartItem c : cartItemList){
//            Product product = productRepository.findProductById(c.getProductId());
//            product.setQuantity(product.getQuantity()-c.getQuantity());
//            productRepository.save(product);
//        }
//    }
}
