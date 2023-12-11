package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.UserAlreadyExistsException;
import com.jmorales.springbootreact.Model.Role;
import com.jmorales.springbootreact.Model.User;
import com.jmorales.springbootreact.Repository.RoleRepository;
import com.jmorales.springbootreact.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){         //Comprobamos si existe en la base de datos
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));           //Si no existe, encodeamos su contraseña
        Role userRole = roleRepository.findByName("ROLE_USER").get();       //Le damos el rol de usuario
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);                                       //Y guardamos al usuario creado
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @Transactional              //Esto se hace porque ese usuario tiene relacion con un rol, y para cuando se vaya a borrar esa columna habra error, Transactional se encarga de ese error.
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }
}
