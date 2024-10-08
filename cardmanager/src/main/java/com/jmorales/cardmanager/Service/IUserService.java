package com.jmorales.cardmanager.Service;


import com.jmorales.cardmanager.Models.User;

public interface IUserService {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
