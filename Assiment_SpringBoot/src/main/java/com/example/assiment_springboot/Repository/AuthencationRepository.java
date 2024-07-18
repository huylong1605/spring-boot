package com.example.assiment_springboot.Repository;

import com.example.assiment_springboot.Model.Account;
import com.example.assiment_springboot.response.loginResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthencationRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a where (a.email = :email and a.password = :password)")
    Account getAccount(String email, String  password);

    @Query("select a.phone from Account a where  a.phone = :phone")
    String checkPhone(String phone);

    @Query("select a.email from Account a where  a.email = :email")
    String checkMail(String email);

}
