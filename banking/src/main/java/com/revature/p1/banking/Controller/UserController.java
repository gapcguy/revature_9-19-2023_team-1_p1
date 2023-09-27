package com.revature.p1.banking.Controller;

import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.TransactionService;
import com.revature.p1.banking.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService uServ;

    @Autowired
    public UserController(UserService uServ) { this.uServ = uServ; }

    @PreAuthorize(value = "hasRole('manager')")
    @GetMapping("")
    public List<User> getUsers() {
        return uServ.findAll();
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User u) {
        return uServ.insertUser(u);
    }
}
