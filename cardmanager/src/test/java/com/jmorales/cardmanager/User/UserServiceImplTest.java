package com.jmorales.cardmanager.User;

import com.jmorales.cardmanager.Models.User;
import com.jmorales.cardmanager.Repository.UserRepository;
import com.jmorales.cardmanager.Service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserByEmailReturnsUser() {
        User user = new User("username", "user@user.com", "123");

        // Simula el comportamiento del repositorio
        Mockito.when(userRepository.findByEmail("user@user.com"))
                .thenReturn(Optional.of(user));

        // Llama al método del servicio
        User result = userService.getUserByEmail("user@user.com");

        // Verifica los resultados
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getUsername(), result.getUsername());
        Assertions.assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    void getUserByNonExistentEmailThrowsException() {
        // Simula que no se encuentra el usuario
        Mockito.when(userRepository.findByEmail("no@existe.com"))
                .thenReturn(Optional.empty());

        // Verifica que se lanza la excepción
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByEmail("no@existe.com");
        });
    }

    @Test
    void getUserByUsernameReturnsUser() {
        User user = new User("username", "user@user.com", "123");

        // Simula el comportamiento del repositorio
        Mockito.when(userRepository.findByUsername("username"))
                .thenReturn(Optional.of(user));

        // Llama al método del servicio
        User result = userService.getUserByUsername("username");

        // Verifica los resultados
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getUsername(), result.getUsername());
        Assertions.assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    void getUserByNonExistentUsernameThrowsException() {
        // Simula que no se encuentra el usuario
        Mockito.when(userRepository.findByUsername("noexiste"))
                .thenReturn(Optional.empty());

        // Verifica que se lanza la excepción
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByUsername("noexiste");
        });
    }
}

