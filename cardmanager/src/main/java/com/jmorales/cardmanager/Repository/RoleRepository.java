package com.jmorales.cardmanager.Repository;


import com.jmorales.cardmanager.Models.ERole;
import com.jmorales.cardmanager.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
