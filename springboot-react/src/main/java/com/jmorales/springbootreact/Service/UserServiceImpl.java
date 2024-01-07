package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.ERole;
import com.jmorales.springbootreact.Model.Role;
import com.jmorales.springbootreact.Model.User;
import com.jmorales.springbootreact.Payload.Request.SignupRequest;
import com.jmorales.springbootreact.Repository.RoleRepository;
import com.jmorales.springbootreact.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository;

    private final IRoleService roleService;

    private final PasswordEncoder encoder;


    @Override
    public void registerUser(SignupRequest signupRequest){

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = roleService.assingRole(strRoles);


        user.setRoles(roles);
        userRepository.save(user);

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

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Error: id not found."));
    }

}
