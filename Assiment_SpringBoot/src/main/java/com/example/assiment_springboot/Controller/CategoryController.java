package com.example.assiment_springboot.Controller;

import com.example.assiment_springboot.Request.CategoryRequest;
import com.example.assiment_springboot.Request.CategoryRequestName;
import com.example.assiment_springboot.Service.Implement.CategoryService;
import com.example.assiment_springboot.response.loginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Con/v2")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category_list")
    public String getListCategory(Model model, HttpSession session) {
        loginResponse response = (loginResponse) session.getAttribute("account");
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("account", response);
        return "category/category_list";
    }


    @GetMapping("/category_detail/{id}")
    public String getCategoryDetail(Model model, @PathVariable("id") int id) {
        model.addAttribute("category", categoryService.getById(id));
        return "category/category_detail";
    }

    @GetMapping("/create_Category_form")
    public String getCategoryCreateFrom(Model model) {
        CategoryRequest categoryRequest = new CategoryRequest();
        model.addAttribute("category", categoryRequest);
        return "category/create_category_form";
    }

    @PostMapping("/create_category")
    public String createCategory(Model model, @ModelAttribute("category") CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return "redirect:category_list";
    }

    @GetMapping("/edit_Category_form/{id}")
    public String getCategoryEditFrom(Model model, @PathVariable("id") int id) {
        CategoryRequest categoryRequest = categoryService.getById(id);
        model.addAttribute("category", categoryRequest);
        return "category/edit_category_form";
    }

    @GetMapping("/update_category")
    public String updateCategory(@ModelAttribute("category") CategoryRequest categoryRequest) {
        categoryService.updateCategory(categoryRequest.getCategoryIdRequest(), categoryRequest);
        return "redirect:category_list";
    }

    @GetMapping("/search_Category_Name")
    public String searchCategoryByName(Model model, @RequestParam("key") String categoryName) {
        model.addAttribute("category", categoryService.getCategoryByName(new CategoryRequestName()));
        model.addAttribute("key", categoryName);
        return "category/category_list";
    }
}
