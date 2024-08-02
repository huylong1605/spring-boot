package com.example.assiment_springboot.Request;

import com.example.assiment_springboot.Model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product2Request {
   private Long id;

    private String productName;

    private double price;

    private int quantity;

    private String description;

    private MultipartFile image;

    private Integer categoryId;
}
