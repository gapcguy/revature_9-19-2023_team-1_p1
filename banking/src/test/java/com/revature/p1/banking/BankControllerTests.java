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
                        "\"lastName\": \"test\"}"))
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

  /*  @Test
    void testRegistrationAndDeletion(){
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/users"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"test32322\", " +
                        "\"password\": \"test2\", " +
                        "\"firstName\": \"Jame\"," +
                        "\"lastName\": \"vidyoe\"," +
                        "\"role\": \"M\"}"))
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

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/users/2"))
                .DELETE()
                .build();

        HttpResponse response2 = null;
        try {
            response2 = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
            int status2 = response2.statusCode();
            System.out.println(status2);
            assert(200 == status2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }*/


    //Account controller tests
    @Test
    void testCreateAccount(){
        HttpRequest postRequest2 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/auth/login"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"test\", " +
                        "\"password\": \"test\" }"))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse response2 = httpClient.send(postRequest2, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/p1/accounts"))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            try {
                HttpResponse response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.statusCode());
                assert (201 == response.statusCode());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/accounts"))
                .build();
        try {
            HttpResponse response3 = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response3.statusCode());
            System.out.println(response3.body());

            assert(200 == response3.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testGetAccounts(){
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/auth/login"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"test\", " +
                        "\"password\": \"test\" }"))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse response2 = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/accounts"))
                .build();
        try {
            HttpResponse response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());

            assert(200 == response.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }/*

    @Test
    void testGetSpecificAccount(){

    }
    */
    //Transactions
    @Test
    void testCreateTransaction(){
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/p1/auth/login"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"test\", " +
                        "\"password\": \"test\" }"))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            assert(response.statusCode() == 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*

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

    }*/
}
