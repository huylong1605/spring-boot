package com.example.assiment_springboot.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponeName {
    private int id;
    private String categoryName;
    private String logo;
}
