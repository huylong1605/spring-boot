package com.example.assiment_springboot.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @Column(name ="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int categoryId;
    @Column(name = "category_name")
     private String categoryName;
  @Column(name = "logo")
  private String logo;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;
}
