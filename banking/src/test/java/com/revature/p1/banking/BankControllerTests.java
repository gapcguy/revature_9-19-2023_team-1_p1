package com.revature.p1.banking;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BankControllerTests {
    static HttpClient httpClient;
    @BeforeAll
    static void setup(){
        SpringApplication.run(BankingApplication.class);
        httpClient = HttpClient.newHttpClient();
    }

    @AfterAll
    static void close(){

    }

    //Test Roles
    /*
    @Test
    void testCreateRole(){

    }

    @Test
    void testUpdateRole(){

    }

    @Test
    void testGetRole(){

    }
    */

    //User controller tests
    @Test
    void testLogin() throws IOException, InterruptedException {
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/auth/login"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"test\", " +
                        "\"password\": \"test\" }"))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        assert(302 == status);
    }

    @Test
    void testRegistration(){

    }

    @Test
    void testDeleteUser(){

    }

    //Account controller tests
    @Test
    void testCreateAccount(){

    }

    @Test
    void testDeleteAccount(){

    }

    @Test
    void testGetAccounts(){

    }

    @Test
    void testGetSpecificAccount(){

    }

    //Transactions
    @Test
    void testCreateTransaction(){

    }

    @Test
    void testGetTransactions(){

    }

    @Test
    void testGetTransactionsForAccount(){

    }

    @Test
    void testGetTransactionsForUser(){

    }

    //Loans
    @Test
    void testCreateLoan(){

    }

    @Test
    void testUpdateLoan(){

    }

    @Test
    void testPayLoan(){

    }

    @Test
    void testGetLoan(){

    }
}
