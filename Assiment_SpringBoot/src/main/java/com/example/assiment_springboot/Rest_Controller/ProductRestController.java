package com.example.assiment_springboot.Rest_Controller;

import com.example.assiment_springboot.Model.Product;
import com.example.assiment_springboot.Request.Product2Request;
import com.example.assiment_springboot.Request.ProductRequest;
import com.example.assiment_springboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("list_product")
    public List<ProductRequest> getListProduct() {
        return productService.listProduct();
    }

    @PostMapping("add_product")
    public Product addProduct(@RequestBody Product2Request request) {
        return productService.addProduct(request);
    }

    @DeleteMapping("delete_product/{id}")
    public String deleteProduct(@PathVariable(name = "id") long id) {
        productService.deleteProduct(id);
        return "da xoa";
    }

    @GetMapping("detail_product/{id}")
    public Product detailProduct(@PathVariable("id") Long id){
          return productService.detailProduct(id);
    }

    @PutMapping("update/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product2Request product2Request){
        return productService.updateProduct(id, product2Request);
    }
}
