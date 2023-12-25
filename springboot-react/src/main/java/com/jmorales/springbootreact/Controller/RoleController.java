package com.jmorales.springbootreact.Controller;

import com.jmorales.springbootreact.Exception.RoleAlreadyExistException;
import com.jmorales.springbootreact.Model.Role;
import com.jmorales.springbootreact.Model.User;
import com.jmorales.springbootreact.Service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @GetMapping("/all-roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.getRoles(), FOUND);
    }


    @PostMapping("/remove-all-users-from-role/{roleId}")
    public Role removeAllUsersFrolRole(@PathVariable("roleId") Long roleId){
        return roleService.removeAllUsersFromRole(roleId);
    }

    @PostMapping("/remove-user-from-role")
    public User removeUserFromRole(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId){
        return roleService.removeUserFromRole(userId,roleId);
    }

    @PostMapping("/assign-user-to-role")
    public User assignUserToRole(@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId){
       return roleService.assignRoleToUser(userId,roleId);
    }



}
