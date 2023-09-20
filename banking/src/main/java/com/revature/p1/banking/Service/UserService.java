package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;  }

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User save(User c){
        return userDAO.save(c);
    }

    public User getReferenceById(int id){
        return userDAO.getReferenceById(id);
    }

    public User saveAndFlush(User c) {
        return userDAO.saveAndFlush(c);
    }
}
