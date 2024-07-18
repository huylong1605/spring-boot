package com.example.assiment_springboot.Request.AuthenRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestRegister {

    private Long Id;
    private String usernName;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private String role;


}
