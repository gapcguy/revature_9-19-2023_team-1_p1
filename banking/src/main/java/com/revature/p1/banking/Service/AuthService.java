package com.revature.p1.banking.Service;

import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Request.UserRequest;

import java.util.Optional;

public interface AuthService {
    public Optional<User> AddUser(UserRequest user);
}
