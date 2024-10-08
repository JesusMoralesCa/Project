package com.jmorales.cardmanager.Repository;

import com.jmorales.cardmanager.Models.BoosterPack;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BoosterPackRepository extends MongoRepository<BoosterPack, String> {
    Optional<BoosterPack> findByName(String name);
}
