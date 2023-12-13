package com.jmorales.springbootreact.Repository;

import com.jmorales.springbootreact.Model.BoosterPack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoosterPackRepository extends JpaRepository<BoosterPack, Long> {

    Optional<BoosterPack> findByName(String name);

}
