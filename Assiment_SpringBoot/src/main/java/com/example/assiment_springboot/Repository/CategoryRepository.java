package com.example.assiment_springboot.Repository;

import com.example.assiment_springboot.Model.Category;
import com.example.assiment_springboot.response.CategoryResponeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select new com.example.assiment_springboot.response.CategoryResponeName(c.categoryId, c.categoryName, c.logo)  from  Category c where 1 = 1 and (:search is null or c.categoryName like %:search%)")
    List<CategoryResponeName> searchCategory(String search);

}
