package com.example.assiment_springboot.Service;

import com.example.assiment_springboot.Model.Category;
import com.example.assiment_springboot.Request.CategoryRequest;
import com.example.assiment_springboot.Request.CategoryRequestName;
import com.example.assiment_springboot.response.CategoryResponeName;

import java.util.List;

public interface CategorySevice {

    List<CategoryRequest> getAllCategory();

    Category createCategory(CategoryRequest request);

    CategoryRequest getById(int id);


    List<CategoryResponeName> getCategoryByName(CategoryRequestName request);
    Category updateCategory(int id, CategoryRequest request);
}
