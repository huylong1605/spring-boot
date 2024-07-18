package com.example.assiment_springboot.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductRequest {
    private long productIdRequest;

    private String productNameRequest;
    private double priceRequest;
    private int quantityRequest;
    private String descriptionRequest;

    private String imageRequest;

    private String categoryName;



}
