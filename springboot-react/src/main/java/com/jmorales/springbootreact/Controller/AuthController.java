package com.jmorales.springbootreact.Controller;


import com.jmorales.springbootreact.Exception.UserAlreadyExistsException;
import com.jmorales.springbootreact.Payload.Request.LoginRequest;
import com.jmorales.springbootreact.Payload.Request.SignupRequest;
import com.jmorales.springbootreact.Payload.Response.JwtResponse;
import com.jmorales.springbootreact.Repository.UserRepository;
import com.jmorales.springbootreact.Security.Service.UserDetailsImpl;
import com.jmorales.springbootreact.Security.jwt.JwtUtils;
import com.jmorales.springbootreact.Service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping("register-user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest){

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserAlreadyExistsException("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserAlreadyExistsException("Error: Email is already in use!"));
        }

    userService.registerUser(signUpRequest);

        return ResponseEntity.ok("User registered successfully!");
    }



}
