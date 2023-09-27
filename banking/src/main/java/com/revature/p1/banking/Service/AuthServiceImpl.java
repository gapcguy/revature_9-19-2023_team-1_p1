package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserDAO userDao;

    public Optional<User> AddUser(UserRequest user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(Role.valueOf(user.getRole()));

        return Optional.of(userDao.save(newUser));
    }
}
