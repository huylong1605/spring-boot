package com.example.assiment_springboot.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private int categoryIdRequest;
    private String categoryNameRequest;
    private String Logo;


}
