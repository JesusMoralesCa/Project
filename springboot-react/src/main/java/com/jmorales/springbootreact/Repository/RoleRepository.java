package com.jmorales.springbootreact.Repository;

import com.jmorales.springbootreact.Model.ERole;
import com.jmorales.springbootreact.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
