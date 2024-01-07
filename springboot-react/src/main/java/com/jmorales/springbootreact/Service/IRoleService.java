package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.Role;
import com.jmorales.springbootreact.Model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IRoleService {
    List<Role> getRoles();


    Set<Role> assingRole(Set<String> strRoles);
}
