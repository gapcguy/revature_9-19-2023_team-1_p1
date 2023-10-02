package com.revature.p1.banking.Controller;


import com.revature.p1.banking.*;
import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController          // Indicates that this class is a REST controller.
@RequestMapping("/auth") // All requests to "/auth" will be handled by methods in this class.
@CrossOrigin()           // Allows handling HTTP requests from different origins.
public class AuthController {

    //we need to AUTOWIRE the DAO, to inject it as a dependency of the controller (since we need to use its methods).
    private UserService uServ; // Service Class for user-related operations.
    private UserDAO uDAO; // DAO for user data.
    public static HttpSession ses; // Static HttpSession for storing user sessions.

    /**
     * Constructor for AuthController.
     * @param uServ An instance of UserService automatically injected by Spring.
     * @param uDAO  An instance of UserDAO automatically injected by Spring.
     */
    @Autowired //remember, spring boot will automagically inject an eDAO thanks to this annotation
    public AuthController(UserService uServ, UserDAO uDAO) {
        this.uServ = uServ;
        this.uDAO = uDAO;
    }

    /**
     * Handles HTTP POST requests to "/auth/login for user logins.
     *
     * @param requestBodyUser The user data provided in the request body.
     * @param request         The HttpServletRequest object for handling requests
     * @return ResponseEntity containing the user session/an error message
     */
    @PostMapping("/login")
    public ResponseEntity<? extends Object> loginHandler(
            @RequestBody(required = false) User requestBodyUser,
            HttpServletRequest request) {

        // Attempt to initialize a user session.
        try {
            User loginUser = uServ.login(requestBodyUser);

            // Create a session and store the logged-in user.
            HttpSession session = request.getSession();
            session.setAttribute("currUser",loginUser);
            ses = session;

            // Return a success response.
            return ResponseEntity.ok(loginUser);

        // If the user session cannot be initialized:
        } catch (Exception e) {
            // Return an HTTP 401 status (Unauthorized user).
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * A static method to retrieve the currently logged-in user from the session.
     *
     * @return The User object representing the currently logged-in user.
     */
    public static User getUser(){
        return (User) ses.getAttribute("currUser");
    }

    /**
     * Log out the currently authenticated user.
     * @param request   is an HttpServletRequest object.
     * @param response  is an HttpServletResponse object.
     */
    @PostMapping("/logout")   // Marks the following method as a handler for HTTP post requests to the "/logout" url.
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // Creates an instance of the `SecurityContextLogoutHandler` class provided by Spring Security.
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        // Sets a configuration property of the logoutHandler object. Configures the handler to invalidate the user's
        // HTTP session during the logout process. Setting it to true means that the user's session will be terminated.
        logoutHandler.setInvalidateHttpSession(true);
        // Calls the `logout` method of the `logoutHandler` object, initiating the process. It takes 3 parameters:
        // 1) The incoming HTTP request.
        // 2) The outgoing HTTP response.
        // 3) The authenticaion information for the currently authenticated user from the security context.
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
    }
}
