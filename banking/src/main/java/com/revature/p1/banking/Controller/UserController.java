package com.revature.p1.banking.Controller;

import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PreAuthorize(value = "hasRole('manager')")
    @GetMapping("")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User u) {
        return userService.insertUser(u);
    }
}
