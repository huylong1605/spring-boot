package com.example.assiment_springboot.Service;

import com.example.assiment_springboot.Model.Product;
import com.example.assiment_springboot.Request.Product2Request;
import com.example.assiment_springboot.Request.ProductRequest;

import java.util.List;

public interface ProductService {

    List<ProductRequest> listProduct();

    Product addProduct(Product2Request request);

   Product detailProduct(Long id);

   Product updateProduct(Long id, Product2Request request);

   void deleteProduct(Long id);









}
