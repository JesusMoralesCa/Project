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

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @GetMapping("/all-roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.getRoles(), FOUND);
    }


}
