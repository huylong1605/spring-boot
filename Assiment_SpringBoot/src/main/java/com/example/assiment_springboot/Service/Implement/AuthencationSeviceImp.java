package com.example.assiment_springboot.Service.Implement;

import com.example.assiment_springboot.Model.Account;
import com.example.assiment_springboot.Repository.AuthencationRepository;
import com.example.assiment_springboot.Request.AuthenRequest.AccountRequestRegister;
import com.example.assiment_springboot.Request.AuthenRequest.loginRequest;
import com.example.assiment_springboot.Service.AuthencationService;
import com.example.assiment_springboot.response.loginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthencationSeviceImp implements AuthencationService {

    @Autowired
    private AuthencationRepository authencationRepository;

    @Override
    @Transactional
    public Account register(AccountRequestRegister acc) {
        Account account = new Account();

        account.setUserName(acc.getUsernName());
        account.setPassword(acc.getPassword());
        account.setEmail(acc.getEmail());
        account.setPhone(acc.getPhone());
        account.setRole("user");
        authencationRepository.save(account);

        return account;

    }

    @Override
    public loginResponse Login(loginRequest loginRequest) {
        Account account = authencationRepository.getAccount(loginRequest.getEmail(), loginRequest.getPassword());

        loginResponse response = new loginResponse();

        if(account != null) {
            response.setId(account.getId());
            response.setUserName(account.getUserName());
            response.setPassword(account.getPassword());
            response.setEmail(account.getEmail());
            response.setImage(account.getImage());
            response.setRole(account.getRole());

        }else{
             response = null;
        }
        return response;
    }

    @Override
    public boolean checkMail(String email) {
        String emaill = authencationRepository.checkMail(email);
        if(emaill == null){
            return  false;
        }else{
            return true;
        }
    }

    @Override
    public boolean checkPhone(String phone) {
         String phonee = authencationRepository.checkPhone(phone);
         if(phonee == null){
             return  false;
         }else{
             return true;
         }

    }

}
