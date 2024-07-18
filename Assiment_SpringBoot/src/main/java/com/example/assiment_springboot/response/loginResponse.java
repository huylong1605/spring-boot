package com.example.assiment_springboot.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginResponse {

    private Long id;


    private String userName;


    private String password;

    private String email;

    private String phone;


    private String image;

    private String role;
}
