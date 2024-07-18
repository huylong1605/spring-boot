package com.example.assiment_springboot.Service;


import com.example.assiment_springboot.Model.Account;
import com.example.assiment_springboot.Request.AuthenRequest.AccountRequestRegister;
import com.example.assiment_springboot.Request.AuthenRequest.loginRequest;
import com.example.assiment_springboot.response.loginResponse;

public interface AuthencationService {

      Account register(AccountRequestRegister acc);

      loginResponse Login(loginRequest loginRequest);

      boolean checkMail(String email);

      boolean checkPhone(String phone);
}
