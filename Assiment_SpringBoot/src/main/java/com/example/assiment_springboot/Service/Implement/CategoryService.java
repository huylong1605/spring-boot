package com.example.assiment_springboot.Service.Implement;

import com.example.assiment_springboot.Model.Category;
import com.example.assiment_springboot.Repository.CategoryRepository;
import com.example.assiment_springboot.Request.CategoryRequest;
import com.example.assiment_springboot.Request.CategoryRequestName;
import com.example.assiment_springboot.Service.CategorySevice;
import com.example.assiment_springboot.response.CategoryResponeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategorySevice {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryRequest> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryRequest(
                        category.getCategoryId(),
                        category.getCategoryName(),
                        category.getLogo()
                )).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Category createCategory(CategoryRequest request) {

        Category category = new Category();
        category.setCategoryName(request.getCategoryNameRequest());
        category.setLogo(request.getLogo());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public CategoryRequest getById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryIdRequest((category.getCategoryId()));
        categoryRequest.setCategoryNameRequest(category.getCategoryName());
        categoryRequest.setLogo(category.getLogo());
        return categoryRequest;

    }

    @Override
    public List<CategoryResponeName> getCategoryByName(CategoryRequestName requestName) {

        return categoryRepository.searchCategory(requestName.getCategoryName());

    }


    @Override
    @Transactional
    public Category updateCategory(int id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setCategoryName(request.getCategoryNameRequest());
        category.setLogo(request.getLogo());
        categoryRepository.save(category);
        return category;
    }
}
