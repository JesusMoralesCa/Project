package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.User;
import com.jmorales.springbootreact.Payload.Request.SignupRequest;

import java.util.List;

public interface IUserService {


    void registerUser(SignupRequest signupRequest);

    List<User> getUsers();

    void deleteUser(String email);

    User getUser(String email);



}
