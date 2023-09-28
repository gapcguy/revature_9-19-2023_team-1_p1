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
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/users"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"test\", " +
                        "\"password\": \"test\", " +
                        "\"firstName\": \"test\"," +
                        "\"lastName\": \"test\"," +
                        "\"role\": 1}"))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
        System.out.println(status);
        assert(200 == status);
    }

    @Test
    void testRegistration(){
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/users"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"test2\", " +
                        "\"password\": \"test2\", " +
                        "\"firstName\": \"Jame\"," +
                        "\"lastName\": \"vidyoe\"," +
                        "\"role\": 2}"))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            assert(202 == response.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
