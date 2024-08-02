package com.example.assiment_springboot.Service.Implement;

import com.example.assiment_springboot.Model.Category;
import com.example.assiment_springboot.Model.Product;
import com.example.assiment_springboot.Repository.CategoryRepository;
import com.example.assiment_springboot.Repository.ProductRepository;
import com.example.assiment_springboot.Request.Product2Request;
import com.example.assiment_springboot.Request.ProductRequest;
import com.example.assiment_springboot.Service.ImageService;
import com.example.assiment_springboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageService imageService;

    @Override
    public List<ProductRequest> listProduct() {

        return productRepository.findAll().stream().map(product -> new ProductRequest(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getQuantity(),
                product.getDescription(),
                product.getImage(),
                product.getCategory().getCategoryName())).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Product addProduct(Product2Request request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        MultipartFile file = request.getImage();
        String urlSaveFile = imageService.saveOneImage(file);

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
        product.setImage(urlSaveFile);

        product.setCategory(category);
        productRepository.save(product);
        return product;
    }


    @Override
    public Product detailProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product2Request request) {
        Product product = productRepository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
//        product.setImage(request.getImage());
        product.setCategory(category);

        productRepository.save(product);

        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }


}
