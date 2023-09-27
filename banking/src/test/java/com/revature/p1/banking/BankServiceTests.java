package com.revature.p1.banking;

import com.revature.p1.banking.DAO.AccountDAO;
import com.revature.p1.banking.Service.*;
import com.revature.p1.banking.util.ConnectionUtil;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BankServiceTests {

    static UserService userService;
    static AccountService accountService;
    static LoanService loanService;
    static RoleService roleService;
    static TransactionService transactionService;


    @BeforeAll
    static void setup(){
        ConnectionUtil.resetTestDatabase();
        userService = new UserService();
        accountService = new AccountService();
    }

    @AfterAll
    static void close(){

    }

    //User Services
    @Test
    void testAddUser(){

    }

    @Test
    void testDeleteUser(){

    }

    @Test
    void testUpdateUser(){

    }

    @Test
    void testGetUser(){

    }
    @Test
    void testGetAllUsers(){

    }


    //Account Services
    @Test
    void testAddAccount(){

    }


    @Test
    void testDeleteAccount(){

    }

    @Test
    void testUpdateAccount(){

    }

    @Test
    void testGetAccount(){

    }

    @Test
    void testGetAllAccount(){

    }

    @Test
    void testGetAccountsForUser(){

    }


    //Transation
    @Test
    void testAddTransaction(){

    }

    @Test
    void testDeleteTransaction(){

    }

    @Test
    void testUpdateTransaction(){

    }

    @Test
    void testGetTransaction(){

    }

    @Test
    void testGetAllTransaction(){

    }

    @Test
    void testGetTransactionsForAccount(){

    }

    @Test
    void testGetTransactionsForUser(){

    }

}
