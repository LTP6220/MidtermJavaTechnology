package com.example.midterm.service;

import com.example.midterm.model.User;

public interface UserService {
    public void register(User user);

    public boolean checkIfUserExist(String email);

    //function to login
    User login(User user);
}
