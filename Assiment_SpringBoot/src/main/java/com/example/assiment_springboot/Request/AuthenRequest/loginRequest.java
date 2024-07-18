package com.example.assiment_springboot.Request.AuthenRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginRequest {

    private String email;
    private String password;
}
