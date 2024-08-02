package com.example.assiment_springboot.Controller;


import com.example.assiment_springboot.Model.Category;
import com.example.assiment_springboot.Model.Product;
import com.example.assiment_springboot.Repository.CategoryRepository;
import com.example.assiment_springboot.Request.Product2Request;
import com.example.assiment_springboot.Service.ProductService;
import com.example.assiment_springboot.response.loginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Con/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list_products")
    public String listProducts(Model model,  HttpSession session) {

        loginResponse response = (loginResponse) session.getAttribute("account");
        model.addAttribute("products", productService.listProduct());
        model.addAttribute("account", response);

        return "product/product-list";
    }

    @GetMapping("/create_product_form")
    public String getFormCreatProduct(Model model) {
        Product2Request product = new Product2Request();
        List<Category> category = categoryRepository.findAll();

        model.addAttribute("products", product);
        model.addAttribute("category", category);

        return "product/form_product_creat";
    }

    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute("products") Product2Request product) {

        productService.addProduct(product);
        return "redirect:list_products";
    }

    @GetMapping("/product_detail/{id}")
    public String getProductDetail(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("product", productService.detailProduct(id));
        return "product/product_detail";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/Con/v1/list_products";
    }

    @GetMapping("/update_form/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        List<Category> categories = categoryRepository.findAll();
        Product product = productService.detailProduct(id);

        Product2Request product2Request = new Product2Request();
        product2Request.setId((product.getProductId()));
        product2Request.setProductName(product.getProductName());
        product2Request.setPrice(product.getPrice());
        product2Request.setQuantity(product.getQuantity());
        product2Request.setDescription(product.getDescription());
//        product2Request.setImage(product.getImage());
        product2Request.setCategoryId(product.getCategory().getCategoryId());

        model.addAttribute("products", product2Request);
        model.addAttribute("categories", categories);
        return "product/form_update_product";
    }


    @PostMapping("/update")
    public String updateProject(@ModelAttribute("products") Product2Request product) {
        productService.updateProduct(product.getId(), product);
        return "redirect:list_products";
    }
}
