package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private UserDAO uDAO;

    public AccountService() {}
    @Autowired
    public AccountService(UserDAO uDAO) {
        this.uDAO = uDAO;
    }


}
