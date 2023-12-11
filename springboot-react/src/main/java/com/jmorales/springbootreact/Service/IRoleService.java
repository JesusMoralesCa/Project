package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.Role;
import com.jmorales.springbootreact.Model.User;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();

    Role createRole(Role theRole);

    void deleteRole(Long id);

    Role findRoleByName(String name);

    User removeUserFromRole(Long userId, Long roleId);

    User assignRoleToUser(Long userId, Long roleId);

    Role removeAllUsersFromRole(long roleId);
}
