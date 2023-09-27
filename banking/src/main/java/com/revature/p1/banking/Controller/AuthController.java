package com.revature.p1.banking.Controller;


import com.revature.p1.banking.*;
import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //a subset of the @Controller stereotype annotation. makes a class a bean, plus MVC stuff!
@RequestMapping("/auth") //every request to 5000/p1/employee will go to this Class
@CrossOrigin() //Configuring this annotation allows us to take in HTTP requests from different origins (FE?)
public class AuthController {

    //we need to AUTOWIRE the DAO, to inject it as a dependency of the controller
    //(since we need to use its methods)
    private UserService uServ;
    private UserDAO uDAO;

    /**
     * @param uServ
     * @param uDAO
     */
    @Autowired //remember, spring boot will automagically inject an eDAO thanks to this annotation
    public AuthController(UserService uServ, UserDAO uDAO) {
        this.uServ = uServ;
        this.uDAO = uDAO;
    }

    @PostMapping("/login")
    public ResponseEntity<? extends Object> loginHandler(
            @RequestBody(required = false) User requestBodyUser,
            HttpServletRequest request) {
        try {

            User loginUser = uServ.login(requestBodyUser);
            HttpSession session = request.getSession();
            session.setAttribute("id", loginUser.getUserId());
            session.setAttribute("username", loginUser.getUsername());
            session.setAttribute("password", loginUser.getPassword());
            session.setAttribute("role", loginUser.getRole());

            return ResponseEntity.ok(loginUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
