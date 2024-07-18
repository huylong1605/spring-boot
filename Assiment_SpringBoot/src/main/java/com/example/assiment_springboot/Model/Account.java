package com.example.assiment_springboot.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")

    private String userName;

    @Column(name = "password")
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "image")
    private String image;
    @Column(name = "role", columnDefinition = "VARCHAR(255) default 'user'")
    private String role;

}
