package com.jmorales.cardmanager.Repository;

import com.jmorales.cardmanager.Models.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CardRepository extends MongoRepository<Card, String> {

    Optional<Card> findByName(String name);
    Optional<Card> findByRareza(String rareza);
    Optional<Card> findByColor(String color);
    Optional<Card> findByDp(int dp);
    Optional<Card> findByBoosterPack(String boosterPack);
}
