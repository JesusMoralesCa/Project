package com.jmorales.springbootreact.Controller;

import com.jmorales.springbootreact.Exception.UserAlreadyExistsException;
import com.jmorales.springbootreact.Model.User;
import com.jmorales.springbootreact.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<?> resgisterUser(User user){
        try {
           userService.registerUser(user);
           return ResponseEntity.ok("Resgistration successfull");
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
