package com.jmorales.cardmanager.Service;

import com.jmorales.cardmanager.Models.Card;
import com.jmorales.cardmanager.Repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService{

    private final CardRepository cardRepository;

    @Override
    public Card createCard(String name, String image, String rarity, String color, int dp, String boosterPack) {
        Optional<Card> optionalCard = cardRepository.findByName(name);
        if (optionalCard.isEmpty()){
            Card card = new Card();
            card.setName(name);
            card.setImage(image);
            card.setRarity(rarity);
            card.setColor(color);
            card.setDp(dp);
            card.setBoosterPack(boosterPack);

            cardRepository.save(card);
            return card;
        }else{
            throw new IllegalArgumentException("Carta ya existente");
        }
    }

    @Override
    public Card getCard(String name) {
        Optional<Card> optionalBoosterPack = cardRepository.findByName(name);
        if (optionalBoosterPack.isPresent()) {
            Card card = optionalBoosterPack.get();
            return card;
        } else {
            throw new IllegalArgumentException("Pack no existente");
        }
    }

    @Override
    public List<Card> getAllCards() {
        List<Card> listaCards = new ArrayList<>();
        listaCards = cardRepository.findAll();
        return listaCards;
    }

    @Override
    public void deleteCard(String name) {
        Optional<Card> optionalCard = cardRepository.findByName(name);

        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            cardRepository.delete(card);
        } else {
            throw new IllegalArgumentException("Carta no existente");
        }
    }
}
