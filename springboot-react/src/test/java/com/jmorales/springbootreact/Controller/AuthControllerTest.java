package com.jmorales.springbootreact.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmorales.springbootreact.Payload.Request.LoginRequest;
import com.jmorales.springbootreact.Payload.Request.SignupRequest;
import com.jmorales.springbootreact.Repository.UserRepository;
import com.jmorales.springbootreact.Security.jwt.JwtUtils;
import com.jmorales.springbootreact.Service.IUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc

class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private UserRepository userRepository;
    @Mock
    private IUserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testAuthenticateUser() throws Exception  {
        // ...
        String email = "admin@admin.com";
        String password = "123456789";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

// Convertir el objeto a JSON para la solicitud
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(loginRequest);

// Act
        MvcResult result = mockMvc.perform(post("/auth/login")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
// ...

    }

    @Test
    public void registerUser() throws Exception {

        String username = "testuser";
        String email = "test@example.com";
        String password = "password";

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername(username);
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);

        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Simular el comportamiento del userService
        doNothing().when(userService).registerUser(any(SignupRequest.class));

        // Act
        MvcResult result = mockMvc.perform(post("/auth/register-user")
                        .content(new ObjectMapper().writeValueAsString(signupRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


    }
}