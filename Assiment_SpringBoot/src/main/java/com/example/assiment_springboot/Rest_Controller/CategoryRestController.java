package com.example.assiment_springboot.Rest_Controller;

import com.example.assiment_springboot.Model.Category;
import com.example.assiment_springboot.Request.CategoryRequest;
import com.example.assiment_springboot.Request.CategoryRequestName;
import com.example.assiment_springboot.Service.Implement.CategoryService;
import com.example.assiment_springboot.response.CategoryResponeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category_list")
    public List<CategoryRequest> getListCategory(){
           return categoryService.getAllCategory();
    }

    @GetMapping("/category_detail/{id}")
    public CategoryRequest getDetailCategory(@PathVariable("id") int id){
        return categoryService.getById(id);
    }

    @PostMapping("/create_category")
    public Category createCategory(@RequestBody CategoryRequest categoryRequest){
          return  categoryService.createCategory(categoryRequest);
    }

    @PutMapping("/update_category/{id}")
    public Category updateCategory(@PathVariable("id") int id,@RequestBody CategoryRequest categoryRequest ){
        return categoryService.updateCategory(id, categoryRequest);
    }

    @PostMapping("/search_category_name")
    public List<CategoryResponeName> getListCategoryByName(@RequestBody CategoryRequestName requestName){
       return categoryService.getCategoryByName(requestName);
    }
}
