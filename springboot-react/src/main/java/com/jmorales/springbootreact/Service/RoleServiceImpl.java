package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.RoleAlreadyExistException;
import com.jmorales.springbootreact.Exception.UserAlreadyExistsException;
import com.jmorales.springbootreact.Model.Role;
import com.jmorales.springbootreact.Model.User;
import com.jmorales.springbootreact.Repository.RoleRepository;
import com.jmorales.springbootreact.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())){
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new UserAlreadyExistsException(user.get().getFirstName() + " is already assigned to the " + role.get().getName() + " role");
        }
        if (role.isPresent()){
            role.get().assingRoleToUser(user.get());
            roleRepository.save(role.get());
        }

        return user.get();
    }

    @Override
    public Role removeAllUsersFromRole(long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.ifPresent(Role :: removeAllUserFromRole);

        return roleRepository.save(role.get());
    }
}
