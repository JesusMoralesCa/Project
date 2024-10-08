package com.jmorales.cardmanager.Controllers;


import com.jmorales.cardmanager.Models.ERole;
import com.jmorales.cardmanager.Models.Role;
import com.jmorales.cardmanager.Models.User;
import com.jmorales.cardmanager.Payload.Request.LoginRequest;
import com.jmorales.cardmanager.Payload.Request.SignupRequest;
import com.jmorales.cardmanager.Payload.Response.JwtResponse;
import com.jmorales.cardmanager.Payload.Response.MessageResponse;
import com.jmorales.cardmanager.Repository.RoleRepository;
import com.jmorales.cardmanager.Repository.UserRepository;
import com.jmorales.cardmanager.Security.Jwt.JwtUtils;
import com.jmorales.cardmanager.Security.Services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

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


    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole;
            try {
                userRole = roleRepository.findByName(ERole.ROLE_USUARIO)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            } catch (RuntimeException e) {
                // If the role does not exist, create it
                userRole = new Role();
                userRole.setName(ERole.ROLE_USUARIO);
                roleRepository.save(userRole);
            }
            roles.add(userRole);
        }  else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "usuario":
                        Role userRole;
                        try {
                            userRole = roleRepository.findByName(ERole.ROLE_USUARIO)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        } catch (RuntimeException e) {
                            // If the role does not exist, create it
                            userRole = new Role();
                            userRole.setName(ERole.ROLE_USUARIO);
                            roleRepository.save(userRole);
                        }
                        roles.add(userRole);

                        break;
                    default:
                        Role userDefRole;
                        try {
                            userDefRole = roleRepository.findByName(ERole.ROLE_USUARIO)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        } catch (RuntimeException e) {
                            // If the role does not exist, create it
                            userDefRole = new Role();
                            userDefRole.setName(ERole.ROLE_USUARIO);
                            roleRepository.save(userDefRole);
                        }
                        roles.add(userDefRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String newPassword){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        }
        return ResponseEntity.ok(new MessageResponse("Has cambiado la contraseña"));
    }
}
